package com.setproject.common.advice;

import com.setproject.common.advice.exception.*;
import com.setproject.common.response.CommonResult;
import com.setproject.common.response.ResponseService;
import com.sun.jdi.request.ExceptionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@RequiredArgsConstructor
@RestControllerAdvice(basePackages = "com.setproject")
public class ControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	private ModelAndView commonException(HttpServletRequest request, Exception exception) {
        CommonResult commonResult = new CommonResult(500, "Internal Server Error");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("result", commonResult);
        modelAndView.setViewName("error");
		return modelAndView;
	}

	@ExceptionHandler(AuthAccessDeniedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	private ModelAndView accessDeniedException(HttpServletRequest request, AuthAccessDeniedException exception) {
		CommonResult commonResult = new CommonResult(401, "Auth Access Denied");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("result", commonResult);
		modelAndView.setViewName("error");
		return modelAndView;
	}

	@ExceptionHandler(ObjectAlreadyExistException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	private ModelAndView objectAlreadyExistException(HttpServletRequest request, ObjectAlreadyExistException exception) {
		CommonResult commonResult = new CommonResult(400, "Object Already Exist");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("result", commonResult);
		modelAndView.setViewName("error");
		return modelAndView;
	}

	@ExceptionHandler(ObjectNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private ModelAndView objectNotFoundException(HttpServletRequest request, ObjectNotFoundException exception) {
		CommonResult commonResult = new CommonResult(404, "Object Not Found");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("result", commonResult);
		modelAndView.setViewName("error");
		return modelAndView;
	}

	@ExceptionHandler(UserAlreadyExistException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ModelAndView userAlreadyExistException(HttpServletRequest request, UserAlreadyExistException exception) {
		CommonResult commonResult = new CommonResult(400, "User Already Exist");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("result", commonResult);
		modelAndView.setViewName("error");
		return modelAndView;
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private ModelAndView userNotFoundException(HttpServletRequest request, UserNotFoundException exception) {
        CommonResult commonResult = new CommonResult(404, "User Not Found");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("result", commonResult);
        modelAndView.setViewName("error");
        return modelAndView;
	}

//	@ExceptionHandler(ConstraintViolationException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	private ModelAndView constraintViolationException(HttpServletRequest request) {
//		CommonResult commonResult = new CommonResult(400, "validation failed");
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("result", commonResult);
//		return modelAndView;
//	}
}
