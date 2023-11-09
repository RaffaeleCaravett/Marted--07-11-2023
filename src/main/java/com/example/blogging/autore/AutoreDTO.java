package com.example.blogging.autore;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public record AutoreDTO (
    @NotEmpty(message = "Il nome è un campo obbligatorio!")
    @Size(min = 3, max=30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
    String nome,
    @NotEmpty(message = "Il cognome è un campo obbligatorio!")
    String cognome,
    @NotEmpty(message = "L'email è un campo obbligatorio!")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")
    String email,
    @NotNull(message = "Il nome è un campo obbligatorio!")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate dataDiNascita
        ) {}
