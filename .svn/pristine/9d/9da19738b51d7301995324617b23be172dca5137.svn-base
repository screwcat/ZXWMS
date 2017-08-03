package com.zx.emanage.cremanage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.IWmsCreCustomerChangeLineContactDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerChangeHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineContactDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingCreditConfirmDao;
import com.zx.emanage.cremanage.service.IWmsCreCustomerChangeLineContactService;
import com.zx.emanage.cremanage.service.IWmsLoanWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsCreCustomerChangeLineContactSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanreview.persist.WmsCreRevAttDao;
import com.zx.emanage.loanreview.persist.WmsCreRevInfoContactDao;
import com.zx.emanage.util.gen.domain.WmsCreCustomerChangeLineContact;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead;
import com.zx.emanage.util.gen.vo.WmsCreCustomerChangeLineContactVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecustomerchangelinecontactService")
public class WmsCreCustomerChangeLineContactServiceImpl implements IWmsCreCustomerChangeLineContactService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCustomerChangeLineContactServiceImpl.class);

    @Autowired
    private IWmsCreCustomerChangeLineContactDao wmscrecustomerchangelinecontactDao;

    @Autowired
    private WmsCreCustomerChangeLineContactDao wmscrecustomerchangelinecontactlistDao;

    @Autowired
    private WmsCreRevInfoContactDao wmsCreRevInfoContactDao;
    
    @Autowired
    private WmsCreCreditLineCustomerChangeHeadDao wmsCreCreditLineCustomerChangeHeadDao;
    
    @Autowired
    private WmsCreRevAttDao wmscrerevattDao;//征信保存附件
    @Autowired
    private IWmsLoanWorkFlowService wmsLoanWorkFlowService;//流程
    @Autowired
    private WmsCreHousingCreditConfirmDao  wmsCreHousingCreditConfirmDao;//授信确认


    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCustomerChangeLineContactSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (queryInfo.getWms_cre_credit_line_customer_change_head_id() != null)
        {
            resMap.put("wms_cre_credit_line_customer_change_head_id",
                       queryInfo.getWms_cre_credit_line_customer_change_head_id());
        }
        resMap.put("sortname", queryInfo.getSortname());
        resMap.put("sortorder", queryInfo.getSortorder());
        resMap.put("Rows", wmscrecustomerchangelinecontactlistDao.search(resMap));
        return resMap;
    }
    
    @Override
    public Map<String, Object> searchList(WmsCreCustomerChangeLineContactSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (queryInfo.getWms_cre_credit_line_customer_change_head_id() != null)
        {
            resMap.put("wms_cre_credit_line_customer_change_head_id", queryInfo.getWms_cre_credit_line_customer_change_head_id());
        }
        resMap.put("sortname", queryInfo.getSortname());
        resMap.put("sortorder", queryInfo.getSortorder());
        resMap.put("Rows", wmscrecustomerchangelinecontactlistDao.searchList(resMap));
        return resMap;
    }

    @Override
    public Map<String, Object> getContactsListWithPagingFK(WmsCreCustomerChangeLineContactSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (queryInfo.getWms_cre_credit_head_id() != null)
        {
            resMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        }
        if (queryInfo.getWms_cre_credit_line_customer_change_head_id() != null)
        {
            resMap.put("wms_cre_credit_line_customer_change_head_id1",
                       queryInfo.getWms_cre_credit_line_customer_change_head_id());
        }
        resMap.put("Rows", wmscrecustomerchangelinecontactlistDao.search(resMap));
        // resMap.put("Rows", wmsCreRevInfoContactDao.search(resMap));
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCustomerChangeLineContactSearchBeanVO queryInfo)
    {
        GridDataBean bean = new GridDataBean(queryInfo.getPage(),
                                             wmscrecustomerchangelinecontactDao.getListCountNum(queryInfo),
                                             wmscrecustomerchangelinecontactDao.getListWithPaging(queryInfo));
        return bean.getGridData();
    }

    @Override
    public WmsCreCustomerChangeLineContactVO getInfoByPK(Integer wms_cre_customer_change_line_contact_id)
    {
        return wmscrecustomerchangelinecontactDao.getInfoByPK(wms_cre_customer_change_line_contact_id);
    }

    @Override
    @Transactional
    public String save(WmsCreCustomerChangeLineContact bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecustomerchangelinecontactDao.addNewRecord(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCustomerChangeLineContact bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecustomerchangelinecontactDao.updateRecordAll(bean); // update
                                                                        // a
                                                                        // record
                                                                        // replace
                                                                        // all,
                                                                        // support
                                                                        // null
                                                                        // val
        // ret = wmscrecustomerchangelinecontactDao.updateRecordCols(bean); //
        // update a record replace columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String delete(WmsCreCustomerChangeLineContact bean)
    {
        String resStr = "success";
        int ret = 0;
        try
        {
            ret = wmscrecustomerchangelinecontactDao.deleteRecordByDomain(bean); // nonsupport
                                                                                 // null
                                                                                 // val
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            resStr = "fkerror"; // default foreign key error
        }
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreCustomerChangeLineContact() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByAndMethod(WmsCreCustomerChangeLineContact queryInfo, Boolean isExcludePKFlag)
    {
        if (isExcludePKFlag)
        {
            Boolean setAllPk = true;
            if (queryInfo.getWms_cre_customer_change_line_contact_id() == null)
            {
                setAllPk = false;
            }
            if (!setAllPk)
            {
                log.info("**************please set pk into the queryInfo.**************");
                return "error";
            }
        }
        String resStr = "success";
        List<WmsCreCustomerChangeLineContact> beanList = wmscrecustomerchangelinecontactDao.getSingleTableListByAndMethod(queryInfo,
                                                                                                                          isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "or" method, each checking, need new
     * WmsCreCustomerChangeLineContact() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByOrMethod(WmsCreCustomerChangeLineContact queryInfo, Boolean isExcludePKFlag)
    {
        if (isExcludePKFlag)
        {
            Boolean setAllPk = true;
            if (queryInfo.getWms_cre_customer_change_line_contact_id() == null)
            {
                setAllPk = false;
            }
            if (!setAllPk)
            {
                log.info("**************please set pk into the queryInfo.**************");
                return "error";
            }
        }
        String resStr = "success";
        List<WmsCreCustomerChangeLineContact> beanList = wmscrecustomerchangelinecontactDao.getSingleTableListByOrMethod(queryInfo,
                                                                                                                         isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    /**
     * 根据贷款单据id和客户变更表id获取对应的联系人信息
     * 
     * @param queryInfo
     * @author 张风�?
     */
    @Override
    public List<com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineContact> getContactList(com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineContact queryInfo)
    {

        return wmscrecustomerchangelinecontactlistDao.getListByEntity(queryInfo);
    }

	@Override
	@Transactional
    public void sendCreditConfirmInfo(WmsCreCustomerChangeLineContactSearchBeanVO queryInfo, String save_type)
    {
		String wms_cre_customer_change_line_contact_id_list="";
		//根据head_id   查出wms_cre_credit_line_customer_change_head_id
		WmsCreCreditLineCustomerChangeHead ChangeHead = wmsCreCreditLineCustomerChangeHeadDao.getChangeHeadId(queryInfo.getWms_cre_credit_head_id());
		List<WmsCreCustomerChangeLineContactSearchBeanVO> relativesList = JsonUtil.jsonArrayToList(queryInfo.getContacts_list(),WmsCreCustomerChangeLineContactSearchBeanVO.class);
		for(WmsCreCustomerChangeLineContactSearchBeanVO beanvo : relativesList){
			if(beanvo.getWms_cre_customer_change_line_contact_id()!=null){
				wms_cre_customer_change_line_contact_id_list=wms_cre_customer_change_line_contact_id_list+","+beanvo.getWms_cre_customer_change_line_contact_id();
			}
		}
		Map<String, Object> paramMap =new HashMap<>();
		paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
		paramMap.put("wms_cre_customer_change_line_contact_id_list", wms_cre_customer_change_line_contact_id_list);
		//根据head_id 删除所有主贷人亲属信息  
		wmscrecustomerchangelinecontactlistDao.deleteForHeadId(paramMap);
		
        com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineContact contact = new com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineContact();
        for (int i = 0; i < relativesList.size(); i++)
        {
            contact = new com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineContact();
            // 修改 01 判断主键是否为空 如果为空则执行保存方法 如果不为空则执行更新方法 baisong
            if (relativesList.get(i).getWms_cre_customer_change_line_contact_id() != null
                || !"".equals(relativesList.get(i).getWms_cre_customer_change_line_contact_id()))
            {
                contact.setWms_cre_customer_change_line_contact_id(relativesList.get(i)
                                                                                .getWms_cre_customer_change_line_contact_id());
            }
			contact.setContact_name(relativesList.get(i).getContact_name());
			contact.setContact_relation_type("亲属");
			contact.setContact_relation_description(relativesList.get(i).getContact_relation_description());
			contact.setContact_mobile_phone(relativesList.get(i).getContact_mobile_phone());
			contact.setWms_cre_credit_head_id(relativesList.get(i).getWms_cre_credit_head_id());
			contact.setIs_major("1");
			contact.setWms_cre_credit_line_customer_change_head_id(ChangeHead.getWms_cre_credit_line_customer_change_head_id());
            contact.setWms_cre_credit_head_id(queryInfo.getWms_cre_credit_head_id());
			if(contact.getWms_cre_customer_change_line_contact_id()!=null){
				wmscrecustomerchangelinecontactlistDao.update(contact);
			}else{
				wmscrecustomerchangelinecontactlistDao.addNewRecord(contact);	
			}
		}
        // 0：暂存，1：提交
        if ("1".equals(save_type))
        {
            // 调用流程
            WmsHouseCreditWorkFlowVO vo = new WmsHouseCreditWorkFlowVO();
            vo.setWms_cre_credit_head_id(queryInfo.getWms_cre_credit_head_id().toString());
            vo.setPass("true");// 核查结果
            vo.setUserId(queryInfo.getUser_id().toString());
            vo.setAdvice("信息完善完成");
            vo.setDebtkey("WSXX");// 完善信息
            wmsLoanWorkFlowService.publicApprovalStatus(vo);
            // 是否完善联系人
            ChangeHead.setIs_finish("1");// 是否完善联系人 是否完善联系人 1完善 0/空 未完善
            wmsCreCreditLineCustomerChangeHeadDao.update(ChangeHead);
        }
	}

    /**
     * @Title: getContactsInfoList
     * @Description: TODO(根据headid查询联系人信息)
     * @param wms_cre_credit_head_id
     * @return Map<String, Object>
     * @author: jiaodelong
     * @time:2017年2月17日 上午11:59:15
     * @see com.zx.emanage.cremanage.service.IWmsCreCustomerChangeLineContactService#getContactsInfoList(java.lang.Integer)
     * history:
     * 1、2017年2月17日 jiaodelong 创建方法
    */
    @Override
    public Map<String, Object> getContactsInfoList(WmsCreCustomerChangeLineContactSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        map.put("sortname", queryInfo.getSortname());
        map.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecustomerchangelinecontactlistDao.getContactsInfoList(map);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecustomerchangelinecontactlistDao.getContactsInfoListCount(map),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * @Title: getBizSavePerfectInfo
     * @Description: TODO(3.2.18        客户联系人信息保存)
     * @param vo
     * @param save_type 
     * @author: jiaodelong
     * @time:2017年3月17日 下午5:01:30
     * @see com.zx.emanage.cremanage.service.IWmsCreCustomerChangeLineContactService#getBizSavePerfectInfo(com.zx.emanage.cremanage.vo.WmsCreCustomerChangeLineContactSearchBeanVO, java.lang.String)
     * history:
     * 1、2017年3月17日 jiaodelong 创建方法
    */
    @Override
    @Transactional
    public void getBizSavePerfectInfo(WmsCreCustomerChangeLineContactSearchBeanVO queryInfo, String save_type)
    {
        String wms_cre_customer_change_line_contact_id_list="";
        //根据head_id   查出wms_cre_credit_line_customer_change_head_id
        WmsCreCreditLineCustomerChangeHead ChangeHead = wmsCreCreditLineCustomerChangeHeadDao.getChangeHeadIdForFour(queryInfo.getWms_cre_credit_head_id());
        List<WmsCreCustomerChangeLineContactSearchBeanVO> relativesList = JsonUtil.jsonArrayToList(queryInfo.getContacts_list(),WmsCreCustomerChangeLineContactSearchBeanVO.class);
        for(WmsCreCustomerChangeLineContactSearchBeanVO beanvo : relativesList){
            if(beanvo.getWms_cre_customer_change_line_contact_id()!=null){
                wms_cre_customer_change_line_contact_id_list=wms_cre_customer_change_line_contact_id_list+","+beanvo.getWms_cre_customer_change_line_contact_id();
            }
        }
        Map<String, Object> paramMap =new HashMap<>();
        paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
        paramMap.put("wms_cre_customer_change_line_contact_id_list", wms_cre_customer_change_line_contact_id_list);
        //根据head_id 删除所有主贷人亲属信息  
        wmscrecustomerchangelinecontactlistDao.deleteForHeadIdToFour(paramMap);
        
        com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineContact contact = new com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineContact();
        for (int i = 0; i < relativesList.size(); i++)
        {
            contact = new com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineContact();
            // 修改 01 判断主键是否为空 如果为空则执行保存方法 如果不为空则执行更新方法 baisong
            if (relativesList.get(i).getWms_cre_customer_change_line_contact_id() != null
                || !"".equals(relativesList.get(i).getWms_cre_customer_change_line_contact_id()))
            {
                contact.setWms_cre_customer_change_line_contact_id(relativesList.get(i)
                                                                                .getWms_cre_customer_change_line_contact_id());
            }
            contact.setContact_name(relativesList.get(i).getContact_name());
            contact.setContact_relation_type("亲属");
            contact.setContact_relation_description(relativesList.get(i).getContact_relation_description());
            contact.setContact_mobile_phone(relativesList.get(i).getContact_mobile_phone());
            contact.setWms_cre_credit_head_id(relativesList.get(i).getWms_cre_credit_head_id());
            contact.setIs_major("1");
            contact.setWms_cre_credit_line_customer_change_head_id(ChangeHead.getWms_cre_credit_line_customer_change_head_id());
            contact.setWms_cre_credit_head_id(queryInfo.getWms_cre_credit_head_id());
            contact.setContact_type(queryInfo.getContact_type());
            contact.setContact_type(relativesList.get(i).getContact_type());
            if(contact.getWms_cre_customer_change_line_contact_id()!=null){
                wmscrecustomerchangelinecontactlistDao.update(contact);
            }else{
                wmscrecustomerchangelinecontactlistDao.addNewRecord(contact);   
            }
        }
        //修改客户电话
        WmsCreCreditLineCustomerChangeHead changeHead = new WmsCreCreditLineCustomerChangeHead();
        if(StringUtil.isNotEmpty(queryInfo.getWms_cre_credit_head_id().toString()) && StringUtil.isNotEmpty(queryInfo.getMobile_telephone())){
            changeHead.setWms_cre_credit_head_id(queryInfo.getWms_cre_credit_head_id());
            changeHead.setMobile_telephone1(queryInfo.getMobile_telephone());
            wmsCreCreditLineCustomerChangeHeadDao.updateIsTelephone(changeHead);
        }
        // 0：暂存，1：提交
        if ("1".equals(save_type))
        {
            // 调用流程
            WmsHouseCreditWorkFlowVO vo = new WmsHouseCreditWorkFlowVO();
            vo.setWms_cre_credit_head_id(queryInfo.getWms_cre_credit_head_id().toString());
            vo.setPass("perfect");// 核查结果
            vo.setUserId(queryInfo.getUser_id().toString());
            vo.setAdvice("信息完善完成");
            vo.setDebtkey("WSXX");// 完善信息
            wmsLoanWorkFlowService.publicApprovalStatus(vo);
            WmsCreCreditLineCustomerChangeHead changeHeadf = new WmsCreCreditLineCustomerChangeHead();
            changeHeadf.setWms_cre_credit_line_customer_change_head_id(ChangeHead.getWms_cre_credit_line_customer_change_head_id());
            // 是否完善联系人
            changeHeadf.setIs_finish("1");// 是否完善联系人 是否完善联系人 1完善 0/空 未完善
            wmsCreCreditLineCustomerChangeHeadDao.update(changeHeadf);
        }
    }
}
