package id.artivisi.belajar.springmvc.advice;

import id.artivisi.belajar.springmvc.domain.Produk;
import java.util.List;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

@Component
public class QueryLogAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        System.out.println("Nama method : "+mi.getMethod().getName());
        
        for(Object param : mi.getArguments()){
            System.out.println("Tipe data argumen "+param.getClass().getName());
            System.out.println("Isi argumen : "+param);
        }
        
        Object hasil = mi.proceed();
        
        System.out.println("Tipe data return value : "+hasil.getClass().getName());
        
        if("cariProduk".equals(mi.getMethod().getName())){
            List<Produk> dataProduk = (List<Produk>) hasil;
            System.out.println("Jumlah data dari query : "+dataProduk.size());
        }
        
        return hasil;
    }
    
}
