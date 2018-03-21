package id.artivisi.belajar.belajarspringboot.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class KonfigurasiSecurity extends WebSecurityConfigurerAdapter {

    private static final String SQL_LOGIN = "select u.username, up.password, u.active as enabled "
            + "from s_user u inner join s_user_password up on u.id = up.id_user "
            + "where u.username = ?";
    
    private static final String SQL_PERMISSION = "select u.username, p.name as authority from s_user u "
            + "inner join s_role r on u.id_role = r.id "
            + "inner join s_role_permission rp on r.id = rp.id_role "
            + "inner join s_permission p on p.id = rp.id_permission "
            + "where u.username = ?";
    
    @Autowired private DataSource dataSource;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(SQL_LOGIN)
                .authoritiesByUsernameQuery(SQL_PERMISSION);
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder(12);
    }
}
