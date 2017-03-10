package cn.tata.t2s.ssm.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tata.t2s.ssm.dto.BaseResult;
import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.Project;
import cn.tata.t2s.ssm.entity.Reply;
import cn.tata.t2s.ssm.entity.Topic;
import cn.tata.t2s.ssm.service.AnonymousUserService;
import cn.tata.t2s.ssm.service.ProjectService;
import cn.tata.t2s.ssm.service.TopicService;

@Controller
@RequestMapping("/anonymous")
public class AnonymousUserController extends BaseController{
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AnonymousUserService anonymousUserService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private TopicService topicService;
	
	
	@GetMapping(value = "/project", 
    		params = "submitFlag=single_query",
    		produces="application/json;charset=UTF-8")
	@ResponseBody
	public Project getProjectByProjectId(
    		@PathVariable("personId") String personId, 
    		@RequestParam("projectId") int projectId) {
		
		Project project = projectService.getProjectByProjectId(projectId);
		
		LOG.info("invoke----------/getProjectByProjectId" + "by  " + personId);
		
		return project;
	}
	
	
	
	@GetMapping(value = "/topic", 
			params = "submitFlag=single_query",
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public Topic getTopicByTopicId(
			@PathVariable("personId") String personId, 
			@RequestParam("topicId") int topicId) {
		
		Topic topic = topicService.getTopicByTopicId(topicId);
		
		LOG.info("invoke----------/getTopicByTopicId" + "by " + personId);
		
		return topic;
	}



	@GetMapping(value = "/project", params = "submitFlag=query", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Project> getAllProjectList(
			@PathVariable("personId") String personId,
			@RequestParam("offset") int offset,
			@RequestParam("limit") int limit) {
		List<Project> projectList = projectService.getAllProjectList(offset, limit);
		LOG.info("invoke----------/getAllProjectList" + "by " + personId);
		return projectList;

	}
	
	@GetMapping(value = "/topic/{className}", params = "submitFlag=query", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Topic> getAllTopicList(
			@PathVariable("personId") String personId,
			@PathVariable("className") String className,
			@RequestParam("offset") int offset,
			@RequestParam("limit") int limit) {
		List<Topic> topicList = topicService.getAllTopicList(className, offset, limit);
		LOG.info("invoke----------/getAllTopicList" + "by " + personId);
		return topicList;

	}
	
	/**
	 * 
	 * @param personId current user's id
	 * @param offset
	 * @param limit
	 * @return
	 */
	@GetMapping(value = "/project", params = "submitFlag=dynamic_query", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Project> dynamicGetProjectList( 
			@PathVariable("personId") String personId,
			@RequestParam("fluzzyName") String fluzzyName,
			@RequestParam("offset") int offset,
			@RequestParam("limit") int limit) {
		List<Project> projectList = projectService.dynamicGetProjectList(personId, fluzzyName, offset, limit);
		LOG.info("invoke----------/dynamicGetProjectList" + "by " + personId);
		return projectList;
	}
	
	@GetMapping(value = "/topic/{className}", params = "submitFlag=dynamic_query", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Topic> dynamicGetTopicList( 
			@PathVariable("personId") String personId,
			@PathVariable("className") String className,
//			@RequestParam("school[]") String[] school,
//			@RequestParam("academy[]") String[] academy,
			@RequestParam("fluzzyName") String fluzzyName,
			@RequestParam("offset") int offset,
			@RequestParam("limit") int limit) {
		if(LOG.isDebugEnabled()) {
			System.out.print("request for the topic which className = " + className);
		}
		List<Topic> topicList = topicService.dynamicGetTopicList(className, null, null, fluzzyName, offset, limit);
		LOG.info("invoke----------/dynamicGetTopicList" + "by " + personId);
		return topicList;
	}
	
	@PostMapping(value = "/profile",
			params = "submitFlag=update",
			consumes = "application/json;charset=UTF-8",
			produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResult<Person>  bindingProfile(
			@RequestBody Person person, 
			@PathVariable("personId") String personId) {
		person.setPersonId(personId);
		anonymousUserService.bindingProfile(person);
		LOG.info("invoke----------/updateProfile" + 
    			"by " + personId);
		//ModelAndView mv = new ModelAndView("update_success");
    	//mv.addObject("update_type_name", "profile");
		BaseResult<Person> result = new BaseResult<Person>(true, person);
		return result;
		
	}
	
	@GetMapping(value="/topic/{topicId}/reply",
			params = "submitFlag=query",
			produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResult<List<Reply>> getTopicReplyList(
			@PathVariable int topicId,
			@RequestParam("offset") int offset,
			@RequestParam("limit") int limit,
			@PathVariable("personId") String personId) {
		
		List<Reply> replyList = topicService.getTopicReplyList(topicId, offset, limit);
		LOG.info("invoke----------/getTopicReplyList" + 
    			"by " + personId);
		BaseResult<List<Reply>> result = new BaseResult<List<Reply>>(true, replyList);
		return result;
		
	}
}
