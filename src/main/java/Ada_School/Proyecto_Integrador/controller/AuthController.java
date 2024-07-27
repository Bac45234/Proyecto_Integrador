package Ada_School.Proyecto_Integrador.controller;

import Ada_School.Proyecto_Integrador.dto.auth.AuthDto;
import Ada_School.Proyecto_Integrador.dto.auth.LoginDto;
import Ada_School.Proyecto_Integrador.dto.auth.RegisterDto;
import Ada_School.Proyecto_Integrador.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthDto> login(@RequestBody LoginDto login){
        AuthDto authDto = this.authService.login(login);
        return ResponseEntity.ok(authDto);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthDto> register(@RequestBody RegisterDto dto){
        AuthDto authDto = this.authService.register(dto);
        return ResponseEntity.ok(authDto);
    }
}
