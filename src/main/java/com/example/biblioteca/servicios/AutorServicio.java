/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.biblioteca.servicios;

import com.example.biblioteca.entidades.Autor;
import com.example.biblioteca.repositorio.AutorRepositorio;
import excepciones.MiExcepcion;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kurco
 */
@Service
public class AutorServicio {
    @Autowired
    AutorRepositorio autorRepositorio;
    @Transactional
    public void crearAutor(String nombre) throws MiExcepcion{
 if(nombre.trim().isEmpty()){
 throw new  MiExcepcion("el nombre no puede estar vacio");
 }        
    Autor autor = new Autor();
    autor.setNombre(nombre);
    autorRepositorio.save(autor);
    }
      public List<Autor> listarAutores(){
       List<Autor> autores = new ArrayList();
       autores = autorRepositorio.findAll();
       return autores; 
   }
      public void modificarAutor(String nombre,String id){
          Optional<Autor> respuesta = autorRepositorio.findById(id);
          if(respuesta.isPresent()){
          Autor autor = respuesta.get();
          autor.setNombre(nombre);
          autorRepositorio.save(autor);
          }
      }
}
