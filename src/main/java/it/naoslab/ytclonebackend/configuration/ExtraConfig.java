package it.naoslab.ytclonebackend.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ExtraConfig implements WebMvcConfigurer {

//    Configurazione per l'utilizzo dello storage dei multimedia in locale

    @Value("${MultimediaDirectoryFolder.upload}")
    String pathFisico;

    @Value("${MultimediaDirectoryFolder.upload-without-prefix}")
    String pathUrl;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/Multimedia/**").addResourceLocations("file:///" + pathFisico + "/");
    }

//    Abilitazione CORS per la comunicazione tra BE e FE
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS").allowedHeaders("*").maxAge(3600);
    }


}
