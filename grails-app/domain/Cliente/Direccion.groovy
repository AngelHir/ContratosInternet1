package Cliente

class Direccion {

    static belongsTo = [cliente:Cliente]

    String calle

    String colonia

    String estado

    int numInterior

    int numExterior

    String codigoPostal

    static constraints = {
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'direccion_seq']
    }
}
