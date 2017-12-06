
package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wad.domain.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long>{
    
}
