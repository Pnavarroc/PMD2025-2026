import 'package:shared_preferences/shared_preferences.dart';

class Preferencias {
  static late SharedPreferences _preferences;

  //Aqui creo las propiedades locales que quiero manejar
  static String _nombre = "";
  static bool _isDarkMode = false;
  static bool _ubicacionUsuario = false;
  static bool _camaraPermiso = false;

  static Future init() async {
    _preferences = await SharedPreferences.getInstance();
  }

  //Me creo los metodos que me devolveran los valores
  static get nombre {
    return _preferences.getString('nombre') ?? _nombre;
  }

  static set nombre(String nombre) {
    _nombre = nombre;
    _preferences.setString('nombre', nombre);
  }

  static get isDarkMode {
    return _preferences.getBool('modo_oscuro') ?? _isDarkMode;
  }

  static set isDarkMode(bool isDarkMode) {
    _isDarkMode = isDarkMode;
    _preferences.setBool('modo_oscuro', isDarkMode);
  }

  static get camara {
    return _preferences.getBool('camara') ?? _camaraPermiso;
  }

  static set camara(bool camara) {
    _camaraPermiso = camara;
    _preferences.setBool('camara', camara);
  }

  static get ubicacion {
    return _preferences.getBool('ubicacion') ?? _ubicacionUsuario;
  }

  static set ubicacion(bool ubicacion) {
    _ubicacionUsuario = ubicacion;
    _preferences.setBool('ubicacion', ubicacion);
  }
}
