package cn.tata.t2s.ssm.service.impl;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tata.t2s.ssm.cache.RedisCache;
import cn.tata.t2s.ssm.dao.PersonDao;
import cn.tata.t2s.ssm.dao.PersonPairDao;
import cn.tata.t2s.ssm.dao.ReplyDao;
import cn.tata.t2s.ssm.dao.StarDao;
import cn.tata.t2s.ssm.dao.TopicDao;
import cn.tata.t2s.ssm.entity.CompositePerson;
import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.PersonPair;
import cn.tata.t2s.ssm.entity.Person_;
import cn.tata.t2s.ssm.entity.Profile;
import cn.tata.t2s.ssm.entity.Reply;
import cn.tata.t2s.ssm.entity.Star;
import cn.tata.t2s.ssm.entity.Topic;
import cn.tata.t2s.ssm.enums.ResultEnum;
import cn.tata.t2s.ssm.exception.BizException;
import cn.tata.t2s.ssm.service.NormalUserService;
import cn.tata.t2s.ssm.service.util.CriteriaQueryHelper;
import cn.tata.t2s.ssm.service.util.PagedResult;

@Service
@Transactional
public class NormalUserServiceImpl extends SuperServiceImpl<Person, String> implements NormalUserService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PersonDao personDao;
	@Autowired
	private PersonPairDao personPairDao;
	@Autowired
	private ReplyDao replyDao;
	@Autowired
	private TopicDao topicDao;
	@Autowired
	private StarDao starDao;
	@Autowired
	private RedisCache cache;

	@Override
	public Person getPerson(String personId) {
		return personDao.selectPerson(personId);
	}
	
	@Override
	public Profile getProfile(String personId) {
		return personDao.selectPerson(personId).getProfile();
	}
	
