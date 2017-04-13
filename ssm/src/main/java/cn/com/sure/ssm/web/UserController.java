package cn.com.sure.ssm.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.sure.ssm.entry.User;
import cn.com.sure.ssm.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Log LOG = LogFactory.getLog(UserController.class);
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value = "insert" ,method = RequestMethod.POST)
	public String insert(User user,HttpServletRequest request,Model model,RedirectAttributes attr){
		LOG.debug("insert - start!");
		this.userService.insert(user);
		model.addAttribute("success", true);
		LOG.debug("insert - end!");
		return "user/userAdd";
	}
	
	@RequestMapping(value = "remove")
	public String remove(User user,HttpServletRequest request,Model model,RedirectAttributes attr){
		LOG.debug("remove - start!");
		this.userService.remove(user.getId());
		LOG.debug("remove - end!");
		return "user/userList";
		
	}
	
	@RequestMapping(value = "select" )
	public String select(HttpServletRequest request,Model model,RedirectAttributes attr){
		LOG.debug("select - start!");
		List<User> users=this.userService.find();
		model.addAttribute("Users", users);
		LOG.debug("select - end!");
		return "user/userList";
		
	}
	@RequestMapping(value = "update" ,method = RequestMethod.POST)
	public String update(User user,HttpServletRequest request,Model model,RedirectAttributes attr){
		LOG.debug("select - start!");
		this.userService.update(user);
		LOG.debug("select - end!");
		return "user/userList";
		
	}

}
