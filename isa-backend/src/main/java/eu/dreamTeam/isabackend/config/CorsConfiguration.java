//package eu.dreamTeam.isabackend.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebMvc
//public class CorsConfiguration implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**").allowedOrigins("http://localhost:4200").allowedMethods("*").allowedHeaders("*");
//    }
//}

//POTENCIJALNO NE TREBA JER SADA API GATEWAY KONTROLISE CORS
//ZAPRAVO NE SME DA POSTOJI DODATNA KONFIGURACIJA ZA CORS KADA API-GATEWAY IMA SVOJU!!!