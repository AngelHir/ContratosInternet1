package com.clases.internet

import grails.converters.JSON

//Controlador de la clase cliente, donde se definen los metodos que tendra la clase

class ClienteController {

    // Este es el servicio que utilizara el controlador de cliente
    ClienteService clienteService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    //Metodo que recibe un id y retorna un registro con ese id
    def show(Long id) {
        respond clienteService.get(id)
    }

    // Metodo que usa el servicio de cliente para crear una lista con los atributos declarados
    def search() {
        try {
            def clienteList = clienteService.search(JSON.parse(request) as Map)
            Map result = [
                    success: true, total: clienteList.totalCount,
                    data: clienteList.collect {[
                            id: it.id,
                            nombre: it.cliente.nombre,
                            apellido: it.cliente.apellido,
                            email: it.cliente.email,
                            telefono: it.cliente.telefono
                    ]}
            ]
            respond result

        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }
    }
    // Metodo que crea una lista de nombres
    def obtenerNombres(){
        def nombreslist= clienteService.obtenerNombres()
        respond(nombreslist)
    }

    // Metodo de guardado, retorna un mensaje de completado cuando se completa
    def save() {
        try {
            def jsonObj = JSON.parse(request)
            println "jsonObj: ${jsonObj}"
            clienteService.create(jsonObj as Map) as JSON
            Map result = [success: true]
            respond result

        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }

    }


    //Metodo para borrar un registro, recibe un id y borra el registro con el id recibido
    def delete(long id){

        println params
        try {
            Map result = [success: clienteService.delete(id)]
            respond result
        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }
    }

}


