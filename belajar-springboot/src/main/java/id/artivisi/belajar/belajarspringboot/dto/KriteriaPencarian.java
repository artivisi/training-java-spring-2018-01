package id.artivisi.belajar.belajarspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class KriteriaPencarian {
    private String nama;
    private String operator;
    private Object nilai;
}
