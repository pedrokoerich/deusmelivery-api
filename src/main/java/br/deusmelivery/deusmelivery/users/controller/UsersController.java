package br.deusmelivery.deusmelivery.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.deusmelivery.deusmelivery.users.entity.Users;
import br.deusmelivery.deusmelivery.users.service.UsersService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    @Autowired
    private final UsersService userService;

    public UsersController(UsersService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        return new ResponseEntity<List<Users>>(userService.getAllUsers(),  HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<Boolean> createUser(@RequestBody Users user) {
        boolean created = userService.createUser(user);
        if (created) {
            return ResponseEntity.ok().build(); // Retorno 200 OK se o usuário foi criado com sucesso
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorno 400 Bad Request se ocorrer algum erro na criação do usuário
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users user) {
        userService.updateUser(id, user);
        return new ResponseEntity<Users>(user, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.ok().build(); // Retorno 200 OK se o usuário foi excluido com sucesso
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorno 400 Bad Request se ocorrer algum erro na exclusão do usuário
        }
    }
    
}
