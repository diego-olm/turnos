package turnosrotativos.entities;

import java.sql.Date;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter 
@Data

@Table(name="empleados")
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@Column(name="nro_documento",nullable=false)
	@NotNull(message="nroDocumento es obligatorio")
	private Number nroDocumento;
	
	
	@Column(name="nombre", nullable=false)
	@NotBlank(message="nombre es obligatorio")
	@Pattern(regexp = "[a-zA-Z]*", message = "Solo se permiten letras en el campo 'nombre'")
	private String nombre;
	
	
	@Column(name="apellido",nullable=false)
	@NotBlank(message="apellido es obligatorio")
	@Pattern(regexp ="[a-zA-Z]*", message = "Solo se permiten letras en el campo 'apellido'")
	private String apellido;
	
	@Column(name="email", nullable=false)
	@NotBlank(message="email es obligatorio")
	@Email(message="El email ingresado no es correcto")
	private String email;
	
	
	@Column(name="fecha_nacimiento", nullable=false)
	@NotNull(message="fechaNacimiento es obligatorio")
	@Past(message="La fecha de nacimiento no puede ser posterior al día de la fecha")
	private Date fechaNacimiento;
	
	@Column(name="fecha_ingreso",nullable=false)
	@NotNull(message="fechaIngreso es obligatorio")
	@Past(message="La fecha de ingreso no puede ser posterior al día de la fecha")
	private Date fechaIngreso;
	
	@Column(name="fecha_creacion")
	@CreationTimestamp
	private Date FechaCreacion;


	
}
