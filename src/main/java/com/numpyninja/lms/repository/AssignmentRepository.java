package com.numpyninja.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.numpyninja.lms.entity.AssignmentEntity;


@Repository
public interface AssignmentRepository extends JpaRepository<AssignmentEntity, Long> {
	
	public List<AssignmentEntity> findByBatchId(int batchId);

}
