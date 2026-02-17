import 'package:flutter/material.dart';


class MarcadorProvider extends ChangeNotifier{
  int _marcadorLocal =0;
  int _marcadorVisitante =0;

  int get marcadorLocal => _marcadorLocal;
  int get marcadorVisitante => _marcadorVisitante;

  void incrementarLocal(){
    _marcadorLocal++;
    notifyListeners();
  }
  void incrementarVisitante(){
    _marcadorVisitante++;
    notifyListeners();
  }

  void resetearMarcadores(){
    _marcadorLocal=0;
    _marcadorVisitante=0;
    notifyListeners();
  }




}