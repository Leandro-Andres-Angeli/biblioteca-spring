
package com.example.biblioteca.controladores;

import com.example.biblioteca.entidades.Autor;
import com.example.biblioteca.entidades.Editorial;
import com.example.biblioteca.servicios.AutorServicio;
import com.example.biblioteca.servicios.EditorialServicio;
import com.example.biblioteca.servicios.LibroServicio;
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
@RequestMapping("/libro")
public class LibroControlador {
    @Autowired
    private LibroServicio libroServicio;
    @Autowired
    private AutorServicio autorServicio;
     @Autowired
    private EditorialServicio editorialServicio;
    @GetMapping("/registrar")
    public String registrar(ModelMap modelo){
        
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        modelo.addAttribute("autores",autores);
        modelo.addAttribute("editoriales",editoriales);
    return "libro_form.html";
    
    }
    @PostMapping("/registro")
    public String registro(@RequestParam String titulo,@RequestParam(required = false) Long isbn,@RequestParam(required = false) Integer ejemplares,@RequestParam String idAutor,@RequestParam String idEditorial, ModelMap modelo) throws MiExcepcion{
  
           
         try{
          libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
          modelo.put("exito", "Libro cargado correctamente");
          return "libro_form.html";
          
          }
         catch(MiExcepcion ex){
             List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        modelo.addAttribute("autores",autores);
        modelo.addAttribute("editoriales",editoriales);
             modelo.put("error",ex.getMessage());
            return "libro_form.html";
         }
         
         
    }
}
