import 'package:http/http.dart' as http;
import 'package:task_manager/api/pizzas_response.dart';

class ApiPizzaService {

  static Future<List<PizzasResponse>> getPizzas() async{

    final response = await http.get(
      Uri.parse('https://private-anon-4fd08a7bb3-pizzaapp.apiary-mock.com/restaurants/restaurantId/menu?category=Pizza&orderBy=rank'));

    if (response.statusCode == 200) {
      return pizzasResponseFromJson(response.body);
    } else {
      throw Exception('Error en la petición');
    }
  }
}