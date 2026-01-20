import 'package:flutter/material.dart';
import 'package:menu_dash/services/simpsons_service.dart';

class SimpsonsScreen extends StatelessWidget {
   
  const SimpsonsScreen({Key? key}) : super(key: key);
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Simpsons'),), 
      body: FutureBuilder(
        future: SimpsonsService().getPersonajesSimpsonWithHttp(), 
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting ){
            return Center(child: CircularProgressIndicator(),);
          } else if ( snapshot.hasError ) {
            return Center(child: Text('Error al cargar personajes de la API'),);
          } else {
            final personajes = snapshot.data?.results ?? [];
            return ListView.builder(
              itemCount: personajes.length,
              itemBuilder: (context, index) {
                final personaje = personajes[index];
                return ListTile(
                  title: Text(personaje.name),
                  subtitle: Text(personaje.occupation),
                );
              },
            );
          }
        }, 
        )
    );
  }
}