package com.example.blogging.exceptions;

import java.util.Date;

public record ErrorResponseDTO(String message, Date timestamp) {
}
