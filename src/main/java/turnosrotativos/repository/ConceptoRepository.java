package turnosrotativos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import turnosrotativos.entities.Concepto;

@Repository
public interface ConceptoRepository extends JpaRepository<Concepto, Integer>{

}
