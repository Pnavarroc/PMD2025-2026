import 'package:flutter/material.dart';
import 'package:menu_dash/widgets/header_widget.dart';
import 'package:menu_dash/widgets/lista_personajes_widget.dart';

class DiseniosScreen extends StatelessWidget {
   
  const DiseniosScreen({Key? key}) : super(key: key);
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Color.fromARGB(255, 16, 16, 16),
      body: Column(
        children: [
          HeaderWidget(),
          ListaPersonajesWidget(),
        ],
      )
    );
  }
}