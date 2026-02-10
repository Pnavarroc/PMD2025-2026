import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class AppTheme {
  static const listaColores = [
    Colors.amberAccent,
    Colors.lightBlue,
    Colors.teal,
    Colors.redAccent,
    Colors.purpleAccent,
    Colors.deepOrangeAccent,
    Colors.pinkAccent,
    Colors.purple,
    Colors.greenAccent,
    Colors.lightGreenAccent,
  ];

  //Tema para mi App
  ThemeData getTheme() => ThemeData(
    textTheme: TextTheme(
      titleLarge: GoogleFonts.montserratAlternates(),
      titleMedium: GoogleFonts.montserratAlternates(fontSize: 35),
      titleSmall: GoogleFonts.montserratAlternates(fontSize: 25),
      bodyLarge: GoogleFonts.montserratAlternates(),
      bodyMedium: GoogleFonts.montserratAlternates(),
      bodySmall: GoogleFonts.montserratAlternates(),
    ),
  );
}
