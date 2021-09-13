package com.nwafu.PISMDB.Reposity;

import com.nwafu.PISMDB.entity.CorInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CorInfoRepository extends JpaRepository<CorInfo,Integer> {
    public List<CorInfo> findByPismid(String pismid);
}
