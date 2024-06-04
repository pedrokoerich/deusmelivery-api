package br.deusmelivery.deusmelivery.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.deusmelivery.deusmelivery.users.entity.Users;
import br.deusmelivery.deusmelivery.users.repository.UsersRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Collections;
import java.util.Objects;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class SecurityFilter extends OncePerRequestFilter{
    @Autowired
    TokenSevice tokenService;
	@Autowired
	UsersRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException
	{
		var token = this.recoverToken(request);
		var login = tokenService.validateToken(token);
		if(Objects.nonNull(login))
		{
			Users users = userRepository.findByLogin(login).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
			var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
			var authentication = new UsernamePasswordAuthenticationToken(users, null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}

	private String recoverToken(HttpServletRequest request)
	{
		var authHeader = request.getHeader("Authorization");
		if(Objects.isNull(authHeader))
		{
			return null;
		}
		return authHeader.replace("Bearer ", "");
	}
}
