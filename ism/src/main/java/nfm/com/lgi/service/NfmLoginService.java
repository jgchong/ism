package nfm.com.lgi.service;

import javax.servlet.http.HttpServletRequest;

public interface NfmLoginService {
	public boolean getArrowIP(String userIp) throws Exception;

	public String getRemoteIP(HttpServletRequest request) throws Exception;

	public String getUserEmail(String uid, String pwd) throws Exception;
}
