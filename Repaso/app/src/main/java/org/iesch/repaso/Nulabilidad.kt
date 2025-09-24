package org.iesch.repaso

fun main() {
    var nombre: String?=null;

    print(nombre?.get(22) ?: "Es nulo");

}