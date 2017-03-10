package cn.tata.t2s.ssm.service.security;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import cn.tata.t2s.ssm.cache.RedisCache;
import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.Project;
import cn.tata.t2s.ssm.entity.WeChatOAuth2Token;
import cn.tata.t2s.ssm.enums.ResultEnum;
import cn.tata.t2s.ssm.exception.BizException;
import cn.tata.t2s.ssm.util.HttpDeal;

/**
 * <li>带验证码校验功能的用户名、密码认证过滤器</li>
 * <p>
 * 支持不输入验证码；支持验证码忽略大小写。
 * 
 * @author cb
 * 
 */
public class WeChatOpenIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	@Autowired
	private RedisCache cache;
	
	// ~ Static fields/initializers
	// =====================================================================================
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "personId";
	public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";

	private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
	private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
	private static boolean postOnly = false;
	public static final String LOGIN_IN_PAGE_URL = "/homepage";
	
	private final static String appid = "wx55588b5b1cf20a27";
	private final static String appsecret = "be6cd508ae4bbf0767d9fc25ced38fb0";

	// ~ Constructors
	// ===================================================================================================

	public WeChatOpenIdAuthenticationFilter() {
		super(new AntPathRequestMatcher("/homepage"));
		
	}

	// ~ Methods
	// ========================================================================================================

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		if(LOG.isDebugEnabled()) {
			String personId = "1";			
			HttpSession session = request.getSession();
			session.setAttribute("personId", personId);
			
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(personId,
					"");
			setDetails(request, authRequest);

			return this.getAuthenticationManager().authenticate(authRequest);
		}
		String personId = null;
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("appid", appid);
		params.put("secret", appsecret);
		params.put("code", request.getParameter("code"));
		params.put("grant_type", "authorization_code");

		String content = HttpDeal.post(url, params);

		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		WeChatOAuth2Token wat = null;
		try {
			// 将json转化为响应的类
			wat = mapper.readValue(content, WeChatOAuth2Token.class);
		} catch (JsonParseException e) {
			// 获取token失败
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// 获取token失败
			e.printStackTrace();
		} catch (IOException e) {
			// 获取token失败
			e.printStackTrace();
		}
		// wat对象不为空再去获取openId
		if (wat != null) {
			personId = wat.getOpenid();
		} else {
			// 获取token失败,系统内部错误
			throw new BizException(ResultEnum.INNER_ERROR.getMsg());
		}
		if (personId != null) {
			HttpSession session = request.getSession();
			session.setAttribute("personId", personId);
			
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(personId,
					"");
			setDetails(request, authRequest);
			
			return this.getAuthenticationManager().authenticate(authRequest);
		} else {
			throw new AuthenticationServiceException("get null openid");
		}
	}

	/**
	 * Enables subclasses to override the composition of the password, such as
	 * by including additional values and a separator.
	 * <p>
	 * This might be used for example if a postcode/zipcode was required in
	 * addition to the password. A delimiter such as a pipe (|) should be used
	 * to separate the password and extended value(s). The
	 * <code>AuthenticationDao</code> will need to generate the expected
	 * password in a corresponding manner.
	 * </p>
	 *
	 * @param request
	 *            so that request attributes can be retrieved
	 *
	 * @return the password that will be presented in the
	 *         <code>Authentication</code> request token to the
	 *         <code>AuthenticationManager</code>
	 */
	protected String obtainPassword(HttpServletRequest request) {
		return request.getParameter(passwordParameter);
	}

	/**
	 * Enables subclasses to override the composition of the username, such as
	 * by including additional values and a separator.
	 *
	 * @param request
	 *            so that request attributes can be retrieved
	 *
	 * @return the username that will be presented in the
	 *         <code>Authentication</code> request token to the
	 *         <code>AuthenticationManager</code>
	 */
	protected String obtainUsername(HttpServletRequest request) {
		return request.getParameter(usernameParameter);
	}

	/**
	 * Provided so that subclasses may configure what is put into the
	 * authentication request's details property.
	 *
	 * @param request
	 *            that an authentication request is being created for
	 * @param authRequest
	 *            the authentication request object that should have its details
	 *            set
	 */
	protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	/**
	 * Sets the parameter name which will be used to obtain the username from
	 * the login request.
	 *
	 * @param usernameParameter
	 *            the parameter name. Defaults to "username".
	 */
	public void setUsernameParameter(String usernameParameter) {
		Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
		this.usernameParameter = usernameParameter;
	}

	/**
	 * Sets the parameter name which will be used to obtain the password from
	 * the login request..
	 *
	 * @param passwordParameter
	 *            the parameter name. Defaults to "password".
	 */
	public void setPasswordParameter(String passwordParameter) {
		Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
		this.passwordParameter = passwordParameter;
	}

	/**
	 * Defines whether only HTTP POST requests will be allowed by this filter.
	 * If set to true, and an authentication request is received which is not a
	 * POST request, an exception will be raised immediately and authentication
	 * will not be attempted. The <tt>unsuccessfulAuthentication()</tt> method
	 * will be called as if handling a failed authentication.
	 * <p>
	 * Defaults to <tt>true</tt> but may be overridden by subclasses.
	 */
	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public final String getUsernameParameter() {
		return usernameParameter;
	}

	public final String getPasswordParameter() {
		return passwordParameter;
	}
}
