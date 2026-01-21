import 'package:flutter/material.dart';
import 'package:menu_dash/screens/apijson_screen.dart';
import 'package:menu_dash/screens/basket_screen.dart';
import 'package:menu_dash/screens/disenios_screen.dart';
import 'package:menu_dash/screens/dog_search_screen.dart';
import 'package:menu_dash/screens/lector_qr_screen.dart';
import 'package:menu_dash/screens/menu_screen.dart';
import 'package:menu_dash/screens/simpsons_screen.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      routes: {
        '/': (context) => MenuScreen(),
        'api1': (context) => ApiJsonPlaceUsersScreen(),
        'simpsons': (context) => SimpsonsScreen(),
        'estilos': (context) => DiseniosScreen(),
        'baloncesto': (context) => BasketScreen(),
        'practica10': (context) => DogSearchScreen(),
        'lectorqr': (context) => LectorQrScreen(),
      },
    );
  }
}
