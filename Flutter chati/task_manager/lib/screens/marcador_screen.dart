import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:task_manager/provider/marcador_provider.dart';

class MarcadorScreen extends StatelessWidget {
  const MarcadorScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final marcador = context.watch<MarcadorProvider>();

    return Scaffold(
      appBar: AppBar(title: Text("Marcador de fútbol")),
      body: Padding(
        padding: const EdgeInsets.all(12.0),
        child: Center(
          child: Column(
            children: [
              Text("Marcador Visitante ", style: TextStyle(fontSize: 20)),
              SizedBox(height: 10),
              Text(
                '${marcador.marcadorVisitante}',
                style: TextStyle(fontSize: 60),
              ),
              //Vamos a poner un icono Pero vamos a poner una row que tenga sumar 1 a visitante el icono y otro boton de sumar al local
              Expanded(
                child: Row(
                  children: [
                    ElevatedButton(
                      onPressed: () => marcador.incrementarVisitante(),
                      child: Text("Gol visitantes"),
                    ),
                    SizedBox(width: 10),
                    Icon(Icons.sports_football, size: 100),
                    SizedBox(width: 10),
                    ElevatedButton(
                      onPressed: () => marcador.incrementarLocal(),
                      child: Text("Gol local"),
                    ),
                  ],
                ),
              ),
              ElevatedButton(
                onPressed: () => marcador.resetearMarcadores(),
                child: Text("Resetear"),
              ),
              SizedBox(height: 100),
              Text("${marcador.marcadorLocal}", style: TextStyle(fontSize: 70)),
              Text("Marcador Local ", style: TextStyle(fontSize: 20)),
              ElevatedButton(
                onPressed: () => Navigator.pushNamed(context, 'resultado'),
                child: Text("Ir a Pantalla de resultados"),
              ),
              SizedBox(height: 80),
            ],
          ),
        ),
      ),
    );
  }
}
