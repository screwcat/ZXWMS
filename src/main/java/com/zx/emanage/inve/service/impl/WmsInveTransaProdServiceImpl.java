package com.zx.emanage.inve.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInvePruductCategoryDao;
import com.zx.emanage.inve.persist.WmsInveTransaCardDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.persist.WmsInveTransaMatchDao;
import com.zx.emanage.inve.persist.WmsInveTransaProdDao;
import com.zx.emanage.inve.persist.WmsInveTransaProtocolDao;
import com.zx.emanage.inve.persist.WmsInveUpdateCardLogDao;
import com.zx.emanage.inve.service.IWmsInveTransaProdService;
import com.zx.emanage.inve.vo.WmsInveTransaProdSearchBeanVO;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.persist.SysSpecialUserDao;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.SysSpecialUser;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaCard;
import com.zx.emanage.util.gen.entity.WmsInveTransaMatch;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.emanage.util.gen.entity.WmsInveUpdateCardLog;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvetransaprodService")
public class WmsInveTransaProdServiceImpl implements IWmsInveTransaProdService
{
    private static Logger log = LoggerFactory.getLogger(WmsInveTransaProdServiceImpl.class);

    @Autowired
    private WmsInveTransaProdDao wmsinvetransaprodDao;

    @Autowired
    private WmsInveTransaProtocolDao wmsInveTransaProtocolDao;

    @Autowired
    private WmsInveTransaMatchDao wmsInveTransaMatchDao;
    
    @Autowired
    private WmsInvePruductCategoryDao wmsInvePruductCategoryDao;
    
    @Autowired
    private SysSpecialUserDao sysSpecialUserDao;
    
    @Autowired
    private WmsInveTransaDao wmsInveTransaDao;
    
    @Autowired
    private PmPersonnelDao pmPersonnelDao;
    
    @Autowired
    private WmsInveTransaCardDao wmsInveTransaCardDao;
    
    @Autowired
    private WmsInveUpdateCardLogDao wmsinveupdatecardlogdao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsInveTransaProdSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsinvetransaprodDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsInveTransaProdSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvetransaprodDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvetransaprodDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsInveTransaProd getInfoByPK(Integer wms_inve_transa_prod_id)
    {
        return wmsinvetransaprodDao.get(wms_inve_transa_prod_id);
    }

    @Override
    @Transactional
    public String save(WmsInveTransaProd bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvetransaprodDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsInveTransaProd bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvetransaprodDao.update(bean); // update a record replace
                                                 // columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsInveTransaProd() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsInveTransaProd> getListByEntity(WmsInveTransaProd queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsInveTransaProd> beanList = wmsinvetransaprodDao.getListByEntity(queryInfo);
        return beanList;
    }

    /**
     * 获取债权匹配初始化数据
     * 通过上单产品信息表主键，查询所对应的上单产品信息，协议表信息，债权列表信息，产品表信息
     * @param queryInfo
     * @return "Map
     * @author 张风山
     */

    public Map<String, Object> getListByID(String wms_inve_transa_prod_id, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        WmsInveTransa wmsInveTransa =wmsInveTransaDao.get(wmsinvetransaprodDao.get(Integer.parseInt(wms_inve_transa_prod_id)).getWms_inve_transa_id());
        WmsInveTransaProd wmsInveTransaProd = wmsinvetransaprodDao.get(Integer.parseInt(wms_inve_transa_prod_id));
        WmsInveTransaProtocol wmsInveTransaProtocol = wmsInveTransaProtocolDao.getByPk(Integer.parseInt(wms_inve_transa_prod_id));
        WmsInveTransaMatch wmsInveTransaMatchSearch = new WmsInveTransaMatch();
        wmsInveTransaMatchSearch.setWms_inve_transa_prod_id(Integer.parseInt(wms_inve_transa_prod_id));
        wmsInveTransaMatchSearch.setWms_inve_redeem_id(0);
        List<WmsInveTransaMatch> wmsInveTransaMatchList = wmsInveTransaMatchDao.getListByEntity(wmsInveTransaMatchSearch);
        WmsInveTransaCard wmsInveTransaCard = new WmsInveTransaCard();
        wmsInveTransaCard.setWms_inve_transa_id(wmsInveTransa.getWms_inve_transa_id());
        wmsInveTransaCard.setEnable_flag("1");
        List<WmsInveTransaCard> wmsInveTransaCards =wmsInveTransaCardDao.getListByEntity(wmsInveTransaCard);
        
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        //将list转map
        for(WmsInveTransaMatch bean : wmsInveTransaMatchList){
            mapList.add(SysTools.ConvertObjToMap(bean));
        }
        
//        SysSpecialUser sys = new SysSpecialUser();
//        sys.setEnable_flag("1");
//        mapList = SysTools.setListView(mapList, user, sysSpecialUserDao.getListByEntity(sys));
        
        boolean isSpecialUserFlag = SysTools.isSpecialUser(sysSpecialUserDao.getListByEntity(new SysSpecialUser()), user);
        
        WmsInvePruductCategory wmsInvePruductCategory = wmsInvePruductCategoryDao.get(wmsInveTransaProd.getWms_inve_pruduct_category_id());
        paramMap.put("wmsInveTransa", wmsInveTransa);
        if(wmsInveTransa.getDepartment_manager_id()!= null){
        	paramMap.put("department_manager_name", pmPersonnelDao.get(wmsInveTransa.getDepartment_manager_id()).getPersonnel_name());
        }
        if(wmsInveTransa.getVice_general_manager_id()!= null){
        	paramMap.put("vice_general_manager_name", pmPersonnelDao.get(wmsInveTransa.getVice_general_manager_id()).getPersonnel_name());
        }
        paramMap.put("wmsInveTransaProd", wmsInveTransaProd);
        paramMap.put("wmsInveTransaProtocol", wmsInveTransaProtocol);
        paramMap.put("wmsInveTransaMatchList", mapList);
        paramMap.put("wmsInvePruductCategory", wmsInvePruductCategory);
        paramMap.put("isSpecialUserFlag", isSpecialUserFlag);//是否为特批人标识
        paramMap.put("wmsInveTransaCards", wmsInveTransaCards);
        return paramMap;
    }

