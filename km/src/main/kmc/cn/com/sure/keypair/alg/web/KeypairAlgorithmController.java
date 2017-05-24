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
	 * ������Կ�㷨
	 */
	@RequestMapping(value="insert")
	public String insert(KeypairAlgorithm keypairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("insert - start");
		try{
			//ִ��insert����
			this.keypairAlgorithmService.insert(keypairAlgorithm);
		}catch(KmApplicationexception e){
			attr.addFlashAttribute("messageInsert",e.getMessage());
			attr.addFlashAttribute("keypairAlgorithm",keypairAlgorithm);
			return "redirect:/algorithm/selectAll.do";
		}
		LOG.debug("insert - end");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","���桾"+keypairAlgorithm.getName()+"���ɹ�");
		return "redirect:/algorithm/selectAll.do";
		
	}
	
	/**
	 * ��ѯ��Կ�㷨�б�
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
	 * ��������ѯ
	 */
	@RequestMapping(value = "list")
	public String listByConditions(KeypairAlgorithmSearchbycondition condition,
			Model model){
		LOG.debug("listByConditions - start!");
		
		LOG.debug("listByConditions - end!");
				return null;
		
	}
	
	
	/**
	 * ������Կ�㷨
	 */
	@RequestMapping(value="update")
	public String update(KeypairAlgorithm keypairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("update - start");
		keypairAlgorithmService.update(keypairAlgorithm);
		LOG.debug("update - end");
		attr.addFlashAttribute("updateSuccess","true");
		attr.addFlashAttribute("message","�޸�����Ϊ��"+keypairAlgorithm.getId()+"������Ϣ�ɹ���");
		return "redirect:/algorithm/selectAll.do";
	}
	
	
	/**
	 *  ɾ����Կ�㷨
	 */
	@RequestMapping(value="remove")
	public String delete(
			@RequestParam(value = "id", required = false)Long id,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("delete - start");
		keypairAlgorithmService.delete(id);
		LOG.debug("delete - end");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","ɾ������Ϊ��"+id+"���ɹ���");
		return "redirect:/algorithm/selectAll.do";
		
	}
	
	
	/**
	* UC-SYS01-17 ͣ��
	* @return "redirect:/list"
	*/
	@RequestMapping(value = "suspend")
	public String suspend(
	@RequestParam(value = "id", required = false)Long id,
	Model model,RedirectAttributes attr){
		LOG.debug("suspend - start!");
		this.keypairAlgorithmService.suspend(id);
        attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","ͣ������Ϊ��"+id+"���ɹ�");
    	LOG.debug("suspend - end!");
        return "redirect:/algorithm/selectAll.do";
		
	}
	
	/**
	 *  UC-SYS01-18 ����
	 * @return "redirect:/list"
	 */
	
	
	@RequestMapping(value = "activate")
	public String activate(
	@RequestParam(value = "id", required = false)Long id,
	Model model,RedirectAttributes attr){
		LOG.debug("activate - start!");
		this.keypairAlgorithmService.activate(id);
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","��������Ϊ��"+id+"���ɹ�");
    	LOG.debug("activate - end!");		
        return "redirect:/algorithm/selectAll.do";
		
	}
	
}
