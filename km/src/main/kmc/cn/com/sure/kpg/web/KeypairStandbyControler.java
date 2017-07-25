/**
 * 
 */
package cn.com.sure.kpg.web;

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

import cn.com.sure.kpg.entry.KeypairStandby;
import cn.com.sure.kpg.service.KeypairStandbyService;

/**
 * @author Limin
 *
 */
@Controller
@RequestMapping(value="keypairStandby")
public class KeypairStandbyControler {
	
	private static final Log LOG = LogFactory.getLog(KeypairStandbyControler.class);
	
	@Autowired
	private KeypairStandbyService keyPairStandbyService;
	
	@RequestMapping(value="selectAll")
	public ModelAndView selectAll(){
		LOG.debug("start - start");
		List<KeypairStandby> keyPairStandbys = keyPairStandbyService.selectAll();
		LOG.debug("start - start");
		return new ModelAndView("keyPair/keyPairStandbyList").addObject("keyPairStandbys", keyPairStandbys);
		
	}
	
	@RequestMapping(value="remove")
	public String remove(@RequestParam(value = "id", required = false)Long id,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
				return null;
		
	}

}
	
