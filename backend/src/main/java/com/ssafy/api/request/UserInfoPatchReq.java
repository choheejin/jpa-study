package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserInfoPatchReq")
public class UserInfoPatchReq {
    @ApiModelProperty(name="유저 부서", example = "SSAFY1")
    String department;
    @ApiModelProperty(name="유저 이름", example = "김싸피1")
    String name;
    @ApiModelProperty(name="유저 직책", example="교육생")
    String position;
}
