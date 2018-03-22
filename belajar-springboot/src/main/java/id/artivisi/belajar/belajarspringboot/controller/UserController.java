package id.artivisi.belajar.belajarspringboot.controller;

import id.artivisi.belajar.belajarspringboot.dao.RoleDao;
import id.artivisi.belajar.belajarspringboot.dao.UserDao;
import id.artivisi.belajar.belajarspringboot.dao.UserPasswordDao;
import id.artivisi.belajar.belajarspringboot.domain.Role;
import id.artivisi.belajar.belajarspringboot.domain.User;
import id.artivisi.belajar.belajarspringboot.domain.UserPassword;
import id.artivisi.belajar.belajarspringboot.dto.UserForm;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/user")
@Transactional
public class UserController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    
    @Autowired private RoleDao roleDao;
    @Autowired private UserDao userDao;
    @Autowired private UserPasswordDao userPasswordDao;
    @Autowired private PasswordEncoder passwordEncoder;
    
    @ModelAttribute("daftarRole")
    public Iterable<Role> daftarRole(){
        return roleDao.findAll();
    }
    
    @GetMapping("/form")
    public ModelMap tampilkanForm(){
        ModelMap mm = new ModelMap();
        mm.addAttribute("userform", new UserForm());
        return mm;
    }
    
    @PostMapping("/form")
    public String prosesForm(@ModelAttribute(name = "userform") @Valid UserForm userForm, BindingResult err, SessionStatus status){
        
        if(err.hasErrors()){
            return "user/form";
        }
        
        if(!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            err.rejectValue("passwordConfirm", "Password Confirmation mismatch");
            return "user/form";
        }
        
        LOGGER.debug("Username : {}", userForm.getUsername());
        LOGGER.debug("Full Name : {}", userForm.getFullname());
        LOGGER.debug("Email : {}", userForm.getEmail());
        LOGGER.debug("Password : {}", userForm.getPassword());
        LOGGER.debug("Confirm Password : {}", userForm.getPasswordConfirm());
        LOGGER.debug("Role : {}", userForm.getRole().getName());
        
        User u = new User();
        BeanUtils.copyProperties(userForm, u);
        if(!StringUtils.hasText(userForm.getId())){
            u.setId(null);
        }
        u.setActive(Boolean.TRUE);
        
        UserPassword up = new UserPassword();
        up.setUser(u);
        up.setPassword(passwordEncoder.encode(userForm.getPassword()));
        
        userDao.save(u);
        userPasswordDao.save(up);
        
        return "redirect:form";
    }
}
