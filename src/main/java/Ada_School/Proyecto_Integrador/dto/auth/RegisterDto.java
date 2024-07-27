package Ada_School.Proyecto_Integrador.dto.auth;

public class RegisterDto {

    private String name;
    private String email;
    private String password;

    public RegisterDto(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

}