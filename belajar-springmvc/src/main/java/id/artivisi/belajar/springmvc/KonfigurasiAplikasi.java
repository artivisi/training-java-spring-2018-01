package id.artivisi.belajar.springmvc;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "id.artivisi.belajar.springmvc")
public class KonfigurasiAplikasi {
    
    @Bean
    public DataSource inisialisasiDatasource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost/belajar");
        ds.setUsername("belajar");
        ds.setPassword("java");
        return ds;
    }
    
}
