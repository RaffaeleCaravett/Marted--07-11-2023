package com.example.blogging.blogposts;

import com.example.blogging.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public record BlogpostPayload(

        @NotEmpty(message = "La categoria è un campo obbligatorio! Deve essere 'JAVA','SPRING',o 'JAVASCRIPT'")
         Categoria categoria,
        @NotEmpty(message = "Il titolo è un campo obbligatorio!")
         String titolo,
        @NotEmpty(message = "La cover è un campo obbligatorio!")
        String cover,
        @NotEmpty(message = "Il contenuto è un campo obbligatorio!")
        @Size(min = 10, message = "Il contenuto deve essere almeno di 10 caratteri.")
       String contenuto,
        @NotEmpty(message = "Il tempo di lettura è un campo obbligatorio!")
        int tempoDiLettura,
        @NotEmpty(message = "L'id autore è un campo obbligatorio!")
       long autore_id
){
}
