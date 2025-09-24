package org.iesch.repaso

var diasSemana= arrayOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo");


fun main() {
    diasSemana[2]= "Hoy es Miercoles!!!"

    //Recorrer Arrays

    //La mas SIMPLE Y LA QUE MAS VAMOS A USAR
    for(dia in diasSemana){
        println(dia)
    }
    // Esta es otra opcion
    println("----------------------------------------------------------")
    for (position in diasSemana.indices){
        println(diasSemana[position])
    }
    //Esta te vale para saber el numero del indice t el valor real que tiene en el Array
    println("----------------------------------------------------------")
    for ((position,value )in diasSemana.withIndex()){
        println("La posicion es $position contiene $value");
    }
    println("----------------------------------------------------------")
    //Vamos a usar el Fore ach

    //Se suele usar el it (diasSemana.forEach { it })
    // Pero para saber lo que estamos recorriendo le podemos cambiar el nombre como se hace abajo  dia-> println(dia)
    diasSemana.forEach { dia-> println(dia) }
}