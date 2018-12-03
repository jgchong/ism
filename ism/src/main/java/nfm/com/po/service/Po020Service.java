package nfm.com.po.service;

import nfm.com.po.service.Ismpomsearch020VO;

public interface Po020Service  {
	public Object selectPoList(Ismpomsearch020VO ismpomsearch020VO) throws Exception;
	
	int selectPoListTotCnt(Ismpomsearch020VO ismpomsearch020VO) throws Exception;
}