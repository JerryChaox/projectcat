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
import cn.tata.t2s.ssm.entity.PersonPair;
import cn.tata.t2s.ssm.entity.Profile;
import cn.tata.t2s.ssm.entity.Reply;
import cn.tata.t2s.ssm.entity.Star;
import cn.tata.t2s.ssm.entity.Topic;
import cn.tata.t2s.ssm.service.NormalUserService;
import cn.tata.t2s.ssm.service.util.PagedResult;

@Controller
@RequestMapping("/normal")
public class NormalUserController extends BaseController{
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private NormalUserService normalUserService;
	
	@GetMapping(value = "/othersProfile", params = "submitFlag=query", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Profile getOtherProfile(
			@RequestParam("othersId") String othersId,
			@RequestParam("personId") String personId) {
		Profile profile = normalUserService.getProfile(othersId);
		LOG.info("invoke----------/getOtherProfile id= " + othersId + " by " + personId);
		return profile;
	}
	
	@GetMapping(value = "/profile", params = "submitFlag=query", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Profile getSelfProfile(@RequestParam("personId") String personId) {
		Profile profile = normalUserService.getProfile(personId);
		LOG.info("invoke----------/getSelfProfile " + "by " + personId);
		return profile;
	}

	@GetMapping(value = "/topic", params = "submitFlag=query", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PagedResult<Topic> getSelfTopicList(@RequestParam("personId") String personId, @RequestParam("offset") int offset,
			@RequestParam("limit") int limit) {
		PagedResult<Topic> Pagedtopic = normalUserService.getPersonTopicList(personId, offset, limit);
		LOG.info("invoke----------/getSelfTopicList" + "by" + personId);
		return Pagedtopic;

	}

	@GetMapping(value = "/reply", params = "submitFlag=query", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PagedResult<Reply> getSelfReplyList(@RequestParam("personId") String personId, @RequestParam("offset") int offset,
			@RequestParam("limit") int limit) {
		PagedResult<Reply> Pagedreply = normalUserService.getPersonReplyList(personId, offset, limit);
		LOG.info("invoke----------/getSelfReplyList" + "by" + personId);
		return Pagedreply;

	}
	
	@GetMapping(value = "/star/topic", params = "submitFlag=query", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public <T> List<Star<Topic>> getSelfTopicStarList(
			@RequestParam("personId") String personId, 
			@RequestParam("offset") int offset,
			@RequestParam("limit") int limit) {
		List<Star<Topic>> topicStarList = normalUserService.getPersonTopicStarList(personId, offset, limit);
		LOG.info("invoke----------/getSelfTopicStarList" + "by" + personId);
		return topicStarList;
	}
	
	@GetMapping(value = "/star/news", params = "submitFlag=query", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public <T> List<Star<Topic>> getSelfNewsStarList(
			@RequestParam("personId") String personId, 
			@RequestParam("offset") int offset,
			@RequestParam("limit") int limit) {
		List<Star<Topic>> topicStarList = normalUserService.getPersonTopicStarList(personId, offset, limit);
		LOG.info("invoke----------/getSelfNewsStarList" + "by" + personId);
		return topicStarList;
	}
	
	@GetMapping(value = "/{followOrFan}", params = "submitFlag=query", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PagedResult<PersonPair> getSelfPairList(
			@RequestParam("personId") String personId
			, @PathVariable("followOrFan") String followOrFan
			, @RequestParam("offset") int offset
			, @RequestParam("limit") int limit) {
		followOrFan = followOrFan
				.equals(PersonPair.FOLLOW.toLowerCase()) 
				? PersonPair.FOLLOW : followOrFan;
		
		followOrFan = followOrFan
				.equals(PersonPair.FAN.toLowerCase()) 
				? PersonPair.FAN : followOrFan;
		
		PagedResult<PersonPair> PagedFollowing 
		= normalUserService.getPersonPairList(followOrFan, personId, offset, limit);
		LOG.info("invoke----------/getSelfPairList" + "by" + personId);
		return PagedFollowing;
	}

	@PostMapping(value = "/topic", params = "submitFlag=create", consumes = "application/json;charset=UTF-8")
	public ModelAndView saveSelfTopic(@RequestBody Topic topic,
			@RequestParam("personId") String personId) {
		Person person = new Person();
		person.setPersonId(personId);
		topic.setPerson(person);
		
		if(LOG.isDebugEnabled()) {
			System.out.println(topic);
		}
		
		normalUserService.savePersonTopic(topic);
		
		LOG.info("invoke----------/saveSelfTopic " + "by " + personId);
		ModelAndView mv = new ModelAndView("create_success");
		mv.addObject("create_type_name", "topic");
		return mv;

	}

	@PostMapping(value = "/reply", params = "submitFlag=create", consumes = "application/json;charset=UTF-8")
	public ModelAndView saveSelfReply(@RequestBody Reply reply,
			@RequestParam("personId") String personId) {
		
		reply.setPerson(new Person(personId));
		
		normalUserService.savePersonReply(reply);
		LOG.info("invoke----------/saveSelfReply" + "by" + personId);
		ModelAndView mv = new ModelAndView("create_success");
		mv.addObject("create_type_name", "reply");
		return mv;
	}
	
	@PostMapping(value="/star", params = "submitFlag=create")
	public ModelAndView saveSelfStar(
			@RequestParam("personId") String personId, 
			@RequestParam("objectId") int objectId, 
			@RequestParam("starClass") String starClass) {
		normalUserService.savePersonStar(personId, objectId, starClass);
		LOG.info("invoke----------/saveStar" + "by" + personId);
		ModelAndView mv = new ModelAndView("create_success");
		mv.addObject("create_type_name", "star");
		return mv;
	}
	
	@PostMapping(value="/follow", params = "submitFlag=create")
	public ModelAndView saveSelfPair(
			@RequestParam("personId") String personId, 
			@RequestParam("followedId") String followedId) {
		normalUserService.savePersonPair(followedId, personId);
		LOG.info("invoke----------/saveFollow" + "by" + personId);
		ModelAndView mv = new ModelAndView("create_success");
		mv.addObject("create_type_name", "follow");
		return mv;
	}

	@PostMapping(value = "/topic", params = "submitFlag=update", consumes = "application/json;charset=UTF-8")
	public ModelAndView refreshSelfTopic(@RequestBody Topic topic, BindingResult result,
			@RequestParam("personId") String personId) {
		normalUserService.refreshPersonTopic(topic);
		LOG.info("invoke----------/refreshTopic" + "by" + personId);
		ModelAndView mv = new ModelAndView("update_success");
		mv.addObject("update_type_name", "topic");
		return mv;

	}

	@PostMapping(value = "/reply", params = "submitFlag=update", consumes = "application/json;charset=UTF-8")
	public ModelAndView refreshSelfReply(@RequestBody Reply reply, BindingResult result,
			@RequestParam("personId") String personId) {
		normalUserService.refreshPersonReply(reply);
		LOG.info("invoke----------/refreshReply" + "by" + personId);
		ModelAndView mv = new ModelAndView("update_success");
		mv.addObject("update_type_name", "reply");
		return mv;
	}

	@PostMapping(value = "/topic", params = "submitFlag=delete")
	public ModelAndView removeSelfTopic(@RequestParam("topicId") int topicId, @RequestParam("personId") String personId) {
		normalUserService.removePersonTopic(topicId, personId);
		LOG.info("invoke----------/removeTopic" + "by" + personId);
		ModelAndView mv = new ModelAndView("delete_success");
		mv.addObject("delete_type_name", "topic");
		return mv;

	}

	@PostMapping(value = "/reply", params = "submitFlag=delete")
	public ModelAndView removeSelfReply(@RequestParam("topicId") long replyId, @RequestParam("personId") String personId) {
		normalUserService.removePersonReply(replyId, personId);
		LOG.info("invoke----------/dropReply" + "by" + personId);
		ModelAndView mv = new ModelAndView("delete_success");
		mv.addObject("delete_type_name", "reply");
		return mv;
	}
	
	@PostMapping(value="/star", params = "submitFlag=delete")
	public ModelAndView removeSelfStar(
			@RequestParam("personId") String personId, 
			@RequestParam("starId") long starId,
			@RequestParam("starClass") String starClass) {
		normalUserService.removePersonStar(starId, personId, starClass);
		LOG.info("invoke----------/unStar" + "by" + personId);
		ModelAndView mv = new ModelAndView("delete_success");
		mv.addObject("delete_type_name", "star");
		return mv;
	}
	
	@PostMapping(value="/follow", params = "submitFlag=delete")
	public ModelAndView removeSelfPair(@RequestParam("followedId") String followedId, @RequestParam("personId") String personId) {
		normalUserService.removePersonPair(followedId, personId);
		LOG.info("invoke----------/unfollow" + "by" + personId);
		ModelAndView mv = new ModelAndView("delete_success");
		mv.addObject("delete_type_name", "follow");
		return mv;
	}


}
