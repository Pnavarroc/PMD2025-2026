import 'package:flutter/material.dart';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text("Daily Tracker"),),
        body: Padding(
          padding: const EdgeInsets.all(18.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                "Hola pablo",
                style: TextStyle(
                  fontSize: 26,
                  fontWeight: FontWeight.bold),
                ),
                SizedBox(height: 10,),
                Text(
                "Esto son tus tareas de hoy:",
                style: TextStyle(
                  fontSize: 20,),
                ),
                SizedBox(height: 16,),
                  CardMolon(
                    title: "Beber agua",
                    icon: Icons.water_drop,
                    subtitle: "8 vasos",),
                  SizedBox(height: 16,),
                  CardMolon(
                    title: "Entrenar",
                    icon: Icons.run_circle_outlined,
                    subtitle: "2 horas",),
                  SizedBox(height: 16,),
                  CardMolon(
                    title: "Estudiar flutter",
                    icon: Icons.book,
                    subtitle: "2 horas",),
                  SizedBox(height: 16,),                  
            ],
          ),
        ),
      )
    );
  }
}

class CardMolon extends StatelessWidget {
  
  final String title;
  final String subtitle;
  final IconData icon;

  const CardMolon({
    super.key, required this.title, required this.subtitle, required this.icon,
  });

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: double.infinity,
      child: Card(
        elevation: 6.0,
        child: Padding(
          padding: const EdgeInsetsGeometry.all(16),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                children: [
                  Text(
                  title,
                  style: TextStyle(
                    fontSize: 18, 
                    fontWeight: FontWeight.bold
                    ), 
                    ),
                    Spacer(),
                    Icon(icon),
                ],
              ),
                  SizedBox(height: 6),
                  Text(
                    subtitle,
                    style: TextStyle(
                      fontSize: 12,
                      fontWeight: FontWeight.w200
                    ),
                    ),
                  SizedBox(height: 12),
                  SizedBox(
                    width: double.infinity,
                    child: ElevatedButton(
                      style: ElevatedButton.styleFrom(
                        backgroundColor: Colors.green
                      ),
                      onPressed: () {
                    },
                    child: Text("Marcar como hecho", style: TextStyle(color: Colors.black),),
                    ),
                  )
            ],
          ),),
      ),
    );
  }
}
