package cn.tata.t2s.ssm.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.tata.t2s.ssm.entity.Enroll;
import cn.tata.t2s.ssm.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController extends BaseController{
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StudentService studentService;
	
	
	@GetMapping(value = "/enroll",
    		params = "submitFlag=query",
    		produces = "application/json;charset=UTF-8")
    @ResponseBody
	public List<Enroll> getSelfEnrollInfo(
			@RequestParam("personId") String personId, 
			@RequestParam("offset") int offset,
    		@RequestParam("limit") int limit) {
		List<Enroll> enrollList = studentService.getProjectEnrollInfo(personId, offset, limit);
		LOG.info("invoke----------/getSelfEnrollInfo" + "by" + personId);
		return enrollList;
	}
	
	@PostMapping(value = "/enroll", 
    		params = "submitFlag=create",
    		consumes = "application/json;charset=UTF-8")
    public ModelAndView enrollProject(
    		@RequestBody Enroll enroll, 
    		BindingResult result) {
		studentService.enrollProject(enroll);
    	LOG.info("invoke----------/enrollProject" + 
    			"[" + enroll.getProject().getProjectId() + "] " + 
    			"by" + enroll.getPerson().getPersonId());
    	ModelAndView mv = new ModelAndView("create_success");
    	mv.addObject("create_type_name", "enroll");
		return mv;
    	
    }
}
