import 'package:flutter/material.dart';
import 'package:mapbox_maps_flutter/mapbox_maps_flutter.dart';

class MapboxScreen extends StatelessWidget {
  const MapboxScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    CameraOptions camara = CameraOptions(
      center: Point(coordinates: Position(-1.1070361111111, 40.342833333333)),
      zoom: 14.0,
      bearing: -161.81,
      pitch: 70.0,
    );
    MapboxMap? _mapboxMap;

    _onMapCreated(MapboxMap mapboxMap) {
      _mapboxMap = mapboxMap;

      //Animacion
      mapboxMap.flyTo(
        camara,
        MapAnimationOptions(duration: 10000, startDelay: 2000),
      );
    }

    return Scaffold(
      appBar: AppBar(title: Text("MapBox")),
      body: MapWidget(
        styleUri: MapboxStyles.STANDARD,
        cameraOptions: CameraOptions(
          center: Point(coordinates: Position(-74.0060, 40.7128)),
          zoom: 14.0,
        ),
        onMapCreated: _onMapCreated,
      ),
    );
  }
}
