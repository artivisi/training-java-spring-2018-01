package id.artivisi.belajar.springmvc.controller;

import id.artivisi.belajar.springmvc.dao.ProdukDao;
import id.artivisi.belajar.springmvc.domain.Produk;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProdukController {
    
    @Autowired ProdukDao produkDao;
    
    @GetMapping("/produk/list")
    public void halo(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter output = response.getWriter();
        output.println("<html><head><title>Data Produk</title></head>");
        output.println("<body>");
        output.println("<h1>Data Produk</h1>");
        
        output.println("<table>");
        output.println("<tr><th>Id</th><th>Kode</th><th>Nama</th><th>Harga</th></tr>");
        
        List<Produk> dataProduk = produkDao.semuaProduk();
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
}
