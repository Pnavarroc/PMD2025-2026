import 'package:flutter/material.dart';
import 'package:menu_dash/config/preferences/preferencias.dart';

class SettingsScreen extends StatefulWidget {
  const SettingsScreen({Key? key}) : super(key: key);

  @override
  State<SettingsScreen> createState() => _SettingsScreenState();
}

class _SettingsScreenState extends State<SettingsScreen> {
  /*bool _isDarkMode = false;
  bool _userLocation = false;
  bool _permisoCamara = false;
  String _nombre = "Pablo";*/

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Preferencias")),
      body: SingleChildScrollView(
        child: Column(
          children: [
            CheckboxListTile.adaptive(
              title: Text("Ubicación"),
              value: Preferencias.ubicacion,
              onChanged: (value) {
                setState(() {
                  Preferencias.ubicacion = value!;
                });
              },
            ),
            SwitchListTile.adaptive(
              title: Text("DarkMode"),
              value: Preferencias.isDarkMode,
              onChanged: (value) {
                setState(() {
                  Preferencias.isDarkMode = value;
                });
              },
            ),
            SwitchListTile.adaptive(
              title: Text("Permiso de camara"),
              value: Preferencias.camara,
              onChanged: (value) {
                setState(() {
                  Preferencias.camara = value;
                });
              },
            ),
            Divider(),
            Padding(
              padding: const EdgeInsets.all(12.0),
              child: TextFormField(
                initialValue: Preferencias.nombre,
                decoration: InputDecoration(
                  labelText: "Nombre",
                  helperText: "Nombre del usuario",
                ),
                onChanged: (value) {
                  Preferencias.nombre = value;
                },
              ),
            ),
          ],
        ),
      ),
    );
  }
}
