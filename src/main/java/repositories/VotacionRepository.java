
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Votacion;

@Repository
public interface VotacionRepository extends JpaRepository<Votacion, Integer> {

}
