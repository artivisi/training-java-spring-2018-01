package id.artivisi.belajar.springmvc.controller;

import id.artivisi.belajar.springmvc.dao.ProdukDaoHibernate;
import id.artivisi.belajar.springmvc.domain.Produk;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProdukController {
    
    //@Autowired ProdukDao produkDao;
    @Autowired ProdukDaoHibernate produkDao;
    
    @GetMapping("/produk/list")
    public void halo(@RequestParam(required = false)String cari,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer rows,
            HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter output = response.getWriter();
        output.println("<html><head><title>Data Produk</title></head>");
        output.println("<body>");
        output.println("<h1>Data Produk</h1>");
        
        output.println("<table>");
        output.println("<tr><th>Id</th><th>Kode</th><th>Nama</th><th>Harga</th></tr>");
        
        if(page == null) {
            page = 0;
        }
        
        if(rows == null) {
            rows = 10;
        }
        
        Integer startRow = page * rows;
        
        List<Produk> dataProduk = new ArrayList<>();
        if(StringUtils.hasText(cari)){
            dataProduk.addAll(produkDao.cariProduk(cari));
        } else {
            dataProduk.addAll(produkDao.semuaProduk(startRow, rows));
        }
        
        for(Produk p : dataProduk) {
            output.println("<tr>");
            output.println("<td>"+p.getId()+"</td>");
            output.println("<td>"+p.getKode()+"</td>");
            output.println("<td>"+p.getNama()+"</td>");
            output.println("<td>"+p.getHarga()+"</td>");
            output.println("</tr>");
        }   
        
        output.println("</table>");
        
        output.println("</body>");
        output.println("</html>");
    }
    
    @PostMapping("/produk/form")
    public String simpan(@ModelAttribute Produk p){
        System.out.println("Kode : "+p.getKode());
        System.out.println("Nama : "+p.getNama());
        System.out.println("Harga : "+p.getHarga());
        produkDao.simpan(p);
        return "redirect:list";
    }
}
