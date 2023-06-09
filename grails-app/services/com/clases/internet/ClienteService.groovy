package com.clases.internet

import grails.gorm.transactions.Transactional


@Transactional
class ClienteService {

    Cliente  get(long id) {
        return Cliente.get(id)
    }

    def search(Map contratoMap){
        println "contratoMap:${contratoMap}"
        return Cliente.createCriteria().list(contratoMap) {
            order ("id", "asc")
        }
    }

    def obtenerNombres(){

        return Cliente.findAllByActivo(true).collect{[id:it.id, nombre:it.nombre, apellido: it.apellido]}

    }


    def save(Cliente contratoInstance) throws Exception {
        if (contratoInstance && contratoInstance.validate()) {
            contratoInstance.save(flush: true)
            return contratoInstance
        }
        throw new Exception("Errores :${contratoInstance.errors}")
    }


    def create(Map contratoMap) {
        try {
            Cliente contratoInstance
            contratoInstance = new Cliente()
            contratoInstance.nombre = contratoMap.nombre
            contratoInstance.apellido= contratoMap.apellido
            contratoInstance.telefono=contratoMap.telefono
            contratoInstance.email=contratoMap.email
            return this.save(contratoInstance)
        } catch (e) {
            println "Cliente create errors: ${e.getMessage()}"
            throw new Exception("Errores :${e.getMessage()}")
        }

    }



    def delete(long id){
        try {
            Cliente contratoInstance = this.get(id)
            if (contratoInstance) {
                contratoInstance.activo= false
                this.save(contratoInstance)
                return contratoInstance.id
            } else {
                throw new Exception("No existe un registro de cliente con id " + id)
            }
        } catch(e) {
            throw new Exception("Errores :${e.getMessage()}")
        }
    }

}