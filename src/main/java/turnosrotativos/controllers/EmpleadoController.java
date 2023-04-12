package turnosrotativos.controllers;


import java.util.List;


import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import turnosrotativos.dto.EmpleadoDTO;
import turnosrotativos.service.EmpleadoService;

@RestController("")
public class EmpleadoController {
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@PostMapping("/empleado")
	public ResponseEntity<EmpleadoDTO> crearEmpleado(@Valid @RequestBody EmpleadoDTO empleado){
		
		return new ResponseEntity<>(empleadoService.agregarEmpleado(empleado),HttpStatus.CREATED );
	}
	@GetMapping("/empleado")
	public ResponseEntity<List<EmpleadoDTO>> ObtenerEmpleados(){
		List<EmpleadoDTO> lista= empleadoService.obtenerEmpleados();
		
		
		
		return new ResponseEntity<>(lista,HttpStatus.OK);
		
		
	}
	@GetMapping("/empleado/{empleadoId}")
	public ResponseEntity<EmpleadoDTO> obtenerInformacionEmpleado(@PathVariable("empleadoId") String empleadoId){
		
		
			return new ResponseEntity<>(empleadoService.obtenerInformacionEmpleado(empleadoId),HttpStatus.OK);
		
	}
	@DeleteMapping("/empleado/{empleadoId}")
	public ResponseEntity<Object> eliminarEmpleado(@PathVariable("empleadoId") String empleadoId){
		empleadoService.eliminarEmpleado(empleadoId);
		
		
		return new ResponseEntity<>( HttpStatus.NO_CONTENT);
	}
	@PutMapping("/empleado/{empleadoId}")
	public ResponseEntity<EmpleadoDTO> actualizarEmpleado(@PathVariable("empleadoId") Integer empleadoId, @RequestBody EmpleadoDTO empleado){
		
		
		return new ResponseEntity<>(empleadoService.actualizarEmpleado(empleadoId, empleado),HttpStatus.CREATED );
	}
}
