package com.clases.internet

import grails.gorm.transactions.Transactional
import org.hibernate.sql.JoinType

@Transactional
class ContratoService {

    ClienteService clienteService
    DireccionService direccionService
    PlanService planService

    Contrato get(long id) {
        return Contrato.get(id)
    }


    def search(Map contratoMap){
        println "calzadoMap:${contratoMap}"
        return Contrato.createCriteria().list(contratoMap) {
            order ("id", "asc")
        }
    }


    def save(Contrato contratoInstance) throws Exception {
        if (contratoInstance && contratoInstance.validate()) {
            contratoInstance.save(flush: true)
            return contratoInstance
        }
        throw new Exception("Errores :${contratoInstance.errors}")
    }


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
