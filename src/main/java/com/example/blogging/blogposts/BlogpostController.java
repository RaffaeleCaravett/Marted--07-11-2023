package com.example.blogging.blogposts;

import com.example.blogging.autore.AutoreRepository;
import com.example.blogging.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public long saveBlogpost(@RequestBody BlogpostDto body){
        if(!Objects.equals(body.getContenuto(), "")){
            BlogPost blogpost = new BlogPost(body.getCategoria(), body.getTitolo(), body.getContenuto(), body.getTempoDiLettura(),autoreRepository.findById(body.getAutore_id()).get());
            return blogpostService.save(blogpost).getId();
        }else{
            throw new BadRequestException("Il contenuto del blogpost è vuoto");
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
}
