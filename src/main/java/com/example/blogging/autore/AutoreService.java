package com.example.blogging.autore;

import com.example.blogging.exceptions.BadRequestException;
import com.example.blogging.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.ListIterator;

@Service
public class AutoreService {
 @Autowired
 private AutoreRepository autoreRepository;

    public Autore save(Autore body){
        System.out.println(body.getNome());
       autoreRepository.findByEmail(body.getEmail()).ifPresent( user -> {
            throw new BadRequestException("L'email " + body.getEmail() + " è già in uso!");
        });

        body.setAvatar("http://ui-avatars.com/api/?name="+body.getNome() + "+" + body.getCognome());
        return autoreRepository.save(body);
    }

    public Page<Autore> getAutori(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return autoreRepository.findAll(pageable);
    }

    public Autore findById(long id)throws NotFoundException{
        return autoreRepository.findById(id).orElseThrow( ()  -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id)throws NotFoundException{
        Autore found = this.findById(id);
        autoreRepository.delete(found);
    }

    public Autore findByIdAndUpdate(int id, Autore body) throws NotFoundException{
        Autore found = this.findById(id);
        found.setCognome(body.getCognome());
        found.setNome(body.getNome());
        found.setEmail(body.getEmail());
        found.setDataDiNascita(body.getDataDiNascita());
        return autoreRepository.save(found);
    }
    public Autore getRandomAuthor() throws NotFoundException{
        return autoreRepository.findRandomAutore();
    }

}