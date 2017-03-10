package cn.tata.t2s.ssm.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	@GetMapping(value = "/index")
	public ModelAndView backToIndex() {
		
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
	@GetMapping(value = "/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
}
