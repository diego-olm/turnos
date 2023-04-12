<h1>turnosRotativos</h1>
<p>
turnos rotativos es una api rest que permite realizar algunas funcionalidades como crear un empleado, obtener empleados, obtener informacion de un empleado, eliminar empleados y Obtener Conceptos.
</p>

<b>los empleados cuentan con los siguientes datos:</b>
id Integer 
nroDocumento Integer required
nombre String required
apellido String required
email  String required
fechaNacimiento date required
fechaIngreso date required
fechaCreacion date


<b>los conceptos cuentas con los siguientes datos:</b>
id Integer
nombre String
hsminimo Integer
hsMaximo Integer
laborable boolean
<h2>
debajo se explicara como realizar las distantas peticiones para poder acceder a la base de datos
</h2>

[TOC]

#CrearEmpleado
<p>
crear empleado permite agregar empleados a la base de datos bajo las siguientes reglas que debe cumplir el body 
- la edad del empleado no puede ser menor a 18
- no puede existir en la base de datos un empleado con el mismo dni
- no puede existir en la base de datos un empleado con el mismo email
- la fecha de ingreso no puede ser posterior al día de la fecha
- la fecha de nacimiento no puede ser posterior al día de la fecha
- el email debe cumplir con el formato estándar de mail
- Solo se permiten letras en los campos nombre y apellido
- enviar enviar todos los campos obligatorios 
</p>


- punto de acceso url: http://localhost:8080/empleado
- tipo de request: Post
- ejemplo de body :
{
  "nroDocumentro": 30415654,
  "nombre": "German",
  "apellido": "Zotella",
  "email": "gzotella@gmail.com",
  "fechaNacimiento": "1998-08-06",
  "fechaIngreso": "2019-06-04"
}
- response:
{
  "id": 1,
  "nroDocumentro": 30415654,
  "nombre": "German",
  "apellido": "Zotella",
  "email": "gzotella@gmail.com",
  "fechaNacimiento": "1998-08-06",
  "fechaIngreso": "2019-01-01",
  "fechaCreacion": "2023-02-22"
}
#ObtenerEmpleados
<p>
obtener empleados retorna una lista de todos los empleados en caso de que no exista ninguna retorna una lista vacia
</p>
- punto de acceso url: http://localhost:8080/empleado
- tipo de request: get
- ejemplo response [
  {
    "id": 1,
    "nroDocumentro": 30415654,
    "nombre": "German",
    "apellido": "Zotella",
    "email": "gzotella@gmail.com",
    "fechaNacimiento": "1998-08-06",
    "fechaIngreso": "2019-06-04",
    "fechaCreacion": "2023-02-22"
  },
  {
    "id": 2,
    "nroDocumentro": 3565454321,
    "nombre": "Jorge",
    "apellido": "Rolon",
    "email": "jrolon@gmail.com",
    "fechaNacimiento": "1998-08-06",
    "fechaIngreso": "2019-06-04",
    "fechaCreacion": "2023-02-22"
  }
]
#ObtenerInformacionEmpleado
obtener informacion empleado retorna la informacion de un empleado especifico por id en caso de que no exista el empleado se devolvera un error

- punto de acceso url: http://localhost:8080/empleado/empleadoId
- tipo de request: get
- ejemplo response: {
  "id": 1,
  "nroDocumentro": 30415654,
  "nombre": "German",
  "apellido": "Zotella",
  "email": "gzotella@gmail.com",
  "fechaNacimiento": "1998-08-06",
  "fechaIngreso": "2019-06-04",
  "fechaCreacion": "2023-02-22"
}
#ObtenerConceptos
obtener conceptos retorna una lista de conceptos 
- punto de acceso url: http://localhost:8080/Concepto
- tipo de request: get
- ejemplo response:
[
  {
    "id": 1,
    "nombre": "Turno Normal",
    "hsMinimo": 6,
    "hsMaximo": 8,
    "laborable": true
  },
  {
    "id": 2,
    "nombre": "Turno Extra",
    "hsMinimo": 2,
    "hsMaximo": 6,
    "laborable": true
  },
  {
    "id": 3,
    "nombre": "Día Libre",
    "hsMinimo": null,
    "hsMaximo": null,
    "laborable": true
  }
]
#eliminarEmpleado
eliminar empleado permite dar de baja un empleado en caso de que no exista se lanza un error
- punto de acceso url: http://localhost:8080/empleado/empleadoId
- tipo de request: delete
