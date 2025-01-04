package forum.api.foro.infra.security;

import forum.api.foro.domain.usuario.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Es quien tiene la logica de autenticacion
//UserDetailsService
@Service
public class AutenticationService implements UserDetailsService {
    //Tiene q recibir el usuario
    @Autowired
    private IUsuarioRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Usuario: " + username);
        return userRepository.findByNombre(username);
    }
}
