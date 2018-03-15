package id.artivisi.belajar.springmvc;

import id.artivisi.belajar.springmvc.advice.MethodDurationAdvice;
import id.artivisi.belajar.springmvc.advice.QueryLogAdvice;
import id.artivisi.belajar.springmvc.dao.ProdukDao;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "id.artivisi.belajar.springmvc")
@EnableTransactionManagement
@EnableWebMvc
public class KonfigurasiAplikasi implements WebMvcConfigurer {
    
    @Autowired @Qualifier("produkDaoAsli") private ProdukDao produkDao;
    @Autowired private QueryLogAdvice queryLogAdvice;
    @Autowired private MethodDurationAdvice methodDurationAdvice;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
            .addResourceLocations("/public", "classpath:/static/");
    }
    
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
    
    @Bean
    public LocalSessionFactoryBean inisialisasiHibernate(){
        LocalSessionFactoryBean lsfb
                = new LocalSessionFactoryBean();
        lsfb.setPackagesToScan("id.artivisi.belajar.springmvc.domain");
        lsfb.setDataSource(inisialisasiDatasource());
        lsfb.setHibernateProperties(hibernateProperties());
        return lsfb;
    }
    
    @Bean
    public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory){
        return new HibernateTransactionManager(sessionFactory);
    }
    
    private Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", "update");
                setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
                setProperty("hibernate.show_sql", "true");
                setProperty("hibernate.format_sql", "true");
            }
        };
    }
    
}
