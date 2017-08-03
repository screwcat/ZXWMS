package com.zx.emanage.loanappro.service.impl;

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
import com.zx.emanage.loanappro.persist.WmsCreApproAppealPostilDao;
import com.zx.emanage.loanappro.service.IWmsCreApproAppealPostilService;
import com.zx.emanage.loanappro.vo.WmsCreApproAppealPostilSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreApproAppealPostil;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineApplicant;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCourtCaseStatus;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscreapproappealpostilService")
public class WmsCreApproAppealPostilServiceImpl implements IWmsCreApproAppealPostilService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreApproAppealPostilServiceImpl.class);

    @Autowired
    private WmsCreApproAppealPostilDao wmscreapproappealpostilDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreApproAppealPostilSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscreapproappealpostilDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreApproAppealPostilSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscreapproappealpostilDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscreapproappealpostilDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsCreApproAppealPostil getInfoByPK(Integer wms_cre_appro_appeal_postil_id)
    {
        return wmscreapproappealpostilDao.get(wms_cre_appro_appeal_postil_id);
    }

    @Override
    @Transactional
    public String save(WmsCreApproAppealPostil bean, UserBean user, int wms_cre_credit_head_id)
    {
        String resStr = "success";
        int ret = 0;
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());// 保存当前时间
        // List<WmsCreApproAppealPostil>
        // csin=JsonUtil.jsonArrayToList(bean,WmsCreApproAppealPostil.class);//法院网案件执行状态

        bean.setCreate_user_id(user.getUserId());
        bean.setCreate_user_name(user.getRealname());
        bean.setCreate_timestamp(sysTime);
        bean.setLast_update_user_id(user.getUserId());
        bean.setLast_update_timestamp(sysTime);
        bean.setEnable_flag("1");
        bean.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        ret = wmscreapproappealpostilDao.save(bean);

        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsCreApproAppealPostil bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmscreapproappealpostilDao.update(bean); // update a record
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
     * WmsCreApproAppealPostil() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsCreApproAppealPostil> getListByEntity(WmsCreApproAppealPostil queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsCreApproAppealPostil> beanList = wmscreapproappealpostilDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    public Map<String, Object> getInfoByFK(Integer wms_cre_credit_head_id)
    {
        List<Map<String, Object>> list = wmscreapproappealpostilDao.searchByFK(wms_cre_credit_head_id);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }
}
