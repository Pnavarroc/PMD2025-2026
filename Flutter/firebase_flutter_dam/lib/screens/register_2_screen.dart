import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';

class Register2Screen extends StatefulWidget {
  const Register2Screen({super.key});

  @override
  State<Register2Screen> createState() => _Register2ScreenState();
}

class _Register2ScreenState extends State<Register2Screen> {


  final TextEditingController _emailController =TextEditingController();
  final TextEditingController _contrasenaController =TextEditingController();

  final _formKey = GlobalKey<FormState>();

@override
  void dispose() {
    _emailController.dispose();
    _contrasenaController.dispose();
    super.dispose();
  }

  Future<UserCredential?> registerEmailPass() async{
    try {
      final credential = await FirebaseAuth.instance.createUserWithEmailAndPassword(
        email: _emailController.text,
        password: _contrasenaController.text,
      );
      return credential;
    } on FirebaseAuthException catch (e) {
      if (e.code == 'weak-password') {
        print('The password provided is too weak.');
      } else if (e.code == 'email-already-in-use') {
        print('The account already exists for that email.');
      }
    } catch (e) {
      print(e);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Register"),),
      body: Padding(
        padding: const EdgeInsets.all(12.0),
        child: Column(
          children: [
            Form(
              key: _formKey,
              child: Column(
                children: [
                  TextFormField(
                    controller: _emailController,
                    decoration: InputDecoration(
                      hintText: "Escribe tu email",
                      helper: Text("Eres un pollo mete un email")
                    ),
                    validator: (value) {
                    if (value == null || value.isEmpty) {
                      return 'Please enter some text';
                    }
                    return null;
                    }
                  ),
                  TextFormField(
                    controller: _contrasenaController,
                    decoration: InputDecoration(
                      hintText: "Escribe tu Contraseña",
                      helper: Text("Eres un pollo mete un email")
                    ),
                    validator: (value) {
                    if (value == null || value.isEmpty) {
                      return 'Please enter some text';
                    }
                    return null;
                    }
                  ),
                ],
              )
              ),
              SizedBox(height: 20,),
              SizedBox(
                width: 100,
                child: FloatingActionButton(
                  onPressed: () async {
                  if (_formKey.currentState!.validate()) {
                    final user = await registerEmailPass();
                    if (user != null) {
                      Navigator.pop(context);
                    }
                  }
                },
                  child: Text("Crear cuenta"),
                  ),
              ),
              
          ],
        ),
      ),
    );
  }
}