package br.ufsm.csi.app.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.ufsm.csi.app.model.UserModel;
import br.ufsm.csi.app.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {
    private final UserRepository uRepository;

    public AuthenticationService(UserRepository repository) {
        this.uRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<UserModel> usuario = uRepository.findByUsername(name);
        if (!usuario.isPresent()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        } else {
            UserDetails user = User.withUsername(usuario.get().getUsername())
                    .password(usuario.get().getPassword())
                    .authorities(usuario.get().getAuthorities())
                    .build();
            return user;
        }
    }
}
