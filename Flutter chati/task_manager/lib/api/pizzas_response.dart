// To parse this JSON data, do
//
//     final pizzasResponse = pizzasResponseFromJson(jsonString);

import 'dart:convert';

List<PizzasResponse> pizzasResponseFromJson(String str) => List<PizzasResponse>.from(json.decode(str).map((x) => PizzasResponse.fromJson(x)));

String pizzasResponseToJson(List<PizzasResponse> data) => json.encode(List<dynamic>.from(data.map((x) => x.toJson())));

class PizzasResponse {
    int id;
    String category;
    String name;
    List<String>? topping;
    int price;
    int? rank;

    PizzasResponse({
        required this.id,
        required this.category,
        required this.name,
        this.topping,
        required this.price,
        this.rank,
    });

    factory PizzasResponse.fromJson(Map<String, dynamic> json) => PizzasResponse(
        id: json["id"],
        category: json["category"],
        name: json["name"],
        topping: json["topping"] == null ? [] : List<String>.from(json["topping"]!.map((x) => x)),
        price: json["price"],
        rank: json["rank"],
    );

    Map<String, dynamic> toJson() => {
        "id": id,
        "category": category,
        "name": name,
        "topping": topping == null ? [] : List<dynamic>.from(topping!.map((x) => x)),
        "price": price,
        "rank": rank,
    };
}
