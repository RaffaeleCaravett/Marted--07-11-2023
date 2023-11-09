package com.example.blogging.autore;

import com.example.blogging.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/autori")
public class AutoreController {
    @Autowired
    private AutoreService autoreService;

    @GetMapping("")
    public Page<Autore> getAutori(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String orderBy){
        return autoreService.getAutori(page, size, orderBy);
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public long saveAutore(@RequestBody @Validated AutoreDTO body, BindingResult validation) throws IOException {
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return autoreService.save(body).getId();
            } catch (IOException | MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping("/{id}")
    public Autore findById(@PathVariable long id){
        return autoreService.findById(id);
    }

    @PutMapping("/{id}")
    public Autore findByIdAndUpdate(@PathVariable long id, @RequestBody Autore body){
        return autoreService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id){
        autoreService.findByIdAndDelete(id);
    }


    @PostMapping("/upload/{id}")
    public String uploadExample(@PathVariable long id,@RequestParam("avatar") MultipartFile body) throws IOException {
        System.out.println(body.getSize());
        System.out.println(body.getContentType());
        return autoreService.uploadPicture(id,body);
    }
}