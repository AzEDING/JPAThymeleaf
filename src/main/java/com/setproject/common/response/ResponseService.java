package com.setproject.common.response;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResponseService {
	private final MessageSource messageSource;
	
	public <T> SingleResult<T> getSingleResult(T data) {
		SingleResult<T> result = new SingleResult<>();
		result.setContent(data);
		result.setCode(200);
		result.setMessage("sucess");
		return result;
	}
	
	public CommonResult successResult() {
		CommonResult commonResult = new CommonResult();
		commonResult.setCode(200);
		commonResult.setMessage("success");
		return commonResult;
	}
	
	public CommonResult failResult(int code, String message) {
		CommonResult commonResult = new CommonResult();
		commonResult.setCode(code);
		commonResult.setMessage(message);
		return commonResult;
	}
	
	public String getMessage(String code) {
		return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
	}

}
