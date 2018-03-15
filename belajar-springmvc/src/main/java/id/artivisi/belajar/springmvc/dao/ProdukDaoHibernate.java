package id.artivisi.belajar.springmvc.dao;

import id.artivisi.belajar.springmvc.domain.Produk;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProdukDaoHibernate {
    @Autowired private SessionFactory sessionFactory;
    
    public void simpan(Produk p){
        sessionFactory.getCurrentSession()
                .saveOrUpdate(p);
    }
    
    public Produk cariById(Integer id) {
        return sessionFactory.getCurrentSession()
                .get(Produk.class, id);
    }
    
    public List<Produk> semuaProduk(Integer startRecord, Integer jumlahRecord){
        return sessionFactory.getCurrentSession()
                .createQuery("select p from Produk p order by p.kode")
                .setMaxResults(jumlahRecord)
                .setFirstResult(startRecord)
                .list();
    }
    
    public List<Produk> cariProduk(String cari){
        return sessionFactory.getCurrentSession()
                .createQuery("select p from Produk p "
                        + "where p.kode like :cari or "
                        + "p.nama like :cari "
                        + "order by p.kode")
                .setParameter("cari", cari)
                .getResultList();
    }

    public void hapus(Produk p) {
        sessionFactory.getCurrentSession().delete(p);
    }
}
