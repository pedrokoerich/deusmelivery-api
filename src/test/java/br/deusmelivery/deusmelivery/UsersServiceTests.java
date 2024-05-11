package br.deusmelivery.deusmelivery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import br.deusmelivery.deusmelivery.users.entity.Users;
import br.deusmelivery.deusmelivery.users.repository.UsersRepository;
import br.deusmelivery.deusmelivery.users.service.UsersService;
import br.deusmelivery.deusmelivery.users.service.Impl.UsersServiceImpl;

public class UsersServiceTests {

    @Mock
    private UsersRepository userRepository;

    @InjectMocks
    private UsersService userService = new UsersServiceImpl(userRepository);

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        // Mockando dados de usuários
        List<Users> mockUsers = new ArrayList<>();
        mockUsers.add(new Users(1L, "User 1"));
        mockUsers.add(new Users(2L, "User 2"));

        // Configurando comportamento do UserRepository mockado
        when(userRepository.findAll()).thenReturn(mockUsers);

        // Chamando o método do serviço
        List<Users> users = userService.getAllUsers();

        // Verificando se o método retornou a lista esperada
        assertEquals(2, users.size());
    }

    @Test
    public void testCreateUser() {
        // Criando um usuário
        Users user = new Users();
        user.setId(1L);
        user.setName("User 1");

        // Mockando o retorno do UserRepository.save()
        when(userRepository.save(user)).thenReturn(user);

        // Chamando o método do serviço para criar o usuário
        Users createdUser = userService.createUser(user);

        // Verificando se o usuário foi criado com sucesso
        assertNotNull(createdUser);
        assertEquals(user.getId(), createdUser.getId());
        assertEquals(user.getName(), createdUser.getName());
    }
}
