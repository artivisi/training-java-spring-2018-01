package id.artivisi.belajar.springmvc;

import id.artivisi.belajar.springmvc.advice.MethodDurationAdvice;
import id.artivisi.belajar.springmvc.advice.QueryLogAdvice;
import id.artivisi.belajar.springmvc.dao.ProdukDao;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "id.artivisi.belajar.springmvc")
public class KonfigurasiAplikasi {
    
    @Autowired @Qualifier("produkDaoAsli") private ProdukDao produkDao;
    @Autowired private QueryLogAdvice queryLogAdvice;
    @Autowired private MethodDurationAdvice methodDurationAdvice;
    
    @Bean
    public DataSource inisialisasiDatasource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost/belajar");
        ds.setUsername("belajar");
        ds.setPassword("java");
        return ds;
    }
    
    @Bean("produkDao")
    public ProdukDao proxyAop(){
        ProxyFactory proxy = new ProxyFactory(produkDao);
        proxy.addAdvice(queryLogAdvice);
        proxy.addAdvice(methodDurationAdvice);
        return (ProdukDao) proxy.getProxy();
    }
    
}
