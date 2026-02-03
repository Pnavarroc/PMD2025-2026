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
      await _googleSignIn.signOut();
      await _auth.signOut();
    } catch (e) {
      throw Exception("Error al cerrar la sesión: $e");
    }
  }

  /////////////////////////////////////////////////
  ///  Iniciar Sesion con Google             //////
  /////////////////////////////////////////////////
  Future<void> initSignIn() async {
    if (!isInitialize) {
      await _googleSignIn.initialize(
        serverClientId:
            '632388232096-nerd8gtqfmqbuvdqapqtuhu1pnh4l046.apps.googleusercontent.com',
      );
      isInitialize = true;
    }
  }

  // Iniciar sesion con Google 7.2.0
  Future<UserCredential?> loginConGoogle() async {
    try {
      //Iniciamos el servicio de google signIn
      //Esto configura el client Id del servidor necesario para autenticarnos
      initSignIn();
      //Autenticar el usuario con google:  Abre la ventanita para seleccionar la cuenta.
      final GoogleSignInAccount googleUser = await _googleSignIn.authenticate();

      // Si el usuario cancela esa ventana, se podria retornar null.
      if (googleUser == null) return null;
      // Obtener el idToken: es un token qe contiene la informacion del usuario
      final idToken = googleUser.authentication.idToken;
      //Obtenemos el cliente de autorización: Este cliente nos permite solicitar los permisos especificos
      final authoritationClient = googleUser.authorizationClient;

      //Solicitamos autorizacion para los scopes email y profile
      GoogleSignInClientAuthorization? authorization = await authoritationClient
          .authorizationForScopes(['email, profile']);
      //Obtenemos el accesToken
      final accesToken = authorization?.accessToken;

      //Validamos el token
      if (accesToken == null) {
        final authorization2 = await authoritationClient.authorizationForScopes(
          ['email, profile'],
        );
        //Si tampoco funciona lanzamos un error
        if (authorization2?.accessToken != null) {
          throw FirebaseAuthException(code: "Error Código");
        }
        authorization = authorization2;
      }
      //Creamos las credenciales para firebase
      final credential = GoogleAuthProvider.credential(
        idToken: idToken,
        accessToken: accesToken,
      );
      //Nos logueamos con Firebase
      final UserCredential userCredential = await _auth.signInWithCredential(
        credential,
      );

      //Obtenemos el objeto user de firebase
      final User? user = userCredential.user;
      //Procesamos la informacion adicional del usuario
      if (user != null) {
        //Aquí podemos meter información en una base de datos de firebase
      }
      //Devolvemos las credenciales del usuario identificado
      return userCredential;
    } catch (e) {
      print("Error en Login con google $e");
    }

    //si hubo algun error devolvemos null
    return null;
  }
}
