package com.ssafy.common.model.response;

import com.ssafy.common.exception.ErrorCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 서버 요청에대한 기본 에러(바디) 정의.
 */
@Getter
@Setter
@ApiModel("ErrorResponseBody")
public class ErrorResponseBody {
	@ApiModelProperty(name="응답 메시지", example = "에러")
	String message = null;
	@ApiModelProperty(name="응답 코드", example = "400")
	Integer statusCode = null;

	public ErrorResponseBody(final ErrorCode code) {
		this.statusCode = code.getStatus();
		this.message = code.getMessage();
	}

	public static ErrorResponseBody of(final ErrorCode code) {
		ErrorResponseBody body = new ErrorResponseBody(code);
		return body;
	}
}
