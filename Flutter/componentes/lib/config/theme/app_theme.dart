import 'package:flutter/material.dart';

const listaDeColores = <Color>[
  Colors.blue,
  Colors.indigo,
  Colors.deepPurple,
  Colors.pink,
  Colors.yellow,
  Colors.green,
];

class AppTheme {
  final int colorSeleccionado;

  AppTheme({required this.colorSeleccionado});

  ThemeData obtenerTema() => ThemeData(
    useMaterial3: true,
    colorSchemeSeed: listaDeColores[colorSeleccionado],

    appBarTheme: AppBarTheme(centerTitle: true),
  );
}
