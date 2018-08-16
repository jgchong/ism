package nfm.com.lgi.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("nfmLoginDAO")
public class NfmLoginDAO extends EgovAbstractDAO {
	public int getArrowIP(String userIp) {
		return (Integer)select("nfmLoginDAO.getArrowIP", userIp);
	}

	public String getUserEmail(String uid, String pwd) {
		LoginVO loginvo = new LoginVO();
		loginvo.setId(uid);
		loginvo.setPassword(pwd);
		return (String) select("nfmLoginDAO.getUserEmail", loginvo);
	}
}
