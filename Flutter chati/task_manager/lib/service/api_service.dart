import 'package:http/http.dart' as http;
import 'package:task_manager/api/rick_morty_response.dart';
class ApiService {
  
  static Future<RickYMortyResponse> getPersonajes() async {
  final response = await http.get(Uri.parse('https://rickandmortyapi.com/api/character'));

  if (response.statusCode == 200) {
    return rickYMortyResponseFromJson(response.body);
  } else {
    throw Exception('Error en la petición');
  }
}
  
}