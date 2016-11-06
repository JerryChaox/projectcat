package cn.tata.t2s.ssm.enums;

/**
 * 业务异常基类，所有业务异常都必须继承于此异常 定义异常时，需要先确定异常所属模块。 例如：无效用户可以定义为 [10010001]
 * 前四位数为系统模块编号，后4位为错误代码 ,唯一。
 * 
 * @author yingjun
 *
 */
public enum ResultEnum {

	// db operation exceptions
	DB_INSERT_RESULT_ERROR(99990001, "db insert error"),
	DB_UPDATE_RESULT_ERROR(99990002, "db update error"),
	DB_SET_ON_DELETE_ERROR(9999003, "db set on delete error"),
	DB_SELECTONE_IS_NULL(99990004,"db select return null"),

	// system exceptions
	INNER_ERROR(99980001, "system inner error"), 
	TOKEN_IS_ILLICIT(99980002, "Token authentication failed"), 
	SESSION_IS_OUT_TIME(99980003, "session time out"),

	// user exceptions
	INVALID_USER(10010001, "invalid_user"),
	NULL_USER(10010002, "null user"),
	
	// project module exceptions
	PROJECT_UPDATE_ERROR(20020001, "cannot modify online project"),
	PROJECT_STATE_UPDATE_ERROR(2002002, "cannot modify the state of online project"),
	PROJECT_DELETE_ERROR(2002003, "cannot delete the online project by yourself");
	private int state;

	private String msg;

	ResultEnum(int state, String msg) {
		this.state = state;
		this.msg = msg;
	}

	public int getState() {
		return state;
	}

	public String getMsg() {
		return msg;
	}

	public static ResultEnum stateOf(int index) {
		for (ResultEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
