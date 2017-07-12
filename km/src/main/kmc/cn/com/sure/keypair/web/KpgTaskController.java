/**
 * 
 */
package cn.com.sure.keypair.web;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
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

import cn.com.sure.common.BaseController;
import cn.com.sure.keypair.entry.KeyPairAlgorithm;
import cn.com.sure.keypair.entry.KpgTask;
import cn.com.sure.keypair.service.KeyPairAlgorithmService;
import cn.com.sure.keypair.service.KpgTaskExecuteService;
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
public class KpgTaskController extends BaseController{
	
	private static final Log LOG = LogFactory.getLog(KpgTaskController.class);
	
	@Autowired
	private KpgTaskService kpgTaskService;
	
	@Autowired
	private KeyPairAlgorithmService keyPairAlgorithmService;
	
	@Autowired
	private SysCodeService sysCodeService;
	
	@Autowired KpgTaskExecuteService kpgTaskExecuteService;
	
	
	/**
	 * 查询所有
	 * @param kpgTask
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value="selectAll")
	public ModelAndView selectAll(KpgTask kpgTask,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("selectAll - start");
		KeyPairAlgorithm keyPairAlgorithm = new KeyPairAlgorithm();
		SysCode sysCode = new SysCode();
		List<KpgTask> kpgTasks = this.kpgTaskService.selectAll();
		List<KeyPairAlgorithm> keyPairAlgorithms = this.keyPairAlgorithmService.selectOpYes(keyPairAlgorithm);
		List<SysCode> sysCodes = this.sysCodeService.selectByType(sysCode);
		LOG.debug("selectAll - end");
		return new ModelAndView("algorithm/keyPairTaskList").addObject("kpgTasks", kpgTasks).addObject("keyPairAlgorithms",keyPairAlgorithms).addObject("sysCodes",sysCodes);
		
	}
	
	/**
	 * 增加密钥任务
	 * @param kpgTask
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value="insert")
	public String insert(KpgTask kpgTask,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("insert - start");
		try {
			this.kpgTaskService.insert(kpgTask);
		} catch (KmApplicationexception e) {
			attr.addFlashAttribute("messageInsert",e.getMessage());
			attr.addFlashAttribute("kpgTask",kpgTask);
			return "redirect:/kpgTask/selectAll.do";
		}
		LOG.debug("insert - end");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","保存【"+kpgTask.getName()+"】成功");
		return "redirect:/kpgTask/selectAll.do";
	}
	
	/**
	 * 更新密钥任务
	 * @param kpgTask
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value="update")
	public String update(KpgTask kpgTask,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("update - start");
		this.kpgTaskService.update(kpgTask);
		LOG.debug("update - end");
		attr.addFlashAttribute("updateSuccess","true");
		attr.addFlashAttribute("message","修改主键为【"+kpgTask.getId()+"】的信息成功！");
		return "redirect:/kpgTask/selectAll.do";
	}
	
	/**
	 * 删除密钥任务
	 * @param id
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value="remove")
	public String remove(@RequestParam(value = "id", required = false)Long id,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("remove - start");
		this.kpgTaskService.delete(id);
		LOG.debug("remove - end");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","删除主键为【"+id+"】成功！");
		return "redirect:/kpgTask/selectAll.do";
		
	}
	
	/**
	 * 启动密钥生成任务
	 * @param id
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KmApplicationexception
	 * @throws NoSuchProviderException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value="start")
	public String genKeypair(Long id,Model model, 
			RedirectAttributes attr,HttpServletRequest request) throws NoSuchAlgorithmException, KmApplicationexception, NoSuchProviderException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		LOG.debug("genKeypair - start");
		kpgTaskService.start(id);
		LOG.debug("genKeypair - end");
		return "redirect:/kpgTask/selectAll.do";
	}
	
	/**
	 * 按条件查询
	 * @param kpgTask
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "searchByCondition")
	public ModelAndView searchByCondition(KpgTask kpgTask,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("searchByCondition - start");
		List<KpgTask> kpgTasks = this.kpgTaskService.searchByCondition(kpgTask);
		LOG.debug("searchByCondition - end");
		return new ModelAndView("algorithm/keyPairTaskList").addObject("kpgTasks", kpgTasks);
		
	}

}
