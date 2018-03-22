package id.artivisi.belajar.belajarspringboot.dto;

import id.artivisi.belajar.belajarspringboot.domain.Role;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UserForm {
    private String id;
    
    @NotNull @NotEmpty @Size(min = 4, max = 255)
    private String username;
    
    @NotNull @NotEmpty @Size(min = 4, max = 255)
    private String fullname;
    
    @NotNull @NotEmpty @Email
    private String email;
    
    @NotNull @NotEmpty @Size(min = 4, max = 255)
    private String password;
    
    @NotNull @NotEmpty @Size(min = 4, max = 255)
    private String passwordConfirm;
    
    @NotNull
    private Role role;
}
