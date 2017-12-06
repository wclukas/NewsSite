
package wad.service;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Kategoria;
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
        kategoriaRepository.save(new Kategoria("Urheilu"));
        kategoriaRepository.save(new Kategoria("Kotimaa"));
        kategoriaRepository.save(new Kategoria("Ulkomaat"));
        kategoriaRepository.save(new Kategoria("Talous"));
        kategoriaRepository.save(new Kategoria("Lääketiede"));
        kategoriaRepository.save(new Kategoria("Viihde"));
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
    
    public List<Uutinen> uutinenByKategoria(Kategoria kategoria) {
        List<Uutinen> uutiset = new ArrayList<>();
        uutiset = uutinenRepository.findAll();
        for (int i = 0; i < uutiset.size(); i++) {
            if (!uutiset.get(i).kuuluuKategoriaan(kategoria)) {
                uutiset.remove(i);
            }
        }
        return uutiset;
    }
    
    public Kategoria getKategoria(Long id) {
        return kategoriaRepository.getOne(id);
    }
    
    
    
    
}
