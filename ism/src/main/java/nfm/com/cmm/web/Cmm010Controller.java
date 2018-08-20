package nfm.com.cmm.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nfm.com.cmm.service.Cmm010Service;
import nfm.com.cmm.service.IsmCmm010VO;
import nfm.com.cmm.service.IsmCmm020VO;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

@Controller
public class Cmm010Controller {

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	/** cmm010Service */
	@Resource(name = "cmm010Service")
	private Cmm010Service cmm010Service;
	
	/**
	 * 메모 목록 조회
	 */
	@ResponseBody
	@RequestMapping(value = "/ism/cmm/selectListMemo.do")
	public String selectListMemo(@RequestParam("buss_key") String buss_key, @RequestParam("buss_type") String buss_type) throws Exception {
		
		IsmCmm010VO ismCmm010VO = new IsmCmm010VO();
		ismCmm010VO.setBuss_key(buss_key);
		ismCmm010VO.setBuss_type(buss_type);

	    String retStr = URLEncoder.encode(cmm010Service.selectListMemo(ismCmm010VO), "UTF-8");
	    
		return retStr;
	}

	@ResponseBody
	@RequestMapping(value = "/ism/cmm/insertMemo.do")
	public String insertMemo(@RequestParam("buss_key") String buss_key, @RequestParam("inputmemo") String inputmemo, @RequestParam("buss_type") String buss_type,
				HttpSession session) throws Exception {
		
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		return "LFAIL";
    	}
    	
		LoginVO loginVO = (LoginVO) session.getAttribute("LoginVO");
		
		IsmCmm010VO ismCmm010VO = new IsmCmm010VO();
		ismCmm010VO.setBuss_key(buss_key);
		ismCmm010VO.setBuss_type(buss_type);
		ismCmm010VO.setMemotext(inputmemo);
		ismCmm010VO.setCreateuserid(loginVO.getId());
		
		cmm010Service.insertMemo(ismCmm010VO);
		
		return "SUCCESS";
	}

	/*첨부파일 가져오기 */
	@RequestMapping(value = "/ism/cmm/attachFileDown.do")
	public void attachFileDown(@RequestParam("cmm020id") int cmm020id, HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("jgc debug cmm020id = "+cmm020id);

		String path = propertiesService.getString("Globals.fileStorePath");

		IsmCmm020VO ismCmm020VO = cmm010Service.selectCmm020(cmm020id);

		File uFile = new File(path + ismCmm020VO.getSavefilename());
		long fSize = uFile.length();
	
		if (fSize > 0) {
			String mimetype = "application/x-msdownload";
		
			response.setContentType(mimetype);
			setDisposition(ismCmm020VO.getOrgfilename(), request, response);
		
			BufferedInputStream in = null;
			BufferedOutputStream out = null;
		
			try {
				in = new BufferedInputStream(new FileInputStream(uFile));
				out = new BufferedOutputStream(response.getOutputStream());
		
				FileCopyUtils.copy(in, out);
				out.flush();
			} catch (Exception ex) {
				System.out.println("IGNORED: {}" + ex.getMessage());
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (Exception ignore) {
						System.out.println("IGNORED: {}" + ignore.getMessage());
					}
				}
				if (out != null) {
					try {
						out.close();
					} catch (Exception ignore) {
						System.out.println("IGNORED: {}" + ignore.getMessage());
					}
				}
			}
		
		} else {
			response.setContentType("application/x-msdownload");
		
			PrintWriter printwriter = response.getWriter();
			printwriter.println("<html>");
			printwriter.println("<br><br><br><h2>Could not get file name:<br>"+ismCmm020VO.getOrgfilename()+"</h2>");
			printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
			printwriter.println("<br><br><br>&copy; webAccess");
			printwriter.println("</html>");
			printwriter.flush();
			printwriter.close();
		}
	}

	/**
	 * 브라우저 구분 얻기.
	 *
	 * @param request
	 * @return
	 */
	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
			return "Trident";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
	}

	/**
	 * Disposition 지정하기.
	 *
	 * @param filename
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String browser = getBrowser(request);

		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			//throw new RuntimeException("Not supported browser");
			throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}
}
