package com.zx.emanage.loancheck.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreHousingAttDao;
import com.zx.emanage.cremanage.service.IWmsHouseCreditWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loancheck.service.IWmsCreHousingCertificateService;
import com.zx.emanage.loancheck.vo.WmsCreHousingCheckSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreHousingAtt;
import com.zx.emanage.util.gen.entity.WmsCreHousingCheck;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

@Service("wmscrehousingcertificateService")
public class WmsCreHousingCertificateServiceImpl implements IWmsCreHousingCertificateService
{

    @Autowired
    private WmsCreHousingAttDao wmscrehousingattDao;

    @Autowired
    private IWmsHouseCreditWorkFlowService houseCreditWorkFlowService;

    @Autowired
    private WmsCreCreditHeadDao wmscrecreditheaddao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreHousingCheckSearchBeanVO queryInfo)
    {

        return null;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsCreHousingCheckSearchBeanVO queryInfo)
    {

        return null;
    }

    @Override
    public WmsCreHousingCheck getInfo(Integer wms_cre_housing_check_id)
    {

        return null;
    }

    /**
     * 证信审核上传附件
     * 
     * @param user vo fileArr
     * @return success or error
     * @author zhubo
     */
    @Override
    public String save(UserBean user, WmsHouseCreditWorkFlowVO vo, String fileArr)
    {
        String resStr = "success";
        int ret = 0;
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());
        List<WmsCreHousingAtt> attachment = JsonUtil.jsonArrayToList(fileArr, WmsCreHousingAtt.class); // 前台json附件路径数据转换为list
        for (int i = 0; i < attachment.size(); i++)
        {
            WmsCreHousingAtt mcha = attachment.get(i);
            mcha.setData_type("6");// 6为证信审核附件
            mcha.setWms_cre_credit_head_id(Integer.valueOf(vo.getWms_cre_credit_head_id()));
            mcha.setCreate_user_id(user.getUserId()); // 创建人id
            mcha.setCreate_user_name(user.getRealname());// 创建人名字
            mcha.setCreate_timestamp(sysTime);// 创建时间
            mcha.setEnable_flag("1");// 是否有效
            ret = wmscrehousingattDao.save(mcha);
        }
        if (ret == 0)
        {
            resStr = "error";
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("certificate_appro_user_id", user.getUserId());
        params.put("certificate_appro_user_name", user.getRealname());
        params.put("certificate_appro_timestamp", sysTime);
        params.put("certificate_appro_result", "true".equals(vo.getPass()) ? "1" : "0");// 当流做出来后需要修改此值为pass的值
        params.put("wms_cre_credit_head_id", vo.getWms_cre_credit_head_id());
        wmscrecreditheaddao.updateRecord(params);
        vo.setUserId(String.valueOf(user.getUserId()));
        houseCreditWorkFlowService.creTeamHouseCheckApproveWorkFlow(vo);
        return resStr;
    }

    @Override
    public String update(WmsCreHousingCheck bean, UserBean user)
    {

        return null;
    }

}
