package id.artivisi.belajar.belajarspringboot.domain;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@EqualsAndHashCode(of = "kode")
public class Produk {
    
    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @NotNull @NotEmpty @Size(min = 3, max = 10)
    private String kode;
    
    @NotNull @NotEmpty @Size(min = 5, max = 255)
    private String nama;
    
    @Min(0)
    private BigDecimal harga;
}
