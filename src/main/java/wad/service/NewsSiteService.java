
package wad.service;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public void teeMuutamaUutinen() {
        uutinenRepository.save(new Uutinen("Häkkinen voitti"));
        uutinenRepository.save(new Uutinen("Räikkönen voitti"));
        uutinenRepository.save(new Uutinen("Häkkinen voitti"));
        uutinenRepository.save(new Uutinen("Häkkinen voitti"));
        uutinenRepository.save(new Uutinen("Häkkinen voitti"));
    }
    
    public List<Kategoria> tulostaKategoriat() {
        return kategoriaRepository.findAll();
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
    
    
    
    
}
