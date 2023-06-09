package com.clases.internet

class UrlMappings {

    static mappings = {
        delete "/$controller/$id(.$format)?"(action:"delete")
        get "/$controller(.$format)?"(action:"index")
        get "/$controller/$id(.$format)?"(action:"show")
        post "/$controller(.$format)?"(action:"save")
        put "/$controller/$id(.$format)?"(action:"update")
        patch "/$controller/$id(.$format)?"(action:"patch")

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')

        post '/contrato/save' (controller: 'contrato', action: 'save')
        post '/cliente/save' (controller: 'cliente', action: 'save')
        post '/direccion/save' (controller: 'direccion', action: 'save')
        post '/plan/save' (controller: 'plan', action: 'save')
        post "/contrato/search"(controller:"contrato", action: "search")
        post "/cliente/search"(controller:"cliente", action: "search")
        get '/cliente/obtenerNombres' (controller: 'cliente', action: 'obtenerNombres')


    }
}
