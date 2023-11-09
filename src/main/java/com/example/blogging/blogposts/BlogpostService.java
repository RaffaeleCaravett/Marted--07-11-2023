package com.example.blogging.blogposts;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.blogging.autore.Autore;
import com.example.blogging.autore.AutoreRepository;
import com.example.blogging.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class BlogpostService {
    @Autowired
    private BlogPostRepository blogpostRepository;
    @Autowired
    private Cloudinary cloudinary;
  @Autowired
  private AutoreRepository autoreRepository;
    public BlogPost save(BlogpostPayload body){
        Autore autore = autoreRepository.findById(body.autore_id()).get();
        BlogPost blogPost = new BlogPost(body.categoria(), body.titolo(),body.cover(),body.contenuto(),body.tempoDiLettura(),autore);
        return blogpostRepository.save(blogPost);
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
    public String uploadPicture(long id,MultipartFile file) throws IOException {
        try {
            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("url");

            BlogPost blogPost = blogpostRepository.findById(id).orElse(null);
            if (blogPost != null) {
                blogPost.setCover(imageUrl);
                blogpostRepository.save(blogPost);
            }

            return imageUrl;
        } catch (IOException e) {
            throw new RuntimeException("Impossibile caricare l'immagine", e);
        }
    }
}