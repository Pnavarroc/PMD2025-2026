import 'package:flutter/material.dart';

class MenuItem {
  final String titulo;
  final String subtitulo;
  final String link;
  final IconData icono;

  MenuItem({
    required this.titulo,
    required this.subtitulo,
    required this.link,
    required this.icono,
  });
}

final menuItems = <MenuItem>[
  MenuItem(
    titulo: 'Botones',
    subtitulo: 'Muchos botones que ofrece flutter',
    link: '/botones',
    icono: Icons.radio_button_checked,
  ),
  MenuItem(
    titulo: 'Listas',
    subtitulo: 'un simple ejemplo de lista',
    link: '/listas',
    icono: Icons.list,
  ),
  MenuItem(
    titulo: 'Tarjetas',
    subtitulo: 'un simple ejemplo de tarjetas',
    link: '/tarjetas',
    icono: Icons.credit_card,
  ),
];
