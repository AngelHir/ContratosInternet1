package com.clases.internet

import grails.gorm.transactions.Transactional

// Servicio de cliente, aqui van las funciones que realizan la logica que utilizan los controladores
@Transactional
class ClienteService {

    //Funcion que recibe un id y retorna el registro con dicho id
    Cliente get(long id) {
        return Cliente.get(id)
    }

    //Funcion que mediante una consulta usando criteria crea una lista de clientes ordenados por id
    def search(Map contratoMap) {
        println "contratoMap:${contratoMap}"
        return Cliente.createCriteria().list(contratoMap) {
            order("id", "asc")
        }
    }

    // Funcion que retorna nombres de clientes que esten activos
    def obtenerNombres() {

        return Cliente.findAllByActivo(true).collect {
            [id: it.id, nombre: it.nombre, apellido: it.apellido]
        }

    }


    // Funcion que guarda una instancia de tipo cliente
    def save(Cliente contratoInstance) throws Exception {
        if (contratoInstance && contratoInstance.validate()) {
            contratoInstance.save(flush: true)
            return contratoInstance
        }
        throw new Exception("Errores :${contratoInstance.errors}")
    }

// Funcion que genera una instancia de tipo cliente
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


// Funcion que cambia el estado de activo de un cliente
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