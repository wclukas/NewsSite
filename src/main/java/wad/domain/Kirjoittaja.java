
package wad.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
public class Kirjoittaja extends AbstractPersistable<Long> {
    private String nimi;
    @ManyToMany(mappedBy = "kirjoittajat")
    private List<Uutinen> uutiset;
}
