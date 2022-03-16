package com.numpyninja.lms.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numpyninja.lms.entity.AssignmentEntity;
import com.numpyninja.lms.entity.ProgBatchEntity;
import com.numpyninja.lms.repository.AssignmentRepository;


@Service
public class AssignmentService {
	
	@Autowired
	private AssignmentRepository assignmentRepository;

	// get all assignments
    public List<AssignmentEntity> getAllAssignments() {
        return assignmentRepository.findAll();
    }

	
	//get all assignments for batch
	public List<AssignmentEntity> getAllAssignmentsForBatch(Integer batchId) {
		List<AssignmentEntity> assignments = new ArrayList<>();
		assignmentRepository.findByBatchId(batchId)
		.forEach(assignments::add);
		return assignments;
	}
		
	//save an assignment 
	public AssignmentEntity createAssignment(AssignmentEntity assignment) {
		return assignmentRepository.save(assignment);
	}
	
	//get assignment by id
	public AssignmentEntity getAssignmentById(Long id) {
		return assignmentRepository.findById(id).orElseThrow();
	}
	
	public AssignmentEntity updateAssignment(AssignmentEntity updatedAssignment) {
		return assignmentRepository.save(updatedAssignment);
	}
	
	public void deleteAssignment(Long id) {
		AssignmentEntity assignment = assignmentRepository.findById(id).orElseThrow();
		assignmentRepository.delete(assignment);
	}
	
	
}
