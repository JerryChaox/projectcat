package cn.tata.t2s.ssm.service;

import java.util.List;
import cn.tata.t2s.ssm.entity.Enroll;

public interface StudentService {
	// 学生
	public List<Enroll> getProjectEnrollInfo(String personId, int offset, int limit);
	
	//学生
	public void enrollProject(Enroll enroll);
	
	//撤销报名
	
	
}
