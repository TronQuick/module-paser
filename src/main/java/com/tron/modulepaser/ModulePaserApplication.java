package com.tron.modulepaser;

import com.tron.modulepaser.utils.UniqueNameGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tron"},nameGenerator = UniqueNameGenerator.class)
@EnableJpaRepositories
public class ModulePaserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModulePaserApplication.class, args);
    }

}
