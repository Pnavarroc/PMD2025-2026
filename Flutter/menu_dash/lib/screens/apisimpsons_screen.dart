import 'package:flutter/material.dart';
import 'package:menu_dash/services/simpson_service.dart';

class ApisimpsonsScreen extends StatefulWidget {
  const ApisimpsonsScreen({Key? key}) : super(key: key);

  @override
  State<ApisimpsonsScreen> createState() => _ApisimpsonsScreenState();
}

class _ApisimpsonsScreenState extends State<ApisimpsonsScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: FutureBuilder(
        future: SimpsonApiService.fetchSimpsonsPersonajeWithHttp(),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return Center(child: CircularProgressIndicator());
          } else if (snapshot.hasError) {
            return Center(child: Text("Error al cargar simpsons"));
          } else {
            final simpsons = snapshot.data?.results ?? [];
            return ListView.builder(
              itemCount: simpsons.length,
              itemBuilder: (context, index) {
                final personaje = simpsons[index];
                return Padding(
                  padding: const EdgeInsets.all(12.0),
                  child: Card(
                    elevation: 12.0,
                    child: Column(
                      children: [
                        Image.network(
                          "https://cdn.thesimpsonsapi.com/500${personaje.portraitPath}",
                        ),
                        Text(personaje.name),
                        Text(personaje.occupation),
                      ],
                    ),
                  ),
                );
              },
            );
          }
        },
      ),
    );
  }
}
