package com.clases.internet

import grails.converters.JSON

class ContratoController {

    ContratoService contratoService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def show(Long id) {
        respond contratoService.get(id)
    }


    //Pendiente el search

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
