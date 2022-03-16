package com.numpyninja.lms.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.numpyninja.lms.entity.AssignmentEntity;
import com.numpyninja.lms.entity.ProgBatchEntity;
import com.numpyninja.lms.services.AssignmentService;
import com.numpyninja.lms.services.ProgBatchServices;
import com.numpyninja.lms.services.ProgramServices;

@Controller
@RequestMapping("/assignment")
public class AssignmentController extends BaseController {
	
	@Autowired
	private AssignmentService assignmentService;
	@Autowired
	private ProgBatchServices progBatchServices;
	@Autowired
	private ProgramServices programServices;
	
	//Get all the Assignments
    public AssignmentController() {
        super("assignment");
    }  
    
    @Override
    protected List<?> getAll(String searchString) {
    	 if (StringUtils.isNotBlank(searchString)) {
    		 return assignmentService.getAllAssignmentsForBatch(Integer.parseInt(searchString));
         } else {
             return assignmentService.getAllAssignments();
         }
    }
    
    private void populateDropdowns(Model model){
        model.addAttribute("programs", programServices.getAllPrograms());
        model.addAttribute("batches", progBatchServices.findBatchByProgramId((long)1));
    }
	
	//get new assignment form
	@GetMapping("/addView") 
	String addAssignment(Model model) {
		model.addAttribute("model", new AssignmentEntity());
		populateDropdowns(model);
		return "LMSAddAssignment";	
	 }
	
	//get all batches of a program
	@GetMapping("/batches") 
	@ResponseBody List<ProgBatchEntity> getBatchesForProgram(Model model, @RequestParam(value = "programId", required = true) Long programId) {
		//model.addAttribute("batches", progBatchServices.findBatchByProgramId(programId));
		//model.addAttribute("model", model);
		//return "LMSAddAssignment :: resultsList";
		return progBatchServices.findBatchByProgramId(programId);
	 }
	 
	
	//create an assignment
	@PostMapping("/add")
	String createAssignment(@ModelAttribute AssignmentEntity assignment, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		System.out.println(ReflectionToStringBuilder.toString(assignment));
		if(bindingResult.hasErrors()) {
			populateDropdowns(model);
			model.addAttribute("errors", formatErrors(bindingResult));
            model.addAttribute("model", assignment);
			return "LMSAddAssignment";
		}
		assignment.setCreatedBy("U02");
		assignment.setGraderId("U02");
		assignmentService.createAssignment(assignment);
		redirectAttributes.addFlashAttribute("message", "Assignment " + assignment.getAssignmentName() + " with Id: " + assignment.getAssignmentId() + " created successfully!");
		return "redirect:/assignment"; 
	}
	
	//get assignment by id
	@GetMapping("/editView/{id}")
	String getAssignmentById(@PathVariable(value="id") Long id, Model model) {
		AssignmentEntity assignment = assignmentService.getAssignmentById(id);
		ProgBatchEntity batchId = progBatchServices.findBatchById(assignment.getBatchId()).get();
		model.addAttribute("programId", batchId.getBatchProgramId());
		model.addAttribute("model", assignment);
		System.out.println("batchId---"+batchId+"_____pId--"+batchId.getBatchProgramId());
        populateDropdowns(model);
		return "LMSEditAssignment";
	}
	
	//update an assignment
	@PostMapping("update")
	String updateAssignment(@PathVariable(value="id") Long id, @Valid AssignmentEntity assignment, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			populateDropdowns(model);
			assignment.setAssignmentId(id);
			ProgBatchEntity batchId = progBatchServices.findBatchById(assignment.getBatchId()).get();
			model.addAttribute("programId", batchId.getBatchProgramId());
			model.addAttribute("errors", formatErrors(bindingResult));
            model.addAttribute("model", assignment);
			return "LMSEditAssignment";
		}
		assignment.setCreatedBy("U02");
		assignment.setGraderId("U02");
		System.out.println("create assignment --" + assignment);
		assignmentService.updateAssignment(assignment);
		redirectAttributes.addFlashAttribute("message", "Assignment " + assignment.getAssignmentName() + " with Id: " + assignment.getAssignmentId() + " updated successfully!");
		return "redirect:/assignment"; 
	}
	
	//delete an assignment
	@GetMapping("/delete/{id}")
	String deleteAssignment(@PathVariable(value= "id") Long id, Model model, RedirectAttributes redirectAttributes) {
		assignmentService.deleteAssignment(id);
		redirectAttributes.addFlashAttribute("message", "Assignment with Id: " + id + " deleted Successfully!");
		return "redirect:/assignment";
	}
	
}
