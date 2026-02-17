import 'package:flutter/material.dart';
import 'package:task_manager/provider/marcador_provider.dart';
import 'package:task_manager/screens/anadir_tarea_screen.dart';
import 'package:task_manager/screens/home_screen.dart';
import 'package:task_manager/screens/marcador_screen.dart';
import 'package:task_manager/screens/resultados_screen.dart';
import 'package:task_manager/screens/tareas_screen.dart';
import 'package:provider/provider.dart';
void main() {
  runApp(
    MultiProvider(providers: [
      ChangeNotifierProvider(create: (_) => MarcadorProvider()),
    ],
    child: const MainApp(),
    ),
    
    );
}


class MainApp extends StatefulWidget {
  const MainApp({super.key});

  @override
  State<MainApp> createState() => _MainAppState();
}

class _MainAppState extends State<MainApp> {


  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      routes: {
        '/': (context)=> HomeScreen(),
        'tareas': (context)=> TareasScreen(),
        'anadirTareas': (context)=> AnadirTareasScreen(),
        'marcador': (context)=> MarcadorScreen(),
        'resultado': (context)=> ResultadosScreen(),
      },
    );
  }
}
