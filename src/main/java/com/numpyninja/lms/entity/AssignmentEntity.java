package com.numpyninja.lms.entity;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_lms_assignments")
public class AssignmentEntity {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="a_id")
	private long assignmentId;
	
	@NotEmpty(message = "Enter Assignment Name")
	@Column(name="a_name")
	private String assignmentName;

	@NotEmpty(message = "Enter Assignment Description")
	@Column(name="a_description")
	private String assignmentDescription;

	@Column(name="a_comments")
	private String comments;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE	)
	@NotNull(message = "Select Due Date")
	@Column(name="a_due_date")
	private Date dueDate;
	
	@Column(name="a_path_attach1")
	private String pathAttachment1;
	
	@Column(name="a_path_attach2")
	private String pathAttachment2;

	@Column(name="a_path_attach3")
	private String pathAttachment3;
	
	@Column(name="a_path_attach4")
	private String pathAttachment4;

	@Column(name="a_path_attach5")
	private String pathAttachment5;

	@NotEmpty
	@Column(name="a_created_by")
	private String createdBy;

	@NotNull(message = "Select a batch")
	@Column(name="a_batch_id")
	private Integer batchId;
	
	@NotEmpty
	@Column(name="a_grader_id")
	private String graderId;
	
}
