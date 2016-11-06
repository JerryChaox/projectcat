package cn.tata.t2s.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tata.t2s.ssm.entity.Star;
import cn.tata.t2s.ssm.entity.Topic;

public interface StarDao {

	public List<Star<Topic>> selectTopicStar(
			@Param("personId") String personId,
			@Param("offset") int offset,
			@Param("limit") int limit);

	public int insertStarItem(@Param("personId") String personId, @Param("objectId") int objectId,
			@Param("starClass") String starClass);

	public int setOnDelete(@Param("starId") long starId, @Param("starClass") String starClass);
}
