package com.iths.labh2;

import com.iths.labh2.Hero.Hero;
import com.iths.labh2.repository.MainCharRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class Labh2Application {

    public static void main(String[] args) {
        SpringApplication.run(Labh2Application.class, args);
    }


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx, MainCharRepository mcRepository) {
        return args -> {

            mcRepository.save(new Hero("Tony Stark","Ironman"));
            mcRepository.save(new Hero("Batman","Batman: Begins"));
            mcRepository.save(new Hero("Peter Parker","Spiderman"));

            System.out.println("Index, web service is running");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
        };
    }

}
