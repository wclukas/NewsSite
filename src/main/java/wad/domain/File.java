
package wad.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class File extends AbstractPersistable<Long>{
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;
    
}
