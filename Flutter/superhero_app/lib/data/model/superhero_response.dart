import 'package:superhero_app/data/model/superhero_detail_response.dart';

class SuperheroResponse {
  final String response;
  final List<SuperheroDetailResponse> listaSuperHeroes;

  SuperheroResponse({required this.response, required this.listaSuperHeroes});

  //Necesito devolver un objeto SuperHeroResponse con los campos que me interesan.
  factory SuperheroResponse.fromJson(Map<String, dynamic> json) {
    var lista = json['results'] as List;

    // El .map es como un for que recorre la lista y la aplica a la funcion que pasamos,.
    List<SuperheroDetailResponse> listaSuperHeroes = lista
        .map((heroe) => SuperheroDetailResponse.fromJson(heroe))
        .toList();
    final response = json['response'];
    return SuperheroResponse(
      response: response,
      listaSuperHeroes: listaSuperHeroes,
    );
  }
}
