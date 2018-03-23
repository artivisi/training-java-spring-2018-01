package id.artivisi.belajar.belajarspringboot.dao;

import id.artivisi.belajar.belajarspringboot.domain.Produk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface ProdukDao extends PagingAndSortingRepository<Produk, String>, 
        QueryByExampleExecutor<Produk>,
        QuerydslPredicateExecutor<Produk>{
    Page<Produk> findByKodeContainingIgnoreCaseOrNamaContainingIgnoreCase(String kode, String nama, Pageable page);
}
