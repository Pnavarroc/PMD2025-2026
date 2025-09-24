package org.iesch.repaso


fun main() {
    listasMutables()
}

fun listasMutables(){
    val diasSemana: MutableList<String> = mutableListOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo")
    //println(diasSemana[5]) Aqui solo enseña el dia de la semana en el puesto 5

    //diasSemana.forEach { dia->println(dia) } Recorrer la lista

    /*
    * if (diasSemana.isEmpty()){
        println("La lista esta Vacia")
    }else diasSemana.forEach { dia->println(dia);}

    if (diasSemana.isNotEmpty()){
        println("La lista esta completa, O por lo menos hay algo dentro")
    }*/

    //println(diasSemana.last()) saca el ultimo de la lista , igual si ponemos first :diasSemana.first()

    //Tambien podemos filtrar en listas

    println(diasSemana.filter { it.startsWith('L')});

    //Añadir elementos a una lista : Debe ser mutable , sino saltara excepción

    diasSemana.add(2,"Chomonero")



    println(diasSemana)
}