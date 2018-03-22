package id.artivisi.belajar.belajarspringboot.dao;

import id.artivisi.belajar.belajarspringboot.domain.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleDao extends PagingAndSortingRepository<Role, String> {
    
}
