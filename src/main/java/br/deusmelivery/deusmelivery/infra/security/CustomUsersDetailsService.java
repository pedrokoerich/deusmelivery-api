package br.deusmelivery.deusmelivery.infra.security;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.deusmelivery.deusmelivery.users.entity.Users;
import br.deusmelivery.deusmelivery.users.repository.UsersRepository;

@Component
public class CustomUsersDetailsService implements UserDetailsService
{
	@Autowired
	private UsersRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		Users users = this.userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
		return new org.springframework.security.core.userdetails.User(users.getLogin(), users.getPassword(), List.of());
	}
}
