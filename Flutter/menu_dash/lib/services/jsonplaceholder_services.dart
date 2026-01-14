import 'dart:convert';
import 'package:dio/dio.dart';
import 'package:http/http.dart' as http;

class JsonplaceholderAPIService {
  //Método para obtener la vista de usuarios mediante http
  static Future<List<dynamic>> fetchUsersWithHttp() async {
    final response = await http.get(
      Uri.parse("https://jsonplaceholder.typicode.com/users"),
    );

    //comprobamos la respuesta
    if (response.statusCode == 200) {
      return json.decode(response.body);
    } else {
      //Lanzamos un error si la respuesta no es correctaç
      throw Exception("Error al lanzar la lista de usuarios");
    }
  }

  //Método para obtener la lista de usuarios usando dio

  static Future<List<dynamic>> fetchUsersWithDio() async {
    final dio = Dio();
    final response = await dio.get(
      "https://jsonplaceholder.typicode.com/users",
    );
    if (response.statusCode == 200) {
      return response.data;
    } else {
      //Lanzamos un error si la respuesta no es correcta
      throw Exception("Error al lanzar la lista de usuarios");
    }
  }
}
