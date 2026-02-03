import 'package:firebase_auth/firebase_auth.dart';
import 'package:google_sign_in/google_sign_in.dart';




class AuthService {
  //Instancia de firebase auth
  final FirebaseAuth _auth = FirebaseAuth.instance;

    // Instancia de GoogleSignIn
  final GoogleSignIn _googleSignIn = GoogleSignIn.instance; 
  static bool isInitialize = false;


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



/////////////////////////////////////////////////
  ///  Iniciar Sesion con Google             //////
  /////////////////////////////////////////////////
  Future<void> initSignIn() async {
    if (!isInitialize){
      await _googleSignIn.initialize(
        serverClientId: '632388232096-nerd8gtqfmqbuvdqapqtuhu1pnh4l046.apps.googleusercontent.com',
      );
      isInitialize = true;
    }
  }
  // Iniciar sesion con Google 7.2.0
  Future<UserCredential?> loginConGoogle() async {
    try {
      initSignIn();
      final GoogleSignInAccount googleUser = await _googleSignIn.authenticate();

      // Si el usuario cancela
      if ( googleUser == null ) return null;

      final idToken = googleUser.authentication.idToken;
      
    } catch (e) {
      
    }
  }






}

