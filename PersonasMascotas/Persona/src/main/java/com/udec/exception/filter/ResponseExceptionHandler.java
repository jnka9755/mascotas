package com.udec.exception.filter;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.udec.exception.ArgumentRequiredException;
import com.udec.exception.ExceptionWrapper;
import com.udec.exception.FoundModelException;
import com.udec.exception.NotFoundModelException;

@RestController
@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundModelException.class)
	public final ResponseEntity<ExceptionWrapper> notFoundModelExceptionHanler(NotFoundModelException ex,
			WebRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;

		ex.printStackTrace();
		ExceptionWrapper error = new ExceptionWrapper(status.value(), status.name(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(error, status);
	}

	@ExceptionHandler(FoundModelException.class)
	public final ResponseEntity<ExceptionWrapper> foundModelExceptionHanler(FoundModelException ex,
			WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		ex.printStackTrace();
		ExceptionWrapper error = new ExceptionWrapper(status.value(), status.name(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(error, status);
	}
	
	@ExceptionHandler(ArgumentRequiredException.class)
	public final ResponseEntity<ExceptionWrapper> argumentRequiredExceptionHandler(ArgumentRequiredException ex,
			WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		ex.printStackTrace();
		ExceptionWrapper error = new ExceptionWrapper(status.value(), status.name(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(error, status);
	}

	@ExceptionHandler(NullPointerException.class)
	public final ResponseEntity<ExceptionWrapper> nullPointerExceptionHandler(NullPointerException ex,
			WebRequest request) {

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ex.printStackTrace();

		ExceptionWrapper error = new ExceptionWrapper(status.value(), status.name(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ExceptionWrapper>(error, status);
	}

	@ExceptionHandler(FileNotFoundException.class)
	public final ResponseEntity<ExceptionWrapper> fileNotFoundExceptionHanler(FileNotFoundException ex,
			WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ex.printStackTrace();
		ExceptionWrapper error = new ExceptionWrapper(status.value(), status.name(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(error, status);
	}

	@ExceptionHandler(IOException.class)
	public final ResponseEntity<ExceptionWrapper> iOExceptionHanler(IOException ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ex.printStackTrace();
		ExceptionWrapper error = new ExceptionWrapper(status.value(), status.name(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ExceptionWrapper>(error, status);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		ExceptionWrapper error = new ExceptionWrapper(status.value(), status.name(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ex.printStackTrace();
		ExceptionWrapper error = new ExceptionWrapper(status.value(), status.name(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ex.printStackTrace();
		ExceptionWrapper error = new ExceptionWrapper(status.value(), status.name(),
				"El objeto JSON está mal construido", request.getDescription(false));
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ex.printStackTrace();
		ExceptionWrapper error = new ExceptionWrapper(status.value(), status.name(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ex.printStackTrace();
		ExceptionWrapper error = new ExceptionWrapper(status.value(), status.name(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<Object>(error, HttpStatus.METHOD_NOT_ALLOWED);
	}

}
