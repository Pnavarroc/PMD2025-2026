import 'package:shared_preferences/shared_preferences.dart';

class Preferences {
  static late SharedPreferences prefs;

  static String username="";
  static bool isDarkMode=false;
  static bool modeAvion=false;
  static bool notificaciones=false;

  static Future init() async {
    prefs = await SharedPreferences.getInstance();
  }

  Future<void> saveData({required String username,
  required bool isDarkMode,
  required bool modeAvion,
  required bool notificaciones,}) async {

  
  
  await prefs.setString('username', username);
  await prefs.setBool('isDarkMode', isDarkMode);
  await prefs.setBool('modeAvion', modeAvion);
  await prefs.setBool('notificaciones', notificaciones);

}
Future<Map<String, dynamic>> readData() async {
  final prefs = await SharedPreferences.getInstance();

  return {
    "username": prefs.getString('username'),
    "isDarkMode": prefs.getBool('isDarkMode') ?? false,
    "modeAvion": prefs.getBool('modeAvion') ?? false,
    "notificaciones": prefs.getBool('notificaciones') ?? false,
  };
}
}
