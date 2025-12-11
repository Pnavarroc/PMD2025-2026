import 'package:flutter/material.dart';

//Este provider va a manejar el contador de la página 1
//ChangeNotifier es una clase que proporciona notificaciones a los widgets cuando el estado cambia
class ContadorProvider extends ChangeNotifier {
  int _contador = 0;
  //Cuando tenemos un estado con provider hemos de poder hacer dos cosas:
  //-1. poder leer el valor de ese estado
  //-2. poder modificar el valor de ese estado
  int get contador => _contador; //{return _contador} es lo mismo que ese lambda

  void incrementar() {
    _contador++;
    notifyListeners();
  }

  void decrementar() {
    _contador--;
    notifyListeners();
  }
}
