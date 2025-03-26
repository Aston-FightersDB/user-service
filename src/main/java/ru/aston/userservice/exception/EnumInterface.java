package ru.aston.userservice.exception;

import org.springframework.http.HttpStatus;

public interface EnumInterface {

  String getErrorMessage();

  String getException();

  HttpStatus getHttpStatus();

}
