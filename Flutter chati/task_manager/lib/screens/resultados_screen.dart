import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:task_manager/provider/marcador_provider.dart';

class ResultadosScreen extends StatelessWidget {
  const ResultadosScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final marcador = context.watch<MarcadorProvider>();

    String mensaje;
    if (marcador.marcadorLocal == marcador.marcadorVisitante) {
      mensaje= "Han empatado :)";
    }else if(marcador.marcadorLocal > marcador.marcadorVisitante){
      mensaje= "El equipo local ha ganado :)";
    }else mensaje = "El equipo visitante ha ganado :)";

    return Scaffold(
      appBar: AppBar(title: Text("Resultado del partido:"),),
      body: Center(
        child: Column(
          children: [
            Text('${marcador.marcadorLocal}-${marcador.marcadorVisitante}', style: TextStyle(fontSize: 60),),
            Text(mensaje)

          ],
        ),
      )
    );
  }
}