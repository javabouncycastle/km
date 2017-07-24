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

import cn.com.sure.keypair.entry.KeyPairStandby;
import cn.com.sure.keypair.service.KeyPairStandbyService;

/**
 * @author Limin
 *
 */
@Controller
@RequestMapping(value="keyPairStandby")
public class KeyPairStandbyControler {
	
	private static final Log LOG = LogFactory.getLog(KeyPairStandbyControler.class);
	
	@Autowired
	private KeyPairStandbyService keyPairStandbyService;
	
	@RequestMapping(value="selectAll")
	public ModelAndView selectAll(){
		LOG.debug("start - start");
		List<KeyPairStandby> keyPairStandbys = keyPairStandbyService.selectAll();
		LOG.debug("start - start");
		return new ModelAndView("keyPair/keyPairArchList").addObject("keyPairStandbys", keyPairStandbys);
		
	}
	
	@RequestMapping(value="remove")
	public String remove(@RequestParam(value = "id", required = false)Long id,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
				return null;
		
	}

}
	
