/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.petstore.repository;

import com.example.petstore.models.PetOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author reneilson
 */
@Repository
public interface PetOrderRepository extends CrudRepository<PetOrder, Long>{
    
}
