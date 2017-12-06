
package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wad.domain.Kategoria;

@Repository
public interface KategoriaRepository extends JpaRepository<Kategoria, Long>{
    
}
