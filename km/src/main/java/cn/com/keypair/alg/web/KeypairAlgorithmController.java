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
	 * ����������Կ�㷨��ҳ��
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
	 * ������Կ�㷨
	 */
	@RequestMapping(value="insert")
	public String insert(KeypairAlgorithm keypairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("insert - start");
		try{
			//ִ��insert����
		Map resultMap=keypairAlgorithmService.insert(keypairAlgorithm);
		}catch(KmApplicationexception e){
			attr.addFlashAttribute("message",e.getMessage());
			attr.addFlashAttribute("keypairAlgorithm",keypairAlgorithm);
			return "redirect:/algorithm/keypairList";
		}
		LOG.debug("insert - end");
		attr.addFlashAttribute("success","���桾"+keypairAlgorithm.getId()+"���ɹ�");
		return "redirect:/algorithm/keypairList";
		
	}
	
	/**
	 * ��ѯ��Կ�㷨�б�
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
	 * ������Կ�㷨
	 */
	@RequestMapping(value="findById")
	public ModelAndView findById(KeypairAlgorithm keypairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("findById - start");
		keypairAlgorithm=keypairAlgorithmService.findById(keypairAlgorithm);//����id�鵽���ݿ��е���Ϣ
		LOG.debug("findById - end");
		attr.addFlashAttribute("success",keypairAlgorithm.getId());
		return new ModelAndView("algorithm/keypairUpdate").addObject("keypairAlgorithm",keypairAlgorithm);//��ת��update��ҳ�棬��������������ʾ����
	}	
	/**
	 *  ɾ����Կ�㷨
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
