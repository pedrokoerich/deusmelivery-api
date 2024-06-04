package br.deusmelivery.deusmelivery.login.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.deusmelivery.deusmelivery.infra.security.TokenSevice;
import br.deusmelivery.deusmelivery.login.entity.DTO.LoginInput;
import br.deusmelivery.deusmelivery.login.entity.DTO.LoginOutput;
import br.deusmelivery.deusmelivery.login.entity.DTO.RegisterInput;
import br.deusmelivery.deusmelivery.login.entity.DTO.RegisterOutput;
import br.deusmelivery.deusmelivery.login.service.LoginService;
import br.deusmelivery.deusmelivery.users.entity.Users;
import br.deusmelivery.deusmelivery.users.repository.UsersRepository;
import br.deusmelivery.deusmelivery.users.service.UsersService;
import ch.qos.logback.core.subst.Token;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenSevice tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginInput entity) {

        Users user = this.usersRepository.findByLogin(entity.login()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		if (passwordEncoder.matches(entity.password(), user.getPassword()))
		{
			String token = this.tokenService.generateToken(user);
			return ResponseEntity.ok(new LoginOutput(user.getName(),token));
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/register")
	public ResponseEntity register(@RequestBody RegisterInput register)
	{
		Optional<Users> existsUser = this.usersRepository.findByLogin(register.login());
		if (existsUser.isEmpty())
		{
			Users newUser = new Users();
			newUser.setPassword(passwordEncoder.encode(register.password()));
			newUser.setLogin(register.login());
			newUser.setName(register.name());
            newUser.setAddress(register.address());
            newUser.setPhone(register.phone());
            newUser.setAddressNumber(register.addressNumber());
            newUser.setBirthday(register.birthday());
            newUser.setCity(register.city());
            newUser.setCpf(register.cpf());
            newUser.setGenre(register.genre());
            newUser.setState(register.state());
            newUser.setStatus("A");
			this.usersRepository.save(newUser);

			String token = this.tokenService.generateToken(newUser);
			return ResponseEntity.ok(new RegisterOutput(newUser.getName(), token));
		}
		return ResponseEntity.badRequest().build();
	}
}
