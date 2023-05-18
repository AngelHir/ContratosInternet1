package Contrato

import Cliente.Cliente

class Contrato {

    boolean activo = true

    Cliente cliente

    Plan plan


    static constraints = {
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'contrato_seq']
    }
}
