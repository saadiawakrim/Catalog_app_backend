package com.ym;

import com.ym.dto.ProductDTO;
import com.ym.dto.UserDto;
import com.ym.service.IProductService;
import com.ym.service.IUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CatalogAppBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogAppBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(IProductService productService, IUserService userService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                for (int i = 1; i <= 10; i++) {
                    productService.addProduct(new ProductDTO(null, "computer " + (i), 1000, Math.random() > 0.5 ? true : false));
                }
                for (int i = 1; i <= 10; i++) {
                    productService.addProduct(new ProductDTO(null, "phone " + (i), 2000, Math.random() > 0.5 ? true : false));
                }
                for (int i = 1; i <= 10; i++) {
                    productService.addProduct(new ProductDTO(null, "tv " + (i), 3000, Math.random() > 0.5 ? true : false));
                }
                userService.addUser(new UserDto(null, "admin", "admin", "ADMIN"));
                userService.addUser(new UserDto(null, "user", "user", "USER"));
            }
        };
    }
}
