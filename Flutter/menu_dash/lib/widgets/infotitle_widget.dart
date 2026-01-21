import 'package:flutter/material.dart';

class InfoTitleWidget extends StatelessWidget {
  const InfoTitleWidget({
    super.key,
    required this.titulo,
    required this.subtitulo,
  });
  final String titulo;
  final String subtitulo;

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: .start,
      children: [
        Text(
          titulo,
          style: TextStyle(
            color: Colors.white,
            fontSize: 16,
            fontWeight: .bold,
          ),
        ),
        SizedBox(height: 5),
        Text(subtitulo, style: TextStyle(color: Colors.white70, fontSize: 12)),
      ],
    );
  }
}
