package uy.edu.bios.obligatorio.bios_work.servicios;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import uy.edu.bios.obligatorio.bios_work.dominio.Rol;
import uy.edu.bios.obligatorio.bios_work.dominio.Usuario;
import uy.edu.bios.obligatorio.bios_work.repositorios.IRepositorioClientes;
import uy.edu.bios.obligatorio.bios_work.repositorios.IRepositorioConsultores;
import uy.edu.bios.obligatorio.bios_work.repositorios.IRepositorioPostulantes;

@Service
public class ServicioDetallesUsuario implements UserDetailsService{

    @Autowired
    private IRepositorioClientes repositorioClientes;

    @Autowired
    private IRepositorioConsultores repositorioConsultores;

    @Autowired
    private IRepositorioPostulantes repositorioPostulantes;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repositorioClientes.findById(username).orElse(null);
        
        if (usuario == null) {
            usuario = repositorioConsultores.findById(username).orElse(null);
        }else {
            usuario = repositorioPostulantes.findById(username).orElse(null);
        }

        if (usuario == null || !usuario.isActivo()) {
            throw new UsernameNotFoundException("El usuario no existe.");
        }

         Set<GrantedAuthority> roles = new HashSet<>();

        for (Rol r : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(r.getNombreRol()));
        } 

        return new User(usuario.getNombreUsuario(), usuario.getClaveAcceso(), true, true, true, true, roles);
    }     
}
