package nfm.com.lgi.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nfm.com.cmm.util.MailHandler;
import nfm.com.cmm.util.TempKey;
import nfm.com.lgi.service.NfmLoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NfmLoginController {

	/** nfmLoginService */
	@Resource(name = "nfmLoginService")
	private NfmLoginService nfmLoginService;
	
	/** jgc add id:mailv1 메일 send */
	@Autowired
    private JavaMailSender mailSender;
	
	/**
	 * jgc add id:mailv1 메일 send
	 */
	@RequestMapping(value = "/emailCheck.do", produces = "application/text; charset=utf8")
	@ResponseBody
	public String mailSending(@RequestParam String uid,@RequestParam String pwd,HttpServletResponse res, HttpSession session ) throws Exception {
		
		String retVal = "";
	 	System.out.println("jgc debug uid="+uid);
	 	System.out.println("jgc debug pwd="+pwd);
	 	
	 	String email = nfmLoginService.getUserEmail(uid,pwd);
	 	
	 	if (email == null) {
	 		return "아이디/패스워드를 확인해주시기 바랍니다";
	 	}
	 	
	 	System.out.println("jgc debug ema="+email);
	 	
		String key = new TempKey().getKey(6, false);
		session.setAttribute("emailkey", key);
	 	System.out.println("jgc debug key="+key);
	    String content = new StringBuffer().
	    		append("제공되는 키를 로그인 인증값에 입력해주세요 키값은 : ").
	    		append(key).
	    		append(" 입니다.").
	    		toString();
	    
	    try {
	    	MailHandler sendMail = new MailHandler(mailSender);
	        sendMail.setSubject("서비스 이메일 인증");
	        sendMail.setText(content);
	        sendMail.setFrom("jgchhong@naver.com", "서어비스센터 ");
	        sendMail.setTo(email);
	        sendMail.send();
	    	retVal = "인증 메일이 발송됐습니다. 메일 확인 후 인증번호를 입력해주시기 바랍니다.";
	    } catch(Exception e){
	    	retVal = "메일전송 중 에러 발생했습니다. 잠시 후 다시 시도해주시기 바랍니다.";
	    	System.out.println(e);
	    }
	    
	    return retVal;
	}
}
