//import 'dart:convert';
//import 'package:dio/dio.dart';
import 'package:http/http.dart' as http;
import 'package:menu_dash/api/simpsons_personaje_response.dart';

class SimpsonApiService {
  //Método para obtener la vista de usuarios mediante http
  static Future<SimpsonsPersonajeResponse>
  fetchSimpsonsPersonajeWithHttp() async {
    final response = await http.get(
      Uri.parse("https://thesimpsonsapi.com/api/characters"),
    );

    //comprobamos la respuesta
    if (response.statusCode == 200) {
      return simpsonsPersonajeResponseFromJson(response.body);
    } else {
      //Lanzamos un error si la respuesta no es correcta
      throw Exception("Error al lanzar la lista de simpsons");
    }
  }
}
