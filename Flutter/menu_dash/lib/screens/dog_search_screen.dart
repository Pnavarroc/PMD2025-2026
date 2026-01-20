import 'package:flutter/material.dart';
import 'package:menu_dash/data/model/repository/repository.dart';
import '../data/model/dog_response.dart';


class DogSearchScreen extends StatefulWidget {
  const DogSearchScreen({super.key});

  @override
  State<DogSearchScreen> createState() => _DogSearchScreenState();
}

class _DogSearchScreenState extends State<DogSearchScreen> {
  Future<DogResponse?>? _dogInfo;
  Repository repository = Repository();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Busca un perro")),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          children: [
            TextField(
              decoration: const InputDecoration(
                hintText: "Busca una raza (ej: husky)",
                prefixIcon: Icon(Icons.search),
                border: OutlineInputBorder(),
              ),
              onChanged: (raza) {
                if (raza.isNotEmpty) {
                  setState(() {
                    _dogInfo = repository.getDogImages(raza);
                  });
                }
              },
            ),
            const SizedBox(height: 10),
            FutureBuilder<DogResponse?>(
              future: _dogInfo,
              builder: (context, snapshot) {
                if (snapshot.connectionState == ConnectionState.waiting) {
                  return const CircularProgressIndicator();
                } else if (snapshot.hasError) {
                  return const Text("Error al realizar la búsqueda");
                } else if (!snapshot.hasData ||
                    snapshot.data!.imagenes.isEmpty) {
                  return const Text("No existen resultados");
                } else {
                  final imagenes = snapshot.data!.imagenes;
                  return Expanded(
                    child: ListView.builder(
                      itemCount: imagenes.length,
                      itemBuilder: (context, index) {
                        return Padding(
                          padding: const EdgeInsets.symmetric(vertical: 8),
                          child: Image.network(imagenes[index]),
                        );
                      },
                    ),
                  );
                }
              },
            ),
          ],
        ),
      ),
    );
  }
}
