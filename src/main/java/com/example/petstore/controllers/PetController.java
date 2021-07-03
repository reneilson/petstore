package com.example.petstore.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.petstore.models.Pet;
import com.example.petstore.repository.PetRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/pets")
public class PetController {
	
	private final PetRepository repository;
	
	@Autowired
	public PetController(PetRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public ResponseEntity<?> get(Pageable pageable, Authentication authentication){
		System.out.println(authentication.getName());
		return new ResponseEntity<>(this.repository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<?> getPetById(@PathVariable(value="id") Integer id){
        return new ResponseEntity<>(this.repository.findById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> putPet(@PathVariable Integer id, @RequestBody Pet newPet){
    	Optional<Pet> petFound = this.repository.findById(id);
        if(petFound.isPresent() && petFound.get().getId() == id){
			Pet pet = petFound.get();
			pet.setName(newPet.getName());
			pet.setCategory(newPet.getCategory());
			pet.setPhotoUrl(newPet.getPhotoUrl());
			pet.setStatus(newPet.getStatus());
			this.repository.save(pet);
			return new ResponseEntity<>(pet, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Item não encontrado", HttpStatus.NOT_FOUND);
        }
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<?> patchPet(@PathVariable(value="id") Integer id, @RequestBody Pet newPet){
    	Optional<Pet> petFound = this.repository.findById(id);
        if(petFound.isPresent() && petFound.get().getId().equals(id)){
			Pet pet = petFound.get();
			if(!newPet.getName().isEmpty()) {
				pet.setName(newPet.getName());
			}
			if(newPet.getCategory() != null) {
				pet.setCategory(newPet.getCategory());
			}
			if(!newPet.getPhotoUrl().isEmpty()) {
				pet.setPhotoUrl(newPet.getPhotoUrl());
			}
			if(newPet.getStatus() != null) {
				pet.setStatus(newPet.getStatus());
			}
			this.repository.save(pet);
			return new ResponseEntity<>(pet, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Item não encontrado", HttpStatus.NOT_FOUND);
        }
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> deletePet(@PathVariable(value="id") Integer id){
		this.repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
	@PostMapping
	public ResponseEntity<?> post(@RequestBody Pet pet){
		Pet savedPet = this.repository.save(pet);
        return new ResponseEntity<>(savedPet, HttpStatus.CREATED);
	}


}
