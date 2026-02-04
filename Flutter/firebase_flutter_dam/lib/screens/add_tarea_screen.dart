import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';

class AddTareaScreen extends StatefulWidget {
  const AddTareaScreen({Key? key}) : super(key: key);

  @override
  State<AddTareaScreen> createState() => _AddTareaScreenState();
}

class _AddTareaScreenState extends State<AddTareaScreen> {
  final _titleController = TextEditingController();
  final _descriptionController = TextEditingController();
  DateTime _selectedDate = DateTime.now();
  String? _tareaId;
  bool _isEditing = false;

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    //Miramos a ver si es una tarea nueva o editar alguna que ya tuvieramos
    final args =
        ModalRoute.of(context)?.settings.arguments as Map<String, dynamic>?;

    if (args != null && args['id'] != null) {
      _isEditing = true;
      _tareaId = args['id'];
      _titleController.text = args['titulo'] ?? '';
      _descriptionController.text = args['descripcion'] ?? '';
    }
  }

  @override
  void dispose() {
    _titleController.dispose();
    _descriptionController.dispose();
    super.dispose();
  }

  Future _guardarEditarTarea() async {
    if (_titleController.text.isEmpty || _descriptionController.text.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Por favor, completa todos los campos.')),
      );
      return;
    }

    try {
      // Comprobamos si estamos EDITANDO
      if (_isEditing) {
        // Actualizamos la tarea existente
        await FirebaseFirestore.instance
            .collection('Tareas')
            .doc(_tareaId)
            .update({
              'titulo': _titleController.text,
              'descripcion': _descriptionController.text,
              'ult_mod': DateTime.now(),
            });
        // Mostramos unmensaje de exito
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Tarea Modificada correctamente')),
        );
      } else {
        // Añadimos una tarea NUEVA
        await FirebaseFirestore.instance.collection('tareas').add({
          'titulo': _titleController.text,
          'descripcion': _descriptionController.text,
          'fecha_creacion': DateTime.now(),
        });

        // Mostramos unmensaje de exito
        ScaffoldMessenger.of(
          context,
        ).showSnackBar(SnackBar(content: Text('Tarea Añadida correctamente')));
      }

      // Volvemos a la lista de tareas
      Navigator.pop(context);
    } catch (e) {
      ScaffoldMessenger.of(
        context,
      ).showSnackBar(SnackBar(content: Text('Error al MODIFICAR tarea: $e')));
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(_isEditing ? 'Editar Tarea' : 'Añadir Tarea')),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            children: [
              SizedBox(height: 20),
              TextFormField(
                controller: _titleController,
                decoration: InputDecoration(hintText: 'Titulo'),
              ),
              SizedBox(height: 20),
              TextFormField(
                controller: _descriptionController,
                decoration: InputDecoration(hintText: 'Descripción'),
                maxLines: 3,
              ),
              SizedBox(height: 20),
              ElevatedButton(
                onPressed: _guardarEditarTarea,
                child: Text(
                  _isEditing ? 'Editar Tarea' : 'Añadir Tarea',
                  style: TextStyle(
                    fontSize: 16,
                    color: const Color.fromARGB(255, 152, 30, 169),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
