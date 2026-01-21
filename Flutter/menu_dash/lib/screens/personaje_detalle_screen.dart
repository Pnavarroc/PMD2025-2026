import 'package:flutter/material.dart';
import 'package:menu_dash/widgets/blur_container.dart';
import 'package:menu_dash/widgets/fade_animation_widget.dart';
import 'package:menu_dash/widgets/infotitle_widget.dart';

class PersonajeDetalleScreen extends StatefulWidget {
  const PersonajeDetalleScreen({
    Key? key,
    required this.color,
    required this.imagen,
    required this.nombre,
  }) : super(key: key);
  final Color color;
  final String imagen;
  final String nombre;

  @override
  State<PersonajeDetalleScreen> createState() => _PersonajeDetalleScreenState();
}

class _PersonajeDetalleScreenState extends State<PersonajeDetalleScreen> {
  double _alturaPantalla = 0;

  @override
  Widget build(BuildContext context) {
    _alturaPantalla = MediaQuery.of(context).size.height;
    return Scaffold(
      backgroundColor: Colors.black,
      body: Container(
        width: double.infinity,
        decoration: BoxDecoration(
          gradient: LinearGradient(
            begin: AlignmentGeometry.topCenter,
            end: AlignmentGeometry.bottomCenter,
            colors: [widget.color, Color.fromARGB(255, 16, 16, 16)],
          ),
        ),
        child: Column(
          crossAxisAlignment: .start,
          children: [
            Stack(
              children: [
                Container(
                  child: SizedBox(
                    height: _alturaPantalla * 0.6,
                    child: Hero(
                      tag: widget.imagen,
                      child: Image.asset("assets/${widget.imagen}.png"),
                    ),
                  ),
                ),
                Positioned(
                  bottom: 20,
                  left: 12,
                  child: FadeAnimationWidget(
                    intervalStart: 0.5,
                    child: BlurContainer(
                      child: Container(
                        width: 160,
                        height: 50,
                        alignment: .center,
                        decoration: BoxDecoration(
                          color: Colors.white.withValues(alpha: 0.1),
                          borderRadius: .circular(10),
                        ),
                        child: Text(
                          widget.nombre,
                          style: TextStyle(
                            fontWeight: .bold,
                            color: Colors.white,
                          ),
                        ),
                      ),
                    ),
                  ),
                ),
              ],
            ),
            SizedBox(height: 10),
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 12.0),
              child: FadeAnimationWidget(
                intervalStart: 0.3,
                child: Text(
                  "Personaje: ${widget.nombre}",
                  style: TextStyle(
                    color: Colors.white,
                    fontSize: 22,
                    fontWeight: .bold,
                  ),
                ),
              ),
            ),
            SizedBox(height: 5),
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 12.0),
              child: FadeAnimationWidget(
                intervalStart: 0.4,
                child: Text(
                  "One Piece",
                  style: TextStyle(color: Colors.white70, fontSize: 12),
                ),
              ),
            ),
            SizedBox(height: 50),
            Padding(
              padding: EdgeInsetsGeometry.symmetric(horizontal: 12),
              child: FadeAnimationWidget(
                intervalStart: 0.5,
                child: Row(
                  mainAxisAlignment: .spaceBetween,
                  children: [
                    InfoTitleWidget(
                      titulo: "Eiichiró Oda",
                      subtitulo: "Creador",
                    ),
                    InfoTitleWidget(titulo: "Japón", subtitulo: "País"),
                  ],
                ),
              ),
            ),
            SizedBox(height: 50),
            GestureDetector(
              onTap: () => Navigator.pop(context),
              child: FadeAnimationWidget(
                intervalStart: 0.6,
                child: Container(
                  height: 50,
                  alignment: .center,
                  margin: EdgeInsets.symmetric(horizontal: 12),
                  decoration: BoxDecoration(
                    color: widget.color,
                    borderRadius: .circular(10),
                  ),
                  child: Text(
                    "Volver",
                    style: TextStyle(
                      color: Colors.white60,
                      fontSize: 16,
                      fontWeight: .bold,
                    ),
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
