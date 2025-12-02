package iesch.org.practica06.model

data class Preguntas(
    val textoPregunta: String,
    val equacion: String,
    val opciones: List<String>,
    val opcionCorrecta: Int
)

