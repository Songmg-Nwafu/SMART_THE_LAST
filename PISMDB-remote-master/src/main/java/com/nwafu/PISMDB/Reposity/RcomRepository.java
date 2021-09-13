package com.nwafu.PISMDB.Reposity;

import com.nwafu.PISMDB.entity.Rcom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RcomRepository extends CrudRepository<Rcom, String> {
}
