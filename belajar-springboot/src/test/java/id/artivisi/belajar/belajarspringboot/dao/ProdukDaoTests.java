package id.artivisi.belajar.belajarspringboot.dao;

import id.artivisi.belajar.belajarspringboot.domain.Produk;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProdukDaoTests {
    
    @Autowired private ProdukDao produkDao;
    
    @Test
    public void testQueryProdukByExample(){
        Produk contoh1 = new Produk();
        contoh1.setKode("P-001");
        
        Produk contoh2 = new Produk();
        contoh2.setNama("Produk 00");
        
        System.out.println("==== Cari by kode ====");
        Iterable<Produk> hasilCariByKode = produkDao.findAll(Example.of(contoh1));
        tampilkanProduk(hasilCariByKode);
        
        ExampleMatcher matcher = ExampleMatcher.matching()
          .withMatcher("kode", match -> match.exact())
          .withMatcher("nama", match -> match.contains().ignoreCase());
        
        System.out.println("==== Cari by nama ====");
        Iterable <Produk> hasilCariByNama = produkDao.findAll(Example.of(contoh2, matcher));
        tampilkanProduk(hasilCariByNama);
    }

    private void tampilkanProduk(Iterable<Produk> hasilCari) {
        for(Produk p : hasilCari){
            System.out.println("ID : "+p.getId());
            System.out.println("Kode : "+p.getKode());
            System.out.println("Nama : "+p.getNama());
            System.out.println("Harga : "+p.getHarga());
        }
    }
}
