package br.ufsm.csi.app.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  final UserRepository userRepository;

  public UserDetailsServiceImplements(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserModel user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    UserModel userWichAutorities = userRepository.getUserWithAutorities(user.getUserId());
    System.out.println(userWichAutorities.getAuthorities());
    UserDetails existingUser = User.withUsername(userWichAutorities.getUsername())
        .password(userWichAutorities.getPassword())
        .authorities(userWichAutorities.getAuthorities()).build();
    return existingUser;
  }

}
