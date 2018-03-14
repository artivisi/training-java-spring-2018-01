package id.artivisi.belajar.ioc;

import java.sql.Connection;
import java.sql.ResultSet;
import org.apache.commons.dbcp2.BasicDataSource;

public class IocTanpaSpring {
    public static void main(String[] args) throws Exception {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost/belajar");
        ds.setUsername("belajar");
        ds.setPassword("java");
        
        Connection dbConn = ds.getConnection();
        String sql = "select * from produk";
        ResultSet rs = dbConn.createStatement().executeQuery(sql);
        while(rs.next()){
            System.out.println("Kode Produk : "+rs.getString("kode"));
        }
        rs.close();
        dbConn.close();
    }
}
