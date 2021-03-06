
package wad;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wad.domain.Kategoria;
import wad.repository.AccountRepository;
import wad.service.NewsSiteService;

import static org.junit.Assert.*;

import wad.domain.Kirjoittaja;
import wad.domain.Uutinen;


@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsSiteTest {
    
    @Autowired
    private NewsSiteService newsSiteService;
    @Autowired
    private AccountRepository accountRepository;
    
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void postConstructMetodinKategoriatNäkyvätOikeasti() {
        List<Kategoria> kategoriat = new ArrayList<>();
        assertTrue(kategoriat.isEmpty());
        kategoriat = newsSiteService.tulostaKategoriat();
        assertFalse(kategoriat.isEmpty());
    }
    
    @Test
    public void tulostaMetoditToimii() {
        List<Kategoria> kategoriat = new ArrayList<>();
        List<Kirjoittaja> kirjoittajat = new ArrayList<>();
        assertTrue(kategoriat.isEmpty());
        assertTrue(kirjoittajat.isEmpty());
        newsSiteService.uusiKategoria("Käsikirurgia");
        newsSiteService.uusiKirjoittaja("Peter Hilden");
        kategoriat = newsSiteService.tulostaKategoriat();
        kirjoittajat = newsSiteService.tulostaKirjoittajat();
//        int kasiKir = 0;
//        int peter = 0;
//        for (Kategoria k : newsSiteService.tulostaKategoriat()) {
//            if (k.getNimi().equals("Käsikirurgia")) {
//                kasiKir++;
//            }
//        }
//        assertTrue(kasiKir == 1);
//        for (Kirjoittaja k : newsSiteService.tulostaKirjoittajat()) {
//            if (k.getNimi().equals("Peter Hilden")) {
//                peter++;
//            }
//        }
//        assertTrue(peter == 1);
        assertFalse(kategoriat.isEmpty());
        assertFalse(kirjoittajat.isEmpty());
    }
    
    @Test
    public void uutisetVoidaanPrintataKategorianMukaan() {
        Uutinen u1 = new Uutinen();
        List<Kategoria> kategoriat = new ArrayList<>();
        kategoriat = newsSiteService.tulostaKategoriat();
        u1.setKategoriat(kategoriat);
        Uutinen u2 = new Uutinen();
        List<Kategoria> kategoriat2 = new ArrayList<>();
        for (Kategoria k : newsSiteService.tulostaKategoriat()) {
            if (k.getNimi().equals("Neurologia")) {
                kategoriat2.add(k);
            }
        }
        u2.setKategoriat(kategoriat2);
        int kategoriamäärä1 = 0;
        int kategoriamäärä2 = 0;
        for (Kategoria k : u1.getKategoriat()) {
            kategoriamäärä1++;
        }
        for (Kategoria k : u2.getKategoriat()) {
            kategoriamäärä2++;
        }
        
        assertTrue("Kategoriamäärän tulisi olla 7 kun se sinulla ei ollut, sinulla oli: " + kategoriamäärä1, kategoriamäärä1 == 7);
        assertTrue("Kategoriamäärän tulisi olla 1 kun se sinulla ei ollut, sinulla oli: " + kategoriamäärä2, kategoriamäärä2 == 1);
        assertTrue(u2.getKategoriat().get(0).getNimi().equals("Neurologia"));
        
        
    }
    
}
