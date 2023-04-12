package turnosrotativos.service;

import java.util.List;

import turnosrotativos.dto.EmpleadoDTO;


public interface EmpleadoService {

	public EmpleadoDTO agregarEmpleado(EmpleadoDTO empleado);
	public List<EmpleadoDTO> obtenerEmpleados();
	public EmpleadoDTO obtenerInformacionEmpleado(String empleadoId);
	public void eliminarEmpleado(String empleadoId);
	public EmpleadoDTO actualizarEmpleado(Integer empleadoId, EmpleadoDTO empleado);
	
}
