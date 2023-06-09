package com.clases.internet

import grails.converters.JSON

//Controlador de la clase direccion, donde se definen los metodos que tendra la clase

class DireccionController {

    // Este es el servicio que utilizara el controlador de direccion
    DireccionService direccionService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    // Metodo que usa el servicio de direccion para crear una lista con los atributos declarados

    def search() {
        try {
            def direccionList = direccionService.search(JSON.parse(request) as Map)
            Map result = [
                    success: true, total: direccionList.totalCount,
                    data: direccionList.collect {[
                            id: it.id,
                            calle: it.direccion.calle,
                            colonia: it.direccion.colonia,
                            estado: it.direccion.estado,
                            codigoPostal: it.direccion.codigoPostal
                    ]}
            ]
            respond result

        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }
    }

    // Metodo que crea una lista de calles

    def obtenerCalle(){
        def calleslist= direccionService.obtenerCalle()
        respond(calleslist)
    }

    // Metodo que crea una lista de colonias

    def obtenerColonia(){
        def colonialist= direccionService.obtenerColonia()
        respond(colonialist)
    }

    // Metodo que crea una lista de estados

    def obtenerEstado(){
        def estadolist= direccionService.obtenerEstado()
        respond(estadolist)
    }

    // Metodo que crea una lista de codigos

    def obtenerCodigo(){
        def codigolist= direccionService.obtenerCodigo()
        respond(codigolist)
    }

    // Metodo de guardado, retorna un mensaje de completado cuando se completa

    def save() {
        try {
            direccionService.create(JSON.parse(request) as Map) as JSON
            Map result = [success: true]
            respond result

        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }

    }
}
