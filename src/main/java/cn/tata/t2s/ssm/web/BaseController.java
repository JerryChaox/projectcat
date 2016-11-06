package cn.tata.t2s.ssm.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import cn.tata.t2s.ssm.entity.WeChatOAuth2Token;
import cn.tata.t2s.ssm.enums.ResultEnum;
import cn.tata.t2s.ssm.exception.BizException;
import cn.tata.t2s.ssm.service.AnonymousUserService;
import cn.tata.t2s.ssm.util.HttpDeal;

@Controller
@SessionAttributes("personId")
public class BaseController {
	private final static String appid = "wx55588b5b1cf20a27";
	private final static String appsecret = "be6cd508ae4bbf0767d9fc25ced38fb0";
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AnonymousUserService anonymousUserService;

	@GetMapping(value = "/homepage")
	public ModelAndView getOrRegisterOpenId(@RequestParam String code) {
		if(LOG.isDebugEnabled()) {
			ModelAndView mv = new ModelAndView("index");
			String personId = "1";
			mv.addObject("personId", personId);
			return mv;
		}
		// 微信文档规定的url
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("appid", appid);
		params.put("secret", appsecret);
		params.put("code", code);
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
			String personId = wat.getOpenid();
			if (personId != null) {
				anonymousUserService.registerOpenId(personId);
				LOG.info("invoke----------/getOrRegisterOpenId" + "by " + personId);
				
				ModelAndView mv = new ModelAndView("index");
				mv.addObject("personId", personId);
				return mv;
			}
		}
		// 获取token失败,系统内部错误
		throw new BizException(ResultEnum.INNER_ERROR.getMsg());
	}
	
	@GetMapping(value = "/index")
	public ModelAndView backToIndex() {
		
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
	@GetMapping(value = "/")
	public ModelAndView index() {
//		System.out.println(request.getSession().getAttribute("personId"));
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
}
