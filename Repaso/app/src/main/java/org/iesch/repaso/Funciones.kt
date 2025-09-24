package org.iesch.repaso
import android.icu.number.Scale


var numero1 = 23;
var numero2=34;


fun saludar(nombre: String): String{
    return "Hola me llamo $nombre";
}
fun sumar(num1: Int, num2: Int): Int{
    return num1+num2;
}
fun main() {

    //El nombre de las funciones siempre es camelCase

    /*
    * var nombre: String = "Pablo";
    println(saludar(nombre));*/


    println("El resultado es: ${sumar(numero1,numero2)}")
}
