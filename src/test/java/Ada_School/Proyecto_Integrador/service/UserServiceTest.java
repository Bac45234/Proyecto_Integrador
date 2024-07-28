package Ada_School.Proyecto_Integrador.service;

import Ada_School.Proyecto_Integrador.dto.UserDto;
import Ada_School.Proyecto_Integrador.entity.UserEntity;
import Ada_School.Proyecto_Integrador.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void buscarTodos(){
        //Preparación
        UserEntity user1 = new UserEntity();
        user1.setId("1");
        user1.setName("FirstName");
        user1.setEmail("first@example.com");

        UserEntity user2 = new UserEntity();
        user2.setId("2");
        user2.setName("SecondName");
        user2.setEmail("second@example.com");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        //Ejecución
        List<UserDto> users = userService.getAll();

        //Validación
        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals("FirstName", users.get(0).getName());
        assertEquals("SecondName", users.get(1).getName());
    }

    @Test
    public void buscarPorId(){
        //Preparación
        UserEntity user = new UserEntity();
        user.setId("1");
        user.setName("ExampleName");
        user.setEmail("correo@example.com");
        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        //Ejecución
        UserDto userDto = userService.getById("1");

        //Validacion
        assert userDto.getId().equals("1");
        assert userDto.getName().equals("ExampleName");
        assert userDto.getEmail().equals("correo@example.com");
    }

    @Test
    public void guardar(){
        //Preparación
        UserDto userDto = new UserDto(null, "ExampleName", "correo@example.com");
        UserEntity user = new UserEntity();
        user.setId("1");
        user.setName("ExampleName");
        user.setEmail("correo@example.com");

        UserEntity savedUser = new UserEntity();
        savedUser.setId("1");
        savedUser.setName("ExampleName");
        savedUser.setEmail("correo@example.com");

        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUser);

        //Ejecucuón
        UserDto savedUserDto = userService.save(userDto);

        //Validación
        assertNotNull(savedUserDto);
        assertEquals("1", savedUserDto.getId());
        assertEquals("ExampleName", savedUserDto.getName());
        assertEquals("correo@example.com", savedUserDto.getEmail());
    }

    @Test
    public void actualizar(){
        //Preparación
        UserDto userDto = new UserDto(null, "NewName", "new@example.com");

        UserEntity user = new UserEntity();
        user.setId("1");
        user.setName("ExampleName");
        user.setEmail("correo@example.com");

        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        //Ejecución
        UserDto updateUser = userService.update(userDto, "1");

        //Validación
        assertNotNull(updateUser);
        assertEquals("1", updateUser.getId());
        assertEquals("NewName", updateUser.getName());
        assertEquals("new@example.com", updateUser.getEmail());
    }

    @Test
    public void eliminar(){
        //Preparación
        UserEntity user = new UserEntity();
        user.setId("1");
        user.setName("ExampleName");
        user.setEmail("correo@example.com");
        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        //Ejecución
        userService.delete("1");

        //Validación
        verify(userRepository, times(1)).delete(user);
    }
}