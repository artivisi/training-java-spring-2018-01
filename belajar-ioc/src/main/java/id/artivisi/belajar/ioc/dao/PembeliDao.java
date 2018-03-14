package id.artivisi.belajar.ioc.dao;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PembeliDao {
    private DataSource dataSource;

    public PembeliDao(@Autowired DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
}
