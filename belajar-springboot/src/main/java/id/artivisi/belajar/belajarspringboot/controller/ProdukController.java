package id.artivisi.belajar.belajarspringboot.controller;

import id.artivisi.belajar.belajarspringboot.dao.ProdukDao;
import id.artivisi.belajar.belajarspringboot.domain.Produk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ProdukController {
    @Autowired ProdukDao produkDao;
    
    @GetMapping("/api/produk")
    @ResponseBody
    public Page<Produk> dataProduk(Pageable page){
        return produkDao.findAll(page);
    }
    
    @PostMapping("/api/produk")
    @ResponseStatus(HttpStatus.CREATED)
    public void simpan(@RequestBody Produk p){
        produkDao.save(p);
    }
}
