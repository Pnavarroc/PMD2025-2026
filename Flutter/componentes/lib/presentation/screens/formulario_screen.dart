import 'package:componentes/presentation/widgets/text_form_personalizado.dart';
import 'package:flutter/material.dart';

class FormularioScreen extends StatelessWidget {
  const FormularioScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Formulario")),
      body: SingleChildScrollView(
        child: Padding(
          padding: EdgeInsets.symmetric(horizontal: 20, vertical: 10),
          child: Column(
            children: [
              TextFromPersonalizado(
                labelText: 'Nombre',
                hintText: 'Nombre del usuario',
                icon: Icons.person,
                suffixIcon: (Icons.group),
              ),
              SizedBox(height: 20),
              TextFromPersonalizado(
                labelText: 'Apellidos',
                hintText: 'Apellido del usuario',
                icon: Icons.person_2,
                suffixIcon: (Icons.surfing_sharp),
              ),
              SizedBox(height: 20),
              TextFromPersonalizado(
                labelText: 'Correo',
                hintText: 'Correo del usuario',
                icon: Icons.send,
                suffixIcon: (Icons.accessibility_sharp),
              ),
              SizedBox(height: 20),

              TextFromPersonalizado(
                labelText: 'Password',
                hintText: 'Contraseña del usuario',
                icon: Icons.password,
                suffixIcon: (Icons.lock),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
