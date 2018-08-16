package nfm.com.main.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IsmMainController {

	/**
	 * 메인 페이지
	 */
	@RequestMapping(value = "/ism/main/mainPage.do")
	public String ismMainPage(HttpServletRequest request, ModelMap model) throws Exception {
		return "ism/main/ismMain";
	}
}
