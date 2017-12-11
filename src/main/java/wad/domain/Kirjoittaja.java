
package wad.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@Entity
@NoArgsConstructor
public class Kirjoittaja extends AbstractPersistable<Long> {
    
    private String nimi;
    @ManyToMany(mappedBy = "kirjoittajat")
    private List<Uutinen> uutiset;
    
    public Kirjoittaja(String nimi) {
        this.uutiset = new ArrayList<>();
        this.nimi = nimi;
    }
}
