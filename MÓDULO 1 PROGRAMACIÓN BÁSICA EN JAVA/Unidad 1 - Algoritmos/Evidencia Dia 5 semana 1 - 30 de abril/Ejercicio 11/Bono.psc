Algoritmo bono_clase_media
	Leer remA�oAnt1
	Leer remA�oAnt2
	Leer remA�oAnt3
	Leer remA�oAnt4
	Leer remA�oAnt5
	Leer remA�oAnt6
	Leer remA�oAct1
	Leer remA�oAct2
	Leer remA�oAct3
	Leer remA�oAct4
	Leer remA�oAct5
	Leer remA�oAct6
	sumRemA�oAnt <- remA�oAnt1+remA�oAnt2+remA�oAnt3+remA�oAnt4+remA�oAnt5+remA�oAnt6
	sumRemA�oAct <- remA�oAct1+remA�oAct2+remA�oAct3+remA�oAct4+remA�oAct5+remA�oAct6
	promA�oAnt <- sumRemA�oAnt/6
	promA�oAct <- sumRemA�oAct/6
	Si promA�oAnt>promA�oAct Entonces
		diferencia <- promA�oAnt-promA�oAct
		disminucion <- (diferencia/promA�oAnt)*100
		Si disminucion>=20 Entonces
			Escribir 'Eres acreedor de un bono por $500.00'
		SiNo
			Escribir 'No es acreedor de un bono por $500.00'
		FinSi
	SiNo
		Escribir 'No es acreedor de un bono por $500.00'
	FinSi
FinAlgoritmo
