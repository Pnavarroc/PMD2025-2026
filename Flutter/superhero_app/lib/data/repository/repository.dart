import 'dart:convert';

import 'package:superhero_app/data/model/superhero_response.dart';
import 'package:http/http.dart' as http;

class Repository {
  Future<SuperheroResponse?> getSuperHeroInfo(String superHeroName) async {
    final response = await http.get(
      Uri.parse(
        "https://www.superheroapi.com/api.php/e81d700b1f2c97bc50bb45b7eba1a7a5/search/${superHeroName}",
      ),
    );

    if (response.statusCode == 200) {
      var decodedJson = jsonDecode(response.body);
      //Aqui ya tenemos el formato perfecto para poder usar nuestro constructor
      SuperheroResponse superheroResponse = SuperheroResponse.fromJson(
        decodedJson,
      );
      return superheroResponse;
    } else {
      //Podemos hacer un par de cosas
      //throw Exception("Failed to load super hero info");
      return null;
    }
  }
}
