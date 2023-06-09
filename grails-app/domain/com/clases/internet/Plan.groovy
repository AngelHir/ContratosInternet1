package com.clases.internet

// Clase de dominio de para plan, donde se registran los atributos de la clase, para este caso esta clase
// sera un catalogo

class Plan {

    boolean activo = true

    String nombrePaquete

    int numMegas

    double precioPaquete




    static constraints = {
    }

    // El mapping genera una secuencia para los id de la clase

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'plan_seq']
    }
}
