package com.nwafu.PISMDB.Reposity;

import com.nwafu.PISMDB.entity.CheckedInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CheckRepository extends CrudRepository<CheckedInformation,Integer> {

}
