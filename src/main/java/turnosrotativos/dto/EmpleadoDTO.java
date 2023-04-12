package turnosrotativos.dto;



import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

@Component
public class EmpleadoDTO {

	
	private Integer id;
	
	
	
	@NotNull(message="nroDocumento es obligatorio")
	private Number nroDocumento;
	
	
	
	@NotBlank(message="nombre es obligatorio")
	@Pattern(regexp = "[a-zA-Z]*", message = "Solo se permiten letras en el campo 'nombre'")
	private String nombre;
	
	
	
	@NotBlank(message="apellido es obligatorio")
	@Pattern(regexp ="[a-zA-Z]*", message = "Solo se permiten letras en el campo 'apellido'")
	private String apellido;
	
	
	@NotBlank(message="email es obligatorio")
	@Email(message="El email ingresado no es correcto")
	private String email;
	
	
	
	@NotNull(message="fechaNacimiento es obligatorio")
	@Past(message="La fecha de nacimiento no puede ser posterior al día de la fecha")
	private Date fechaNacimiento;
	
	
	@NotNull(message="fechaIngreso es obligatorio")
	@Past(message="La fecha de ingreso no puede ser posterior al día de la fecha")
	private Date fechaIngreso;
	
	
	
	private Date FechaCreacion;
}
