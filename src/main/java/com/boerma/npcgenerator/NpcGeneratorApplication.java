package com.boerma.npcgenerator;

import com.boerma.npcgenerator.cli.NpcGeneratorCli;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NpcGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(NpcGeneratorApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ApplicationContext context) {
        return args -> {
            NpcGeneratorCli cli = context.getBean(NpcGeneratorCli.class);
            cli.start();
        };
    }

}
