package id.artivisi.belajar.springmvc;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class InisialisasiAplikasiWeb implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
        appContext.setConfigLocation("/WEB-INF/konfigurasi-aplikasi-web.xml");

        ServletRegistration.Dynamic registration = sc
                .addServlet("dispatcher", new DispatcherServlet(appContext));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
    
}
