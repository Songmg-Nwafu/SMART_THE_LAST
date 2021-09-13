package com.nwafu.PISMDB.Reposity;

import com.nwafu.PISMDB.entity.Correction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorrectionRepository extends JpaRepository<Correction,Integer> {
}
