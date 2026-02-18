import 'package:firebase_flutter_dam/screens/register_2_screen.dart';
import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';

class Login2Screen extends StatefulWidget {
  const Login2Screen({super.key});

  @override
  State<Login2Screen> createState() => _Login2ScreenState();
}

class _Login2ScreenState extends State<Login2Screen> {
  String email ="";
  String contrasena ="";

  final TextEditingController _emailController =TextEditingController();
  final TextEditingController _contrasenaController =TextEditingController();

  final _formKey = GlobalKey<FormState>();

@override
  void dispose() {
    
    _emailController.dispose();
    _contrasenaController.dispose();
    super.dispose();
  }
  Future<UserCredential?> loginEmailPass() async{
      try {
        final credential = await FirebaseAuth.instance.signInWithEmailAndPassword(
          email: _emailController.text,
          password: _contrasenaController.text
        );
        return credential;
      } on FirebaseAuthException catch (e) {
        if (e.code == 'user-not-found') {
          print('No user found for that email.');
        } else if (e.code == 'wrong-password') {
          print('Wrong password provided for that user.');
        }
        
      }
      
    }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Login"),),
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
                  onPressed: ()async {
                    if(_formKey.currentState!.validate()){
                      final user = await loginEmailPass();
                      if(user!=null){
                        
                        await Navigator.pushNamed(context, '/tareas2');
                      }
                    }
                  },
                  child: Text("Iniciar sesion"),
                  ),
              ),
              SizedBox(height: 20,),
              SizedBox(
                width: 200,
                child: FloatingActionButton(
                  onPressed: (){
                    Navigator.pushNamed(context, '/register2');
                  },
                  child: Text("No tengo cuenta"),
                  ),
              ),
          ],
        ),
      ),
    );
  }
}

  class Inicios{
    

}
  