package com.daersh.daersh_project.exception;


public class InvalidUserException extends RuntimeException{

    public InvalidUserException(String message){
        super(message);
    }
}
