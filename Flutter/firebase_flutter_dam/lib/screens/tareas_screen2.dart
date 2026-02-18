import 'package:flutter/material.dart';
import 'package:cloud_firestore/cloud_firestore.dart';

class TareasScreen2 extends StatefulWidget {
  const TareasScreen2({super.key});

  @override
  State<TareasScreen2> createState() => _TareasScreen2State();
}

class _TareasScreen2State extends State<TareasScreen2> {
  @override
  Widget build(BuildContext context) {
 final Stream<QuerySnapshot> _usersStream = FirebaseFirestore.instance.collection('Tareas').snapshots();

    return Scaffold(
      appBar: AppBar(title: Text("Tareas"),),
      body: StreamBuilder<QuerySnapshot>(
      stream: _usersStream,
      builder: (BuildContext context, AsyncSnapshot<QuerySnapshot> snapshot) {
        if (snapshot.hasError) {
          return Text('Something went wrong');
        }

        if (snapshot.connectionState == ConnectionState.waiting) {
          return Text("Loading");
        }

        return ListView(
          children: snapshot.data!.docs.map((DocumentSnapshot document) {
          Map<String, dynamic> data = document.data()! as Map<String, dynamic>;
            return ListTile(
              title: Text(data['titulo']),
              subtitle: Text(data['descripcion']),
            );
          }).toList(),
        );
      },
    )
    );
  }
}