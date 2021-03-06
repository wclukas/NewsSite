
package wad.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
@Data
public class Uutinen extends AbstractPersistable<Long>{
    
    private String otsikko;
    private String ingressi;
    private String teksti;
    @ManyToMany
    private List<Kirjoittaja> kirjoittajat;
    @ManyToMany
    private List<Kategoria> kategoriat;
    private LocalDate julkaisuAika;
    @OneToOne
    private File kuva;
    
//    Tälle pitäisi saada muuttuja tyypiltään File!
    
    
    public Uutinen() {
        this.kirjoittajat = new ArrayList<>();
        this.kategoriat = new ArrayList<>();
    }
    
    public Uutinen(String otsikko, LocalDate julkaisuAika) {
        this.otsikko = otsikko;
        this.julkaisuAika = julkaisuAika;
        this.kirjoittajat = new ArrayList<>();
        this.kategoriat = new ArrayList<>();
    }
    
    public boolean kuuluuKategoriaan(Kategoria kategoria) {
        if (kategoriat.contains(kategoria)) {
            return true;
        }
        return false;
    }
    
}
