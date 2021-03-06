package egovframework.let.uat.uia.web;

import java.util.Map;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.let.uat.uap.service.EgovLoginPolicyService;
import egovframework.let.uat.uap.service.LoginPolicyVO;
import egovframework.let.uat.uia.service.EgovLoginService;
import egovframework.let.utl.sim.service.EgovClntInfo;
import egovframework.rte.fdl.cmmn.trace.LeaveaTrace;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import nfm.com.lgi.service.NfmLoginService;

import org.springframework.context.ApplicationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 일반 로그인, 인증서 로그인을 처리하는 컨트롤러 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.06
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.06  박지욱          최초 생성
 *  2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 *  </pre>
 */
@Controller
public class EgovLoginController {

	/** EgovLoginService */
	@Resource(name = "loginService")
	private EgovLoginService loginService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** EgovLoginPolicyService */
	@Resource(name = "egovLoginPolicyService")
	EgovLoginPolicyService egovLoginPolicyService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** jgc add id:ipv1 접속자 ip 가져오기 */
	@Resource(name = "nfmLoginService")
	private NfmLoginService nfmLoginService;

	/** TRACE */
	@Resource(name = "leaveaTrace")
	LeaveaTrace leaveaTrace;

	/**
	 * 로그인 화면으로 들어간다
	 * @param vo - 로그인후 이동할 URL이 담긴 LoginVO
	 * @return 로그인 페이지
	 * @exception Exception
	 */
	@RequestMapping(value = "/uat/uia/egovLoginUsr.do")
	public String loginUsrView(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

        String clientIp1 = nfmLoginService.getRemoteIP(request); //jgc add id:ipv1 접속자 ip 가져오기
		System.out.println("jgc1===>"+clientIp1); //jgc add id:ipv1 접속자 ip 가져오기
		request.getSession().setAttribute("isArrowIP", nfmLoginService.getArrowIP(clientIp1)); //jgc add id:ipv1 접속자 ip 가져오기
		model.addAttribute("isArrowIP", nfmLoginService.getArrowIP(clientIp1)); //jgc add id:ipv1 접속자 ip 가져오기
		request.getSession().setAttribute("baseMenuNo", "6000000"); //jgc add id:lmv1 초기왼쪽 메뉴 setting
		
		return "uat/uia/EgovLoginUsr";
	}

