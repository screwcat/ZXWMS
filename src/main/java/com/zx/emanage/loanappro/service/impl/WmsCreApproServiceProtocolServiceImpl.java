package com.zx.emanage.loanappro.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.loanappro.persist.WmsCreApproServiceProtocolDao;
import com.zx.emanage.loanappro.service.IWmsCreApproServiceProtocolService;
import com.zx.emanage.loanappro.vo.WmsCreApproServiceProtocolSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreApproBorrowProtocol;
import com.zx.emanage.util.gen.entity.WmsCreApproServiceProtocol;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscreapproserviceprotocolService")
public class WmsCreApproServiceProtocolServiceImpl implements IWmsCreApproServiceProtocolService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreApproServiceProtocolServiceImpl.class);

    @Autowired
    private WmsCreApproServiceProtocolDao wmscreapproserviceprotocolDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreApproServiceProtocolSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscreapproserviceprotocolDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreApproServiceProtocolSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscreapproserviceprotocolDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscreapproserviceprotocolDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreApproServiceProtocol getInfoByPK(Integer wms_cre_appro_service_protocol_id)
    {
        return wmscreapproserviceprotocolDao.get(wms_cre_appro_service_protocol_id);
    }

    @Override
    @Transactional
    public String save(WmsCreApproServiceProtocol bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        bean.setCreate_user_id(user.getUserId());
        bean.setCreate_user_name(user.getRealname());
        bean.setEnable_flag("1");
        bean.setCreate_timestamp(new Timestamp(new Date().getTime()));
        bean.setLast_update_timestamp(new Timestamp(new Date().getTime()));
        ret = wmscreapproserviceprotocolDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreApproServiceProtocol bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscreapproserviceprotocolDao.update(bean); // update a record
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
     * WmsCreApproServiceProtocol() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreApproServiceProtocol> getListByEntity(WmsCreApproServiceProtocol queryInfo,
                                                             Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreApproServiceProtocol> beanList = wmscreapproserviceprotocolDao.getListByEntity(queryInfo);
        return beanList;
    }

    /**
     * 根据贷款主表id查询居间服务协议信息
     * 
     * @param
     * @return
     * @author 张风山
     */
    @Override
    public Map<String, Object> selectAllByWmsCreCreditHeadId(String wms_cre_credit_head_id, UserBean user)
    {
        WmsCreApproServiceProtocol queryInfo = new WmsCreApproServiceProtocol();
        queryInfo.setWms_cre_credit_head_id(Integer.parseInt(wms_cre_credit_head_id));
        List<WmsCreApproServiceProtocol> beanList = wmscreapproserviceprotocolDao.getListByEntity(queryInfo);

        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("fwht", beanList);
        return resMap;

    }

    /**
     * 根据贷款主表id查询居间服务协议信息
     * 
     * @param
     * @return
     * @author 张风山
     */
    @Override
    public Map<String, Object> selectAllByProtocolId(String judgeString, UserBean user)
    {

        WmsCreApproServiceProtocol queryInfo = new WmsCreApproServiceProtocol();
        Map<String, Object> resMap = new HashMap<String, Object>();
        String[] protocolIdStr = judgeString.split(",");
        String result = "have";
        if (protocolIdStr.length == 0)
        {
            resMap.put("fwhtpd", result);
            return resMap;
        }
        queryInfo.setProtocol_id_year(protocolIdStr[0]);
        queryInfo.setProtocol_id_num(protocolIdStr[1]);
        List<WmsCreApproServiceProtocol> beanList = wmscreapproserviceprotocolDao.getListByEntity(queryInfo);

        if (beanList.size() == 0)
        {
            result = "nohave";
        }
        resMap.put("fwhtpd", result);
        return resMap;

    }
}
