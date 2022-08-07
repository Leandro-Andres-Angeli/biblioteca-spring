
package com.example.biblioteca.servicios;

import com.example.biblioteca.entidades.Autor;
import com.example.biblioteca.entidades.Editorial;
import com.example.biblioteca.entidades.Libro;
import com.example.biblioteca.repositorio.AutorRepositorio;
import com.example.biblioteca.repositorio.EditorialRepositorio;
import com.example.biblioteca.repositorio.LibroRepositorio;
import excepciones.MiExcepcion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiExcepcion{
          
        validar(isbn, titulo, idAutor, idEditorial, ejemplares);
        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);

        libro.setAlta(new Date());
      
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        System.out.println(libro);
        libroRepositorio.save(libro);

    }

    public List<Libro> listarLibro() {
        List<Libro> libros = new ArrayList();
        libros = libroRepositorio.findAll();
        return libros;
    }

    public void modificarLibro(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiExcepcion {
//       Libro libro = libroRepositorio.findById(isbn).get();
validar(isbn, titulo, idAutor, idEditorial, ejemplares);
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);
        Autor autor = new Autor();
        Editorial editorial = new Editorial();
        if (respuestaAutor.isPresent()) {
            autor = respuestaAutor.get();
        }
        if (respuestaEditorial.isPresent()) {
            editorial = respuestaEditorial.get();
        }
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(ejemplares);
            libroRepositorio.save(libro);
        }

    }
    private void validar(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiExcepcion{
         if(isbn == null){
            throw new MiExcepcion("el isbn no puede ser nulo"); 
        }
        if(titulo.isEmpty() || titulo == null){
            throw new MiExcepcion("el titulo no puede estar vacio"); 
        }
            if(ejemplares == null){
            throw new MiExcepcion("los ejemplares no pueden ser nulos"); 
        }
        if(idAutor.isEmpty() || idAutor == null){
            throw new MiExcepcion("id autor no puede estar vacio"); 
        }
        if(idEditorial.isEmpty() || idEditorial == null){
            throw new MiExcepcion("id editorial no puede estar vacio"); 
        }
    }
}
