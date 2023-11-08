package com.example.blogging.runner;

import com.example.blogging.autore.Autore;
import com.example.blogging.autore.AutoreService;
import com.example.blogging.blogposts.BlogPost;
import com.example.blogging.blogposts.BlogpostService;
import com.example.blogging.enums.Categoria;
import com.example.blogging.exceptions.ExceptionsHandler;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Slf4j
public class ApplicationRunner implements CommandLineRunner {

    @Autowired
    AutoreService autoreService;
    @Autowired
    BlogpostService blogpostService;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.ITALY);
       /* for(int i =0;i<=1000;i++){

            try{
                if(i%2==0){
                    BlogPost genericBlogpost =
                            new BlogPost(Categoria.JAVA,faker.name().title(),faker.shakespeare().hamletQuote(),faker.number().numberBetween(10,100),autoreService.getRandomAuthor() );
                    blogpostService.save(genericBlogpost);
                }else {
                    BlogPost genericBlogpost =
                            new BlogPost(Categoria.JAVASCRIPT,faker.name().title(),faker.shakespeare().hamletQuote(),faker.number().numberBetween(10,100),autoreService.getRandomAuthor() );
                    blogpostService.save(genericBlogpost);
                }

           Reservation reservation =  new Reservation(
                    LocalDate.now().plusDays(2),
           stationRepository.findRandomStation(),
          userRepository.findRandomUser()
          );*/
               /*List<Reservation> reservationList= reservationDAO.findAll();
                reservationList.forEach(r->{
                    reservationDAO.findByIdAndDelete(r.getId());
                    System.out.println("La prenotazione era scaduta.");
                });

                //  Reservation reservation =  new Reservation(
                //        LocalDate.now().minusDays(1),
                //      stationRepository.findRandomStation(),
                //    userRepository.findRandomUser()
                //   );
                // reservationDAO.save(reservation);
            }catch (Exception e){
                System.err.println(e.getMessage());
            }

        }*/
        }
}
