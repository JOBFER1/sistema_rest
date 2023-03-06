package com.sistema.blog.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.blog.dto.ComentarioDTO;
import com.sistema.blog.servicio.ComentarioServicio;

@RestController
@RequestMapping("/api/")
public class ComentarioControlador {

	@Autowired
	private ComentarioServicio comentarioServicio;
	
	@PostMapping("/publicaciones/{publicacionId}/comentarios")
	public ResponseEntity<ComentarioDTO> guardarComentario(@PathVariable(value = "publicacionId") long publicacionId,@RequestBody ComentarioDTO comentarioDTO){
		return new ResponseEntity<>(comentarioServicio.crearComentario(publicacionId, comentarioDTO),HttpStatus.CREATED);
	}
	
	@GetMapping("/publicaciones/{publicacionId}/comentarios")
	public List<ComentarioDTO> listarComentariosPorPublicacionId(@PathVariable(value = "publicacionId") Long publicacionId){
		return comentarioServicio.obtenerComentariosPorPublicacionId(publicacionId);
	}
	
	@GetMapping("/publicaciones/{publicacionId}/comentarios/{id}")
	public ResponseEntity<ComentarioDTO> obtenerComentarioPorId(@PathVariable(value = "publicacionId") Long publicacionId,@PathVariable(value = "id") Long comentarioId){
		ComentarioDTO comentarioDTO = comentarioServicio.obtenerComentarioPorId(publicacionId, comentarioId);
		return new ResponseEntity<>(comentarioDTO,HttpStatus.OK);
	}
}
