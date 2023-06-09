package com.clases.internet

import grails.gorm.transactions.Transactional
import org.hibernate.sql.JoinType

// Servicio de contrato, aqui van las funciones que realizan la logica que utilizan los controladores

@Transactional
class ContratoService {

    // Servicios utilizados por el servicio de contrato
    ClienteService clienteService
    DireccionService direccionService
    PlanService planService

    //Funcion que recibe un id y retorna el registro con dicho id

    Contrato get(long id) {
        return Contrato.get(id)
    }

    //Funcion que mediante una consulta usando criteria crea una lista de contratos ordenados por id

    def search(Map contratoMap){
        println "calzadoMap:${contratoMap}"
        return Contrato.createCriteria().list(contratoMap) {
            order ("id", "asc")
        }
    }

    // Funcion que guarda una instancia de tipo contrato

    def save(Contrato contratoInstance) throws Exception {
        if (contratoInstance && contratoInstance.validate()) {
            contratoInstance.save(flush: true)
            return contratoInstance
        }
        throw new Exception("Errores :${contratoInstance.errors}")
    }

// Funcion que genera una instancia de tipo Contrato

    def create(Map contratoMap) {
        try {
            Contrato contratoInstance
            contratoInstance = new Contrato()
            contratoInstance.cliente = clienteService.get(contratoMap.cliente.id)
            contratoInstance.plan = planService.get(contratoMap.plan.id)
            contratoInstance.direccion = direccionService.get(contratoMap.direccion.id)
            return this.save(contratoInstance)
        } catch (e) {
            println "Contrato create errors: ${e.getMessage()}"
            throw new Exception("Errores :${e.getMessage()}")
        }

    }



    def delete(long id){
        try {
            Contrato contratoInstance = this.get(id)
            if (contratoInstance) {
                contratoInstance.activo= false
                this.save(contratoInstance)
                return contratoInstance.id
            } else {
                throw new Exception("No existe un registro de contrato con id " + id)
            }
        } catch(e) {
            throw new Exception("Errores :${e.getMessage()}")
        }
    }
}
