package uy.edu.bios.obligatorio.bios_work.configuracion;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uy.edu.bios.obligatorio.bios_work.repositorios.IRepositorioClientes;
import uy.edu.bios.obligatorio.bios_work.repositorios.IRepositorioConsultores;
import uy.edu.bios.obligatorio.bios_work.repositorios.IRepositorioPostulantes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.support.SessionFlashMapManager;



@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad {
    
    @Autowired
    private IRepositorioClientes repositorioClientes;

    @Autowired
    private IRepositorioConsultores repositorioConsultores;

    @Autowired
    private IRepositorioPostulantes repositorioPostulantes;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) 
            throws Exception {
    http            
        .authorizeHttpRequests(authorize -> authorize                
            .requestMatchers("/", "/inicio", "/login").permitAll()  
            .requestMatchers("/consultores/**", "/clientes/**", "/areas/**","/postulantes/**").hasAuthority("consultor")
            .requestMatchers("/ofertas/**").hasAnyAuthority("consultor", "cliente")
            .requestMatchers("/postulantes/**").hasAuthority("postulante")
            .anyRequest().authenticated()
        )            
            .formLogin(login -> login
                .loginPage("/login").permitAll()         
                .successHandler(new SimpleUrlAuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                            throws IOException, ServletException {                        
                        FlashMap flashMap = new FlashMap();
                        flashMap.put("mensaje", "Has iniciado la sesión.");

                        FlashMapManager flashMapManager = new SessionFlashMapManager();
                        flashMapManager.saveOutputFlashMap(flashMap, request, response);

                        super.onAuthenticationSuccess(request, response, authentication);
                    }
                }))            
            .logout(logout -> logout
                .logoutUrl("/logout").logoutSuccessUrl("/").permitAll()
                .logoutSuccessHandler(new SimpleUrlLogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                            throws IOException, ServletException {

                        FlashMap flashMap = new FlashMap();
                        flashMap.put("mensaje", "Has cerrado la sesión.");

                        FlashMapManager flashMapManager = new SessionFlashMapManager();
                        flashMapManager.saveOutputFlashMap(flashMap, request, response);

                        super.onLogoutSuccess(request, response, authentication);
                    }
                }));       

        return http.build();
    }

     @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
            .requestMatchers("/css/**", "/imagenes/**");
    }
}