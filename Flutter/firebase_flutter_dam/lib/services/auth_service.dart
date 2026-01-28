import 'package:firebase_auth/firebase_auth.dart';

class AuthService {
  //Instancia de firebase auth
  final FirebaseAuth _auth = FirebaseAuth.instance;
  //Me creo un Stream para que emita cambios en el estado de autenticación
  Stream<User?> get authStateChanger => _auth.authStateChanges();
  //Para obtener el usuario actual
  User? get currentUser => _auth.currentUser;

  //==============REGISTRARSE CON EMAIL Y CONTRASEÑA =============================

  Future<UserCredential?> registroConEmailYContrasenia({
    //required String name,
    required String email,
    required String password,
  }) async {
    try {
      UserCredential userCredential = await _auth
          .createUserWithEmailAndPassword(email: email, password: password);
      return userCredential;
    } on FirebaseAuthException catch (e) {
      //Manejo de errores especificos de firebase
      if (e.code == 'email-already-in-use') {
        throw Exception('Este Email ya esta registrado');
      } else if (e.code == 'invalid-email') {
        throw Exception('Este Email no es valido');
      }
      throw Exception("Error al registrar usuario: ${e.message}");
    } catch (e) {
      throw Exception("Error inesperado: $e");
    }
  }

  //==============INICIAR SESION CON EMAIL Y CONTRASEÑA =============================

  Future<UserCredential?> iniciarSesion({
    required String email,
    required String password,
  }) async {
    try {
      UserCredential userCredential = await _auth.signInWithEmailAndPassword(
        email: email,
        password: password,
      );
      return userCredential;
    } on FirebaseAuthException catch (e) {
      //Manejo de errores especificos de firebase
      if (e.code == 'user-not-found') {
        throw Exception('Usuario no encontrado');
      } else if (e.code == 'wrong-password') {
        throw Exception('Contraseña incorrecta');
      }
      throw Exception("Error al loguear usuario: ${e.message}");
    } catch (e) {
      throw Exception("Error inesperado: $e");
    }
  }

  //==============CERRAR SESIÓN=============================
  Future<void> cerrarSesion() async {
    try {
      await _auth.signOut();
    } catch (e) {
      throw Exception("Error al cerrar la sesión: $e");
    }
  }
}
