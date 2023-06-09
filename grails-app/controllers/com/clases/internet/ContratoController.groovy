package com.clases.internet

import grails.converters.JSON

//Controlador de la clase contrato, donde se definen los metodos que tendra la clase

class ContratoController {

    // Este es el servicio que utilizara el controlador de contrato
    ContratoService contratoService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    //Metodo que recibe un id y retorna un registro con ese id

    def show(Long id) {
        respond contratoService.get(id)
    }

    // Metodo que usa el servicio de contrato para crear una lista con los atributos declarados

    def search() {
        try {
            def contratoList = contratoService.search(JSON.parse(request) as Map)
            Map result = [
                    success: true, total: contratoList.totalCount,
                    data: contratoList.collect {[
                            id: it.id,
                            nombre: it.nombre.nombre,
                            apellido: it.apellido.apellido,
                            email: it.email.email,
                            telefono: it.telefono.telefono,
                            paquete:it.paquete.paquete,
                            megas: it.megas.megas,
                            precio: it.precio.precio,
                    ]}
            ]
            respond result

        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }
    }

    // Metodo de guardado, retorna un mensaje de completado cuando se completa

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

    //Metodo para borrar un registro, recibe un id y borra el registro con el id recibido

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
