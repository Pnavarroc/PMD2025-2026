import 'package:flutter/material.dart';
import 'package:menu_dash/services/jsonplaceholder_services.dart';

class ApiJsonPlaceUsersScreen extends StatefulWidget {
  const ApiJsonPlaceUsersScreen({Key? key}) : super(key: key);

  @override
  State<ApiJsonPlaceUsersScreen> createState() =>
      _ApiJsonPlaceUsersScreenState();
}

class _ApiJsonPlaceUsersScreenState extends State<ApiJsonPlaceUsersScreen> {
  late Future<List<dynamic>> _futureUsers;

  @override
  void initState() {
    super.initState();
    //_futureUsers = JsonplaceholderAPIService.fetchUsersWithHttp();
    _futureUsers = JsonplaceholderAPIService.fetchUsersWithDio();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: FutureBuilder(
        future: _futureUsers,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return Center(child: CircularProgressIndicator.adaptive());
          } else if (snapshot.hasError) {
            return Text("Eres un payo: ${snapshot.error}");
          } else {
            final usuarios = snapshot.data!;
            return ListView.builder(
              itemCount: usuarios.length,
              itemBuilder: (context, index) {
                final usuario = usuarios[index];
                return ListTile(
                  title: Text(usuario['name']),
                  subtitle: Text(usuario['address']['street']),
                );
              },
            );
          }
        },
      ),
    );
  }
}
