package Ada_School.Proyecto_Integrador.service;

import Ada_School.Proyecto_Integrador.config.JwtService;
import Ada_School.Proyecto_Integrador.dto.auth.AuthDto;
import Ada_School.Proyecto_Integrador.dto.auth.LoginDto;
import Ada_School.Proyecto_Integrador.dto.auth.RegisterDto;
import Ada_School.Proyecto_Integrador.entity.UserEntity;
import Ada_School.Proyecto_Integrador.repository.UserRepository;
import Ada_School.Proyecto_Integrador.util.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    public void login(){
        //Preparación
        LoginDto loginDto = new LoginDto("exampleUser", "examplePasswrod");
        UserEntity user = new UserEntity();
        user.setEmail("exampleUser");
        user.setPassword("examplePasswrod");

        when(userRepository.findByEmail("exampleUser")).thenReturn(Optional.of(user));
        when(jwtService.getToken(any(UserDetails.class))).thenReturn("exampleToken");

        //Ejecución
        AuthDto authDto = authService.login(loginDto);

        //Verificación
        assertNotNull(authDto);
        assertEquals("exampleToken", authDto.getToken());

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository).findByEmail("exampleUser");
        verify(jwtService).getToken(any(UserDetails.class));
    }

    @Test
    public void register() {
        // Preparación
        RegisterDto registerDto = new RegisterDto("NAMEexample", "EMAILexample", "PASSWORDexample");
        UserEntity user = new UserEntity();
        user.setName("NAMEexample");
        user.setEmail("EMAILexample");
        user.setPassword("PASSWORDexample");
        user.setRole(Role.USER);

        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("passwordENCODED");
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);
        when(jwtService.getToken(any(UserDetails.class))).thenReturn("TOKENexample");

        // Ejecución
        AuthDto authDto = authService.register(registerDto);

        //Validación
        assertNotNull(authDto);
        assertEquals("TOKENexample", authDto.getToken());

        verify(passwordEncoder).encode(any(CharSequence.class));
        verify(userRepository).save(any(UserEntity.class));
        verify(jwtService).getToken(any(UserDetails.class));
    }
}