package uy.edu.bios.obligatorio.bios_work.configuracion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class ConfiguracionWeb implements WebMvcConfigurer {

    @Value("${ruta-fotografias-postulantes}")
    private String rutaFotografiasPostulantes;

    @Value("${ruta-cvs-postulantes}")
    private String rutaCvsPostulantes;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/fotografias-postulantes/**").addResourceLocations("file:" + rutaFotografiasPostulantes + "/");
        registry.addResourceHandler("/cvs-postulantes/**").addResourceLocations("file:" + rutaCvsPostulantes + "/");
        
    }
}