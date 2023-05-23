package com.clases.internet

import grails.converters.JSON


class DireccionController {

    DireccionService direccionService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def obtenerCalle(){
        def calleslist= direccionService.obtenerCalle()
        respond(calleslist)
    }

    def obtenerColonia(){
        def colonialist= direccionService.obtenerColonia()
        respond(colonialist)
    }

    def obtenerEstado(){
        def estadolist= direccionService.obtenerEstado()
        respond(estadolist)
    }

    def obtenerCodigo(){
        def codigolist= direccionService.obtenerCodigo()
        respond(codigolist)
    }

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
