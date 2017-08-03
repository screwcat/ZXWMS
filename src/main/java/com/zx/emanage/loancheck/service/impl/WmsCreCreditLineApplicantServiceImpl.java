package com.zx.emanage.loancheck.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.cremanage.persist.WmsCreCreditAttDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.service.IWmsCreditWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineApplicantDao;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineCustomerHouseDao;
import com.zx.emanage.loancheck.persist.WmsCreCreditLineCustomerVehicleDao;
import com.zx.emanage.loancheck.service.IWmsCreCreditLineApplicantService;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineApplicantSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditAtt;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineApplicant;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerHouse;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerVehicle;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditlineapplicantService")
public class WmsCreCreditLineApplicantServiceImpl implements IWmsCreCreditLineApplicantService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditLineApplicantServiceImpl.class);

    @Autowired
    private WmsCreCreditLineApplicantDao wmscrecreditlineapplicantDao;

    @Autowired
    private WmsCreCreditLineCustomerHouseDao wmscrecreditlinecustomerhouseDao;

    @Autowired
    private WmsCreCreditAttDao wmsCreCreditAttDao;

    @Autowired
    private WmsCreCreditLineCustomerVehicleDao wmscrecreditLinecustomervehicle;

    @Autowired
    private WmsCreCreditHeadDao wmscrecreditheaddao;

    @Autowired
    private IWmsCreditWorkFlowService creditWorkFlowService;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineApplicantSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditlineapplicantDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditLineApplicantSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditlineapplicantDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditlineapplicantDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreCreditLineApplicant getInfoByPK(Integer wms_cre_credit_line_applicant_id)
    {
        return wmscrecreditlineapplicantDao.get(wms_cre_credit_line_applicant_id);
    }

    @Override
    @Transactional
    public String save(WmsCreCreditLineApplicant bean, String houseStr, String carStr,
                       WmsCreditWorkFlowVO approveWorkFlowVO, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        List<WmsCreCreditLineCustomerHouse> houses = JsonUtil.jsonArrayToList(houseStr,
                                                                              WmsCreCreditLineCustomerHouse.class);
        List<WmsCreCreditLineCustomerVehicle> cars = JsonUtil.jsonArrayToList(carStr,
                                                                              WmsCreCreditLineCustomerVehicle.class);

        bean.setCreate_user_id(user.getUserId());
        bean.setCreate_user_name(user.getRealname());
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());
        bean.setCreate_timestamp(sysTime);
        bean.setLast_update_user_id(user.getUserId());
        bean.setLast_update_timestamp(sysTime);
        bean.setEnable_flag("1");
        bean.setWms_cre_credit_head_id(approveWorkFlowVO.getWms_cre_credit_head_id());

        ret = wmscrecreditlineapplicantDao.save(bean);
        if (houses != null)
        {
            for (WmsCreCreditLineCustomerHouse h : houses)
            {
                h.setCreate_user_id(user.getUserId());
                h.setCreate_user_name(user.getRealname());
                h.setCreate_timestamp(sysTime);
                h.setLast_update_user_id(user.getUserId());
                h.setLast_update_timestamp(sysTime);
                h.setEnable_flag("1");
                h.setWms_cre_credit_head_id(approveWorkFlowVO.getWms_cre_credit_head_id());
                wmscrecreditlinecustomerhouseDao.save(h);
            }
        }
        if (cars != null)
        {
            for (WmsCreCreditLineCustomerVehicle v : cars)
            {
                v.setCreate_user_id(user.getUserId());
                v.setCreate_user_name(user.getRealname());
                v.setCreate_timestamp(sysTime);
                v.setLast_update_user_id(user.getUserId());
                v.setLast_update_timestamp(sysTime);
                v.setEnable_flag("1");
                v.setWms_cre_credit_head_id(approveWorkFlowVO.getWms_cre_credit_head_id());
                wmscrecreditLinecustomervehicle.save(v);
            }
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("certificate_appro_user_id", user.getUserId());
        params.put("certificate_appro_user_name", user.getRealname());
        params.put("certificate_appro_timestamp", sysTime);
        params.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        wmscrecreditheaddao.updateRecord(params);
        approveWorkFlowVO.setUser_id(user.getUserId());
        creditWorkFlowService.creTeamCheckApproveWorkFlow(approveWorkFlowVO);
        return "success";
    }

    @Override
    @Transactional
    public String saveForAdd(String fileArr, WmsCreditWorkFlowVO approveWorkFlowVO, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());
        List<WmsCreCreditAtt> wmsCreCreditAtts = JsonUtil.jsonArrayToList(fileArr, WmsCreCreditAtt.class);
        for (WmsCreCreditAtt a : wmsCreCreditAtts)
        {
            a.setData_type("1");
            a.setWms_cre_credit_head_id(approveWorkFlowVO.getWms_cre_credit_head_id());
            a.setCreate_user_id(user.getUserId());
            a.setCreate_user_name(user.getRealname());
            a.setCreate_timestamp(sysTime);
            a.setLast_update_user_id(user.getUserId());
            a.setLast_update_timestamp(sysTime);
            a.setEnable_flag("1");
            ret = wmsCreCreditAttDao.save(a);
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("certificate_appro_user_id", user.getUserId());
        params.put("certificate_appro_user_name", user.getRealname());
        params.put("certificate_appro_timestamp", sysTime);
        params.put("wms_cre_credit_head_id", approveWorkFlowVO.getWms_cre_credit_head_id());
        wmscrecreditheaddao.updateRecord(params);
        if (ret == 0)
        {
            resStr = "error";
        }
        approveWorkFlowVO.setUser_id(user.getUserId());
        creditWorkFlowService.creTeamCheckApproveWorkFlow(approveWorkFlowVO);
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreCreditLineApplicant bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditlineapplicantDao.update(bean); // update a record
                                                         // replace columns,
                                                         // nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsCreCreditLineApplicant() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreCreditLineApplicant> getListByEntity(WmsCreCreditLineApplicant queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreCreditLineApplicant> beanList = wmscrecreditlineapplicantDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    public WmsCreCreditLineApplicant getInfoByFK(Integer wms_cre_credit_head_id)
    {
        return wmscrecreditlineapplicantDao.getByFK(wms_cre_credit_head_id);
    }

    @Override
    public List<Map<String, Object>> getFileList(Integer wms_cre_credit_head_id, String data_type)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        paramMap.put("data_type", data_type);
        return wmsCreCreditAttDao.search(paramMap);
    }
}
