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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.sure.keypair.entry.KeyPairArchive;
import cn.com.sure.keypair.service.KeyPairArchiveService;

/**
 * @author Limin
 *
 */
@Controller
/*@RequestMapping(value="kpArchive")*/
public class KeyPairArchiveController {
	
	/*private static final Log LOG = LogFactory.getLog(KeyPairArchiveController.class);
	
	@Autowired
	private KeyPairArchiveService keyPairArchiveService;
	
	@RequestMapping(value="selectAll")
	public ModelAndView selectAll(KeyPairArchive keyPairArchive,Model model, 
			RedirectAttributes attr,HttpServletRequest request){
		LOG.debug("selectAll - start");
		List<KeyPairArchive> keyPairArchives=keyPairArchiveService.selectAll();
		LOG.debug("selectAll - end");
		return new ModelAndView("keyPair/keyPairArchList").addObject("keyPairArchives", keyPairArchives);
		
	}
*/
}
