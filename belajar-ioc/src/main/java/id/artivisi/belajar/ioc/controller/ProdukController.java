package id.artivisi.belajar.ioc.controller;

import id.artivisi.belajar.ioc.dao.ProdukDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdukController {
    @Autowired
    private ProdukDao produkDao;
    
    public void tampilkanDataProduk(){
        produkDao.cariDataProduk();
    }
}
