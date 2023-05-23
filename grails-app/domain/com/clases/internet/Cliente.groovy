package com.clases.internet


class Cliente {

    static hasOne = [direccion: Direccion]

    boolean activo = true

    String nombre

    String apellido

    String telefono

    String email



    static constraints = {
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'cliente_seq']
    }
}
