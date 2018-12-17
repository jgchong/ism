package nfm.com.po.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import nfm.com.po.service.Po020Service;
import nfm.com.po.service.Ismpomsearch020VO;

@Service("po020Service")
public class Po020ServiceImpl extends EgovAbstractServiceImpl implements Po020Service {

	
	/** ord020DAO */
	@Resource(name="po020DAO")
	private Po020DAO po020DAO;
	
	public Object selectPoList(Ismpomsearch020VO ismpomsearch020VO) throws Exception {
		return po020DAO.selectPoList(ismpomsearch020VO);
	}
	
	@Override
	public int selectPoListTotCnt(Ismpomsearch020VO ismpomsearch020VO) throws Exception {
		return po020DAO.selectPoListTotCnt(ismpomsearch020VO);
	}
	
	public Object selectByc020List(String bycId) throws Exception {
		return po020DAO.selectByc020List(bycId); //byc010id로 조회
	}

}