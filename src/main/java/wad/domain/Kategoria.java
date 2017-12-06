
package wad.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@NoArgsConstructor
@Entity
public class Kategoria extends AbstractPersistable<Long>{
    
    private String nimi;
    @ManyToMany(mappedBy = "kategoriat")
    private List<Uutinen> uutiset;
    
    public Kategoria(String nimi) {
        this.nimi = nimi;
    }
    
   
}
