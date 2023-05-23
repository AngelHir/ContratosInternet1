package com.clases.internet

import grails.gorm.transactions.Transactional

@Transactional
class DireccionService {

    Direccion get(long id) {
        return Direccion.get(id)
    }

    def obtenerCalle(){

        return Direccion.findAllByActivo(true).collect{[id:it.id, calle:it.calle]}

    }

    def obtenerColonia(){

        return Direccion.findAllByActivo(true).collect{[id:it.id, colonia:it.colonia]}

    }

    def obtenerEstado(){

        return Direccion.findAllByActivo(true).collect{[id:it.id, estado:it.estado]}

    }

    def obtenerCodigo(){

        return Direccion.findAllByActivo(true).collect{[id:it.id, codigoPostal:it.codigoPostal]}

    }



    def save(Direccion contratoInstance) throws Exception {
        if (contratoInstance && contratoInstance.validate()) {
            contratoInstance.save(flush: true)
            return contratoInstance
        }
        throw new Exception("Errores :${contratoInstance.errors}")
    }


    def create(Map contratoMap) {
        try {
            Direccion contratoInstance
            contratoInstance = new Direccion()
            contratoInstance.calle = contratoMap.calle
            contratoInstance.colonia = contratoMap.colonia
            contratoInstance.estado = contratoMap.estado
            contratoInstance.codigoPostal=contratoMap.codigoPostal
            return this.save(contratoInstance)
        } catch (e) {
            throw new Exception("Errores :${e.getMessage()}")
        }

    }

}

