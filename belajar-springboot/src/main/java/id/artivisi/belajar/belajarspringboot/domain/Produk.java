package id.artivisi.belajar.belajarspringboot.domain;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Produk {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @NotNull @NotEmpty @Size(min = 3, max = 10)
    private String kode;
    
    @NotNull @NotEmpty @Size(min = 5, max = 255)
    private String nama;
    
    @Min(0)
    private BigDecimal harga;
}