	/**
	 * 일반(스프링 시큐리티) 로그인을 처리한다
	 * @param vo - 아이디, 비밀번호가 담긴 LoginVO
	 * @param request - 세션처리를 위한 HttpServletRequest
	 * @return result - 로그인결과(세션정보)
	 * @exception Exception
	 */
	@RequestMapping(value = "/uat/uia/actionSecurityLogin.do")
	public String actionSecurityLogin(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletResponse response, HttpServletRequest request, ModelMap model) throws Exception {
		// jgc add id:ipv1 접속 가능 아이피가 아닐경우 이메일 인증번호 체크 [s]
		System.out.println("loginVO.ihidNum="+loginVO.getIhidNum());
		System.out.println("session.isArrowIP="+request.getSession().getAttribute("isArrowIP"));
		System.out.println("session.emailkey="+request.getSession().getAttribute("emailkey"));

		boolean isArrowIP = false;

		if (request.getSession().getAttribute("isArrowIP") == null) { //세션값 없는 경우 다시 세션 set
	        String clientIp1 = nfmLoginService.getRemoteIP(request); //jgc add id:ipv1 접속자 ip 가져오기
			System.out.println("jgc1===>"+clientIp1); //jgc add id:ipv1 접속자 ip 가져오기
			isArrowIP = nfmLoginService.getArrowIP(clientIp1);
			request.getSession().setAttribute("isArrowIP", isArrowIP); //jgc add id:ipv1 접속자 ip 가져오기
		}else{
			isArrowIP = (boolean) request.getSession().getAttribute("isArrowIP");
		}

		if (!isArrowIP) {
			String userInputStr = loginVO.getIhidNum();
			String emailStr = (String) request.getSession().getAttribute("emailkey");
			//session 값이 없어서 이메일 인증인지 아닌지 모름 그래서 다시 메세지와 함께 로그인 페이지 표시
			if (emailStr == null) {
				model.addAttribute("message", "허용된 IP가 아닙니다. 이메일인증을 해주시기 바랍니다.");
				return "forward:/uat/uia/actionMain.do";
			}
			if (!emailStr.equals(userInputStr)) {
				model.addAttribute("message", "이메일 인증번호와 일치하지 않습니다.");
				return "uat/uia/EgovLoginUsr";
			}
		}
		//jgc add id:ipv1 접속 가능 아이피가 아닐경우 이메일 인증번호 체크 [e]
		// 접속IP
		String userIp = EgovClntInfo.getClntIP(request);

		// 1. 일반 로그인 처리
		LoginVO resultVO = loginService.actionLogin(loginVO);

		boolean loginPolicyYn = true;

		LoginPolicyVO loginPolicyVO = new LoginPolicyVO();
		loginPolicyVO.setEmplyrId(resultVO.getId());
		loginPolicyVO = egovLoginPolicyService.selectLoginPolicy(loginPolicyVO);

		if (loginPolicyVO == null) {
			loginPolicyYn = true;
		} else {
			if (loginPolicyVO.getLmttAt().equals("Y")) {
				if (!userIp.equals(loginPolicyVO.getIpInfo())) {
					loginPolicyYn = false;
				}
			}
		}
		if (resultVO != null && resultVO.getId() != null && !resultVO.getId().equals("") && loginPolicyYn) {

			// 2. spring security 연동
			request.getSession().setAttribute("LoginVO", resultVO);

			UsernamePasswordAuthenticationFilter springSecurity = null;

			ApplicationContext act = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
			@SuppressWarnings("rawtypes")
			Map beans = act.getBeansOfType(UsernamePasswordAuthenticationFilter.class);
			if (beans.size() > 0) {
				springSecurity = (UsernamePasswordAuthenticationFilter) beans.values().toArray()[0];
			} else {
				throw new IllegalStateException("No AuthenticationProcessingFilter");
			}

			springSecurity.setContinueChainBeforeSuccessfulAuthentication(false); // false 이면 chain 처리 되지 않음.. (filter가 아닌 경우 false로...)

			springSecurity.doFilter(new RequestWrapperForSecurity(request, resultVO.getUserSe() + resultVO.getId(), resultVO.getUniqId()), response, null);

			return "forward:/uat/uia/actionMain.do"; // 성공 시 페이지.. (redirect 불가)

		} else {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "uat/uia/EgovLoginUsr";
		}
	}

	/**
	 * 로그인 후 메인화면으로 들어간다
	 * @param
	 * @return 로그인 페이지
	 * @exception Exception
	 */
	@RequestMapping(value = "/uat/uia/actionMain.do")
	public String actionMain(ModelMap model, HttpServletRequest request) throws Exception {

        String clientIp1 = nfmLoginService.getRemoteIP(request); //jgc add id:ipv1 접속자 ip 가져오기
		System.out.println("jgc1===>"+clientIp1); //jgc add id:ipv1 접속자 ip 가져오기
		request.getSession().setAttribute("isArrowIP", nfmLoginService.getArrowIP(clientIp1)); //jgc add id:ipv1 접속자 ip 가져오기
		model.addAttribute("isArrowIP", nfmLoginService.getArrowIP(clientIp1)); //jgc add id:ipv1 접속자 ip 가져오기
		request.getSession().setAttribute("baseMenuNo", "6000000"); //jgc add id:lmv1 초기왼쪽 메뉴 setting

		// 1. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			//model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "uat/uia/EgovLoginUsr";
		}

		// 2. 메인 페이지 이동
		return "forward:/cmm/main/mainPage.do";
	}

	/**
	 * 로그아웃한다.
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/uat/uia/actionLogout.do")
	public String actionLogout(HttpServletRequest request, ModelMap model) throws Exception {
		request.getSession().setAttribute("LoginVO", null);

		return "redirect:/j_spring_security_logout";
	}
}

class RequestWrapperForSecurity extends HttpServletRequestWrapper {
	private String username = null;
	private String password = null;

	public RequestWrapperForSecurity(HttpServletRequest request, String username, String password) {
		super(request);

		this.username = username;
		this.password = password;
	}

	@Override
	public String getRequestURI() {
		return ((HttpServletRequest) super.getRequest()).getContextPath() + "/j_spring_security_check";
	}

	@Override
	public String getParameter(String name) {
		if (name.equals("j_username")) {
			return username;
		}

		if (name.equals("j_password")) {
			return password;
		}

		return super.getParameter(name);
	}
}