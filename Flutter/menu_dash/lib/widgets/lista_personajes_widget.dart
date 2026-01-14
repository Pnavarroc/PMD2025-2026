import 'package:flutter/material.dart';

class ListaPersonajesWidget extends StatefulWidget {
  const ListaPersonajesWidget({super.key});

  @override
  State<ListaPersonajesWidget> createState() => _ListaPersonajesWidgetState();
}

class _ListaPersonajesWidgetState extends State<ListaPersonajesWidget> {
  final tituloStyleText = TextStyle(
    fontSize: 30,
    fontWeight: FontWeight.bold,
    color: Colors.white,
  );

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: ListView(
        padding: EdgeInsets.all(20),
        children: [
          Text("Personajes One Piece", style: tituloStyleText),
          Row(
            children: [
              Column(
                children: [
                  ClipRRect(
                    borderRadius: BorderRadius.circular(10),
                    child: Image.asset(
                      "assets/p1.jpg",
                      width: 200,
                      height: 110,
                      fit: BoxFit.cover,
                    ),
                  ),
                ],
              ),
            ],
          ),
        ],
      ),
    );
  }
}
