import 'package:flutter/material.dart';

class MarcadorProvider extends ChangeNotifier {
  int local = 0;
  int visitante = 0;

  void sumarLocal(int puntos) {
    local += puntos;
    notifyListeners();
  }

  void sumarVisitante(int puntos) {
    visitante += puntos;
    notifyListeners();
  }

  void reset() {
    local = 0;
    visitante = 0;
    notifyListeners();
  }
}