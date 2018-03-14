package id.artivisi.belajar.ioc;

import id.artivisi.belajar.ioc.dao.PembeliDao;
import id.artivisi.belajar.ioc.dao.PesananDao;
import id.artivisi.belajar.ioc.dao.ProdukDao;
import org.apache.commons.dbcp2.BasicDataSource;

public class IocTanpaSpring {
    public static void main(String[] args) throws Exception {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost/belajar");
        ds.setUsername("belajar");
        ds.setPassword("java");
        
        ProdukDao produkDao = new ProdukDao(ds);
        PesananDao pesananDao = new PesananDao(ds);
        PembeliDao pembeliDao = new PembeliDao(ds);
    }
}
