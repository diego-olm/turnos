package turnosrotativos.serviceimp;


import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import turnosrotativos.dto.EmpleadoDTO;
import turnosrotativos.entities.Empleado;
import turnosrotativos.exceptions.EmpleadoNegocio;
import turnosrotativos.repository.EmpleadoRepository;
import turnosrotativos.service.EmpleadoService;

@Service
public class EmpleadoServiceImp implements EmpleadoService {
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public EmpleadoDTO agregarEmpleado(EmpleadoDTO empleado) {
		// TODO Auto-generated method stub
		/*
		 * cree un metodo de validacion para que este metodo un poco
		 * mas limpio y tambien por que estas mismas validaciones se utilizan 
		 * para actualizar los empleados. quiero evitar copiar y pegar el 
		 * mismo codigo en agregarEmpleado y actualizarEmpleado
		 * 
		 */
		validacionDeDatos(empleado);
		/*luego de que el empleado pase la validaciones se pasa a guardarlo,
		 * con model mapper se pasa el empleado que se recibe a entidad se lo guarda
		 * el repository retorna la entidad guardada, esa entidad se pasa a
		 * empleadoDTO y se la envia al controller 
		 */
		return modelMapper.map(empleadoRepository.save(modelMapper.map(empleado, Empleado.class)), EmpleadoDTO.class);
	}
	@Override
	public List<EmpleadoDTO> obtenerEmpleados() {
		 
		List<Empleado> listaEntidad=empleadoRepository.findAll();
		List<EmpleadoDTO> listaDto=listaEntidad.stream().
				map(empleado -> modelMapper.map(empleado,EmpleadoDTO.class))
				.collect(Collectors.toList());
		return listaDto;
	}
	@Override
	public EmpleadoDTO obtenerInformacionEmpleado(String empleadoId) {
		 /*
		  * se utiliza uno de los metodos del repository para saber si existe un empleado
		  * con el empleadoId recibido si existe se pasa la entidad a dto
		  * y se lo envia al controller, en caso de que no exista 
		  * se envia el mensaje de error con el codigo httpStatus
		  * definido en el hu 003
		  */
		if(!isNumeric(empleadoId)) {
			throw new EmpleadoNegocio("No se encontró el empleado con Id: "+ empleadoId, HttpStatus.NOT_FOUND);
		}
		 
		else if( !empleadoRepository.existsById(Integer.valueOf(empleadoId))) {
			throw new EmpleadoNegocio("No se encontró el empleado con Id: "+ empleadoId, HttpStatus.NOT_FOUND);
		}
		
			return modelMapper.map(empleadoRepository.findById(Integer.valueOf(empleadoId)).get(), EmpleadoDTO.class);
		
		
		
	}
	@Override
	public void eliminarEmpleado(String empleadoId) {
		/*
		 * se pregunta si existe un empleado con el id recibido
		 * si se cumple se lanza la excepcion correspondiente con el codigo httpStatus
		 * de los contrario se elimina el empleado 
		 */
		if(!isNumeric(empleadoId)) {
			throw new EmpleadoNegocio("No se encontró el empleado con Id: "+ empleadoId, HttpStatus.NOT_FOUND);
		}
		else if(!empleadoRepository.existsById(Integer.valueOf(empleadoId))) {
			throw new EmpleadoNegocio("No se encontró el empleado con Id: "+empleadoId,HttpStatus.NOT_FOUND);
		}
		empleadoRepository.deleteById(Integer.valueOf(empleadoId));
		
	}

	@Override
	public EmpleadoDTO actualizarEmpleado(Integer empleadoId, EmpleadoDTO empleado) {
	Optional<Empleado> existe=empleadoRepository.findById(empleadoId);
	 if(!existe.isPresent()) {
			throw new EmpleadoNegocio("No se encontró el empleado con Id: "+empleadoId,HttpStatus.NOT_FOUND);
		}
	    validacionDeDatos(empleado);
	    Empleado actualizado = modelMapper.map(empleado, Empleado.class);
	    //se hace el set del id y fecha de creacion ya que estos datos no viene 
	    //cargados en el empleado enviado
	    actualizado.setId(empleadoId);
	    actualizado.setFechaCreacion(existe.get().getFechaCreacion());
	    
	    
		return modelMapper.map(empleadoRepository.save(actualizado),EmpleadoDTO.class); 
	}
	
	public boolean esMayorDeEdad(Date fechaNacimiento) {
		boolean bandera=false;
		LocalDate hoy = LocalDate.now(); 
		Instant instant= fechaNacimiento.toInstant();
		 LocalDate nacimiento = instant.atZone(ZoneId.systemDefault()).toLocalDate(); 
		 if(Period.between(nacimiento, hoy).getYears()>=18) {
			 bandera=true;
			 
		 }

		return bandera;
	}
	public void validacionDeDatos(EmpleadoDTO empleado) {
	    
		if(!esMayorDeEdad(empleado.getFechaNacimiento())) {
	    	throw new EmpleadoNegocio("La edad del empleado no puede ser menor a 18 años.", HttpStatus.BAD_REQUEST);
	    }
	    /*
	     * se agregaron 2 metodos en el repository para saber si
	     * existe un empleado con el nroDocumento o mail en caso que se
	     * cumpla se lanza la excepciones con los codigos httpStatus correspondiente
	     */
	    if(empleadoRepository.existsByNroDocumento(empleado.getNroDocumento())) {
	    	 throw new EmpleadoNegocio("Ya existe un empleado con el documento ingresado.",HttpStatus.CONFLICT);
	    }
	    if(empleadoRepository.existsByEmail(empleado.getEmail())) {
	    	throw new EmpleadoNegocio("Ya existe un empleado con el email ingresado.", HttpStatus.CONFLICT);
	    }
	    
	}
	public boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }

}

