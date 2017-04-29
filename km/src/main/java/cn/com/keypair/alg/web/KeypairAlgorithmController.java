package cn.com.keypair.alg.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.keypair.alg.entry.KeypairAlgorithm;
import cn.com.keypair.alg.service.KeypairAlgorithmService;

@Controller
@RequestMapping(value="algorithm")
public class KeypairAlgorithmController {

	private static final Log LOG = LogFactory.getLog(KeypairAlgorithmController.class);
	
	@Autowired
	private KeypairAlgorithmService keypairAlgorithmService;
	
	
	/**
	 * ����������Կ�㷨��ҳ��
	 * @return
	 */
/*	@RequestMapping(value="insert",method=RequestMethod.POST)
	public String searchMain(){
		LOG.debug("searchMain - start");
		
		LOG.debug("searchMain - end");
		return "";
	}*/
	
	/**
	 * ������Կ�㷨
	 */
	@RequestMapping(value="insert")
	public String insert(KeypairAlgorithm keypairAlgorithm,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("insert - start");
		Map resultMap=keypairAlgorithmService.insert(keypairAlgorithm);
		LOG.debug("insert - end");
		return "redirect:/algorithm/list";
		
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
		return null;
		
	}
	
}
