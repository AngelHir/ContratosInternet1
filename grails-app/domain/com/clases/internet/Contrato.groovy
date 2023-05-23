package com.clases.internet



class Contrato {

    boolean activo = true

    Cliente nombre

    Cliente apellido

    Plan plan

    Direccion direccion


    static constraints = {
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'contrato_seq']
    }
}
