
package com.example.biblioteca.servicios;

import com.example.biblioteca.entidades.Editorial;
import com.example.biblioteca.repositorio.EditorialRepositorio;
import excepciones.MiExcepcion;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {
    @Autowired
    EditorialRepositorio editorialRepositorio;
    @Transactional
    public void crearEditorial(String nombre) throws MiExcepcion{
         if(nombre.trim().isEmpty()){
 throw new  MiExcepcion("el nombre no puede estar vacio");
 }       
    Editorial editorial = new Editorial();
    editorial.setNombre(nombre);
    editorialRepositorio.save(editorial);
    }
        public List<Editorial> listarEditoriales(){
       List<Editorial> editoriales = new ArrayList();
       editoriales = editorialRepositorio.findAll();
       return editoriales; 
   }
        public void modificarEditorial(String id,String nombre){
            Optional<Editorial> respuesta =editorialRepositorio.findById(id);
            if(respuesta.isPresent()){
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);
            editorialRepositorio.save(editorial);
            }
        }
        
}
