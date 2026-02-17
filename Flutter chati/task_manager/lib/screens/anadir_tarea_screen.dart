import 'package:flutter/material.dart';


class AnadirTareasScreen extends StatefulWidget {
  const AnadirTareasScreen({super.key});

  @override
  State<AnadirTareasScreen> createState() => _AnadirTareasScreenState();
}

class _AnadirTareasScreenState extends State<AnadirTareasScreen> {

final _formKey = GlobalKey<FormState>();

final TextEditingController _controller = TextEditingController();

@override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }



  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Nueva Tarea"),),
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            children: [
              Form(
                key: _formKey,
                child: Column(
                  children: [
                    TextFormField(
                      controller: _controller,
                      validator: (value){
                        if(value == null || value.isEmpty){
                          return "Inserte alguna tarea";
                        }
                        return null;
                      },
                      decoration: InputDecoration(
                        border: OutlineInputBorder(),
                        hintText: "Inserte una tarea"
                      ),
                      
                    ),
                    SizedBox(height: 20.0,),
                    ElevatedButton(
                      onPressed: (){
                        if(_formKey.currentState!.validate()){
                        Navigator.pop(context, _controller.text);

                        }
                      }, 
                      child: Text("Añadir Tarea"))
                  ],
                )
                )
            ],
          ),
        )
        ),
    );
  }
}

