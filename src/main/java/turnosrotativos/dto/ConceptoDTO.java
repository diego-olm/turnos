package turnosrotativos.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ConceptoDTO {
	private Integer id;
	private String nombre;
	private Boolean laborable;
	@JsonInclude(Include.NON_NULL)
	private Integer hsMinimo;
	@JsonInclude(Include.NON_NULL)
	private Integer hsMaximo;
}
