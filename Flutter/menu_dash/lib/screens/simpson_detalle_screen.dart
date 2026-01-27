import 'package:flutter/material.dart';
import 'package:menu_dash/api/simpsons_personajes_response.dart';


class SimpsonDetailScreen extends StatelessWidget {
  final Personaje personaje;

  const SimpsonDetailScreen({Key? key, required this.personaje}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(personaje.name),
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Image.network(
              personaje.imageUrl,
              height: 250,
            ),
            const SizedBox(height: 16),
            Text(
              personaje.name,
              style: const TextStyle(
                fontSize: 24,
                fontWeight: FontWeight.bold,
              ),
            ),
            const SizedBox(height: 8),
            Text(
              'Trabajo: ${personaje.occupation}',
              style: const TextStyle(fontSize: 18),
            ),
            Text(
              'Frases de ${personaje.name}',
              style: TextStyle(
                fontSize: 22,
                fontWeight: FontWeight.bold                
              ),
            ),

            ListView.builder(
              itemCount: personaje.phrases.length,
              shrinkWrap: true,
              physics: const NeverScrollableScrollPhysics(),
              itemBuilder: (context, index) {
                final frase = personaje.phrases[index];
                return ListTile(
                  title: Text(frase),
                );
              },
            ),
          ],
        ),
      ),
    );
  }
}
