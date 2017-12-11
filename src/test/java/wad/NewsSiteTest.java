
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        int kasiKir = 0;
        int peter = 0;
        for (Kategoria k : newsSiteService.tulostaKategoriat()) {
            if (k.getNimi().equals("Käsikirurgia")) {
                kasiKir++;
            }
        }
        for (Kirjoittaja k : newsSiteService.tulostaKirjoittajat()) {
            if (k.getNimi().equals("Peter Hilden")) {
                peter++;
            }
        }
        
        assertTrue(peter == 1);
        assertTrue(kasiKir == 1);
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
        
//        assertTrue(kategoriamäärä1 == 6, "Kategoriamäärän olisi kuulunut olla 6, kun se sinulla oli: " + kategoriamäärä1);
        assertTrue(kategoriamäärä2 == 1);
        assertTrue(u2.getKategoriat().get(0).getNimi().equals("Neurologia"));
        
        
    }
    
}
