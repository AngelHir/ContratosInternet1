package com.clases.internet

import grails.converters.JSON
//Controlador de la clase Plan, donde se definen los metodos que tendra la clase


class PlanController {

    // Este es el servicio que utilizara el controlador de Plan
    PlanService planService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    // Metodo que crea una lista de precios

    def obtenerPrecio(){
        def precioslist= planService.obtenerPrecio()
        respond(precioslist)
    }

    // Metodo que crea una lista de paquetes

    def obtenerPaquete(){
        def paqueteslist= planService.obtenerPaquete()
        respond(paqueteslist)
    }

    // Metodo de guardado, retorna un mensaje de completado cuando se completa

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
