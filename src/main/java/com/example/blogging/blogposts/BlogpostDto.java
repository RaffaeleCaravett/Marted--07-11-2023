package com.example.blogging.blogposts;

import com.example.blogging.autore.Autore;
import com.example.blogging.enums.Categoria;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogpostDto {

        @Enumerated(EnumType.STRING)
        private Categoria categoria;
        private String titolo;
        private String cover;
        private String contenuto;
        private int tempoDiLettura;
        private long autore_id;

}
