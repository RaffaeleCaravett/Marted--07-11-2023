package com.example.blogging.blogposts;


import com.example.blogging.autore.Autore;
import com.example.blogging.enums.Categoria;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "blogposts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "BlogpostBuilder")
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
    @Enumerated(EnumType.STRING)
private Categoria categoria;
private String titolo;
private String cover;
private String contenuto;
private int tempoDiLettura;
    @JsonBackReference
    @ManyToOne
    private Autore autore;

    public BlogPost(Categoria categoria, String titolo, String contenuto, int tempoDiLettura,Autore autore) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.autore=autore;
    }

    public BlogPost(Categoria categoria, String titolo, String cover, String contenuto, int tempoDiLettura, Autore autore) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.cover = cover;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.autore = autore;
    }
}
