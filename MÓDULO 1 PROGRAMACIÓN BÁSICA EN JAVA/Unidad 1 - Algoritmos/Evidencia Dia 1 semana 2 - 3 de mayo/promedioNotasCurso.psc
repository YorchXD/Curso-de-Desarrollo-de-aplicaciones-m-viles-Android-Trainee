Algoritmo promedio_estudiantes
	estudiantes <- 5
	notas <- 3
	Dimension matrizNotas[estudiantes,notas]
	filaEstudiante <- 1
	// Ciclo para obtener las notas de cada estudiante
	Mientras filaEstudiante<=estudiantes Hacer
		columnaNota <- 1
		Mientras columnaNota<=notas Hacer
			Escribir 'Ingrese la nota ',columnaNota,' del estudiante ',filaEstudiante Sin Saltar
			Leer matrizNotas[filaEstudiante,columnaNota]
			columnaNota <- columnaNota+1
		FinMientras
		filaEstudiante <- filaEstudiante+1
	FinMientras
	filaEstudiante <- 1
	// Ciclo para mostrar las notas de los estudiantes como matriz
	Mientras filaEstudiante<=estudiantes Hacer
		columnaNota <- 1
		Escribir 'Estudiante ',filaEstudiante,': ' Sin Saltar
		Mientras columnaNota<=notas Hacer
			Escribir matrizNotas[filaEstudiante,columnaNota],', ' Sin Saltar
			columnaNota <- columnaNota+1
		FinMientras
		filaEstudiante <- filaEstudiante+1
		// Salto de linea
		Escribir ''
	FinMientras
	filaEstudiante <- 1
	// Ciclo que calcula los promedios de cada estudiante
	Mientras filaEstudiante<=estudiantes Hacer
		columnaNota <- 1
		sumaNotas <- 0
		Mientras columnaNota<=notas Hacer
			sumaNotas <- sumaNotas+matrizNotas[filaEstudiante,columnaNota]
			columnaNota <- columnaNota+1
		FinMientras
		promedio <- sumaNotas/notas
		Escribir 'Promedio estudiante ',filaEstudiante,': ',promedio
		filaEstudiante <- filaEstudiante+1
	FinMientras
FinAlgoritmo
