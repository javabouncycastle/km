package cn.com.sure.keypair.web;

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

import cn.com.sure.keypair.entry.KeyPairAlgorithm;
import cn.com.sure.keypair.service.KeyPairAlgorithmService;
import cn.com.sure.km.KmApplicationexception;

@Controller
@RequestMapping(value="algorithm")
public class KeyPairAlgorithmController {

	private static final Log LOG = LogFactory.getLog(KeyPairAlgorithmController.class);
	
	@Autowired
	private KeyPairAlgorithmService keyPairAlgorithmService;
	
	/**
	 * 增加密钥算法
	 */
	@RequestMapping(value="insert")
	public String insert(KeyPairAlgorithm keyPairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("insert - start");
		try{
			//执行insert操作
			this.keyPairAlgorithmService.insert(keyPairAlgorithm);
		}catch(KmApplicationexception e){
			attr.addFlashAttribute("messageInsert",e.getMessage());
			attr.addFlashAttribute("keyPairAlgorithm",keyPairAlgorithm);
			return "redirect:/algorithm/selectAll.do";
		}
		LOG.debug("insert - end");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","保存【"+keyPairAlgorithm.getName()+"】成功");
		return "redirect:/algorithm/selectAll.do";
		
	}
	
	/**
	 * 查询密钥算法列表
	 */
	@RequestMapping(value="selectAll")
	public ModelAndView selectAll(KeyPairAlgorithm keyPairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		System.out.println(request.getRemoteAddr());
		LOG.debug("selectAll - start");
		List <KeyPairAlgorithm> keyPairAlgorithms = keyPairAlgorithmService.selectAll(keyPairAlgorithm);
		LOG.debug("selectAll - end");
		return new ModelAndView("algorithm/keyPairAlgList").addObject("keyPairAlgorithms", keyPairAlgorithms);
		
	}
	
	/**
	 * 更新密钥算法
	 */
	@RequestMapping(value="update")
	public String update(KeyPairAlgorithm keyPairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("update - start");
		keyPairAlgorithmService.update(keyPairAlgorithm);
		LOG.debug("update - end");
		attr.addFlashAttribute("updateSuccess","true");
		attr.addFlashAttribute("message","修改主键为【"+keyPairAlgorithm.getId()+"】的信息成功！");
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
		keyPairAlgorithmService.delete(id);
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
		this.keyPairAlgorithmService.suspend(id);
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
		this.keyPairAlgorithmService.activate(id);
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","启用主键为【"+id+"】成功");
    	LOG.debug("activate - end!");		
        return "redirect:/algorithm/selectAll.do";
		
	}
	

	/**
	 * 
	 * @param keypairAlgorithm
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "searchByCondition")
	public ModelAndView searchByCOndition(KeyPairAlgorithm keyPairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("searchByCOndition - start");
		List<KeyPairAlgorithm> keyPairAlgorithms = this.keyPairAlgorithmService.searchByCondition(keyPairAlgorithm);
		LOG.debug("searchByCOndition - end");
		return new ModelAndView("algorithm/keyPairAlgList").addObject("keyPairAlgorithms", keyPairAlgorithms);
		
	}
	
}
