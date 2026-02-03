import 'package:cloud_firestore/cloud_firestore.dart';

class FirestoreService {
  //Obtenemos la instancia
  final FirebaseFirestore _firestore = FirebaseFirestore.instance;

  //================================= Obtener todas la tareas del usuario=================================

  /* 
  Stream<List<Map<String, dynamic>>> obtenerTareas() {
    try {
      return _firestore.collection("Tareas").snapshots();

    } catch (e) {}
  }
  */
}
