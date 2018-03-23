package id.artivisi.belajar.belajarspringboot.controller;

import id.artivisi.belajar.belajarspringboot.dao.ProdukDao;
import id.artivisi.belajar.belajarspringboot.domain.Produk;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProdukController {
    
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ProdukController.class);
    
    @Value("${folder.foto}")
    private String folderFoto;
    
    @Autowired ProdukDao produkDao;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
    
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
    
    @PreAuthorize("hasAuthority('VIEW_PRODUCT')")
    @GetMapping("/produk/list")
    public ModelMap halo(
            @ModelAttribute Produk cariProduk,
            @RequestParam(required = false)String cari,
            @PageableDefault(sort = {"kode"}) Pageable page) throws IOException {
       
        ModelMap data = new ModelMap();
        
        if(cariProduk != null){
            LOGGER.debug("Kode : {}", cariProduk.getKode());
            LOGGER.debug("Nama : {}", cariProduk.getNama());
            
            ExampleMatcher matcher = ExampleMatcher.matchingAny()
            .withMatcher("kode", match -> match.exact())
            .withMatcher("nama", match -> match.contains().ignoreCase());
            
            data.addAttribute("dataProduk", produkDao.findAll(Example.of(cariProduk, matcher), page));
            return data;
        }
        
        if(StringUtils.hasText(cari)){
            data.addAttribute("dataProduk", 
                    produkDao.findByKodeContainingIgnoreCaseOrNamaContainingIgnoreCase(cari, cari, page));
        } else {
            data.addAttribute("dataProduk", produkDao.findAll(page));
        }
        
        return data;
    }
    
    @PreAuthorize("hasAuthority('EDIT_PRODUCT')")
    @GetMapping("/produk/form")
    public ModelMap tampilkanForm(@RequestParam(name = "id", required = false) String produkId ){
        Produk produk = new Produk();
        if(produkId != null) {
            Optional<Produk> p = produkDao.findById(produkId);
            if(p.isPresent()){
                produk = p.get();
            }
        }
        ModelMap mm = new ModelMap();
        mm.addAttribute("produk", produk);
        return mm;
    }
    
    @PreAuthorize("hasAuthority('EDIT_PRODUCT')")
    @PostMapping("/produk/form")
    public String prosesForm(
            @RequestParam(name = "fileFoto", required = false) MultipartFile fileFoto,
            @ModelAttribute @Valid Produk p, BindingResult errors, SessionStatus status){
        
        if(errors.hasErrors()) {
            return "produk/form";
        }
        
        if(fileFoto != null) {
            try {
                Path pathFolderFoto = Paths.get(folderFoto);
                
                // create foldernya kalau belum ada
                Files.createDirectories(pathFolderFoto);
                
                String namaFile = UUID.randomUUID().toString();
                
                LOGGER.debug("Nama file : "+fileFoto.getOriginalFilename());
                LOGGER.debug("Content Type : "+fileFoto.getContentType());
                LOGGER.debug("Ukuran : "+fileFoto.getSize());
                
                // misal nama file : foto.produk.001.jpg
                // extension : jpg
                String extensionFile = fileFoto.getOriginalFilename()
                        .substring(fileFoto.getOriginalFilename().lastIndexOf("."));
                
                String namafileTujuan = namaFile + extensionFile;
                
                String lokasiFile = pathFolderFoto.toAbsolutePath().toString() + File.separator + namafileTujuan;
                LOGGER.debug("Lokasi file di server : "+lokasiFile);
                
                fileFoto.transferTo(new File(lokasiFile));
                
                p.setFoto(namafileTujuan);
                
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
        
        produkDao.save(p);
        status.setComplete();
        return "redirect:list";
    }
}
