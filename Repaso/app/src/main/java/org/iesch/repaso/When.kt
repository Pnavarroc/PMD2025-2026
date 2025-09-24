package org.iesch.repaso

import kotlin.random.Random


fun main() {
   //conseguirMes(3)
    println()
    //conseguirTrimestre(5)

    resultado(true)


}

fun conseguirMes(mes: Int){
    when (mes) {
        1 -> print("Es Enero")
        2-> print("Es Febrero")
        3-> print("Es Marzo")
        4-> print("Es Abril")
        5-> print("Es Mayo")
        6-> print("Es Junio")
        7-> print("Es Julio")
        8-> print("Es Agosto")
        9-> print("Es Septiembre")
        10-> print("Es Octubre")
        11-> print("Es Noviembre")
        12-> print("Es Diciembre")

        else -> print("Entrada inválida")
    }
}

fun conseguirTrimestre(mes: Int){

    when (mes){
        1,2,3 -> print("Primer Trimestre")
        4,5,6 -> print("Segundo Trimestre")
        7,8,9 -> print("Tercer Trimestre")
        10,11,12 -> print("Cuarto Trimestre")
        else -> ("Mes incorrecto")
    }

}

fun conseguirSemestre(mes: Int){

    when (mes){
        in 1..6-> print("Primer Semestre")
        in 7..12-> print("Segundo Semestre")
        else -> print("Mes Incorrecto")
    }
}

fun resultado(valor: Any){

    when(valor){
        is Int -> print(valor)
        is String-> println(valor)
        is Boolean-> if (valor)print("Es booleano y vale true")



    }
}