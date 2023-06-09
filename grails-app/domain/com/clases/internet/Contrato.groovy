package com.clases.internet

// Clase de dominio de para contrato, donde se registran los atributos de la clase

class Contrato {

    boolean activo = true

// esta clase contiene atributos de otras clases por lo que se usa la notacion de la clase origen del atributo
// seguido del nombre que va a tener dicha propiedad

    Cliente nombre

    Cliente apellido

    Cliente telefono

    Cliente email

    Plan paquete

    Plan megas

    Plan precio



    static constraints = {
    }

    // El mapping genera una secuencia para los id de la clase
    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'contrato_seq']
    }
}
