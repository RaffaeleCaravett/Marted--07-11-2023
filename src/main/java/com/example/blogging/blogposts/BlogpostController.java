package com.example.blogging.blogposts;

import com.example.blogging.autore.AutoreRepository;
import com.example.blogging.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/api/blogposts")
public class BlogpostController {
    @Autowired
    private BlogpostService blogpostService;

    @Autowired
    private AutoreRepository autoreRepository;

    @GetMapping("")
    public Page<BlogPost> getBlogposts(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String orderBy){
        return blogpostService.getBlogPosts(page, size, orderBy);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public long saveBlogpost(@RequestBody @Validated BlogpostPayload body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return blogpostService.save(body).getId();
        }
    }

    @GetMapping("/{id}")
    public BlogPost findById(@PathVariable int id){
        return blogpostService.findById(id);
    }

    @PutMapping("/{id}")
    public BlogPost findByIdAndUpdate(@PathVariable int id, @RequestBody BlogPost body){
        return blogpostService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int id){
        blogpostService.findByIdAndDelete(id);
    }

    @PostMapping("/upload/{id}")
    public String uploadExample(@PathVariable long id,@RequestParam("cover") MultipartFile body) throws IOException {
        System.out.println(body.getSize());
        System.out.println(body.getContentType());
        return blogpostService.uploadPicture(id,body);
    }
}
