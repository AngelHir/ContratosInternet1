package com.clases.internet

import grails.converters.JSON


class ClienteController {

    ClienteService clienteService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def show(Long id) {
        respond clienteService.get(id)
    }

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

    def obtenerNombres(){
        def nombreslist= clienteService.obtenerNombres()
        respond(nombreslist)
    }


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


