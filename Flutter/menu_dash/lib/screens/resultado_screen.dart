import 'package:flutter/material.dart';
import 'package:menu_dash/provider/marcador_provider.dart';
import 'package:provider/provider.dart';


class ResultadoScreen extends StatelessWidget {
  const ResultadoScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final score = context.watch<MarcadorProvider>();

    String mensaje;
    if (score.local == score.visitante) {
      mensaje = 'Han empatado';
    } else if (score.local > score.visitante) {
      mensaje = 'Ha ganado el equipo local';
    } else {
      mensaje = 'Ha ganado el equipo visitante';
    }

    return Scaffold(
      appBar: AppBar(title: const Text('Resultado')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(
              '${score.local} - ${score.visitante}',
              style: const TextStyle(
                fontSize: 60,
                fontWeight: FontWeight.bold,
              ),
            ),
            const SizedBox(height: 20),
            Text(
              mensaje,
              style: const TextStyle(fontSize: 30),
            ),
          ],
        ),
      ),
    );
  }
}
