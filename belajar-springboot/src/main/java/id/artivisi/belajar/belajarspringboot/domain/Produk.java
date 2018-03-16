package id.artivisi.belajar.belajarspringboot.domain;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Produk {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String kode;
    private String nama;
    private BigDecimal harga;
}
