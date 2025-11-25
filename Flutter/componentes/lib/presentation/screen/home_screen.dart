import 'package:componentes/config/theme/routes/menu_items.dart';
import 'package:componentes/presentation/screen/botones_screen.dart';
import 'package:flutter/material.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(centerTitle: true, title: Text('Home Screen')),
      body: Container(
        color: Colors.greenAccent,
        child: ListView.builder(
          itemCount: menuItems.length,
          itemBuilder: (context, index) {
            final MenuItem = menuItems[index];
            return ListTile(
              title: Text(MenuItem.titulo),
              subtitle: Text(MenuItem.subtitulo),
              leading: Icon(MenuItem.icono),
              trailing: Icon(Icons.arrow_forward_ios),
              onTap: () {
                //Navegar a otra pantalla
                Navigator.of(context).push(
                  MaterialPageRoute(builder: (context) => BotonesScreen()),
                );
              },
            );
          },
        ),
      ),
    );
  }
}
