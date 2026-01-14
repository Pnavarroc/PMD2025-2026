import 'package:flutter/material.dart';
import 'package:menu_dash/config/theme/app_theme.dart';
import 'package:menu_dash/widgets/option_menu_item.dart';

class MenuItems {
  List<OptionMenuItem> get listaOpcionesMenu => [
    OptionMenuItem(
      color: AppTheme.listaColores[0],
      icono: Icons.sports_basketball,
      texto: "Marcador Basket",
      screenName: "baloncesto",
    ),
    OptionMenuItem(
      color: AppTheme.listaColores[1],
      icono: Icons.pets_rounded,
      texto: "API perretes",
      screenName: "practica10",
    ),
    OptionMenuItem(
      color: AppTheme.listaColores[2],
      icono: Icons.api,
      texto: "API JsonPlace",
      screenName: "api1",
    ),
    OptionMenuItem(
      color: AppTheme.listaColores[3],
      icono: Icons.local_drink,
      texto: "API simpsons",
      screenName: "simpsons",
    ),
    OptionMenuItem(
      color: AppTheme.listaColores[4],
      icono: Icons.design_services,
      texto: "Diseño",
      screenName: "estilos",
    ),
    OptionMenuItem(
      color: AppTheme.listaColores[5],
      icono: Icons.pool,
      texto: "Opcion 6",
      screenName: "Opcion 6",
    ),
    OptionMenuItem(
      color: AppTheme.listaColores[6],
      icono: Icons.snowmobile,
      texto: "Opcion 7",
      screenName: "Opcion 7",
    ),
    OptionMenuItem(
      color: AppTheme.listaColores[7],
      icono: Icons.soup_kitchen,
      texto: "Opcion 8",
      screenName: "Opcion 8",
    ),
  ];
}
