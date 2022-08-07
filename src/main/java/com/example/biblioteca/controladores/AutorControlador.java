
package com.example.biblioteca.controladores;


import com.example.biblioteca.entidades.Autor;
import com.example.biblioteca.servicios.AutorServicio;
import excepciones.MiExcepcion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/autor")
public class AutorControlador {
    @Autowired
    private AutorServicio autorServicio;
       @GetMapping("/registrar") 
       public String registrar(){
       return "autor_form.html";
       }
       @PostMapping("/registro")
    public String registro(@RequestParam String nombre,ModelMap modelo ) throws MiExcepcion{
         
        try{
          autorServicio.crearAutor(nombre);
           modelo.put("exito", "Libro cargado correctamente");
          }
        catch(MiExcepcion ex){
            
             modelo.put("error", ex.getMessage());
            return "autor_form.html";
        }
         
          return "index.html";
    }
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
    List<Autor> autores = autorServicio.listarAutores();
    modelo.addAttribute("autores",autores);
    return "autor_list.html";
    }
        
}
