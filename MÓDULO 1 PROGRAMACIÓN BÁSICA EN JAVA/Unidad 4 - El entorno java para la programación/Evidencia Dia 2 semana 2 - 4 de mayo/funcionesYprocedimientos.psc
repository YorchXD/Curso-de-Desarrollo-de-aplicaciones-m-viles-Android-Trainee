//Funciones 
Funcion respuesta <- suma(a,b)
	respuesta <- a+b
FinFuncion

Funcion respuesta <- resta(a,b)
	respuesta <- a-b
FinFuncion

//Procedimiento
Funcion menu()
	Escribir "Menú de opciones: "
	Escribir "1. Sumar"
	Escribir "2. Restar"
	Escribir "3. Salir"
	Escribir Sin Saltar "Ingrese su opción: "
FinFuncion

//Funcion principal
Algoritmo ejemplo
	a <- 0
	b <- 0
	tipoTarea<-0
	respuesta <- 0
	Escribir "Calculadora para sumar o restar 2 numeros"
	menu()
	Leer tipoTarea
	
	Mientras tipoTarea<1 o tipoTarea>3 Hacer
		Escribir "La opción ingresada no es valida."
		menu()
		Leer tipoTarea
	FinMientras
	
	Si tipoTarea==1 Entonces
		Escribir sin saltar "Ingrese el valor de a: "
		Leer a
		Escribir sin saltar "Ingrese el valor de b: "
		Leer b
		respuesta <- suma(a,b)
		Escribir a,'+',b,'=',respuesta
	SiNo
		Si tipoTarea==2 Entonces
			Escribir sin saltar "Ingrese el valor de a: "
			Leer a
			Escribir sin saltar "Ingrese el valor de b: "
			Leer b
			respuesta <- resta(a,b)
			Escribir a,'-',b,'=',respuesta
		SiNo
			Escribir "Ha salido del programa. Adios!!!"
		FinSi
	FinSi
FinAlgoritmo
