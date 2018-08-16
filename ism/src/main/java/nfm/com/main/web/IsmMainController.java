package nfm.com.main.web;

import javax.servlet.http.HttpServletRequest;

import nfm.com.main.service.MainGraphYearVO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IsmMainController {

	@RequestMapping(value = "/ism/main/mainPage.do")
	public String ismMainPage(HttpServletRequest request, ModelMap model) throws Exception {
		
		MainGraphYearVO mainGraphYearVO = new MainGraphYearVO();
		
		String[] aaa = {"1","2","3","4","5"};
		mainGraphYearVO.setProd1(aaa);
		
		String[] bbb = {"10","20","30","40","50"};
		mainGraphYearVO.setProd2(bbb);
		
		String[] ccc = {"11","12","14","21","11"};
		mainGraphYearVO.setProd3(ccc);
		
		String[] ddd = {"33","33","11","12","34"};
		mainGraphYearVO.setProd4(ddd);
		
		String[] eee = {"55","32","67","21","39"};
		mainGraphYearVO.setProd5(eee);
		
		String[] fff = {"505","320","607","2001","309"};
		mainGraphYearVO.setAmt(fff);
		
		model.addAttribute("graphVal", mainGraphYearVO);
		
		return "ism/main/ismMain";
	}
}
