import 'package:flutter/material.dart';
import 'package:menu_dash/config/theme/app_theme.dart';
import 'package:menu_dash/widgets/option_menu_item.dart';

class MenuItems {
  List<OptionMenuItem> get listaOpcionesMenu => [
    OptionMenuItem(
      color: AppTheme.listaColores[0],
      icono: Icons.sports_basketball,
      texto: 'Marcador Basket',
      screenName: 'baloncesto',
    ),
    OptionMenuItem(
      color: AppTheme.listaColores[1],
      icono: Icons.pets,
      texto: 'API Perretes',
      screenName: 'practica10',
    ),
    OptionMenuItem(
      color: AppTheme.listaColores[3],
      icono: Icons.supervised_user_circle,
      texto: 'API JsonPlace',
      screenName: 'api1',
    ),
    OptionMenuItem(
      color: AppTheme.listaColores[4],
      icono: Icons.local_drink,
      texto: 'API Simpsons',
      screenName: 'simpsons',
    ),
    OptionMenuItem(
      color: AppTheme.listaColores[5],
      icono: Icons.design_services,
      texto: 'Diseño',
      screenName: 'estilos',
    ),
    OptionMenuItem(
      color: AppTheme.listaColores[6],
      icono: Icons.qr_code_2_rounded,
      texto: 'Lector QR',
      screenName: 'lectorqr',
    ),
    OptionMenuItem(
      color: AppTheme.listaColores[7],
      icono: Icons.settings,
      texto: 'Settings',
      screenName: 'settings',
    ),
    OptionMenuItem(
      color: AppTheme.listaColores[8],
      icono: Icons.map,
      texto: 'Mapas',
      screenName: 'mapas',
    ),
  ];
}
