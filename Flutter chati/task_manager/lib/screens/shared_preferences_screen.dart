import 'package:flutter/material.dart';
import 'package:task_manager/preferences/preferences.dart';

class SharedPreferencesScreen extends StatefulWidget {
  const SharedPreferencesScreen({super.key});

  @override
  State<SharedPreferencesScreen> createState() => _SharedPreferencesScreenState();
}

class _SharedPreferencesScreenState extends State<SharedPreferencesScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Shared preferences"),),
      body: Padding(
        padding: const EdgeInsets.all(12.0),
        child: Column(
          children: [
            CheckboxListTile.adaptive(
              title: Text("Modo oscuro"),
              value: Preferences.isDarkMode, 
              onChanged: (value){
                setState(() {
                  Preferences.isDarkMode=value!;
                });
              }),
              CheckboxListTile.adaptive(
              title: Text("Modo avion"),
              value: Preferences.modeAvion, 
              onChanged: (value){setState(() {
                  Preferences.modeAvion=value!;
                });}),
              SwitchListTile.adaptive(
                title: Text("Notificaciones"),
                value: Preferences.notificaciones, 
                onChanged: (value){setState(() {
                  Preferences.notificaciones=value;
                });}),
              TextFormField(
                initialValue: Preferences.username,
                decoration: InputDecoration(
                  hintText: "Nombre",
                  helperText: "Pon tu nombre"
                ),
                
                onChanged: (value) {
                  setState(() {
                  Preferences.username=value;
                });
                },
              )  
          ],
        ),
      ),
    );
  }
}