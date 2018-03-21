package id.artivisi.belajar.belajarspringboot.dao;

import id.artivisi.belajar.belajarspringboot.domain.Produk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProdukDao extends PagingAndSortingRepository<Produk, String> {
    Page<Produk> findByKodeContainingIgnoreCaseOrNamaContainingIgnoreCase(String kode, String nama, Pageable page);
}
