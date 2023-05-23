package com.clases.internet

class Direccion {


    boolean activo = true

    String calle

    String colonia

    String estado

    String codigoPostal

    static constraints = {
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'direccion_seq']
    }
}
