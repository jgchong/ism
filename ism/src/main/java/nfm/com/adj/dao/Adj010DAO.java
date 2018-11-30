package nfm.com.adj.dao;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nfm.com.ord.service.Adj020VO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class Adj010DAO extends EgovAbstractDAO {
    public void insertadj0201(Adj020VO adj020VO) {
        insert("adj010DAO.insertadj0201", adj020VO);
    }

    public void insertadj0202(Adj020VO adj020VO) {
        insert("adj010DAO.insertadj0202", adj020VO);
    }

    public void insertadj0203(Adj020VO adj020VO) {
        insert("adj010DAO.insertadj0203", adj020VO);
    }

    public void insertadj0204(Adj020VO adj020VO) {
        insert("adj010DAO.insertadj0204", adj020VO);
    }

    public void insertadj0205(Adj020VO adj020VO) {
        insert("adj010DAO.insertadj0205", adj020VO);
    }

    public void insertadj0206(Adj020VO adj020VO) {
        insert("adj010DAO.insertadj0206", adj020VO);
    }

}

