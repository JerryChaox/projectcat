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

import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.Reply;
import cn.tata.t2s.ssm.entity.Star;
import cn.tata.t2s.ssm.entity.Topic;
import cn.tata.t2s.ssm.service.NormalUserService;

@Controller
@RequestMapping("/normal")
public class NormalUserController extends BaseController{
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private NormalUserService normalUserService;
	
	@GetMapping(value = "/othersProfile", params = "submitFlag=query", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public <T extends Person> T getOtherProfile(
			@RequestParam("othersId") String othersId,
			@RequestParam("personId") String personId) {
		T person = normalUserService.getOthersProfile(othersId);
		LOG.info("invoke----------/getOtherProfile id= " + othersId + " by " + personId);
		return person;
	}
	
	@GetMapping(value = "/profile", params = "submitFlag=query", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public <T extends Person> T getSelfProfile(@RequestParam("personId") String personId) {
		T person = normalUserService.getSelfProfile(personId);
		LOG.info("invoke----------/getSelfProfile " + "by " + personId);
		return person;
	}

	@GetMapping(value = "/topic", params = "submitFlag=query", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Topic> getSelfTopicList(@RequestParam("personId") String personId, @RequestParam("offset") int offset,
			@RequestParam("limit") int limit) {
		List<Topic> topicList = normalUserService.getSelfTopicList(personId, offset, limit);
		LOG.info("invoke----------/getSelfTopicList" + "by" + personId);
		return topicList;

	}

	@GetMapping(value = "/reply", params = "submitFlag=query", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Reply> getSelfReplyList(@RequestParam("personId") String personId, @RequestParam("offset") int offset,
			@RequestParam("limit") int limit) {
		List<Reply> replyList = normalUserService.getSelfReplyList(personId, offset, limit);
		LOG.info("invoke----------/getSelfReplyList" + "by" + personId);
		return replyList;

	}
	
	@GetMapping(value = "/star/topic", params = "submitFlag=query", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public <T> List<Star<Topic>> getTopicStarList(
			@RequestParam("personId") String personId, 
			@RequestParam("offset") int offset,
			@RequestParam("limit") int limit) {
		List<Star<Topic>> topicStarList = normalUserService.getTopicStarList(personId, offset, limit);
		LOG.info("invoke----------/getStarList" + "by" + personId);
		return topicStarList;
	}
	
	@GetMapping(value = "/star/news", params = "submitFlag=query", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public <T> List<Star<Topic>> getNewsStarList(
			@RequestParam("personId") String personId, 
			@RequestParam("offset") int offset,
			@RequestParam("limit") int limit) {
		List<Star<Topic>> topicStarList = normalUserService.getTopicStarList(personId, offset, limit);
		LOG.info("invoke----------/getStarList" + "by" + personId);
		return topicStarList;
	}
	
	@GetMapping(value = "/follow", params = "submitFlag=query", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Person> getFollowingList(@RequestParam("personId") String personId, @RequestParam("offset") int offset,
			@RequestParam("limit") int limit) {
		List<Person> followingList = normalUserService.getFollowingList(personId, offset, limit);
		LOG.info("invoke----------/getFollowingList" + "by" + personId);
		return followingList;
	}

	@PostMapping(value = "/topic", params = "submitFlag=create", consumes = "application/json;charset=UTF-8")
	public ModelAndView createTopic(@RequestBody Topic topic,
			@RequestParam("personId") String personId) {
		Person person = new Person();
		person.setPersonId(personId);
		topic.setPerson(person);
		
		if(LOG.isDebugEnabled()) {
			System.out.println(topic);
		}
		
		normalUserService.createTopic(topic);
		
		LOG.info("invoke----------/createTopic " + "by " + personId);
		ModelAndView mv = new ModelAndView("create_success");
		mv.addObject("create_type_name", "topic");
		return mv;

	}

	@PostMapping(value = "/reply", params = "submitFlag=create", consumes = "application/json;charset=UTF-8")
	public ModelAndView createReply(@RequestBody Reply reply,
			@RequestParam("personId") String personId) {
		
		reply.setPerson(new Person(personId));
		
		normalUserService.createReply(reply);
		LOG.info("invoke----------/createReply" + "by" + personId);
		ModelAndView mv = new ModelAndView("create_success");
		mv.addObject("create_type_name", "reply");
		return mv;
	}
	
	@PostMapping(value="/star", params = "submitFlag=create")
	public ModelAndView createStar(
			@RequestParam("personId") String personId, 
			@RequestParam("objectId") int objectId, 
			@RequestParam("starClass") String starClass) {
		normalUserService.star(personId, objectId, starClass);
		LOG.info("invoke----------/createStar" + "by" + personId);
		ModelAndView mv = new ModelAndView("create_success");
		mv.addObject("create_type_name", "star");
		return mv;
	}
	
	@PostMapping(value="/follow", params = "submitFlag=create")
	public ModelAndView createfollow(
			@RequestParam("personId") String personId, 
			@RequestParam("followedId") String followedId) {
		normalUserService.follow(followedId, personId);
		LOG.info("invoke----------/createfollow" + "by" + personId);
		ModelAndView mv = new ModelAndView("create_success");
		mv.addObject("create_type_name", "follow");
		return mv;
	}

	@PostMapping(value = "/topic", params = "submitFlag=update", consumes = "application/json;charset=UTF-8")
	public ModelAndView saveTopic(@RequestBody Topic topic, BindingResult result,
			@RequestParam("personId") String personId) {
		normalUserService.saveTopic(topic);
		LOG.info("invoke----------/updateTopic" + "by" + personId);
		ModelAndView mv = new ModelAndView("update_success");
		mv.addObject("update_type_name", "topic");
		return mv;

	}

	@PostMapping(value = "/reply", params = "submitFlag=update", consumes = "application/json;charset=UTF-8")
	public ModelAndView saveReply(@RequestBody Reply reply, BindingResult result,
			@RequestParam("personId") String personId) {
		normalUserService.saveReply(reply);
		LOG.info("invoke----------/updateReply" + "by" + personId);
		ModelAndView mv = new ModelAndView("update_success");
		mv.addObject("update_type_name", "reply");
		return mv;
	}

	@PostMapping(value = "/topic", params = "submitFlag=delete")
	public ModelAndView dropTopic(@RequestParam("topicId") int topicId, @RequestParam("personId") String personId) {
		normalUserService.dropTopic(topicId, personId);
		LOG.info("invoke----------/dropTopic" + "by" + personId);
		ModelAndView mv = new ModelAndView("delete_success");
		mv.addObject("delete_type_name", "topic");
		return mv;

	}

	@PostMapping(value = "/reply", params = "submitFlag=delete")
	public ModelAndView dropReply(@RequestParam("topicId") long replyId, @RequestParam("personId") String personId) {
		normalUserService.dropReply(replyId, personId);
		LOG.info("invoke----------/dropReply" + "by" + personId);
		ModelAndView mv = new ModelAndView("delete_success");
		mv.addObject("delete_type_name", "reply");
		return mv;
	}
	
	@PostMapping(value="/star", params = "submitFlag=delete")
	public ModelAndView unStar(
			@RequestParam("personId") String personId, 
			@RequestParam("starId") long starId,
			@RequestParam("starClass") String starClass) {
		normalUserService.unStar(starId, personId, starClass);
		LOG.info("invoke----------/unStar" + "by" + personId);
		ModelAndView mv = new ModelAndView("delete_success");
		mv.addObject("delete_type_name", "star");
		return mv;
	}
	
	@PostMapping(value="/follow", params = "submitFlag=delete")
	public ModelAndView unfollow(@RequestParam("followedId") String followedId, @RequestParam("personId") String personId) {
		normalUserService.follow(followedId, personId);
		LOG.info("invoke----------/unfollow" + "by" + personId);
		ModelAndView mv = new ModelAndView("delete_success");
		mv.addObject("delete_type_name", "follow");
		return mv;
	}


}
