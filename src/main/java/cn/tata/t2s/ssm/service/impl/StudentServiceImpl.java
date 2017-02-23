package cn.tata.t2s.ssm.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.tata.t2s.ssm.cache.RedisCache;
import cn.tata.t2s.ssm.dao.EnrollDao;
import cn.tata.t2s.ssm.entity.Enroll;
import cn.tata.t2s.ssm.enums.ResultEnum;
import cn.tata.t2s.ssm.exception.BizException;
import cn.tata.t2s.ssm.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EnrollDao enrolldao;
	@Autowired
	private RedisCache cache;

	@Override
	public List<Enroll> getProjectEnrollInfo(String personId, int offset, int limit) {
		String cacheKey = RedisCache.CAHCENAME + "|getProjectEnrollInfo|" + personId;
		List<Enroll> resultCache = cache.getListCache(cacheKey, Enroll.class);
		if (resultCache != null) {
			LOG.info("get cache with key:" + cacheKey);
			return resultCache;
		} else {
			resultCache = enrolldao.selectProjectEnrollInfoByPersonId(personId, offset, limit);
			cache.putCache(cacheKey, resultCache);
			LOG.info("put cache with key:" + cacheKey);
			return resultCache;
		}
	}

	@Override
	public void enrollProject(Enroll enroll) {
		int result = enrolldao.insertEnroll(enroll);
		String personId = enroll.getPerson().getPersonId();
		long projectId = enroll.getProject().getProjectId();
		if (result <= 0) {
			// 报名失败 有可能已经报名
			throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
		} else {
			cache.deleteCacheWithPattern("getProjectEnrollInfo*" + projectId + "*");
			cache.deleteCacheWithPattern("getProjectEnrollInfo*" + personId + "*");
			LOG.info("delete cache with key: getProjectEnrollInfo*" + projectId + "*");
			LOG.info("delete cache with key: getProjectEnrollInfo*" + personId + "*");
			return;
		}
	}

}
