/**
 * 
 */
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
import cn.com.sure.syscode.entry.SysCodeType;
import cn.com.sure.syscode.service.SysCodeTypeService;

/**
 * @author Limin
 *
 */
@Controller
@RequestMapping(value="syscodetype")
public class SysCodeTypeController {
	
	private static final Log LOG = LogFactory.getLog(SysCodeTypeController.class);
	
	@Autowired
	private SysCodeTypeService sysCodeTypeService;
	
	/**
	 * 增加数据字典类别
	 * @param sysCode
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 * @throws KmApplicationexception 
	 */
	@RequestMapping(value = "insert")
	public String insert(SysCodeType sysCodeType,
			Model model, RedirectAttributes attr,HttpServletRequest request) {
		LOG.debug("insert - start");
		try {
			this.sysCodeTypeService.insert(sysCodeType);
		} catch (KmApplicationexception e) {
			attr.addFlashAttribute("message",e.getMessage());
			attr.addFlashAttribute("sysCodeType",sysCodeType);
			return "redirect:/syscodetype/selectAll.do";
		}
		LOG.debug("insert - end");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","保存【"+sysCodeType.getParaType()+"】成功");
		return "redirect:/syscodetype/selectAll.do";
		
	}
	
	/**
	 * 更新数据字典类别
	 * @param sysCode
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "update")
	public String update(SysCodeType sysCodeType,
			Model model, RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("update - start");
		this.sysCodeTypeService.update(sysCodeType);
		LOG.debug("update - start");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","修改数据字典类别=【"+sysCodeType.getParaType()+"】信息成功");
				return "redirect:/syscodetype/selectAll.do";
	}
	
	/**
	 * 删除数据字典类别
	 * @param sysCode
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "remove")
	public String remove(@RequestParam(value = "id", required = false)Long id,
			Model model, RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("remove - start");
		this.sysCodeTypeService.delete(id);
		LOG.debug("remove - end");
		attr.addFlashAttribute("success","true");
		attr.addFlashAttribute("msg","删除主键为【"+id+"】信息成功");
				return "redirect:/syscodetype/selectAll.do";
		
	}
	
	/**
	 * 查询数据字典类别
	 * @param sysCode
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "selectAll")	
	public ModelAndView selectAll(SysCodeType sysCodeType,
			Model model, RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("selectAll - start");
		List<SysCodeType> sysCodeTypes=this.sysCodeTypeService.selectAll(sysCodeType);
		LOG.debug("selectAll - end");
		return new ModelAndView("syscode/syscodeTypeList").addObject("sysCodeTypes", sysCodeTypes);
		
	}
	
	
	/**
	 * 
	 * @param sysCodeType
	 * @param model
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "searchByCondition")	
	public ModelAndView searchByCondition(SysCodeType sysCodeType,
			Model model, RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("searchByCondition - start");
		List<SysCodeType> sysCodeTypes=this.sysCodeTypeService.searchByCondition(sysCodeType);
		LOG.debug("searchByCondition - end");
		return new ModelAndView("syscode/syscodeTypeList").addObject("sysCodeTypes", sysCodeTypes);
		
	}


}
