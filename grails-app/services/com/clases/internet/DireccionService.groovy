package com.clases.internet

import grails.gorm.transactions.Transactional

// Servicio de direccion, aqui van las funciones que realizan la logica que utilizan los controladores
@Transactional
class DireccionService {

    //Funcion que recibe un id y retorna el registro con dicho id

    Direccion get(long id) {
        return Direccion.get(id)
    }

    //Funcion que mediante una consulta usando criteria crea una lista de direcciones ordenados por id

    def search(Map contratoMap){
        println "contratoMap:${contratoMap}"
        return Direccion.createCriteria().list(contratoMap) {
            order ("id", "desc")
        }
    }

    // Funcion que retorna calles que esten activas

    def obtenerCalle(){

        return Direccion.findAllByActivo(true).collect{[id:it.id, calle:it.calle]}

    }
    // Funcion que retorna colonias que esten activas


    def obtenerColonia(){

        return Direccion.findAllByActivo(true).collect{[id:it.id, colonia:it.colonia]}

    }
    // Funcion que retorna estados que esten activos


    def obtenerEstado(){

        return Direccion.findAllByActivo(true).collect{[id:it.id, estado:it.estado]}

    }

    // Funcion que retorna codigos que esten activos


    def obtenerCodigo(){

        return Direccion.findAllByActivo(true).collect{[id:it.id, codigoPostal:it.codigoPostal]}

    }


    // Funcion que guarda una instancia de tipo direccion

    def save(Direccion contratoInstance) throws Exception {
        if (contratoInstance && contratoInstance.validate()) {
            contratoInstance.save(flush: true)
            return contratoInstance
        }
        throw new Exception("Errores :${contratoInstance.errors}")
    }

// Funcion que genera una instancia de tipo Direccion

    def create(Map contratoMap) {
        try {
            Direccion contratoInstance
            contratoInstance = new Direccion()
            contratoInstance.calle = contratoMap.calle
            contratoInstance.colonia = contratoMap.colonia
            contratoInstance.estado = contratoMap.estado
            contratoInstance.numExterior = contratoMap.numExterior as int
            contratoInstance.numInterior = contratoMap.numExterior as int
            contratoInstance.codigoPostal=contratoMap.codigoPostal
            return this.save(contratoInstance)
        } catch (e) {
            throw new Exception("Errores :${e.getMessage()}")
        }

    }

}

