package com.numpyninja.lms.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.numpyninja.lms.controller.Grades;
import com.numpyninja.lms.entity.SubmissionEntity;


@Repository
public interface SubmissionRepository extends JpaRepository<SubmissionEntity, Long> {

	public List<SubmissionEntity> findBySubmissionAssignmentId(Integer assignmentId);

	public List<SubmissionEntity> findBySubmissionStudentId(String studentId);

	public SubmissionEntity findBySubmissionAssignmentIdAndSubmissionStudentId(Integer assignmentId, String studentId);

	/*@Query("SELECT sub FROM tbl_lms_submissions sub INNER JOIN tbl_lms_assignments assign "
			+ "ON sub.sub_a_id = assign.a_id and assign.a_batch_id = ?1")
	public List<SubmissionEntity> findSubmissionsUsingJpql(int batchId);*/

	public SubmissionEntity findBySubmissionId(Long submissionId);

	public List<SubmissionEntity> findBySubmissionAssignmentIdIn(List<Integer> assignmentIds);
	
	//get grades by a student 
	@Query("select sub_id, graded_by, graded_datetime, grade, last_mod_time from tbl_lms_submissions s where s.sub_student_id =:studentId")
	List<Grades> findGradesBySubmissionStudentId(String studentId);
	
	//get grades of an assignment
	@Query("select sub_id, graded_by, graded_datetime, grade, last_mod_time from tbl_lms_submissions s where s.sub_a_id =:assignmentId")
	List<Grades> findGradesBySubmissionAssignmentId(Integer assignmentId);
	
	public List<Grades> findGradesBySubmissionAssignmentIdIn(List<Integer> assignmentIds);


}
