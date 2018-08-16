package nfm.com.lgi.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import nfm.com.lgi.service.NfmLoginService;

import org.springframework.stereotype.Service;

import egovframework.let.utl.sim.service.EgovFileScrty;

@Service("nfmLoginService")
public class NfmLoginServiceimpl implements NfmLoginService {
	
	@Resource(name="nfmLoginDAO")
	private NfmLoginDAO nfmLoginDAO;
	
    public boolean getArrowIP(String userIp) throws Exception {
    	int arrowIp = nfmLoginDAO.getArrowIP(userIp);
    	if (arrowIp > 0) return true;
    	else return false;
    }

    /**
	 * jgc add id:ipv1 접속자 ip 가져오기
	 */
	@Override
	public String getRemoteIP(HttpServletRequest request) throws Exception {
        String ip = request.getHeader("X-FORWARDED-FOR"); 
        
        //proxy 환경일 경우
        if (ip == null || ip.length() == 0) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        //웹로직 서버일 경우
        if (ip == null || ip.length() == 0) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0) {
            ip = request.getRemoteAddr() ;
        }
        
        return ip;
	}

	@Override
	public String getUserEmail(String uid, String pwd) throws Exception {
		String enpassword = EgovFileScrty.encryptPassword(pwd, uid);
		return nfmLoginDAO.getUserEmail(uid, enpassword);
	}
}
