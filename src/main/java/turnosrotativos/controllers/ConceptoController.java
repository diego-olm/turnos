package turnosrotativos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import turnosrotativos.dto.ConceptoDTO;
import turnosrotativos.service.ConceptoService;

@RestController("")
public class ConceptoController {

	@Autowired
	private ConceptoService conceptoService;
	@GetMapping("/concepto")
	public ResponseEntity<List<ConceptoDTO>> obtenerConcepto(){
		
		
		return new ResponseEntity<>(conceptoService.obtenerConcepto(),HttpStatus.OK);
		
	}
}
