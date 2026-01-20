import 'package:flutter/material.dart';
import '../provider/marcador_provider.dart';
import 'home_screen.dart';
import 'package:provider/provider.dart';
class BasketScreen extends StatelessWidget {
  const BasketScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (_) => MarcadorProvider(),
      child: const HomeScreen(),
    );
  }
}