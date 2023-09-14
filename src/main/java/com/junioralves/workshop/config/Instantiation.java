package com.junioralves.workshop.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.junioralves.workshop.domain.Post;
import com.junioralves.workshop.domain.User;
import com.junioralves.workshop.dto.AuthorDTO;
import com.junioralves.workshop.dto.CommentDTO;
import com.junioralves.workshop.repository.PostRepository;
import com.junioralves.workshop.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository; // Repositório

    @Autowired
    private PostRepository postRepository; // Repositório

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        
        userRepository.deleteAll(); // Vai limpar a coleção de dados no MongoDB
        postRepository.deleteAll();
        
        User junior = new User(null, "Junior Alves ", "junior@gmail.com");
        User rui = new User(null, "Rui Alves", "rui@gmail.com");
        User heitor = new User(null, "Heitor Alves", "heitor@gmail.com");

        userRepository.saveAll(Arrays.asList(junior, rui, heitor)); // Salvando no banco de dados
        
        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem", "Vou viajar para SP. Abraços!", new AuthorDTO(junior));
        Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom Dia", "Acordei feliz hoje", new AuthorDTO(junior));
       
        CommentDTO c1 = new CommentDTO("Boa viagme mano",sdf.parse("21/03/2018"), new AuthorDTO(rui));
        CommentDTO c2 = new CommentDTO("Aproveite",sdf.parse("22/03/2018"),new AuthorDTO(heitor));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia",sdf.parse("23/03/2018"), new AuthorDTO(rui));

        post1.getComments().addAll(Arrays.asList(c1,c2));
        post2.getComments().addAll(Arrays.asList(c3));
        
        postRepository.saveAll(Arrays.asList(post1, post2	));
    
        junior.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(junior);
    }
}
