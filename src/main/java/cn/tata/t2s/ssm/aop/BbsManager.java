package cn.tata.t2s.ssm.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.tata.t2s.ssm.dao.TopicDao;
import cn.tata.t2s.ssm.entity.Topic;

@Component
@Aspect
public class BbsManager {
	private final static int DAY_MAX_TOPIC_NUM = 10;
	
	@Autowired
	TopicDao topicDao;
	//检查一天之内发表的帖子是否超过了10个
	@Pointcut("execution(* cn.tata.t2s.ssm.service.NormalUserService.createTopic(..)) "
			+ "&& args(topic)")
	private void checkIsAvailableBeforePublishTopic(Topic topic) {}
	
	@Around("checkIsAvailableBeforePublishTopic(topic)")
	public void before(ProceedingJoinPoint joinPoint, Topic topic) throws Throwable {
		if(topicDao.selectTopicCountTody(topic.getPerson().getPersonId()) >= DAY_MAX_TOPIC_NUM) {
			//一天发了超过10个帖子抛出业务异常
			
		} else {
			joinPoint.proceed();
		}
	}
}
