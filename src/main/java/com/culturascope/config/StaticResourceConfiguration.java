package com.culturascope.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// La classe StaticResourceConfiguration est automatiquement appelée
// au démarrage de l'application grâce à son annotation @Configuration. 

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {

    // Cette classe configure la gestion des ressources statiques dans Spring MVC

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Ajoute un gestionnaire de ressources pour les requêtes correspondant au chemin "/uploads/**"
        // Les ressources correspondantes sont localisées dans le répertoire de ressources de classe "uploads/"
        registry.addResourceHandler("/uploads/**").addResourceLocations("classpath:/uploads/");
    }
}
