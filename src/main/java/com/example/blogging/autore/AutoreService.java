package com.example.blogging.autore;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.blogging.config.EmailSender;
import com.example.blogging.exceptions.BadRequestException;
import com.example.blogging.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

@Service
public class AutoreService {
 @Autowired
 private AutoreRepository autoreRepository;


   // @Autowired
    //private EmailSender emailSender;
    @Autowired
    private Cloudinary cloudinary;

    public Autore save(AutoreDTO body) throws IOException, MessagingException {
        System.out.println(body.nome());
       autoreRepository.findByEmail(body.email()).ifPresent( user -> {
            throw new BadRequestException("L'email " + body.email() + " è già in uso!");
        });
       Autore autore= new Autore(body.nome(), body.cognome(), body.email(),body.dataDiNascita());
       // emailSender.sendRegistrationEmail(body.email());
        return autoreRepository.save(autore);
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

    public Autore findByIdAndUpdate(long id, Autore body) throws NotFoundException{
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
    public String uploadPicture(long id,MultipartFile file) throws IOException {
        try {
            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("url");

            Autore autore = autoreRepository.findById(id).orElse(null);
            if (autore != null) {
                autore.setAvatar(imageUrl);
                autoreRepository.save(autore);
            }

            return imageUrl;
        } catch (IOException e) {
            // Handle the exception (e.g., log it or throw a custom exception)
            throw new RuntimeException("Impossibile caricare l'immagine", e);
        }
    }

}