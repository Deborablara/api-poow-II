package br.ufsm.csi.app.security;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.ufsm.csi.app.model.UserModel;
import br.ufsm.csi.app.repository.UserRepository;

@Service
@Transactional
public class UserDetailsServiceImplements implements UserDetailsService {

  final UserRepository userRepository;

  public UserDetailsServiceImplements(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserModel user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    return new User(user.getUsername(), user.getPassword(), true, true, true, true, user.getAuthorities());
  }

}
