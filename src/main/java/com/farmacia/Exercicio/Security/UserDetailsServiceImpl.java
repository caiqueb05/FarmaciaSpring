package com.farmacia.Exercicio.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.farmacia.Exercicio.Model.UsuarioModel;
import com.farmacia.Exercicio.Repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UsuarioRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
		Optional<UsuarioModel> user = userRepository.findByNomeUsuario(nomeUsuario);
		user.orElseThrow(() -> new UsernameNotFoundException(nomeUsuario + " Not Found."));

		return user.map(UserDetailsImpl::new).get();
	}

}
