package id.artivisi.belajar.ioc.dao;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("pesanDao")
public class PesananDao {
    private DataSource dataSource;

    public PesananDao(@Autowired DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    
}
