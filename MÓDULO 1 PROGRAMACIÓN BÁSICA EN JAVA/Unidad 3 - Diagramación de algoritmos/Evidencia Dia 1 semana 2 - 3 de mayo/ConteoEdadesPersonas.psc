Algoritmo contador_personas
	cantPersonas <- 10
	Dimension edadesPersonas[cantPersonas]
	cont <- 1
	personasMayoresEdad <- 0
	personasMenoresEdad <- 0
	Mientras cont<=cantPersonas Hacer
		Escribir 'Ingrese la edad de la persona ',cont
		Leer edadesPersonas[cont]
		cont <- cont+1
	FinMientras
	cont <- 1
	Mientras cont<=cantPersonas Hacer
		Si edadesPersonas[cont]<18 Entonces
			personasMenoresEdad <- personasMenoresEdad+1
		SiNo
			personasMayoresEdad <- personasMayoresEdad+1
		FinSi
		cont <- cont+1
	FinMientras
	Escribir 'Personas Menores de edad: ',personasMenoresEdad
	Escribir 'Personas mayores de edad: ',personasMayoresEdad
FinAlgoritmo
