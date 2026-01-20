import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:menu_dash/data/model/dog_response.dart';


class Repository {
  Future<DogResponse?> getDogImages(String raza) async {
    final response = await http.get(
      Uri.parse(
        "https://dog.ceo/api/breed/${raza.toLowerCase()}/images",
      ),
    );

    if (response.statusCode == 200) {
      final decodedJson = jsonDecode(response.body);
      DogResponse dogResponse = DogResponse.fromJson(decodedJson);
      return dogResponse;
    } else {
      return null;
    }
  }
}
