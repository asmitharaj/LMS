package com.numpyninja.lms.controller;

import java.math.BigDecimal;
import java.sql.Date;

public interface Grades {
	Long getSubmissionId();
	String getGradedBy();
	Date getGradedTime();
	BigDecimal getGrade();
	Date getLastModTime();
}
