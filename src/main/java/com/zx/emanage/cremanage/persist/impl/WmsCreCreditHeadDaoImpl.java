package com.zx.emanage.cremanage.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zx.platform.database.AbstractSimpleDao;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.emanage.cremanage.persist.IWmsCreCreditHeadDao;
import com.zx.emanage.cremanage.vo.WmsCreCreditHeadSearchBeanVO;
import com.zx.emanage.util.gen.SqlString;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.domain.WmsCreCreditHead;
import com.zx.emanage.util.gen.vo.WmsCreCreditHeadVO;

/*
 * @version 2.0
 */

@Repository("wmscrecreditheadDao")
public class WmsCreCreditHeadDaoImpl extends AbstractSimpleDao implements IWmsCreCreditHeadDao
{

    @Override
    public List<Map<String, Object>> getListWithoutPaging(WmsCreCreditHeadSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            paramMap.put("create_timestamp", SysTools.getSqlLikeParam(queryInfo.getCreate_timestamp()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.queryForListByTemplate(SqlString.AUTOSINGLE2_WMS_CRE_CREDIT_HEAD_LIST, paramMap);
    }

    @Override
    public List<Map<String, Object>> getListWithPaging(WmsCreCreditHeadSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            paramMap.put("create_timestamp", SysTools.getSqlLikeParam(queryInfo.getCreate_timestamp()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.pagingForListByTemplate(SqlString.AUTOSINGLE2_WMS_CRE_CREDIT_HEAD_LIST, queryInfo.getPagesize(),
                                            queryInfo.getPage(), paramMap);
    }

    @Override
    public int getListCountNum(WmsCreCreditHeadSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", "%" + queryInfo.getSalesman_name() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            paramMap.put("create_timestamp", "%" + queryInfo.getCreate_timestamp() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", "%" + queryInfo.getId_card() + "%");
        }
        return this.recordNumberForListByTemplate(SqlString.AUTOSINGLE2_WMS_CRE_CREDIT_HEAD_LIST, paramMap);
    }

    @Override
    public WmsCreCreditHeadVO getInfoByPK(Integer wms_cre_credit_head_id)
    {
        return WmsCreCreditHead.getRecordVOByPK(this, wms_cre_credit_head_id);
    }

    @Override
    public int addNewRecord(WmsCreCreditHead bean)
    {
        return bean.insertRecord(this);
    }

    @Override
    public int updateRecordAll(WmsCreCreditHead bean)
    {
        return bean.updateRecordAll(this);
    }

    @Override
    public int updateRecordCols(WmsCreCreditHead bean)
    {
        return bean.updateRecordColumn(this);
    }

    @Override
    public int deleteRecordByPK(Integer wms_cre_credit_head_id)
    {
        return WmsCreCreditHead.deleteRecordsByPK(this, wms_cre_credit_head_id);
    }

    @Override
    public int deleteRecordByDomain(WmsCreCreditHead bean)
    {
        return bean.deleteRecordsByDomain(this);
    }

    @Override
    public List<WmsCreCreditHead> getSingleTableListByAndMethod(WmsCreCreditHead queryInfo, Boolean isExcludePKFlag)
    {
        return WmsCreCreditHead.getSingleTableListByAndMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public List<WmsCreCreditHead> getSingleTableListByOrMethod(WmsCreCreditHead queryInfo, Boolean isExcludePKFlag)
    {
        return WmsCreCreditHead.getSingleTableListByOrMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public int saveByPk(WmsCreCreditHead mcch)
    {
        return this.insert(mcch, "wms_cre_credit_head", "wms_cre_credit_head_id");
    }
}