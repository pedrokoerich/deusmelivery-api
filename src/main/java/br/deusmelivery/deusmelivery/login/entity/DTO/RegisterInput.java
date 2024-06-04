package br.deusmelivery.deusmelivery.login.entity.DTO;

import java.sql.Date;

public record RegisterInput(String name, String genre, Date birthday, String login, String password, String phone, String address, int addressNumber, String city, String state, String cpf) {

    
    
}
