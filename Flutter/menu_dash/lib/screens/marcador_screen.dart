import 'package:flutter/material.dart';
import 'package:menu_dash/provider/marcador_provider.dart';
import 'package:provider/provider.dart';

class MarcadorScreen extends StatelessWidget {
  const MarcadorScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final score = context.watch<MarcadorProvider>();

    return Scaffold(
      appBar: AppBar(title: const Text('Marcador')),
      body: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          children: [
            const SizedBox(height: 20),
            const Text(
              'Local',
              style: TextStyle(fontSize: 35, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 10),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                // -1
                ElevatedButton(
                  onPressed: () => score.sumarLocal(-1),
                  child: const Text('-1'),
                ),
                const SizedBox(width: 20),
                
                Text(
                  score.local.toString(),
                  style: const TextStyle(
                    fontSize: 70,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                const SizedBox(width: 20),
                
                Column(
                  children: [
                    ElevatedButton(
                      onPressed: () => score.sumarLocal(1),
                      child: const Text('+1'),
                    ),
                    const SizedBox(height: 8),
                    ElevatedButton(
                      onPressed: () => score.sumarLocal(2),
                      child: const Text('+2'),
                    ),
                  ],
                ),
              ],
            ),
            
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                ElevatedButton(
                  onPressed: score.reset,
                  child: const Text('Reset'),
                ),
                const SizedBox(width: 20),
                Image.asset(
                  'assets/baloncesto.png',
                  width: 80,
                  height: 80,
                ),
              ],
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                
                ElevatedButton(
                  onPressed: () => score.sumarVisitante(-1),
                  child: const Text('-1'),
                ),
                const SizedBox(width: 20),

                
                Text(
                  score.visitante.toString(),
                  style: const TextStyle(
                    fontSize: 70,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                const SizedBox(width: 20),

                Column(
                  children: [
                    ElevatedButton(
                      onPressed: () => score.sumarVisitante(1),
                      child: const Text('+1'),
                    ),
                    const SizedBox(height: 8),
                    ElevatedButton(
                      onPressed: () => score.sumarVisitante(2),
                      child: const Text('+2'),
                    ),
                  ],
                ),
              ],
            ),
            const SizedBox(height: 10),
            const Text(
              'Visitante',
              style: TextStyle(fontSize: 35, fontWeight: FontWeight.bold),
            ),
          ],
        ),
      ),
    );
  }
}
