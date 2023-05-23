package com.clases.internet

class Plan {

    boolean activo = true

    String nombrePaquete

    int numMegas

    double precioPaquete




    static constraints = {
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'plan_seq']
    }
}
