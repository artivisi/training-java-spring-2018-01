package id.artivisi.belajar.ioc.dao;

import java.util.List;
import javax.sql.DataSource;

public class ProdukDao {
    
    private DataSource dataSource;

    public ProdukDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public void simpan(){
        System.out.println("Insert data produk");
    }
    
    public List<Object> cariDataProduk(){
        System.out.println("select * from produk");
        return null;
    }
}
