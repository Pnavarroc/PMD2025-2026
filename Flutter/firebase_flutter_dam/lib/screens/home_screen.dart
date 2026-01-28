import 'package:firebase_flutter_dam/services/auth_service.dart';
import 'package:flutter/material.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final authService = AuthService();
    final user = authService.currentUser;

    return Scaffold(
      appBar: AppBar(
        title: Text("Bienvenido AZZAMM"),
        actions: [IconButton(onPressed: () {}, icon: Icon(Icons.logout))],
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: .center,
          children: [
            Icon(Icons.check_circle_outline, size: 100, color: Colors.green),
            SizedBox(height: 24),
            Text(
              "Sesión iniciada correctamente",
              style: TextStyle(fontSize: 24, fontWeight: .bold),
            ),
            SizedBox(height: 14),
            Text('Email: ${user?.email}', style: TextStyle(fontSize: 16)),
            SizedBox(height: 8),
            Text(
              'Nombre: ${user?.uid}',
              style: TextStyle(fontSize: 12, color: Colors.grey),
            ),
          ],
        ),
      ),
    );
  }
}
