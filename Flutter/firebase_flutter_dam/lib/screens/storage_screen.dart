import 'dart:io';

import 'package:firebase_storage/firebase_storage.dart';
import 'package:flutter/material.dart';
import 'package:file_picker/file_picker.dart';

class StorageScreen extends StatefulWidget {
  const StorageScreen({Key? key}) : super(key: key);

  @override
  State<StorageScreen> createState() => _StorageScreenState();
}

class _StorageScreenState extends State<StorageScreen> {
  //Funcion para seleccionar el archivo

  PlatformFile? selectedFile;
  Future _seleccionarArchivo() async {
    final result = await FilePicker.platform.pickFiles();
    if (result == null) return;
    setState(() {
      selectedFile = result.files.first;
    });
  }

  //Subir el archivo a firebase storage
  UploadTask? uploadTask;
  Future _subirArchivo() async {
    if (selectedFile == null) return;
    //Ruta donde quiero dejar el archivo
    final path = 'dam2/${selectedFile!.name}';
    final file = File(selectedFile!.path!);

    final ref = FirebaseStorage.instance.ref().child(path);
    uploadTask = ref.putFile(file);

    //puedo esperar a que la tarea se complete
    final snapshot = await uploadTask!.whenComplete(() {});

    final downloadUrl = await ref.getDownloadURL();

    print("Archivo subido correctamente a : $downloadUrl");
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Subir Archivos")),
      body: Center(
        child: Column(
          children: [
            if (selectedFile != null)
              Container(
                height: 350,
                color: Colors.blue[100],
                child: //Text('Archivo seleccionado: ${selectedFile!.name}'),
                Image.file(
                  File(selectedFile!.path!),
                  fit: .cover,
                  width: double.infinity,
                ),
              )
            else
              Icon(Icons.cloud_upload, size: 100, color: Colors.blue),
            SizedBox(height: 24),
            Text(
              "Aquí puedes subir archivos a la nube",
              style: TextStyle(fontSize: 18),
            ),

            SizedBox(height: 24),
            ElevatedButton(
              onPressed: _seleccionarArchivo,
              child: Text("Seleccionar archivo"),
            ),
            SizedBox(height: 24),
            ElevatedButton(
              onPressed: _subirArchivo,
              child: Text("Subir archivo"),
            ),
          ],
        ),
      ),
    );
  }
}
