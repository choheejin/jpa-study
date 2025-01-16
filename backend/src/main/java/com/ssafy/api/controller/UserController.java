package com.ssafy.api.controller;

import com.ssafy.api.request.UserInfoPatchReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.response.UserRes;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.exception.BusinessException;
import com.ssafy.common.exception.ErrorCode;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Slf4j
@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    @ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> register(
            @RequestBody @ApiParam(value = "회원가입 정보", required = true) UserRegisterPostReq registerInfo) {

        //임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
        User user = userService.createUser(registerInfo);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @GetMapping("/me")
    @ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<UserRes> getUserInfo(@ApiIgnore Authentication authentication) {
        /**
         * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
         * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
         */
        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();

        String userId = userDetails.getUsername();
        User user = userService.getUserByUserId(userId).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        log.debug(user.toString());

        return ResponseEntity.status(200).body(UserRes.of(user));
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "회원 아이디 존재 여부", notes = "아이디가 존재하는지 확인한다")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true, dataType = "string", paramType = "path", defaultValue = "ssafy_web"),
    })
    @ApiResponses({
            @ApiResponse(code = 409, message = "이미 존재하는 사용자 ID 입니다")
    })
    public void findUserId(@PathVariable("userId") String userId) {
        log.info("찾는 유저 아이디::: {}", userId);

        userService.getUserByUserId(userId).ifPresent(user -> {
            throw new BusinessException(ErrorCode.USER_FORBIDDEN);
        });

        return;
    }


    @PatchMapping("/{userId}")
    @ApiOperation(value = "유저 정보 수정", notes = "유저 정보를 수정합니다")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true, dataType = "string", paramType = "path", defaultValue = "ssafy_web"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success")
    })
    public ResponseEntity<? extends BaseResponseBody> updateUserInfo(@RequestBody @ApiParam(value = "로그인한 사용자의 정보", required = true) UserInfoPatchReq userInfo,
                                                                     @PathVariable("userId") String userId) {
        log.debug("수정하려는 유저 정보: {}", userInfo);

        User user = userService.patchUserInfo(userInfo, userId);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }
}
