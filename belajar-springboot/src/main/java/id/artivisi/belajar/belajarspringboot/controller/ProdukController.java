package id.artivisi.belajar.belajarspringboot.controller;

import id.artivisi.belajar.belajarspringboot.dao.ProdukDao;
import id.artivisi.belajar.belajarspringboot.domain.Produk;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @GetMapping("/produk/list")
    public ModelMap halo(@RequestParam(required = false)String cari,
            @PageableDefault(sort = {"kode"}) Pageable page) throws IOException {
       
        ModelMap data = new ModelMap();
        
        if(StringUtils.hasText(cari)){
            data.addAttribute("dataProduk", 
                    produkDao.findByKodeContainingIgnoreCaseOrNamaContainingIgnoreCase(cari, cari, page));
        } else {
            data.addAttribute("dataProduk", produkDao.findAll(page));
        }
        
        return data;
    }
}
