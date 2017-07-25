/**
 * 
 */
package cn.com.sure.kpg.web;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.sure.kpg.entry.KeypairInuse;
import cn.com.sure.kpg.service.KeypairInuseService;

/**
 * @author Limin
 *
 */
@Controller
@RequestMapping(value="keypairInuse")
public class KeypairInuseController {
	
	private static final Log LOG = LogFactory.getLog(KeypairInuseController.class);
	
	@Autowired
	private KeypairInuseService keypairInuseService;
	
	@RequestMapping(value="selectAll")
	public ModelAndView selectAll(){
		LOG.debug("selectAll - start");
		List<KeypairInuse> keypairInuses = keypairInuseService.selectAll();
		LOG.debug("selectAll - end");
		return new ModelAndView("keyPair/keypairInuseList").addObject("keypairInuses", keypairInuses);
		
	}
}
