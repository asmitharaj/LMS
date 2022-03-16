package com.numpyninja.lms.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_lms_submissions")
public class SubmissionEntity {

	@Id
	@Column(name="sub_id")
	private long submissionId;
	
	@Column(name="sub_a_id")
	private int submissionAssignmentId;

	@Column(name="sub_student_id")
	private String submissionStudentId;

	@Column(name="sub_description")
	private String description;

	@Column(name="sub_comments")
	private String comments;

	@Column(name="sub_path_attach1")
	private String pathAttachment1;

	@Column(name="sub_path_attach2")
	private String pathAttachment2;

	@Column(name="sub_path_attach3")
	private String pathAttachment3;

	@Column(name="sub_path_attach4")
	private String pathAttachment4;

	@Column(name="sub_path_attach5")
	private String pathAttachment5;

	@Column(name="sub_datetime")
	private Date subDateTime;

	@Column(name="graded_by")
	private String gradedBy;

	@Column(name="graded_datetime")
	private Date gradedTime;

	@Column(name="grade")
	private BigDecimal grade;

	@Column(name="creation_time")
	private Date creationTime;

	@Column(name="last_mod_time")
	private Date lastModTime;

}
