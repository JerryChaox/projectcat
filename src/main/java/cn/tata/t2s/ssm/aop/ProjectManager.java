package cn.tata.t2s.ssm.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import cn.tata.t2s.ssm.dao.EnrollDao;
import cn.tata.t2s.ssm.dao.ProjectDao;
import cn.tata.t2s.ssm.dao.StateDao;
import cn.tata.t2s.ssm.entity.Enroll;
import cn.tata.t2s.ssm.entity.Project;
import cn.tata.t2s.ssm.enums.ResultEnum;
import cn.tata.t2s.ssm.exception.BizException;

/**
 * 采用aop的方式检查项目板块相关的限制
 * 
 * @author chan
 *
 */
@Component
@Aspect
public class ProjectManager implements Ordered{
	//private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	private final static int MAX_ADMITTED_ENROLL_NUM = 3;
	
	private final static int MAX_VALIDATING_ENROLL_NUM = 10;

	private final static int MAX_ONLINE_PROJECT_NUM = 10;
	
	
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private EnrollDao enrollDao;
	@Autowired
	private StateDao stateDao;
	private int order = 1;
	
	@Override
	public int getOrder() {
		return this.order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	/*	@Pointcut("execution(* cn.tata.t2s.ssm.service.ProjectService.getProjectByProjectId(..)) "
		+ "&& args(project)")
			private void checkEnrollIsAvailableBeforeGetProject(Project project) {
		}
	 */
	/*@Before("checkEnrollIsAvailableBeforeGetProject(project)")
	public void before(ProceedingJoinPoint joinPoint, Project project) throws Throwable {
		// 检查报名人数是否已经到达
		int projectId = project.getProjectId();
		if(enrollDao.hasFullEnrolled(projectId)) {
			//报名人数已满 抛出业务异常
		
		}
		// 检查当前时间是否超过了deadline
		if(enrollDao.isDead(projectId)) {
			//已经截止报名 抛出业务异常
		}
		
		
		joinPoint.proceed();
	}*/
	
	/**
	 * 报名前检验已投递的简历数
	 * @param enroll
	 */
	@Pointcut("execution(* cn.tata.t2s.ssm.service.StudentService.enrollProject(..))"
			+ "&& args(enroll)")
	private void checkIsAvailableBeforeEnrolling(Enroll enroll) {}
	@Around("checkIsAvailableBeforeEnrolling(enroll)")
	public void before(ProceedingJoinPoint joinPoint, Enroll enroll) throws Throwable { 
		/*if(enrollDao.hasFullEnrolled(projectId)) {
			//报名人数已满 抛出业务异常
		
		}
		// 检查当前时间是否超过了deadline
		
		if(enrollDao.isDead(projectId)) {
			//已经截止报名 抛出业务异常
		}*/
		
		// 检查已经报名的项目是否超过了3个
		
		if(enrollDao.countPersonEnrollByState(enroll.getPerson().getPersonId(), "validating") 
				>= MAX_VALIDATING_ENROLL_NUM) {
			//超过了最大投简历次数, 抛出异常
			
		}
		
		
		joinPoint.proceed();
	}
	
	@Pointcut("execution(* cn.tata.t2s.ssm.service.TeacherService.createProject(..)) "
			+ "&& args(project)")
	private void checkIsAvailableBeforePublishProject(Project project) {}
	@Around("checkIsAvailableBeforePublishProject(project)")
	public Object around(ProceedingJoinPoint joinPoint, Project project) throws Throwable {
		Object retVal = joinPoint.proceed();

		if(projectDao.countProjectByState(project.getPerson().getPersonId(), "online") 
				>= MAX_ONLINE_PROJECT_NUM) {
			//超过最大上线项目数 自动修改项目状态为验证失败 抛出业务异常
			int offlineStateTypeId = stateDao.selectIdByStateName("failed", "project");
			int result = projectDao.updateProjectStateById(project.getProjectId(), offlineStateTypeId);
			if(result <= 0) {
				//更新失败, 内部错误
				throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
			}
			//抛出业务异常
			
		}
		return retVal;
	}
	
	@Pointcut("execution(* cn.tata.t2s.ssm.service.TeacherService.admitStudentEnroll(..)) "
			+ "&& args(sPersonId)")
	private void checkIsAvailableBeforeAdmitting(String sPersonId) {}
	@Around("checkIsAvailableBeforeAdmitting(sPersonId)")
	public void before(ProceedingJoinPoint joinPoint, String sPersonId) throws Throwable {
		
		if(enrollDao.countPersonEnrollByState(sPersonId, "admitted") 
				>= MAX_ADMITTED_ENROLL_NUM) {
			//该学生已经被3个项目录用了, 抛出业务异常
			
			return;
		}
		return;
	}
}
