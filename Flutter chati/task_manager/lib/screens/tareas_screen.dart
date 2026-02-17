import 'package:flutter/material.dart';
import 'package:task_manager/screens/anadir_tarea_screen.dart';


class TareasScreen extends StatefulWidget {
  const TareasScreen({super.key});

  @override
  State<TareasScreen> createState() => _TareasScreenState();
}

class _TareasScreenState extends State<TareasScreen> {
List<String> tareas = ["Comprar pan", "Estudiar Flutter", "Entrenar", "Estudiar Empresa"];



  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text("Mis Tareas"),),
        body: Padding(
          padding: const EdgeInsets.all(12.0),
          child: Column(
            children: [
              Text(
                  "Tareas pendientes: ${tareas.length}",
                  style: TextStyle(
                    fontSize: 22,
                    fontWeight: FontWeight.bold
                  ),
                ),
                SizedBox(height: 20,),
                Expanded(
                  child: ListView.builder(
                    itemCount: tareas.length,
                    itemBuilder: (context, index) {
                      return Card(
                        child: ListTile(
                        leading: Icon(Icons.task),
                        title: Text(tareas[index]),
                        trailing: Icon(Icons.arrow_right),
                      ),
                      );   
                    },
                  )
                  )
            ],
          ),
        ),
        floatingActionButton: FloatingActionButton(onPressed: () async{
          //Esperamos el resultado de la pantalla
          final nuevaTarea = await Navigator.push<String>(
            context,  MaterialPageRoute(builder: (context) => const AnadirTareasScreen()));
            //Si nueva Tarea no viene vacio entonces 
            if(nuevaTarea!=null && nuevaTarea.isNotEmpty){
              setState(() {
                tareas.add(nuevaTarea);
              });
            }
        }, child: Icon(Icons.add) ,),
        
      );
  }
}