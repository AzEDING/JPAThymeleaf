package com.setproject.common.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonResult {
	private int code;
	private String message;
	private String detail;
	
	public CommonResult(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public CommonResult(int code, String message, String detail) {
		this.code = code;
		this.message = message;
		this.detail = detail;
	}
}
