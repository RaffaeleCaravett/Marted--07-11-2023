package com.example.blogging.autore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public long saveAutore(@RequestBody Autore body){
        return autoreService.save(body).getId();
    }

    @GetMapping("/{id}")
    public Autore findById(@PathVariable int id){
        return autoreService.findById(id);
    }

    @PutMapping("/{id}")
    public Autore findByIdAndUpdate(@PathVariable int id, @RequestBody Autore body){
        return autoreService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int id){
        autoreService.findByIdAndDelete(id);
    }
}