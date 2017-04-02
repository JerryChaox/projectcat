package cn.tata.t2s.ssm.dao;
import java.lang.reflect.ParameterizedType;

import cn.tata.t2s.ssm.entity.Base;

public interface SuperDao<X extends Base, Y> extends BaseDao<X, Y>, CountDao<X>{

}
