package com.clases.internet

// Clase de dominio de para direccion, donde se registran los atributos de la clase

class Direccion {

    boolean activo = true

    String calle

    String colonia

    String estado

    int numInterior

    int numExterior

    String codigoPostal

    static constraints = {
    }
    // El mapping genera una secuencia para los id de la clase

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'direccion_seq']
    }
}
