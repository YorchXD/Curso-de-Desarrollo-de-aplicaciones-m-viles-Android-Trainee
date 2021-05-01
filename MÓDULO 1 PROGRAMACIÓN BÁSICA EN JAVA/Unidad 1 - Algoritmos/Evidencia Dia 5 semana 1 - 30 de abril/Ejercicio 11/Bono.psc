Algoritmo bono_clase_media
	Leer remAñoAnt1
	Leer remAñoAnt2
	Leer remAñoAnt3
	Leer remAñoAnt4
	Leer remAñoAnt5
	Leer remAñoAnt6
	Leer remAñoAct1
	Leer remAñoAct2
	Leer remAñoAct3
	Leer remAñoAct4
	Leer remAñoAct5
	Leer remAñoAct6
	sumRemAñoAnt <- remAñoAnt1+remAñoAnt2+remAñoAnt3+remAñoAnt4+remAñoAnt5+remAñoAnt6
	sumRemAñoAct <- remAñoAct1+remAñoAct2+remAñoAct3+remAñoAct4+remAñoAct5+remAñoAct6
	promAñoAnt <- sumRemAñoAnt/6
	promAñoAct <- sumRemAñoAct/6
	Si promAñoAnt>promAñoAct Entonces
		diferencia <- promAñoAnt-promAñoAct
		disminucion <- (diferencia/promAñoAnt)*100
		Si disminucion>=20 Entonces
			Escribir 'Eres acreedor de un bono por $500.00'
		SiNo
			Escribir 'No es acreedor de un bono por $500.00'
		FinSi
	SiNo
		Escribir 'No es acreedor de un bono por $500.00'
	FinSi
FinAlgoritmo
