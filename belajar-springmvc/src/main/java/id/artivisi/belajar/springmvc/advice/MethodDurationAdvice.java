package id.artivisi.belajar.springmvc.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

@Component
public class MethodDurationAdvice implements MethodInterceptor{

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        long start = System.nanoTime();
        Object hasil = mi.proceed();
        long finish = System.nanoTime();
        long durasi = finish - start;
        System.out.println("Method "+mi.getMethod().getName()+" dijalankan dalam waktu "+ (durasi / 1000000) +" milliseconds");
        return hasil;
    }
    
}
