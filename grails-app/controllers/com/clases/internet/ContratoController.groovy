package com.clases.internet

import grails.converters.JSON

class ContratoController {

    ContratoService contratoService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def show(Long id) {
        respond ContratoService.get(id)
    }

    def search() {
        try {
            def contratoList = contratoService.search(JSON.parse(request) as Map)
            Map result = [
                    success: true, total: contratoList.totalCount,
                    data: contratoList.collect {[
                            id: it.id,
                            cliente: it.cliente.nombre&&it.cliente.apellido,
                            paquete: it.plan.nombrePaquete,
                            precio: it.plan.precio,
                            direccion: it.direccion.calle&&it.direccion.colonia&&it.direccion.estado
                    ]}
            ]
            respond result

        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }
    }

    def save() {
        try {
            def jsonObj = JSON.parse(request)
            println "jsonObj: ${jsonObj}"
            contratoService.create(jsonObj as Map) as JSON
            Map result = [success: true]
            respond result

        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }

    }


    def update () {

        try {
            render contratoService.update(JSON.parse(request) as Map) as JSON
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
            Map result = [success: contratoService.delete(id)]
            respond result
        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }
    }

}
