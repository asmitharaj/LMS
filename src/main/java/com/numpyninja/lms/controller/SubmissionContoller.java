package com.numpyninja.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.numpyninja.lms.entity.SubmissionEntity;
import com.numpyninja.lms.services.SubmissionService;


@RestController
@RequestMapping("/")
public class SubmissionContoller {

	@Autowired
	private SubmissionService submissionService;
	
	//@Autowired
	//private ModelMapper modelMapper;

	//create a submission	
	@PostMapping("assignmentsubmission")
	SubmissionEntity createSubmission(@RequestBody SubmissionEntity submission) {
		return submissionService.createSubmission(submission);
	}

	//get all the submissions for an assignment 
	@GetMapping("assignmentsubmission/{assignmentId}") 
	List<SubmissionEntity> getSubmissionsForAssignment(@PathVariable(value="assignmentId") Integer assignmentId) {
		return submissionService.getSubmissionsForAssignment(assignmentId); 
	}


	//get the submissions for an assignment by a student  
	@GetMapping("assignmentsubmission/assignmentIdanduserId")
	SubmissionEntity getSubmissionsForAssignmentByUser(@RequestParam Integer assignmentId, @RequestParam String userId) {
		return submissionService.getSubmissionsForAssignmentByUser(assignmentId, userId); 
	}


	//get submissions by a student
	@GetMapping("assignmentsubmission/student/{UserId}")
	List<SubmissionEntity> getSubmissionsByStudent(@PathVariable(value="UserId") String studentId) {
		return submissionService.getSubmissionsByStudent(studentId); 
	}


	
	//get all the submissions by a batch  
	@GetMapping("assignmentsubmission/batch/{batchId}") List<SubmissionEntity>
	getSubmissionsByBatch(@PathVariable(value="batchId") Integer  batchId) { 
		return submissionService.getSubmissionsByBatchId(batchId); 
	}
	 


	//update a submission
	@PutMapping("assignmentsubmission/{id}")
	SubmissionEntity updateSubmission(@PathVariable(value="id") Long id, @RequestBody SubmissionEntity updatedSubmission) {
		return submissionService.updateSubmission(updatedSubmission);
	}

	//delete a submission
	@DeleteMapping("assignmentsubmission/{id}")
	void deleteSubmission(@PathVariable(value= "id") Long id) {
		submissionService.deleteSubmission(id);
	}
	
	//assign a grade
	@PutMapping("grades/{id}")
	void assignGrade(@PathVariable(value="id") Long submissionId, @RequestBody SubmissionEntity updatedGrade) { 
		submissionService.assignGrade(submissionId, updatedGrade); 
	}

	//get grades by student
	@GetMapping("grades/student/{studentId}") 
	List<Grades> getGradesByStudent(@PathVariable(value="studentId") String studentId) {
		return submissionService.getGradesByStudent(studentId);
		//.stream().map(grade -> modelMapper.map(grade, GradesDTO.class)).collect(Collectors.toList()); 
	}

	//get grades by assignment
	@GetMapping("grades/{assignmentId}") 
	List<Grades> getGradesByAssignment(@PathVariable(value="assignmentId") Integer assignmentId) {
		return submissionService.getGradesByAssignment(assignmentId); 
	}
	
	//get grades of a batch
	@GetMapping("grades/batch/{batchId}") 
	List<Grades> getGradesByBatch(@PathVariable(value="batchId") Integer batchId) {
		return submissionService.getGradesByBatch(batchId); 
	}
	
	//get grades of a program
	@GetMapping("grades/program/{programId}") 
	List<Grades> getGradesByProgram(@PathVariable(value="programId") Long programId) {
		return submissionService.getGradesByProgram(programId); 
	}

}
