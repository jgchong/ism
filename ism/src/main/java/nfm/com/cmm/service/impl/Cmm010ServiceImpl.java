package nfm.com.cmm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import nfm.com.cmm.service.Cmm010Service;
import nfm.com.cmm.service.IsmCmm010VO;

import org.springframework.stereotype.Service;

@Service("cmm010Service")
public class Cmm010ServiceImpl extends EgovAbstractServiceImpl implements Cmm010Service {

	/** cmm010DAO */
	@Resource(name="cmm010DAO")
	private Cmm010DAO cmm010DAO;

	@SuppressWarnings("unchecked")
	@Override
	//public List<?> selectListMemo(IsmCmm010VO ismCmm010VO) throws Exception {
	public String selectListMemo(IsmCmm010VO ismCmm010VO) throws Exception {
		List<IsmCmm010VO> result = (List<IsmCmm010VO>) cmm010DAO.selectListMemo(ismCmm010VO);

		String retStr = "";
	    for(IsmCmm010VO vo : result){
	    	retStr += "<li><div><div class='inmemo'><div><img src='"+vo.getUserphotosrc()+"'/></div><div>"+vo.getCreateusername()+"</div></div><div class='inmemotxt'>"+vo.getMemotext()+"</div><div class='memotime'>"+vo.getCreatedt()+"</div></div></li>";
	    }
	    
		return retStr;
	}

	@Override
	public void insertMemo(IsmCmm010VO ismCmm010VO) throws Exception {
		cmm010DAO.insertMemo(ismCmm010VO);
	}
}
