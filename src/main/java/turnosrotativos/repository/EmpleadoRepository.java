package turnosrotativos.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import turnosrotativos.entities.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository <Empleado, Integer> {

	
	
	public boolean existsByEmail(String email);
	public boolean existsByNroDocumento(Number nroDocumento);
}
