package turnosrotativos.exceptions;

import org.springframework.http.HttpStatus;
public class EmpleadoNegocio extends RuntimeException{

	private HttpStatus status;
	/*
	 * se le agrego el atributo status con la intencion de que
	 * cuando se envie un mensaje personalizado tambien se envie el codigo
	 * httpstatus correspondiente ya que no todos los mensajes tiene el mismo
	 * codigo htpp y esto me permite que el apiExceptionHandler levante el 
	 * mensaje + el codigo httpStatus
	 */
	public EmpleadoNegocio (String massege, HttpStatus status) {
		super(massege);
		this.status=status;
	}
	public HttpStatus getStatus() {
		return status;
	}

	
}
