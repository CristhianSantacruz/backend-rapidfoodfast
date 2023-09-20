package com.example.comidasapi.controllers;

import com.example.comidasapi.ComidasApiApplication;
import com.example.comidasapi.dtos.ComidaRecordDto;
import com.example.comidasapi.models.ComidaModel;
import com.example.comidasapi.respositories.ComidaRespository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="*",allowedHeaders ="*" )
@RequestMapping("foods")
public class ComidaController {

    @Autowired
    ComidaRespository comidaRespository;
    @CrossOrigin(origins="*",allowedHeaders ="*" )
    @PostMapping("/add")
    public ResponseEntity<ComidaModel> savefood(@RequestBody @Valid ComidaRecordDto comidaRecordDto){
        var comidaModel = new ComidaModel();
        BeanUtils.copyProperties(comidaRecordDto,comidaModel);
        if(comidaModel.getCompany()==null || comidaModel.getCompany().isEmpty()){
            comidaModel.setCompany("Food");
        }
        if(comidaModel.getDirection()==null || comidaModel.getDirection().isEmpty() ){
            comidaModel.setDirection("nodirection");
        }
        if(comidaModel.getLink()==null  || comidaModel.getLink().isEmpty() || comidaModel.getLink().length()==1){
            comidaModel.setLink("Contact");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(comidaRespository.save(comidaModel));
    }
    @CrossOrigin(origins="*",allowedHeaders ="*" )
    @GetMapping
    public ResponseEntity<List<ComidaModel>> getAllComidas(){
        return ResponseEntity.status(HttpStatus.CREATED).body(comidaRespository.findAll());
    }
    @CrossOrigin(origins="*",allowedHeaders ="*" )
    @GetMapping("{id}")
    public ResponseEntity<Object> getComidaById(@PathVariable(value="id") long id){
        Optional<ComidaModel>  comida = comidaRespository.findById(id);
        return comida.<ResponseEntity<Object>>map(clienteModel -> ResponseEntity.status(HttpStatus.OK).body(comida)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found"));
    }


    @PutMapping("{id}")

    public ResponseEntity<Object> updateComida(
            @PathVariable(value="id") long id,
            @RequestBody @Valid ComidaRecordDto comidaRecordDto

    ){
        Optional<ComidaModel> comida = comidaRespository.findById(id);
        if(comida.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
        var comidaModel = comida.get();
        BeanUtils.copyProperties(comidaRecordDto,comidaModel);
        if(comidaModel.getCompany()==null || comidaModel.getCompany().isEmpty()){
            comidaModel.setCompany("Food");
        }
        if(comidaModel.getLink()==null  || comidaModel.getLink().isEmpty() || comidaModel.getLink().length()==1){
            comidaModel.setLink("Contact");
        }
        if(comidaModel.getDirection()==null || comidaModel.getDirection().isEmpty() ){
            comidaModel.setDirection("nodirection");
        }
        return ResponseEntity.status(HttpStatus.OK).body(comidaRespository.save(comidaModel));

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteComida(@PathVariable(value="id") long id){
        Optional<ComidaModel> comida = comidaRespository.findById(id);
        if(comida.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
       comidaRespository.delete(comida.get());
        return ResponseEntity.status(HttpStatus.OK).body("deleted succesfully");
         // comidaRespository.deleteById(id); change return to void
    }

    @DeleteMapping
    public void deleteAllComida(){
        comidaRespository.deleteAll();
    }



}
