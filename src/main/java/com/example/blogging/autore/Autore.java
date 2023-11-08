package com.example.blogging.autore;

import com.example.blogging.blogposts.BlogPost;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.javafaker.Faker;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "autori")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "AutoreBuilder")
public class Autore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataDiNascita;
    private String avatar;
    @JsonManagedReference
    @OneToMany(cascade=CascadeType.ALL,mappedBy = "autore", fetch = FetchType.EAGER)
    private List<BlogPost> blogPostList;

    public static class AutoreBuilder{
        private Faker faker = new Faker(Locale.ITALY);
        private String nome=faker.name().firstName();
        private String cognome=faker.name().lastName();
        private String email=faker.internet().emailAddress();
        private LocalDate dataDiNascita=faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setBlogPostList(List<BlogPost> blogPostList) {
        this.blogPostList = blogPostList;
    }

    @Override
    public String toString() {
        return "Autore{" +
                "id=" + id +
                '}';
    }
}