//	@Override
//	public <T extends Person> T getSelfProfile(String personId) {
//		T person = (T) personDao.selectPerson(personId);
//		return person;
//	}

	@Override
	public PagedResult<Topic> getPersonTopicList
	(String personId, int pageSize, int pageNumber) {
		CriteriaQueryHelper<Person, String, Topic> cqh
		= cqm.getCriteriaQueryHelper(
				superDao.getCriteriaBuilder(), Person_.topicSet);
		
		Pair<SingularAttribute<Person, String>, String> idPair 
		= cqm.getIdPair(Person_.personId, personId);
		
		PagedResult<Topic> pagedResult = cqm.getPagedResult(pageSize, pageNumber);
		
		cqh.addPredicate(superDao
				.getIdPredicate(idPair, cqh.getEntityRoot()))
		.addPredicate(superDao
				.onDeletePredicate(cqh.getSetJoin()))
		.predicateInit()
		.select();
		
		return superDao.select(pagedResult, cqh);
	}

	@Override
	public PagedResult<Reply> getPersonReplyList
	(String personId, int pageSize, int pageNumber) {
		
		CriteriaQueryHelper<Person, String, Reply> cqh
		= cqm.getCriteriaQueryHelper(
				superDao.getCriteriaBuilder(), Person_.replySet);
		
		Pair<SingularAttribute<Person, String>, String> idPair 
		= cqm.getIdPair(Person_.personId, personId);
		
		PagedResult<Reply> pagedResult = cqm.getPagedResult(pageSize, pageNumber);
		
		
		cqh.addPredicate(superDao
				.getIdPredicate(idPair, cqh.getEntityRoot()))
		.addPredicate(superDao
				.onDeletePredicate(cqh.getSetJoin()))
		.predicateInit()
		.select();
		
		return superDao.select(pagedResult, cqh);
	}

	@Override
	public List<Star<Topic>> getPersonTopicStarList
	(String personId, int pageSize, int pageNumber) {
		
		List<Star<Topic>> starTopicList = starDao.selectTopicStar(personId, pageSize, pageNumber);
		return starTopicList;
	}

	@Override
	public PagedResult<PersonPair> getPersonPairList
	(String followOrFan, String personId, int pageSize, int pageNumber) {
		CriteriaQueryHelper<Person, String, PersonPair> cqh
		= cqm.getCriteriaQueryHelper(
				superDao.getCriteriaBuilder(), Person_.personPairSet);
		
		Pair<SingularAttribute<Person, String>, String> idPair 
		= cqm.getIdPair(Person_.personId, personId);
		
		PagedResult<PersonPair> pagedResult 
		= cqm.getPagedResult(pageSize, pageNumber);
		
		cqh.addPredicate(superDao
				.getIdPredicate(idPair, cqh.getEntityRoot()))
		.addPredicate(superDao
				.onDeletePredicate(cqh.getSetJoin()))
		.addPredicate(personDao
				.getPersonFollowPredicate(followOrFan, cqh.getSetJoin()))
		.predicateInit()
		.select();
		
		return superDao.select(pagedResult, cqh);
	}

	@Override
	public Topic savePersonTopic(Topic topic) {
		Person person = personDao.selectPerson(topic.getPerson().getPersonId());
		topic.setPerson(person);
		person.getTopicSet().add(topic);
		topicDao.insertTopic(topic);
		personDao.updatePerson(person);
		return topic;
	}

	@Override
	public Reply savePersonReply(Reply reply) {
		Person person = personDao.selectPerson(reply.getPerson().getPersonId());
		reply.setPerson(person);
		person.getReplySet().add(reply);
		personDao.updatePerson(person);
		return replyDao.updateReply(reply);
	}

	@Override
	public void savePersonStar(String personId, int objectId, String starClass) {
		int result = starDao.insertStarItem(personId, objectId, starClass);
		if (result <= 0) {
			// star failed
			throw new BizException(ResultEnum.DB_INSERT_RESULT_ERROR.getMsg());
		}
	}

	@Override
	public void savePersonPair(String fromPersonId, String toPersonId) {
		Person personOrFan = new Person(fromPersonId);
		Person followedOrPerson = new Person(toPersonId);
		
		CompositePerson followPair 
		= new CompositePerson(personOrFan, followedOrPerson, PersonPair.FOLLOW);
		CompositePerson fanPair 
		= new CompositePerson(followedOrPerson, personOrFan, PersonPair.FAN);
		
		//followed before
		PersonPair managedFollow = personPairDao.selectPersonPair(followPair);
		if(managedFollow != null) {
			PersonPair managedFan = personPairDao.selectPersonPair(fanPair);
			managedFollow.getCommonInfo().setOnDelete(false);
			managedFan.getCommonInfo().setOnDelete(false);
			personPairDao.updatePersonPair(managedFollow);
			personPairDao.updatePersonPair(managedFan);
			return;
		}
		
		//have not followed before
		PersonPair follow = new PersonPair();
		PersonPair fan = new PersonPair();
		follow.setCompositePerson(followPair);
		fan.setCompositePerson(fanPair);
		
		//persist
		personPairDao.insertPersonPair(follow);
		personPairDao.insertPersonPair(fan);
		return;
	}
	
	@Override
	public Topic refreshPersonTopic(Topic topic) {
		Topic result = topicDao.updateTopic(topic);
		return result;
	}

	@Override
	public Reply refreshPersonReply(Reply reply) {
		Reply result = replyDao.updateReply(reply);
		return result;
	}

	@Override
	public void removePersonTopic(long topicId, String personId) {
		Topic topic = topicDao.selectTopic(topicId);
		topic.getCommonInfo().setOnDelete(true);
		topicDao.updateTopic(topic);
//		if (first_result <= 0) {
//			// 删除帖子失败
//			throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
//		} else {
//			int second_result = topicDao.deleteTopicById(topicId);
//			if (second_result <= 0) {
//				// 删除帖子失败
//				throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
//			}
//			cache.deleteCacheWithPattern("getTopicList*");
//			cache.deleteCacheWithPattern("getSelfTopicList*" + personId + "*");
//			LOG.info("delete cache with key: getTopicList*");
//			LOG.info("delete cache with key: getSelfTopicList*" + personId + "*");
//			return;
//		}
	}

	@Override
	public void removePersonReply(long replyId, String personId) {
		Reply reply = replyDao.selectReply(replyId);
		reply.getCommonInfo().setOnDelete(true);
		replyDao.updateReply(reply);
//		if (result <= 0) {
//			// 删除帖子失败
//			throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
//		}
//		cache.deleteCacheWithPattern("getReplyList*");
//		cache.deleteCacheWithPattern("getSelfReplyList*" + personId + "*");
//		LOG.info("delete cache with key: getReplyList*");
//		LOG.info("delete cache with key: getSelfReplyList*" + personId + "*");
//		return;
	}

	@Override
	public void removePersonStar(long starId, String personId, String starClass) {
		int result = starDao.setOnDelete(starId, starClass);
		if (result <= 0) {
			// 删除帖子失败
			throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
		}
		return;
	}

	@Override
	public void removePersonPair(String fromPersonId, String toPersonId) {
		Person personOrFan = new Person(fromPersonId);
		Person followedOrPerson = new Person(toPersonId);
		
		PersonPair follow = personPairDao
				.selectPersonPair
				(new CompositePerson(personOrFan, followedOrPerson, PersonPair.FOLLOW));
		follow.getCommonInfo().setOnDelete(true);
		
		PersonPair fan = personPairDao
				.selectPersonPair
				(new CompositePerson(followedOrPerson, personOrFan, PersonPair.FAN));
		fan.getCommonInfo().setOnDelete(true);
		
		//merge
		personPairDao.updatePersonPair(follow);
		personPairDao.updatePersonPair(fan);
		return;
	}
}
