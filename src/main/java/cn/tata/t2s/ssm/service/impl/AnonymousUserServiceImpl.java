package cn.tata.t2s.ssm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tata.t2s.ssm.cache.RedisCache;
import cn.tata.t2s.ssm.dao.PersonDao;
import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.enums.ResultEnum;
import cn.tata.t2s.ssm.exception.BizException;
import cn.tata.t2s.ssm.service.AnonymousUserService;

@Service
public class AnonymousUserServiceImpl implements AnonymousUserService{
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RedisCache cache;
	@Autowired
	private PersonDao personDao;
	
	@Override
	public void registerOpenId(String personId) {
		personDao.insertVisitor(personId);
		
	}

	@Override
	public  void bindingProfile(Person person) {
		String personId = person.getPersonId();
		int result = personDao.savePerson(person);
		if(result <= 0) {
			//更新个人资料失败
			throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
		}
		cache.deleteCacheWithPattern("getSelfProfile*" + personId + "*");
		LOG.info("delete cache with key: getSelfProfile*" + personId + "*");
		return;
	}

}
