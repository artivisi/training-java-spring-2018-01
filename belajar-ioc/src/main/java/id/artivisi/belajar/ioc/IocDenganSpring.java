package id.artivisi.belajar.ioc;

import id.artivisi.belajar.ioc.controller.ProdukController;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IocDenganSpring {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("belajar-ioc.xml");
        // BasicDataSource ds = ac.getBean(BasicDataSource.class);
        BasicDataSource ds = (BasicDataSource) ac.getBean("ds");
        ProdukController pc = ac.getBean(ProdukController.class);
    }
}
