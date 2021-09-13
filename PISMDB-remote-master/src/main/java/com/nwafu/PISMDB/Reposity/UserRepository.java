package com.nwafu.PISMDB.Reposity;

import com.nwafu.PISMDB.entity.Compounds;
import com.nwafu.PISMDB.entity.Temp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface UserRepository extends CrudRepository<Temp, String> {

}