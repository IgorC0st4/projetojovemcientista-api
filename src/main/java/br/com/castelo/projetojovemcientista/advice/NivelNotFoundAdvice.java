package br.com.castelo.projetojovemcientista.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.castelo.projetojovemcientista.exception.NivelNotFoundException;

@ControllerAdvice
public class NivelNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(NivelNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String nivelNotFoundHandler(NivelNotFoundException ex) {
		return ex.getMessage();
	}
}