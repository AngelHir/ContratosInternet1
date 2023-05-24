package com.clases.internet

import grails.gorm.transactions.Transactional

@Transactional
class PlanService {

    Plan get(long id) {
        return Plan.get(id)
    }

    def search(Map contratoMap){
        println "catalogoMap:${contratoMap}"
        return Plan.createCriteria().list(contratoMap) {
            order ("id", "desc")
        }
    }

    def obtenerPrecio(){

        return Plan.findAllByActivo(true).collect{[id:it.id, precio:it.precioPaquete]}

    }

    def obtenerPaquete(){

        return Plan.findAllByActivo(true).collect{[id:it.id, nombre:it.nombrePaquete]}

    }



    def save(Plan contratoInstance) throws Exception {
        if (contratoInstance && contratoInstance.validate()) {
            contratoInstance.save(flush: true)
            return contratoInstance
        }
        throw new Exception("Errores :${contratoInstance.errors}")
    }


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

