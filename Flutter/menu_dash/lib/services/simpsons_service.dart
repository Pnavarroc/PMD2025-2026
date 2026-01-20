import 'package:menu_dash/api/simpsons_personajes_response.dart';
import 'package:http/http.dart' as http;

class SimpsonsService {

  Future<SimpsonsPersonajesResponse> getPersonajesSimpsonWithHttp() async {

    final response = await http.get(
      Uri.parse('https://thesimpsonsapi.com/api/characters')
    );

    if ( response.statusCode == 200 ) {
      return simpsonsPersonajesResponseFromJson(response.body);
    } else {
      throw Exception('Fallo al cargar los personajes de la API');
    }
  }
}