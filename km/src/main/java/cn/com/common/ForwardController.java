package cn.com.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForwardController {

@RequestMapping("forward")
  public String forward(HttpServletRequest request,@RequestParam("page") String page){
	  return page;
  }


}
