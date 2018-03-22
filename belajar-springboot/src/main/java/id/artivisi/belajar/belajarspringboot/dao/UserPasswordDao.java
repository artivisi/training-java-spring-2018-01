package id.artivisi.belajar.belajarspringboot.dao;

import id.artivisi.belajar.belajarspringboot.domain.UserPassword;
import org.springframework.data.repository.CrudRepository;

public interface UserPasswordDao extends CrudRepository<UserPassword, String> {
    
}
