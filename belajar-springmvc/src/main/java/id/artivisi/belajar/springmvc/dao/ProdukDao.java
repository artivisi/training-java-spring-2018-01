package id.artivisi.belajar.springmvc.dao;

import id.artivisi.belajar.springmvc.domain.Produk;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdukDao {
    
    @Autowired private DataSource dataSource;
    
    public List<Produk> semuaProduk(){
        List<Produk> hasil = new ArrayList<>();
            
        try {
            String sql = "select * from produk";
            Connection conn = dataSource.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            
            while(rs.next()){
                Produk p = new Produk();
                p.setId(rs.getInt("id"));
                p.setKode(rs.getString("kode"));
                p.setNama(rs.getString("nama"));
                p.setHarga(rs.getBigDecimal("harga"));
                hasil.add(p);
            }
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return hasil;
    }
}
