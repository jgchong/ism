package nfm.com.adj.service;

import nfm.com.adj.model.Adj010SearchVO;
import nfm.com.adj.model.Adj020Result;
import nfm.com.adj.model.Adj030AllResult;
import nfm.com.adj.model.Adj030Result;
import nfm.com.ord.service.Adj020VO;
import nfm.com.ord.service.impl.Ord020DAO;
import nfm.com.prd.service.impl.Prd010DAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class Adj010Service {
    /**
     * ord020DAO
     */
    @Resource(name = "ord020DAO")
    private Ord020DAO ord020DAO;

    public Object adj020selectListBycAll(String yyyymm) {
        List<Adj020VO> adj020VOList = (List<Adj020VO>) ord020DAO.adj020selectListBYCAll(yyyymm);
        List<Adj020VO> top10bycList = (List<Adj020VO>) ord020DAO.adj020selectTop10List(yyyymm);
        initData(top10bycList);

        Adj020Result adj020Result = new Adj020Result();
        for (Adj020VO adj020VO : adj020VOList) {
            setBycAdj020Result(top10bycList, adj020Result, adj020VO);
        }

        StringBuilder sb = new StringBuilder(yyyymm);
        String yyyymmm = sb.insert(4, '-').toString();
        Adj020VO adj020VONamuge = (Adj020VO) ord020DAO.adj020selectListBYCAllNull(yyyymmm);
        adj020Result.setNamuge(adj020VONamuge.getItembuyprice());
        adj020Result.priceAll = adj020Result.priceAll + adj020VONamuge.getItembuyprice();
        return adj020Result;
    }


    public List<Adj020Result> adj020selectListByc(String yyyymm) {
        List<Adj020VO> adj020VOList = (List<Adj020VO>) ord020DAO.adj020selectListBYC(yyyymm);
        List<Adj020VO> top10bycList = (List<Adj020VO>) ord020DAO.adj020selectTop10List(yyyymm);
        initData(top10bycList);

        int prviousCum030id = -1;
        List<Adj020Result> adj020ResultList = new ArrayList<>();
        Adj020Result adj020Result = null;
        for (Adj020VO adj020VO : adj020VOList) {
            if (prviousCum030id == adj020VO.getCum030id()) {
                prviousCum030id = adj020VO.getCum030id();
            } else {
                prviousCum030id = adj020VO.getCum030id();
                adj020Result = new Adj020Result();
                adj020ResultList.add(adj020Result);
                adj020Result.name = adj020VO.getCum030name();
                adj020Result.cum010id = adj020VO.getCum010id();
                adj020Result.cum010name = adj020VO.getCum010name();
            }
            setBycAdj020Result(top10bycList, adj020Result, adj020VO);


        }
        return adj020ResultList;
    }

    public List<Adj020Result> adj020selectListCum(String yyyymm) {
        List<Adj020VO> adj020VOList = (List<Adj020VO>) ord020DAO.adj020selectList(yyyymm);
        List<Adj020VO> top10bycList = (List<Adj020VO>) ord020DAO.adj020selectTop10List(yyyymm);
        initData(top10bycList);

        int prviousCum030id = -1;
        List<Adj020Result> adj020ResultList = new ArrayList<>();
        Adj020Result adj020Result = null;
        for (Adj020VO adj020VO : adj020VOList) {
            if (prviousCum030id == adj020VO.getCum030id()) {
                prviousCum030id = adj020VO.getCum030id();
            } else {
                prviousCum030id = adj020VO.getCum030id();
                adj020Result = new Adj020Result();
                adj020ResultList.add(adj020Result);
                adj020Result.name = adj020VO.getCum030name();
                adj020Result.cum010id = adj020VO.getCum010id();
                adj020Result.cum010name = adj020VO.getCum010name();
            }
            setCumAdj020Result(top10bycList, adj020Result, adj020VO);
        }
        return adj020ResultList;
    }

    public Object adj020selectListCumAll(String yyyymm) {
        List<Adj020VO> adj020VOList = (List<Adj020VO>) ord020DAO.adj020selectListAll(yyyymm);
        List<Adj020VO> top10bycList = (List<Adj020VO>) ord020DAO.adj020selectTop10List(yyyymm);
        initData(top10bycList);

        Adj020Result adj020Result = new Adj020Result();
        for (Adj020VO adj020VO : adj020VOList) {
            setCumAdj020Result(top10bycList, adj020Result, adj020VO);
        }
        StringBuilder sb = new StringBuilder(yyyymm);
        String yyyymmm = sb.insert(4, '-').toString();
        Adj020VO adj020VONamuge = (Adj020VO) ord020DAO.adj020selectListAllNull(yyyymmm);
        adj020Result.setNamuge(adj020VONamuge.getItemprice());
        adj020Result.priceAll = adj020Result.priceAll + adj020VONamuge.getItemprice();
        return adj020Result;
    }

    private void setBycAdj020Result(List<Adj020VO> top10bycList, Adj020Result adj020Result, Adj020VO adj020VO) {
        adj020Result.priceAll = adj020Result.priceAll + adj020VO.getItembuyprice();
        boolean isPass = false;
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                if (top10bycList.get(i).getByc010id() == adj020VO.getByc010id()) {
                    adj020Result.price01 = adj020VO.getItembuyprice();
                    isPass = true;
                    break;
                }
            } else if (i == 1) {
                if (top10bycList.get(i).getByc010id() == adj020VO.getByc010id()) {
                    adj020Result.price02 = adj020VO.getItembuyprice();
                    isPass = true;
                    break;
                }
            } else if (i == 2) {
                if (top10bycList.get(i).getByc010id() == adj020VO.getByc010id()) {
                    adj020Result.price03 = adj020VO.getItembuyprice();
                    isPass = true;
                    break;
                }
            } else if (i == 3) {
                if (top10bycList.get(i).getByc010id() == adj020VO.getByc010id()) {
                    adj020Result.price04 = adj020VO.getItembuyprice();
                    isPass = true;
                    break;
                }
            } else if (i == 4) {
                if (top10bycList.get(i).getByc010id() == adj020VO.getByc010id()) {
                    adj020Result.price05 = adj020VO.getItembuyprice();
                    isPass = true;
                    break;
                }
            } else if (i == 5) {
                if (top10bycList.get(i).getByc010id() == adj020VO.getByc010id()) {
                    adj020Result.price06 = adj020VO.getItembuyprice();
                    isPass = true;
                    break;
                }
            } else if (i == 6) {
                if (top10bycList.get(i).getByc010id() == adj020VO.getByc010id()) {
                    adj020Result.price07 = adj020VO.getItembuyprice();
                    isPass = true;
                    break;
                }
            } else if (i == 7) {
                if (top10bycList.get(i).getByc010id() == adj020VO.getByc010id()) {
                    adj020Result.price08 = adj020VO.getItembuyprice();
                    isPass = true;
                    break;
                }
            } else if (i == 8) {
                if (top10bycList.get(i).getByc010id() == adj020VO.getByc010id()) {
                    adj020Result.price09 = adj020VO.getItembuyprice();
                    isPass = true;
                    break;
                }
            } else if (i == 9) {
                if (top10bycList.get(i).getByc010id() == adj020VO.getByc010id()) {
                    adj020Result.price10 = adj020VO.getItembuyprice();
                    isPass = true;
                    break;
                }
            }
        }
        if (!isPass) {
            adj020Result.price11 = adj020Result.price11 + adj020VO.getItembuyprice();
        }
    }

    private void setCumAdj020Result(List<Adj020VO> top10bycList, Adj020Result adj020Result, Adj020VO adj020VO) {
        adj020Result.priceAll = adj020Result.priceAll + adj020VO.getItemprice();
        boolean isPass = false;
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                if (top10bycList.get(i).getByc010id() == adj020VO.getByc010id()) {
                    adj020Result.price01 = adj020VO.getItemprice();
                    isPass = true;
                    break;
                }
            } else if (i == 1) {
                if (top10bycList.get(i).getByc010id() == adj020VO.getByc010id()) {
                    adj020Result.price02 = adj020VO.getItemprice();
                    isPass = true;
                    break;
                }
            } else if (i == 2) {
                if (top10bycList.get(i).getByc010id() == adj020VO.getByc010id()) {
                    adj020Result.price03 = adj020VO.getItemprice();
                    isPass = true;
                    break;
                }
            } else if (i == 3) {
                if (top10bycList.get(i).getByc010id() == adj020VO.getByc010id()) {
                    adj020Result.price04 = adj020VO.getItemprice();
                    isPass = true;
                    break;
                }
            } else if (i == 4) {
                if (top10bycList.get(i).getByc010id() == adj020VO.getByc010id()) {
                    adj020Result.price05 = adj020VO.getItemprice();
                    isPass = true;
                    break;
                }
            } else if (i == 5) {
                if (top10bycList.get(i).getByc010id() == adj020VO.getByc010id()) {
                    adj020Result.price06 = adj020VO.getItemprice();
                    isPass = true;
                    break;
                }
            } else if (i == 6) {
                if (top10bycList.get(i).getByc010id() == adj020VO.getByc010id()) {
                    adj020Result.price07 = adj020VO.getItemprice();
                    isPass = true;
                    break;
                }
            } else if (i == 7) {
                if (top10bycList.get(i).getByc010id() == adj020VO.getByc010id()) {
                    adj020Result.price08 = adj020VO.getItemprice();
                    isPass = true;
                    break;
                }
            } else if (i == 8) {
                if (top10bycList.get(i).getByc010id() == adj020VO.getByc010id()) {
                    adj020Result.price09 = adj020VO.getItemprice();
                    isPass = true;
                    break;
                }
            } else if (i == 9) {
                if (top10bycList.get(i).getByc010id() == adj020VO.getByc010id()) {
                    adj020Result.price10 = adj020VO.getItemprice();
                    isPass = true;
                    break;
                }
            }
        }
        if (!isPass) {
            adj020Result.price11 = adj020Result.price11 + adj020VO.getItemprice();
        }
    }

    private void initData(List<Adj020VO> top10bycList) {
        for (int i = 0; i < 10; i++) {
            if (top10bycList.size() < 10) {
                Adj020VO adj020VO = new Adj020VO();
                adj020VO.setByc010name("-");
                adj020VO.setByc010id(-1);
                top10bycList.add(adj020VO);
            }
        }
    }

    @Resource(name = "prd010DAO")
    private Prd010DAO prd010DAO;
    private Adj030AllResult adj030AllResult;

    public List<Adj030Result> adj030selectList(Adj010SearchVO adj010SearchVO) {
        List<Adj030Result> adj030ResultList = (List<Adj030Result>) prd010DAO.adj030selectList(adj010SearchVO);
        int i = 0;
        adj030AllResult = new Adj030AllResult();
        for (Adj030Result adj030Result : adj030ResultList) {
            adj030Result.setNo(String.valueOf(i));
            if (adj030Result.getItemeaAll() == null) {
                adj030Result.setItemeaAll(0);
            }
            if (adj030Result.getItemeaEx() == null) {
                adj030Result.setItemeaEx(0);
            }
            if (adj030Result.getItemeaBroken() == null) {
                adj030Result.setItemeaBroken(0);
            }
            if (adj030Result.getItemeaOut() == null) {
                adj030Result.setItemeaOut(0);
            }
            adj030Result.setItemeaNamuge(adj030Result.getItemeaAll() - adj030Result.getItemeaOut() - adj030Result.getItemeaBroken());
            adj030AllResult.a = adj030AllResult.a + adj030Result.getItemeaEx();
            adj030AllResult.b = adj030AllResult.b + adj030Result.getItemeaAll();
            adj030AllResult.c = adj030AllResult.c + adj030Result.getItemeaOut();
            adj030AllResult.d = adj030AllResult.d + adj030Result.getItemeaBroken();
            adj030AllResult.e = adj030AllResult.e + adj030Result.getItemeaNamuge();
        }

        return adj030ResultList;
    }

    public Adj030AllResult getAdj030AllResult() {
        return adj030AllResult;
    }

}
