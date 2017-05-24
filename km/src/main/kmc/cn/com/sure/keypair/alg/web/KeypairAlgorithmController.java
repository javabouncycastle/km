package cn.com.sure.keypair.alg.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.sure.keypair.alg.entry.KeypairAlgorithm;
import cn.com.sure.keypair.alg.service.KeypairAlgorithmSearchbycondition;
import cn.com.sure.keypair.alg.service.KeypairAlgorithmService;
import cn.com.sure.km.KmApplicationexception;

@Controller
@RequestMapping(value="algorithm")
public class KeypairAlgorithmController {

	private static final Log LOG = LogFactory.getLog(KeypairAlgorithmController.class);
	
	@Autowired
	private KeypairAlgorithmService keypairAlgorithmService;
	
	/**
	 * 增加密钥算法
	 */
	@RequestMapping(value="insert")
	public String insert(KeypairAlgorithm keypairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("insert - start");
		try{
			//执行insert操作
			this.keypairAlgorithmService.insert(keypairAlgorithm);
		}catch(KmApplicationexception e){
			attr.addFlashAttribute("messageInsert",e.getMessage());
			attr.addFlashAttribute("keypairAlgorithm",keypairAlgorithm);
			return "redirect:/algorithm/selectAll.do";
		}
		LOG.debug("insert - end");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","保存【"+keypairAlgorithm.getName()+"】成功");
		return "redirect:/algorithm/selectAll.do";
		
	}
	
	/**
	 * 查询密钥算法列表
	 */
	@RequestMapping(value="selectAll")
	public ModelAndView selectAll(KeypairAlgorithm keypairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		System.out.println(request.getRemoteAddr());
		LOG.debug("selectAll - start");
		List <KeypairAlgorithm> keypairAlgorithms = keypairAlgorithmService.selectAll(keypairAlgorithm);
		LOG.debug("selectAll - end");
		return new ModelAndView("algorithm/keyPairAlgList").addObject("keypairAlgorithms", keypairAlgorithms);
		
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
	public String update(KeypairAlgorithm keypairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("update - start");
		keypairAlgorithmService.update(keypairAlgorithm);
		LOG.debug("update - end");
		attr.addFlashAttribute("updateSuccess","true");
		attr.addFlashAttribute("message","修改主键为【"+keypairAlgorithm.getId()+"】的信息成功！");
		return "redirect:/algorithm/selectAll.do";
	}
	
	
	/**
	 *  删除密钥算法
	 */
	@RequestMapping(value="remove")
	public String delete(
			@RequestParam(value = "id", required = false)Long id,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("delete - start");
		keypairAlgorithmService.delete(id);
		LOG.debug("delete - end");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","删除主键为【"+id+"】成功！");
		return "redirect:/algorithm/selectAll.do";
		
	}
	
	
	/**
	* UC-SYS01-17 停用
	* @return "redirect:/list"
	*/
	@RequestMapping(value = "suspend")
	public String suspend(
	@RequestParam(value = "id", required = false)Long id,
	Model model,RedirectAttributes attr){
		LOG.debug("suspend - start!");
		this.keypairAlgorithmService.suspend(id);
        attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","停用主键为【"+id+"】成功");
    	LOG.debug("suspend - end!");
        return "redirect:/algorithm/selectAll.do";
		
	}
	
	/**
	 *  UC-SYS01-18 启用
	 * @return "redirect:/list"
	 */
	
	
	@RequestMapping(value = "activate")
	public String activate(
	@RequestParam(value = "id", required = false)Long id,
	Model model,RedirectAttributes attr){
		LOG.debug("activate - start!");
		this.keypairAlgorithmService.activate(id);
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","启用主键为【"+id+"】成功");
    	LOG.debug("activate - end!");		
        return "redirect:/algorithm/selectAll.do";
		
	}
	
}
