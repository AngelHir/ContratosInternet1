package com.clases.internet

// Clase de dominio de para cliente, donde se registran los atributos de la clase

class Cliente {


    boolean activo = true

    String nombre

    String apellido

    String telefono

    String email



    static constraints = {
    }

    // El mapping genera una secuencia para los id de la clase

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'cliente_seq']
    }
}
