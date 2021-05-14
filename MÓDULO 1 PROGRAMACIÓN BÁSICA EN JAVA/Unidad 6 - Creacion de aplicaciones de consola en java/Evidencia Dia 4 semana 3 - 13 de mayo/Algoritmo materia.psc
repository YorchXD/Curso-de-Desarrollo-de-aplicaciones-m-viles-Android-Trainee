Funcion contenidos()
	//Curso
	//Seleccionar unidad
	//Ingresar contenido
	//Verificar que el contenido no se encuentre registrado
FinFuncion

Funcion actividad()
	//Curso
	//Seleccionar unidad
	//Ingresar actividad
	//Verificar que la actividad no se encuentre registrada
	//Fecha de evaluacion
	//ingresar porcentaje de evaluacion
FinFuncion

Funcion notas()
	//Curso
	//Seleccionar unidad
	//Seleccionar actividad
	//Ingresar notas de alumnos
FinFuncion

Funcion asistencia()
	//Curso
	//ingresar fecha
	//ingresar asstencia del alumno
FinFuncion


Funcion menu()
	Escribir "#####################Menu de la materia#####################"
	Escribir "# 1. Ingresar contenidos                                   #"
	Escribir "# 2. Ingresar actividad                                    #"
	Escribir "# 3. Ingresar notas                                        #"
	Escribir "# 4. Ingresar asistencia                                   #"
	Escribir "# 5. Salir                                                 #"
	Escribir "############################################################"
	Escribir ""
	Escribir Sin Saltar "Ingrese su opcion: "
Fin Funcion

Algoritmo main
	Definir opc Como Entero;
	Repetir
		menu();
		Leer opc;
		Segun opc Hacer
			1:
				Escribir "Ingresar contenidos"
			2:
				Escribir "Ingresar actividad"
			3:
				Escribir "Ingresar notas"
			4: 
				Escribir "Ingresar Asistencia"
			5:
				Escribir "Salir"
			De Otro Modo:
				Escribir "Opcion no valida"
		Fin Segun
	Hasta Que opc==5
	
FinAlgoritmo
