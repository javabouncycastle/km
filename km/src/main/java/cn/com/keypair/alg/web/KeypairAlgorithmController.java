package cn.com.keypair.alg.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.keypair.alg.entry.KeypairAlgorithm;
import cn.com.keypair.alg.service.KeypairAlgorithmSearchbycondition;
import cn.com.keypair.alg.service.KeypairAlgorithmService;
import cn.com.sure.km.KmApplicationexception;

@Controller
@RequestMapping(value="algorithm")
public class KeypairAlgorithmController {

	private static final Log LOG = LogFactory.getLog(KeypairAlgorithmController.class);
	
	@Autowired
	private KeypairAlgorithmService keypairAlgorithmService;
	
	
	/**
	 * 进入增加密钥算法主页面
	 * @return
	 /*
	 /*@RequestMapping(value="insert")
	public String searchMain(){
		LOG.debug("searchMain - start");
		
		LOG.debug("searchMain - end");
		return "";
	}
	*/
	/**
	 * 增加密钥算法
	 */
	@RequestMapping(value="insert")
	public String insert(KeypairAlgorithm keypairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("insert - start");
		try{
			//执行insert操作
		Map resultMap=keypairAlgorithmService.insert(keypairAlgorithm);
		}catch(KmApplicationexception e){
			attr.addFlashAttribute("message",e.getMessage());
			attr.addFlashAttribute("keypairAlgorithm",keypairAlgorithm);
			return "redirect:/algorithm/keypairList";
		}
		LOG.debug("insert - end");
		attr.addFlashAttribute("success","保存【"+keypairAlgorithm.getId()+"】成功");
		return "redirect:/algorithm/keypairList";
		
	}
	
	/**
	 * 查询密钥算法列表
	 */
	@RequestMapping(value="selectAll")
	public ModelAndView selectAll(KeypairAlgorithm keypairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("selectAll - start");
		List <KeypairAlgorithm> keypairAlgorithms = keypairAlgorithmService.selectAll(keypairAlgorithm);
		LOG.debug("selectAll - end");
		return new ModelAndView("algorithm/keypairList").addObject("keypairAlgorithms", keypairAlgorithms);
		
	}
	
	/**
	 * 按条件查询
	 */
	@RequestMapping(value = "list")
	public String listByConditions(KeypairAlgorithmSearchbycondition condition,
			Model model){
		LOG.debug("listByConditions - start!");
		
		LOG.debug("listByConditions - end!");
				return null;
		
	}
	
	
	/**
	 * 更新密钥算法
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public Map<String,Object> update(KeypairAlgorithm keypairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		LOG.debug("update - start");
		keypairAlgorithmService.update(keypairAlgorithm);
		resultMap.put("success", true);
		resultMap.put("id", keypairAlgorithm.getId());
		LOG.debug("update - end");
		return resultMap;
	}
	/**
	 * 更新密钥算法
	 */
	@RequestMapping(value="findById")
	public ModelAndView findById(KeypairAlgorithm keypairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("findById - start");
		keypairAlgorithm=keypairAlgorithmService.findById(keypairAlgorithm);//根据id查到数据库中的信息
		LOG.debug("findById - end");
		attr.addFlashAttribute("success",keypairAlgorithm.getId());
		return new ModelAndView("algorithm/keypairUpdate").addObject("keypairAlgorithm",keypairAlgorithm);//跳转到update的页面，并把这条数据显示出来
	}	
	/**
	 *  删除密钥算法
	 */
	@RequestMapping(value="delete")
	public String delete(
			@RequestParam(value = "ids", required = false)Long id,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("delete - start");
		keypairAlgorithmService.delete(id);
		attr.addFlashAttribute("sucess", id);
		LOG.debug("delete - end");
		attr.addFlashAttribute("success",id);
		return "redirect:/algorithm/keypairList";
		
	}
	
}
