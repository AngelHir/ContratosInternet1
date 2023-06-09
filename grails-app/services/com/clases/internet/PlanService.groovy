package com.clases.internet

import grails.gorm.transactions.Transactional

// Servicio de plan, aqui van las funciones que realizan la logica que utilizan los controladores
@Transactional
class PlanService {

    //Funcion que recibe un id y retorna el registro con dicho id

    Plan get(long id) {
        return Plan.get(id)
    }

    //Funcion que mediante una consulta usando criteria crea una lista de planes ordenados por id

    def search(Map contratoMap){
        println "catalogoMap:${contratoMap}"
        return Plan.createCriteria().list(contratoMap) {
            order ("id", "desc")
        }
    }

    // Funcion que retorna precios que esten activos

    def obtenerPrecio(){

        return Plan.findAllByActivo(true).collect{[id:it.id, precio:it.precioPaquete]}

    }

    // Funcion que retorna paquetes que esten activos

    def obtenerPaquete(){

        return Plan.findAllByActivo(true).collect{[id:it.id, nombre:it.nombrePaquete]}

    }


    // Funcion que guarda una instancia de tipo Plan

    def save(Plan contratoInstance) throws Exception {
        if (contratoInstance && contratoInstance.validate()) {
            contratoInstance.save(flush: true)
            return contratoInstance
        }
        throw new Exception("Errores :${contratoInstance.errors}")
    }

// Funcion que genera una instancia de tipo Plan

    def create(Map contratoMap) {
        try {
            Plan contratoInstance
            contratoInstance = new Plan()
            contratoInstance.nombrePaquete = contratoMap.nombrePaquete
            contratoInstance.numMegas = contratoMap.numMegas as int
            contratoInstance.precioPaquete = contratoMap.precioPaquete as double
            return this.save(contratoInstance)
        } catch (e) {
            throw new Exception("Errores :${e.getMessage()}")
        }

    }

}

