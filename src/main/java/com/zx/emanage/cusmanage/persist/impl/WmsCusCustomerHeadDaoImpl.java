package com.zx.emanage.cusmanage.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zx.platform.database.AbstractSimpleDao;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.emanage.cusmanage.persist.IWmsCusCustomerHeadDao;
import com.zx.emanage.cusmanage.vo.WmsCusCustomerHeadSearchBeanVO;
import com.zx.emanage.util.gen.SqlString;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.domain.WmsCusCustomerHead;
import com.zx.emanage.util.gen.vo.WmsCusCustomerHeadVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Repository("wmscuscustomerheadDao")
public class WmsCusCustomerHeadDaoImpl extends AbstractSimpleDao implements IWmsCusCustomerHeadDao
{

    /**
     * 新增并返回新增后的id.
     * 
     * @param bean
     * @return id
     */
    @Override
    public int saveByPk(WmsCusCustomerHead bean)
    {
        return this.insert(bean, "wms_cus_customer_head", "wms_cus_customer_id");
    }

    @Override
    public List<Map<String, Object>> getListWithoutPaging(WmsCusCustomerHeadSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            paramMap.put("mobile_telephone", SysTools.getSqlLikeParam(queryInfo.getMobile_telephone()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            paramMap.put("create_timestamp", SysTools.getSqlLikeParam(queryInfo.getCreate_timestamp()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.queryForListByTemplate(SqlString.AUTOSINGLE2_WMS_CUS_CUSTOMER_HEAD_LIST, paramMap);
    }

    @Override
    public List<Map<String, Object>> getListWithoutPagingforadd(WmsCusCustomerHeadSearchBeanVO queryInfo, Integer userId)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            paramMap.put("mobile_telephone", SysTools.getSqlLikeParam(queryInfo.getMobile_telephone()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            paramMap.put("create_timestamp", SysTools.getSqlLikeParam(queryInfo.getCreate_timestamp()));
        }
        if (StringUtil.isNotBlank(queryInfo.getStatus()))
        {
            paramMap.put("status", queryInfo.getStatus());
        }
        if (userId != null)
        {
            paramMap.put("create_user_id", userId);
        }
        return this.queryForListByTemplate(SqlString.AUTOSINGLE2_WMS_CUS_CUSTOMER_HEAD_LIST1, paramMap);
    }

    @Override
    public List<Map<String, Object>> getListWithPaging(WmsCusCustomerHeadSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            paramMap.put("mobile_telephone", SysTools.getSqlLikeParam(queryInfo.getMobile_telephone()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            paramMap.put("create_timestamp", SysTools.getSqlLikeParam(queryInfo.getCreate_timestamp()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.pagingForListByTemplate(SqlString.AUTOSINGLE2_WMS_CUS_CUSTOMER_HEAD_LIST, queryInfo.getPagesize(),
                                            queryInfo.getPage(), paramMap);
    }

    @Override
    public List<Map<String, Object>> getListforAddWithPaging(WmsCusCustomerHeadSearchBeanVO queryInfo, Integer userId)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            paramMap.put("mobile_telephone", SysTools.getSqlLikeParam(queryInfo.getMobile_telephone()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            paramMap.put("create_timestamp", SysTools.getSqlLikeParam(queryInfo.getCreate_timestamp()));
        }
        if (StringUtil.isNotBlank(queryInfo.getStatus()))
        {
            paramMap.put("status", queryInfo.getStatus());
        }
        if (userId != null)
        {
            paramMap.put("create_user_id", userId);
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.pagingForListByTemplate(SqlString.AUTOSINGLE2_WMS_CUS_CUSTOMER_HEAD_LIST1, queryInfo.getPagesize(),
                                            queryInfo.getPage(), paramMap);
    }

    @Override
    public int getListCountNum(WmsCusCustomerHeadSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", "%" + queryInfo.getCustomer_name() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            paramMap.put("mobile_telephone", "%" + queryInfo.getMobile_telephone() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", "%" + queryInfo.getId_card() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            paramMap.put("create_timestamp", "%" + queryInfo.getCreate_timestamp() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getStatus()))
        {
            paramMap.put("status", "%" + queryInfo.getStatus() + "%");
        }
        return this.recordNumberForListByTemplate(SqlString.AUTOSINGLE2_WMS_CUS_CUSTOMER_HEAD_LIST, paramMap);
    }

    @Override
    public int getListforAddCountNum(WmsCusCustomerHeadSearchBeanVO queryInfo, Integer userId)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", "%" + queryInfo.getCustomer_name() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
        {
            paramMap.put("mobile_telephone", "%" + queryInfo.getMobile_telephone() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            paramMap.put("create_timestamp", "%" + queryInfo.getCreate_timestamp() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getStatus()))
        {
            paramMap.put("status", queryInfo.getStatus());
        }
        if (userId != null)
        {
            paramMap.put("create_user_id", userId);
        }
        return this.recordNumberForListByTemplate(SqlString.AUTOSINGLE2_WMS_CUS_CUSTOMER_HEAD_LIST1, paramMap);
    }

    @Override
    public WmsCusCustomerHeadVO getInfoByPK(Integer wms_cus_customer_id)
    {
        return WmsCusCustomerHead.getRecordVOByPK(this, wms_cus_customer_id);
    }

    @Override
    public int addNewRecord(WmsCusCustomerHead bean)
    {
        return bean.insertRecord(this);
    }

    @Override
    public int updateRecordAll(WmsCusCustomerHead bean)
    {
        return bean.updateRecordAll(this);
    }

    @Override
    public int updateRecordCols(WmsCusCustomerHead bean)
    {
        return bean.updateRecordColumn(this);
    }

    @Override
    public int deleteRecordByPK(Integer wms_cus_customer_id)
    {
        return WmsCusCustomerHead.deleteRecordsByPK(this, wms_cus_customer_id);
    }

    @Override
    public int deleteRecordByDomain(WmsCusCustomerHead bean)
    {
        return bean.deleteRecordsByDomain(this);
    }

    @Override
    public List<WmsCusCustomerHead> getSingleTableListByAndMethod(WmsCusCustomerHead queryInfo, Boolean isExcludePKFlag)
    {
        return WmsCusCustomerHead.getSingleTableListByAndMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public List<WmsCusCustomerHead> getSingleTableListByOrMethod(WmsCusCustomerHead queryInfo, Boolean isExcludePKFlag)
    {
        return WmsCusCustomerHead.getSingleTableListByOrMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public Map<String, Object> getInfoMapByPK(Integer wms_cus_customer_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_cus_customer_id", wms_cus_customer_id);
        List<Map<String, Object>> list = this.queryForListByTemplate(SqlString.AUTOSINGLE2_WMS_CUS_CUSTOMER_HEAD_WMS_CUS_CUSTOMER_LINE_WORKINFO_LIST,
                                                                     paramMap);
        return list.get(0);
    }
}