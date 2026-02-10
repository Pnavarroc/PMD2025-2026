import 'package:flutter/material.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';
import 'package:mapbox_maps_flutter/mapbox_maps_flutter.dart';
import 'package:menu_dash/config/preferences/preferencias.dart';
import 'package:menu_dash/config/theme/app_theme.dart';
import 'package:menu_dash/screens/apijson_screen.dart';
import 'package:menu_dash/screens/basket_screen.dart';
import 'package:menu_dash/screens/disenios_screen.dart';
import 'package:menu_dash/screens/dog_search_screen.dart';
import 'package:menu_dash/screens/lector_qr_screen.dart';
import 'package:menu_dash/screens/mapbox_screen.dart';
import 'package:menu_dash/screens/menu_screen.dart';
import 'package:menu_dash/screens/settings_screen.dart';
import 'package:menu_dash/screens/simpsons_screen.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Preferencias.init();
  await dotenv.load(fileName: '.env');
  MapboxOptions.setAccessToken(dotenv.env['MAPBOX_ACCESS_TOKEN']!);
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: AppTheme().getTheme(),
      routes: {
        '/': (context) => MenuScreen(),
        'api1': (context) => ApiJsonPlaceUsersScreen(),
        'simpsons': (context) => SimpsonsScreen(),
        'estilos': (context) => DiseniosScreen(),
        'baloncesto': (context) => BasketScreen(),
        'practica10': (context) => DogSearchScreen(),
        'lectorqr': (context) => LectorQrScreen(),
        'settings': (context) => SettingsScreen(),
        'mapas': (context) => MapboxScreen(),
      },
    );
  }
}
