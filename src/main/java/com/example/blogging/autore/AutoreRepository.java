package com.example.blogging.autore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutoreRepository extends JpaRepository<Autore, Long> {
    Optional<Autore> findByEmail(String ics);
    @Query(value = "SELECT * FROM autori ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Autore findRandomAutore();
}