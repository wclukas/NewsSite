
package wad.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.File;
import wad.domain.Kategoria;
import wad.domain.Kirjoittaja;
import wad.domain.Uutinen;
import wad.repository.FileRepository;
import wad.repository.KategoriaRepository;
import wad.repository.KirjoittajaRepository;
import wad.repository.UutinenRepository;

@Service
public class NewsSiteService {
    
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private UutinenRepository uutinenRepository;
    @Autowired
    private KategoriaRepository kategoriaRepository;
    @Autowired
    private KirjoittajaRepository kirjoittajaRepository;
    
    @PostConstruct
    public void teeKategoriat() {
        kategoriaRepository.save(new Kategoria("Neurologia"));
        kategoriaRepository.save(new Kategoria("Psykiatria"));
        kategoriaRepository.save(new Kategoria("Endokrinologia"));
        kategoriaRepository.save(new Kategoria("Neurokirurgia"));
        kategoriaRepository.save(new Kategoria("Geriatria"));
        kategoriaRepository.save(new Kategoria("Verisuonikirurgia"));
    }
    
    @PostConstruct
    public void teeKirjoittajat() {
        kirjoittajaRepository.save(new Kirjoittaja("Jani Huuhtanen"));
        kirjoittajaRepository.save(new Kirjoittaja("Peter Hilden"));
        kirjoittajaRepository.save(new Kirjoittaja("Lukas Wilenius"));
        kirjoittajaRepository.save(new Kirjoittaja("Joona Sarkkinen"));
        kirjoittajaRepository.save(new Kirjoittaja("Anni Pohjola"));
        kirjoittajaRepository.save(new Kirjoittaja("Osma Rautila"));
    }
    
    @PostConstruct
    public void teeMuutamaUutinen() {
        uutinenRepository.save(new Uutinen("Häkkinen voitti", LocalDate.of(2006, 10, 7)));
        uutinenRepository.save(new Uutinen("Häkki voitti", LocalDate.of(2004, 10, 7)));
        uutinenRepository.save(new Uutinen("Häkkinen voitti", LocalDate.of(2007, 10, 7)));
        uutinenRepository.save(new Uutinen("Hä voitti", LocalDate.of(2008, 10, 7)));
        uutinenRepository.save(new Uutinen("Hän voitti", LocalDate.of(2009, 10, 7)));
        uutinenRepository.save(new Uutinen("Lol voitti", LocalDate.of(2010, 10, 7)));
    }
    
    public List<Kategoria> tulostaKategoriat() {
        return kategoriaRepository.findAll();
    }
    
    public List<Kirjoittaja> tulostaKirjoittajat() {
        return kirjoittajaRepository.findAll();
    }
    
    public List<Uutinen> tulostaUutiset() {
        return uutinenRepository.findAll();
    }
    
    public List<Uutinen> uutinenByKategoria(Kategoria kategoria) {
        List<Uutinen> uutiset = new ArrayList<>();
        
        for (Uutinen uutinen : uutinenRepository.findAll()) {
            if (uutinen.kuuluuKategoriaan(kategoria)) {
                uutiset.add(uutinen);
            }
        }
        return uutiset;
    }
    
    public Kategoria getKategoria(Long id) {
        return kategoriaRepository.getOne(id);
    }
    
    public Uutinen getUutinen(Long id) {
        return uutinenRepository.getOne(id);
    }
    
    public Kirjoittaja getKirjoittaja(Long id) {
        return kirjoittajaRepository.getOne(id);
    }
    
    public Uutinen uusiUutinen(String otsikko, String ingressi, String teksti, File file) {
        Uutinen uutinen = new Uutinen();
        uutinen.setOtsikko(otsikko);
        uutinen.setIngressi(ingressi);
        uutinen.setTeksti(teksti);
        uutinen.setJulkaisuAika(LocalDate.now());
        uutinen.setKuva(file);
        uutinenRepository.save(uutinen);
        return uutinen;
    }
    
    public File getKuva(Long id) {
        Uutinen uutinen = uutinenRepository.getOne(id);
        return uutinen.getKuva();
    }
    
    public void uusiKategoria(String nimi) {
        Kategoria kategoria = new Kategoria();
        kategoria.setNimi(nimi);
        kategoriaRepository.save(kategoria);
    }
    
    public void uusiKirjoittaja(String nimi) {
        Kirjoittaja kirjoittaja = new Kirjoittaja();
        kirjoittaja.setNimi(nimi);
        kirjoittajaRepository.save(kirjoittaja);
    }
    
    public void kirjoittajatJaKategoriatUutiselle(Uutinen uutinen, List<Long> kategoriat, List<Long> kirjoittajat) {
        List<Kategoria> kat = new ArrayList<>();
        List<Kirjoittaja> kir = new ArrayList<>();
        for (Long l : kategoriat) {
            kat.add(kategoriaRepository.getOne(l));
        }
        for (Long l : kirjoittajat) {
            kir.add(kirjoittajaRepository.getOne(l));
        }
        
        uutinen.setKategoriat(kat);
        uutinen.setKirjoittajat(kir);
        
        uutinenRepository.save(uutinen);
    }
    
}
