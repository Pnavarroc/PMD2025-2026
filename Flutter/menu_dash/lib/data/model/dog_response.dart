class DogResponse {
  final String status;
  final List<String> imagenes;

  DogResponse({
    required this.status,
    required this.imagenes,
  });

  factory DogResponse.fromJson(Map<String, dynamic> json) {
    final lista = json['message'] as List;

    List<String> imagenes =
        lista.map((img) => img.toString()).toList();

    return DogResponse(
      status: json['status'],
      imagenes: imagenes,
    );
  }
}
