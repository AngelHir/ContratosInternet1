package com.clases.internet

import grails.converters.JSON

class PlanController {

    PlanService planService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def obtenerPrecio(){
        def precioslist= planService.obtenerPrecio()
        respond(precioslist)
    }

    def obtenerPaquete(){
        def paqueteslist= planService.obtenerPaquete()
        respond(paqueteslist)
    }

    def save() {
        try {
            planService.create(JSON.parse(request) as Map) as JSON
            Map result = [success: true]
            respond result

        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }

    }
}
