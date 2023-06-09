package com.clases.internet



class Contrato {

    boolean activo = true

    Cliente nombre

    Cliente apellido

    Cliente telefono

    Cliente email

    Plan paquete

    Plan megas

    Plan precio



    static constraints = {
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'contrato_seq']
    }
}
