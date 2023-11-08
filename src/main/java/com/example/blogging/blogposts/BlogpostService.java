package com.example.blogging.blogposts;


import com.example.blogging.autore.AutoreRepository;
import com.example.blogging.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
@Service
public class BlogpostService {
    @Autowired
    private BlogPostRepository blogpostRepository;
  @Autowired
  private AutoreRepository autoreRepository;
    public BlogPost save(BlogPost body){
        body.setCover("https://picsum.photos/200/300");
        return blogpostRepository.save(body);
    }

    public Page<BlogPost> getBlogPosts(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return blogpostRepository.findAll(pageable);
    }

    public BlogPost findById(long id)throws NotFoundException{
        return blogpostRepository.findById(id).orElseThrow( ()  -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id)throws NotFoundException{
        BlogPost found = this.findById(id);
        blogpostRepository.delete(found);
    }

    public BlogPost findByIdAndUpdate(int id, BlogPost body) throws NotFoundException{
        BlogPost found = this.findById(id);
        found.setCategoria(body.getCategoria());
        found.setTitolo(body.getTitolo());
        found.setContenuto(body.getContenuto());
        found.setTempoDiLettura(body.getTempoDiLettura());
        return blogpostRepository.save(found);
    }
}