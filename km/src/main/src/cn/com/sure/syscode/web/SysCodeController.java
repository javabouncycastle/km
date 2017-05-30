package cn.com.sure.syscode.web;

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

import cn.com.sure.km.KmApplicationexception;
import cn.com.sure.syscode.entry.SysCode;
import cn.com.sure.syscode.entry.SysCodeType;
import cn.com.sure.syscode.service.SysCodeService;
import cn.com.sure.syscode.service.SysCodeTypeService;

@Controller
@RequestMapping(value="syscode")
public class SysCodeController {
	
	private static final Log LOG = LogFactory.getLog(SysCodeController.class);
	
	@Autowired
	private SysCodeService sysCodeService;
	
	@Autowired
	private SysCodeTypeService sysCodeTypeService;
	
	/**
	* UC-SYS01-11 ���������ֵ�����
	* @return "redirect:/list"
	*/
	@RequestMapping(value = "insert")
	public String insert(SysCode sysCode,
			Model model, RedirectAttributes attr,HttpServletRequest request
			){
		LOG.debug("insert - start!");
		try{
			//ִ��insert����
			this.sysCodeService.insert(sysCode,request);
		}catch(KmApplicationexception e){
			attr.addFlashAttribute("message",e.getMessage());
			attr.addFlashAttribute("frSysCode",sysCode);
			return "redirect:/syscode/selectAll.do";
		}
		LOG.debug("insert - end!");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","���桾"+sysCode.getParaCode()+"���ɹ�");
		return "redirect:/syscode/selectAll.do";
		
	}
	
	/**
	 * �����ֵ��б�
	 * @param sysCode
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value="selectAll")
	public ModelAndView selectAll(SysCode sysCode,
			Model model, RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("selectAll - start");
		List<SysCode> sysCodes = this.sysCodeService.selectAll(sysCode);
		List<SysCodeType> sysCodeTypes = this.sysCodeTypeService.selectAll(null);
		LOG.debug("selectAll - end");
		return new ModelAndView("syscode/syscodeList").addObject("sysCodes", sysCodes).addObject("sysCodeTypes",sysCodeTypes);
	}
	
	
	/**
	* ����UC-SYS01-12 �޸������ֵ�����
	* @return "redirect:/syscode/selectAll.do"
	*/
	@RequestMapping(value = "update")
	public String update(
	SysCode sysCode, Model model,RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("update - start!");
		this.sysCodeService.update(sysCode);
		LOG.debug("update - end!");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","�޸ġ�"+sysCode.getParaCode()+"����Ϣ�ɹ�");
		return  "redirect:/syscode/selectAll.do";
		
	}
	
	
	/**
	* UC-SYS01-13 ɾ�������ֵ�����
	* @return "redirect:/syscode/selectAll.dot"
	*/
	@RequestMapping(value = "remove")
	public String remove(
	@RequestParam(value = "id", required = false)Long id,
	Model model,RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("remove - start!");
		this.sysCodeService.remove(id);
		LOG.debug("remove - end!");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","ɾ������Ϊ��"+id+"����Ϣ�ɹ�");
		return  "redirect:/syscode/selectAll.do";
		
	}
	
	
	
	/**
	* UC-SYS01-17 ͣ�������ֵ�����
	* @return "redirect:/syscode/selectAll.do"
	*/
	@RequestMapping(value = "suspend")
	public String suspend(
	@RequestParam(value = "id", required = false)Long id,
	Model model,RedirectAttributes attr){
		LOG.debug("suspend - start!");
		this.sysCodeService.suspend(id);
    	LOG.debug("suspend - end!");
    	attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","ͣ������Ϊ��"+id+"���ɹ�");
        return "redirect:/syscode/selectAll.do";
		
	}
	
	
	
	/**
	 *  UC-SYS01-18 ���������ֵ�����
	 * @return "redirect:/syscode/list"
	 */
	
	
	@RequestMapping(value = "activate")
	public String activate(
	@RequestParam(value = "id", required = false)Long id,
	Model model,RedirectAttributes attr){
		LOG.debug("activate - start!");
		this.sysCodeService.activate(id);
        attr.addFlashAttribute("success", id);
    	LOG.debug("activate - end!");	
    	attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","��������Ϊ��"+id+"���ɹ�");
        return "redirect:/syscode/selectAll.do";
		
	}
	
	


}
