package cn.tata.t2s.ssm.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.tata.t2s.ssm.entity.Enroll;
import cn.tata.t2s.ssm.entity.Project;
import cn.tata.t2s.ssm.service.TeacherService;

@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseController{
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TeacherService teacherService;
	
	
	@GetMapping(value = "/project", 
    		params = "submitFlag=self_query",
    		produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<Project> getSelfProjectList(
    		@ModelAttribute("personId") String personId, 
    		@RequestParam("stateName") String stateName, 
    		@RequestParam("offset") int offset,
    		@RequestParam("limit") int limit) {
		
		List<Project> projectList = teacherService.getSelfProjectList(personId, stateName, offset, limit);
		
		LOG.info("invoke----------/getSelfProjectList" + "by" + personId);
		
		return projectList;
	}
	
	@GetMapping(value = "/project/{proejectId}/enroll", 
    		params = "submitFlag=query",
    		produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<Enroll> getProjectEnrollInfo(
    		@ModelAttribute("personId") String personId, 
    		@PathVariable int projectId, 
    		@RequestParam("offset") int offset,
    		@RequestParam("limit") int limit) {
		
		List<Enroll> enrollList = teacherService.getProjectEnrollInfo(personId, projectId, offset, limit);
		
		LOG.info("invoke----------/getProjectEnrollInfo" +
		"[" + projectId + "] " + "by" + personId);
		
		return enrollList;
	}
	
	@GetMapping(value = "/project/{proejectId}/enroll/{enrollId}", 
    		params = "submitFlag=single_query",
    		produces="application/json;charset=UTF-8")
	@ResponseBody
	public Enroll getEnrollInfo(
    		@ModelAttribute("personId") String personId, 
    		@PathVariable("proejectId") int projectId, 
    		@PathVariable("enrollId") long enrollId) {
		
		Enroll enroll = teacherService.getEnroll(enrollId, personId, projectId);
		
		LOG.info("invoke----------/getEnrollInfo " +
		"[" + enrollId + "] " + "in project " + "[" + projectId + "] " + "by " + personId);
		
		return enroll;
	}
	
	@PostMapping(value = "/project", 
    		params = "submitFlag=create",
    		consumes = "application/json;charset=UTF-8")
	public ModelAndView createProject(
    		@RequestBody Project project, 
    		BindingResult result) {
		
		teacherService.createProject(project);
		
		LOG.info("invoke----------/createProject" + 
		"by" + project.getPerson().getPersonId());
		
		ModelAndView mv = new ModelAndView("create_success");
		mv.addObject("create_type_name", "project");
		return mv;
    }
	
	 @PostMapping(value = "/project/{projectId}", 
	    		params = "submitFlag=update",
	    		consumes = "application/json;charset=UTF-8")
	 public ModelAndView saveProject(
			 @RequestBody Project project, 
	    	 BindingResult result,
	    	 @PathVariable int projectId) {
		 
		
		if(project.getProjectId() != projectId) {
				 project.setProjectId(projectId);
		}
		 
		 
		teacherService.saveProject(project);
		
	    LOG.info("invoke----------/updateProject" +
	    "[" + project.getProjectId() + "] "
	    + "by" + project.getPerson().getPersonId());
	    
	    ModelAndView mv = new ModelAndView("update_success");
	    mv.addObject("update_type_name", "project");
		return mv;	    	
	 }
	 
	 @PostMapping(value = "/project/{projectId}/state", 
	    		params = "submitFlag=update")
	 public ModelAndView saveProjectState(
			 @ModelAttribute("personId") String personId,
			 @RequestParam("stateName") String sateName, 
	    	 @PathVariable int projectId) {
		 
		teacherService.saveProjectState(personId, projectId, sateName, null);
		
	    LOG.info("invoke----------/updateProjectState" +
	    "[" + projectId + "] " + "by" + personId);
	    
	    ModelAndView mv = new ModelAndView("update_success");
	    mv.addObject("update_type_name", "project_state");
		return mv;	    	
	 }
	 
	 @PostMapping(value = "/project/{projectId}/enroll", 
	    		params = "submitFlag=update")
	 public ModelAndView admitStudentEnroll(
			 @ModelAttribute("personId") String personId,
			 @PathVariable("projectId") int projectId,
			 @RequestParam("enrolledPersonId") String enrolledPersonId) {
		teacherService.admitStudentEnroll(personId, enrolledPersonId,projectId);
		
	    LOG.info("invoke----------/admitStudentEnroll" +
	    "[enrolledPersonId: " + enrolledPersonId + "] " + "by" + personId + "in project: " + projectId);
	    
	    ModelAndView mv = new ModelAndView("update_success");
	    mv.addObject("update_type_name", "admit_enroll");
		return mv;	    	
	 }
	 
	 @PostMapping(value = "/project/{projectId}", 
	    		params = "submitFlag=delete")
	 public ModelAndView dropProject(
			 @ModelAttribute("personId") String personId, 
	    	 @PathVariable int projectId) {
		 
		teacherService.dropProject(projectId, personId);
		
	    LOG.info("invoke----------/deleteProject" +
	    "[" + projectId + "] " + "by" + personId);
	    
	    ModelAndView mv = new ModelAndView("delete_success");
	    mv.addObject("delete_type_name", "project");
		return mv;	    	
	 }
	 
	 
}
