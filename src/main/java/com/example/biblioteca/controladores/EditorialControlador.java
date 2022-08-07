
package com.example.biblioteca.controladores;

import com.example.biblioteca.servicios.EditorialServicio;
import excepciones.MiExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editorial")
public class EditorialControlador {
    @Autowired
    private EditorialServicio editorialServicio;
    @GetMapping("/registrar")
    public String registrar(){
    return "editorial_form.html";
    }
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre,ModelMap modelo){
        try{
        editorialServicio.crearEditorial(nombre);
        modelo.put("exito", "editorial cargada");
        }
        catch(MiExcepcion ex){
             modelo.put("error", ex.getMessage());
             return "editorial_form.html";
        }
        return "index.html";
    }
}