    @Override
    public WmsInveTransaProd getInfoByPKForJEGL(Integer wms_inve_transa_id)
    {
        return wmsinvetransaprodDao.getForJEGL(wms_inve_transa_id);
    }
    /**
     * Description :修改收益卡信息
     * 
     * @param bean
     * @author jiaodelong
     */
    @Override
    @Transactional
	public String updateIncomeCard(UserBean user,WmsInveTransaProd wmsTransaProd) {
		String resStr = "";
		int ret = 0;
		WmsInveTransaProd wmsTransaProdOld = wmsinvetransaprodDao.get(wmsTransaProd.getWms_inve_transa_prod_id());
		ret = wmsinvetransaprodDao.updateIncomeCard(wmsTransaProd);
		WmsInveUpdateCardLog log = new WmsInveUpdateCardLog();
		log.setWms_inve_transa_id(wmsTransaProdOld.getWms_inve_transa_id());
		log.setWms_inve_transa_prod_id(wmsTransaProd.getWms_inve_transa_prod_id());
		
		log.setCard_owner_name(wmsTransaProd.getCard_owner_name());
		log.setBank_of_deposit(wmsTransaProd.getBank_of_deposit());
		log.setCard_no(wmsTransaProd.getCard_no());
		log.setBank_of_deposit_pro(wmsTransaProd.getBank_of_deposit_pro());
		log.setBank_of_deposit_city(wmsTransaProd.getBank_of_deposit_city());
		log.setBank_branch(wmsTransaProd.getBank_branch());
		
		log.setCard_owner_name_old(wmsTransaProdOld.getCard_owner_name());
		log.setBank_of_deposit_old(wmsTransaProdOld.getBank_of_deposit());
		log.setCard_no_old(wmsTransaProdOld.getCard_no());
		log.setBank_of_deposit_pro_old(wmsTransaProdOld.getBank_of_deposit_pro());
		log.setBank_of_deposit_city_old(wmsTransaProdOld.getBank_of_deposit_city());
		log.setBank_branch_old(wmsTransaProdOld.getBank_branch());
		
		log.setCreate_user_id(user.getUserId());
		log.setCreate_user_name(user.getRealname());
		log.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
		log.setEnable_flag("1");
		wmsinveupdatecardlogdao.save(log);
		
		
		if(ret > 0){
			resStr = "success";
		}
		if(ret == 0){
	       resStr = "error";
	    }
		
		return resStr;
	}

    /**
     * @Title: getWmsInveTransaProdInfos
     * @Description: 根据客户姓名和身份证号获取对应的收益卡信息
     * @param customer_name 客户姓名
     * @param id_card_number 身份证号
     * @return 返回Map集合
     * @author: DongHao
     * @time:2016年12月16日 上午11:02:11
     * @see com.zx.emanage.inve.service.IWmsInveTransaProdService#getWmsInveTransaProdInfos(java.lang.String, java.lang.String)
     * history:
     * 1、2016年12月16日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getWmsInveTransaProdInfos(String customer_name, String id_card_number)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> params = new HashMap<String, Object>();
        if (customer_name != null && !"".equals(customer_name))
        {
            params.put("customer_name", customer_name);
        }
        if (id_card_number != null && !"".equals(id_card_number))
        {
            params.put("id_card_number", id_card_number);
        }

        List<Map<String, Object>> resultList = wmsinvetransaprodDao.getWmsInveTransaProdInfos(params);

        resultMap.put("flag", "true");
        resultMap.put("data_list", resultList);

        return resultMap;
    }
}
