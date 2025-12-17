import 'package:componentes/presentation/widgets/text_form_personalizado.dart';
import 'package:flutter/material.dart';

class FormularioScreen extends StatelessWidget {
  const FormularioScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // Los keys me ayudan a mantener la referencia a los valores del formulario
    final formKey = GlobalKey<FormState>();

    final Map<String, String> formValues = {
      'nombre': 'Pablo',
      'apellido': 'Navarro',
      'correo': 'pnavarroc@gmail.com',
      'password': '123456',
      'rol': 'Admin',
    };

    return Scaffold(
      appBar: AppBar(title: Text('Formulario')),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 10),
          child: Form(
            key: formKey,
            child: Column(
              children: [
                SizedBox(height: 40),
                TextFormPersonalizado(
                  labelText: 'Nombre',
                  hintText: 'Nombre del usuario',
                  icon: Icons.person,
                  formPropiedad: 'nombre',
                  formValues: formValues,
                ),
                SizedBox(height: 20),
                TextFormPersonalizado(
                  labelText: 'Apellido',
                  hintText: 'Apellido del usuario',
                  icon: Icons.person_2,
                  formPropiedad: 'apellido',
                  formValues: formValues,
                ),
                SizedBox(height: 20),
                TextFormPersonalizado(
                  labelText: 'Correo',
                  hintText: 'Email del usuario',
                  icon: Icons.email,
                  keyboardType: TextInputType.emailAddress,
                  formPropiedad: 'correo',
                  formValues: formValues,
                ),
                SizedBox(height: 20),
                TextFormPersonalizado(
                  labelText: 'Password',
                  hintText: 'Contraseña del usuario',
                  icon: Icons.lock,
                  obscureText: true,
                  formPropiedad: 'password',
                  formValues: formValues,
                ),
                SizedBox(height: 20),
                DropdownButtonFormField(
                  initialValue: 'Admin',
                  items: [
                    DropdownMenuItem(value: 'Admin', child: Text('Admin')),
                    DropdownMenuItem(value: 'User', child: Text('User')),
                  ],
                  onChanged: (value) {
                    formValues['rol'] = value!;
                  },
                ),
                SizedBox(height: 30),
                ElevatedButton(
                  onPressed: () {
                    // Imprimir los valores del formulario
                    print(formValues);
                  },
                  child: SizedBox(
                    width: double.infinity,
                    child: Center(child: Text('Guardar')),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
