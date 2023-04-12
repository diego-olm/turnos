package turnosrotativos.serviceimp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import turnosrotativos.dto.ConceptoDTO;
import turnosrotativos.dto.EmpleadoDTO;
import turnosrotativos.entities.Concepto;
import turnosrotativos.entities.Empleado;
import turnosrotativos.repository.ConceptoRepository;
import turnosrotativos.service.ConceptoService;

@Service
public class ConceptoServiceImp implements ConceptoService{

	@Autowired
	private ConceptoRepository conceptoRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public List<ConceptoDTO> obtenerConcepto() {
		List<Concepto> listaConcepto= conceptoRepository.findAll();
		
		return  listaConcepto.stream().
				map(p -> modelMapper.map(p, ConceptoDTO.class))
				.collect(Collectors.toList());
	}

}
