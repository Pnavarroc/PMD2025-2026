import 'package:flutter/material.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {

    final opciones = [
  {
    "titulo": "Marcador de futbol",
    "icono": Icons.scoreboard_outlined,
    "color": Color.fromARGB(255, 90, 118, 243),
    "ruta": "marcador"
  },
  {
    "titulo": "Tareas",
    "icono": Icons.task,
    "color": Color.fromARGB(255, 249, 163, 120),
    "ruta": "tareas"
  },
  {
    "titulo": "Api",
    "icono": Icons.api,
    "color": Color.fromARGB(255, 155, 242, 140),
    "ruta": "api"
  },
  {
    "titulo": "Pizzas",
    "icono": Icons.local_pizza,
    "color": Color.fromARGB(255, 207, 255, 63),
    "ruta": "pizzas"
  },
  {
    "titulo": "Shared",
    "icono": Icons.share,
    "color": Color.fromARGB(255, 92, 43, 79),
    "ruta": "shared"
  },
];
    return Scaffold(
      appBar: AppBar(title: Text("Aplicaciones demo"),),
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(12.0),
          child: Column(
            children: [
              Text("Elige una aplicacion para usar:",
              style: TextStyle(
                fontSize: 20,
                fontWeight: FontWeight.bold
              )
              ,),
              SizedBox(height: 20,),
              Expanded(
                child: GridView.builder(
                  gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                    crossAxisCount: 2,
                  ),
                  itemCount: opciones.length,
                  itemBuilder: (BuildContext context, int index) {
                    final opcion=opciones[index];
                    return SizedBox(
                        height: 120,
                        child: GestureDetector(
                          onTap: () => Navigator.pushNamed(context, opcion["ruta"]as String),
                          child: Card(
                            color: opcion["color"] as Color,
                            elevation: 4,
                            child: Column(
                              mainAxisSize: MainAxisSize.min,
                              children: [
                                ListTile(
                                  leading: Icon(opcion["icono"] as IconData) ,
                                  title: Text(opcion["titulo"] as String )  ,
                                  
                                )
                              ],
                            )
                          ),
                        ),
                      );
                  },
                ),
              ),
              
            ],
          ),
        ),
      )
    );
  }
}


/*Expanded(
                      child: SizedBox(
                        height: 120,
                        child: GestureDetector(
                          onTap: () => Navigator.pushNamed(context, 'marcador'),
                          child: Card(
                            color: const Color.fromARGB(255, 155, 242, 140),
                            elevation: 4,
                            child: Column(
                              mainAxisSize: MainAxisSize.min,
                              children: [
                                ListTile(
                                  leading: Icon(Icons.scoreboard_outlined),
                                  title: Text("Marcador de futbol"),
                                  
                                )
                              ],
                            )
                          ),
                        ),
                      ),
                    ), 
                    SizedBox(width: 10,),
                    Expanded(
                      child: SizedBox(
                        height: 120,
                        child: GestureDetector(
                          onTap: () => Navigator.pushNamed(context, 'tareas'),
                          child: Card(
                            color: const Color.fromARGB(255, 150, 208, 255),
                            elevation: 4,
                            child: Column(
                              mainAxisSize: MainAxisSize.min,
                              children: [
                                ListTile(
                                  leading: Icon(Icons.task),
                                  title: Text("Tareas"),
                                )
                              ],
                            )
                          ),
                        ),
                      ),
                    ),*/