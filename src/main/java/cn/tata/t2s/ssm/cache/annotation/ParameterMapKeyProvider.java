package cn.tata.t2s.ssm.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ParameterMapKeyProvider {

	/**
	 * 参数序号
	 * @return
	 */
	int order() default 0;

}