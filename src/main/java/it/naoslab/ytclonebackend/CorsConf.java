package it.naoslab.ytclonebackend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConf implements WebMvcConfigurer {

    @Value("${MultimediaDirectoryFolder.upload}")
    String pathFisico;

    @Value("${MultimediaDirectoryFolder.upload-without-prefix}")
    String pathUrl;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/Multimedia/**").addResourceLocations("file:///" + pathFisico + "/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT").allowedHeaders("*").maxAge(3600);
    }


}
