package com.numpyninja.lms.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numpyninja.lms.controller.Grades;
import com.numpyninja.lms.entity.SubmissionEntity;
import com.numpyninja.lms.repository.SubmissionRepository;


@Service
public class SubmissionService {

	@Autowired
	private SubmissionRepository submissionRepository;
	
	@Autowired
	private AssignmentService assignmentService;
	
	@Autowired
	private ProgBatchServices batchServices;

	//create a submission
	public SubmissionEntity createSubmission(SubmissionEntity submission) {
		return submissionRepository.save(submission);
	}

	//get all the submissions for an assignment 
	public List<SubmissionEntity> getSubmissionsForAssignment(int assignmentId) {
		return submissionRepository.findBySubmissionAssignmentId(assignmentId);
	}


	//get all the submission for an assignment by a student
	public SubmissionEntity getSubmissionsForAssignmentByUser(int assignmentId, String
			userId) { 
		return submissionRepository.findBySubmissionAssignmentIdAndSubmissionStudentId(
				assignmentId, userId); 
	}

	//get submissions by a student
	public List<SubmissionEntity> getSubmissionsByStudent(String studentId) {
		return submissionRepository.findBySubmissionStudentId(studentId); 
	}

	//Get all the Submissions for assignment id
	public List<SubmissionEntity> getSubmissionsForAssignments(List<Long> ids){
		List<Integer>  assignmentIds = ids.stream()
                .map(Long::intValue)
                .collect(Collectors.toList());
		return submissionRepository.findBySubmissionAssignmentIdIn(assignmentIds);
	}
	
	//Get submissions by a batch
	public List<SubmissionEntity> getSubmissionsByBatchId(Integer batchId){
		List<Long> assignmentIds = assignmentService.getAllAssignmentsForBatch(batchId).stream().map(assignment -> assignment.getAssignmentId()).collect(Collectors.toList());
		return getSubmissionsForAssignments(assignmentIds); 
	}

	//update a submission
	public SubmissionEntity updateSubmission(SubmissionEntity updatedSubmission) {
		return submissionRepository.save(updatedSubmission);
	}

	//delete a submission
	public void deleteSubmission(Long id) {
		SubmissionEntity submission = submissionRepository.findBySubmissionId(id);
		submissionRepository.delete(submission);
	}

	//assign a grade 
	public void assignGrade(Long submissionId, SubmissionEntity updatedGrade) { 
		SubmissionEntity submissionEntity = submissionRepository.findBySubmissionId(submissionId);
		submissionEntity.setGradedBy(updatedGrade.getGradedBy());
		submissionEntity.setGradedTime(updatedGrade.getGradedTime());
		submissionEntity.setGrade(updatedGrade.getGrade());
		submissionEntity.setLastModTime(updatedGrade.getLastModTime());
		submissionRepository.save(submissionEntity); 
	}


	//get grades by a student 
	public List<Grades> getGradesByStudent(String studentId) { 
		return submissionRepository.findGradesBySubmissionStudentId(studentId); 
	}

	//get grades of an assignment
	public List<Grades> getGradesByAssignment(Integer asssignmentId) { 
		return submissionRepository.findGradesBySubmissionAssignmentId(asssignmentId); 
	}
	
	//get grades of a batch
	public List<Grades> getGradesByBatch(Integer batchId) { 
		List<Long> assignmentIds = assignmentService.getAllAssignmentsForBatch(batchId).stream().map(assignment -> assignment.getAssignmentId()).collect(Collectors.toList());
		return getGradesForAssignments(assignmentIds); 
	}
	

	//Get all the grades for assignment id
	public List<Grades> getGradesForAssignments(List<Long> ids){
		List<Integer>  assignmentIds = ids.stream()
                .map(Long::intValue)
                .collect(Collectors.toList());
		return submissionRepository.findGradesBySubmissionAssignmentIdIn(assignmentIds);
	}
	
	//get grades of a program
	public List<Grades> getGradesByProgram(Long programId) { 
		List<Integer> batchIds = batchServices.findBatchByProgramId(programId).stream().map(batch -> batch.getBatchId()).collect(Collectors.toList());
		//List<Long> assignmentIds = assignmentService.getAllAssignmentsForBatch(batchId).stream().map(assignment -> assignment.getAssignmentId()).collect(Collectors.toList());
		return getAssignmentsForBatch(batchIds); 
	}


	//Get all the assignments for batch
	public List<Grades> getAssignmentsForBatch(List<Integer> ids){
		List<Integer>  batchIds = ids.stream()
                .collect(Collectors.toList());

		List<Grades> grades = new ArrayList<>();
		batchIds.forEach(a -> grades.addAll(getGradesByBatch(a)));
		return grades;
	}

}
