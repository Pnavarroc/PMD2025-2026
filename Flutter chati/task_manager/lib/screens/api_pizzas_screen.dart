import 'package:flutter/material.dart';
import 'package:task_manager/service/api_pizza_service.dart';

class PizzasScreen extends StatefulWidget {
  const PizzasScreen({super.key});

  @override
  State<PizzasScreen> createState() => _PizzasScreenState();
}

class _PizzasScreenState extends State<PizzasScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Pizzas"),),
      body: FutureBuilder(
        future: ApiPizzaService.getPizzas(), 
        builder: (context, snapshot) {
        if (snapshot.connectionState == ConnectionState.waiting){
            return Center(child: CircularProgressIndicator.adaptive(),);
          } else if (snapshot.hasError) {
            return Text('Error: ${snapshot.error}');
          } else{
            final pizzas= snapshot.data?? [];
            return GridView.builder(
              gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(crossAxisCount: 2), 
              itemCount: pizzas.length,
              itemBuilder: (context, index) {
                final pizza = pizzas[index];
                return ListTile(
                  title: Text(pizza.name) ,
                  subtitle: Text("${pizza.price}€") ,
                );
              },
              );
          }
          
        },
        ),
    );
  }
}