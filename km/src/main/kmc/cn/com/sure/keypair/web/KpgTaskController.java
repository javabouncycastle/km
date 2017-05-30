/**
 * 
 */
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

import cn.com.sure.common.KmConstants;
import cn.com.sure.keypair.entry.KeypairAlgorithm;
import cn.com.sure.keypair.entry.KpgTask;
import cn.com.sure.keypair.service.KeypairAlgorithmService;
import cn.com.sure.keypair.service.KpgTaskService;
import cn.com.sure.km.KmApplicationexception;
import cn.com.sure.syscode.entry.SysCode;
import cn.com.sure.syscode.service.SysCodeService;

/**
 * @author Limin
 *
 */
@Controller
@RequestMapping(value="kpgTask")
public class KpgTaskController {
	
	private static final Log LOG = LogFactory.getLog(KpgTaskController.class);
	
	@Autowired
	private KpgTaskService kpgTaskService;
	
	@Autowired
	private KeypairAlgorithmService keypairAlgorithmService;
	
	@Autowired
	private SysCodeService sysCodeService;
	
	@RequestMapping(value="selectAll")
	public ModelAndView selectAll(KpgTask kpgTask,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("selectAll - start");
		List<KpgTask> kpgTasks = this.kpgTaskService.selectAll();
		List<KeypairAlgorithm> keypairAlgorithms = this.keypairAlgorithmService.selectAll(null);
		List<SysCode> sysCodes = this.sysCodeService.selectByType(KmConstants.TYPE_ID_TASK_STATUS);
		LOG.debug("selectAll - end");
		return new ModelAndView("algorithm/keyPairTaskList").addObject("kpgTasks", kpgTasks).addObject("keypairAlgorithms",keypairAlgorithms).addObject("sysCodes",sysCodes);
		
	}
	
	@RequestMapping(value="insert")
	public String insert(KpgTask kpgTask,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("insert - start");
		try {
			this.kpgTaskService.insert(kpgTask);
		} catch (KmApplicationexception e) {
			attr.addFlashAttribute("messageInsert",e.getMessage());
			attr.addFlashAttribute("kpgTask",kpgTask);
			return "redirect:/gTask/selectAll.do";
		}
		LOG.debug("insert - end");
		return "redirect:/kpgTask/selectAll.do";
	}
	
	@RequestMapping(value="update")
	public String update(KpgTask kpgTask,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("update - start");
		this.kpgTaskService.update(kpgTask);
		LOG.debug("update - end");
		return "redirect:/kpgTask/selectAll.do";
	}
	
	public String remove(@RequestParam(value = "id", required = false)Long id,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("remove - start");
		this.kpgTaskService.delete(id);
		LOG.debug("remove - end");
				return "redirect:/kpgTask/selectAll.do";
		
	}

}
