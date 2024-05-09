package br.deusmelivery.deusmelivery.login.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    
    @GetMapping
    public ResponseEntity<Object> login() {
        return new ResponseEntity<Object>(new Login("user", "pass"), HttpStatus.OK);
    }

    class Login {
        private String user;
        private String pass;

        public Login(String user, String pass) {
            this.user = user;
            this.pass = pass;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }
    }
}
