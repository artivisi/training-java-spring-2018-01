package id.artivisi.belajar.belajarspringboot.helper;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptGeneratorTests {
    @Test
    public void generatePassword(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String hasil = encoder.encode("test001");
        Assert.assertNotNull(hasil);
        System.out.println("Hashed Password : "+hasil);
    }
}
