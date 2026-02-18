import 'package:flutter/material.dart';
import 'package:task_manager/api/rick_morty_response.dart';
import 'package:task_manager/service/api_service.dart';


class ApiRickMortyScreen extends StatefulWidget {
  const ApiRickMortyScreen({super.key});

  @override
  State<ApiRickMortyScreen> createState() => _ApiRickMortyScreenState();
}

class _ApiRickMortyScreenState extends State<ApiRickMortyScreen> {

  

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Api Rick y Morty"),),
      body: FutureBuilder(
        future: ApiService.getPersonajes(), 
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting){
            return Center(child: CircularProgressIndicator.adaptive(),);
          } else if (snapshot.hasError) {
            return Text('Error: ${snapshot.error}');
          } else{
          final personajes = snapshot.data?.results ?? [];
          return GridView.builder(
            gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(crossAxisCount: 2), 
            itemCount: personajes.length,
            itemBuilder: (context, index) {
              final personaje =personajes[index];
              return ListTile(
                title: Text(personaje.name),
                subtitle: Image.network(personaje.image),
              );
            },
            );
          }
        },
        ),
      );
    
  }
}