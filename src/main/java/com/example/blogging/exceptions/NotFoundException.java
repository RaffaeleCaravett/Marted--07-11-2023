package com.example.blogging.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(long id){

        super("Record con id : " + id + " non trovato.");
    }
}