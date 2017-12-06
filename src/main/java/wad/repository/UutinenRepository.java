
package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wad.domain.Kategoria;
import wad.domain.Uutinen;

@Repository
public interface UutinenRepository extends JpaRepository<Uutinen, Long>{
    
}
