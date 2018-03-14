package id.artivisi.belajar.junit;

import id.artivisi.belajar.Kalkulator;
import org.junit.Assert;
import org.junit.Test;

public class KalkulatorTests {
    
    @Test
    public void testTambah(){
        Kalkulator k = new Kalkulator();
        Integer hasil = k.tambah(2, 3);
        Assert.assertTrue(hasil.equals(5));
    }
    
    @Test
    public void testKali(){
        Kalkulator k = new Kalkulator();
        Integer hasil = k.kali(2, 3);
        Assert.assertTrue(hasil.equals(5));
    }
}
