package com.zx.emanage.sysmanage.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import com.zx.emanage.sysmanage.persist.WmsInveWarningDao;
import com.zx.emanage.sysmanage.service.IWmsInveWarningService;
import com.zx.emanage.sysmanage.vo.WmsInveWarningBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;

@Service("wmsinvewarningservice")
public class WmsInveWarningServiceImpl implements IWmsInveWarningService{
	private static Logger log = LoggerFactory.getLogger(WmsInveWarningServiceImpl.class);
	
	@Autowired
	private WmsInveWarningDao wmsInveWarningDao;
	/**
	 * 获取债权预警信息
	 * @param WmsInveWarningBeanVO
	 * @return map
	 * @author baisong
	 */
	@Override
	public Map<String, Object> getInfo(WmsInveWarningBeanVO bean) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		if (bean.getCre_type() != null && !"".equals(bean.getCre_type()))
        {
        	paramMap.put("cre_type", bean.getCre_type());
        }
		if (bean.getProtocol_date_begin()!= null && !"".equals(bean.getProtocol_date_begin()))
        {
        	paramMap.put("protocol_date_begin", bean.getProtocol_date_begin());
        }
		if (bean.getProtocol_date_end()!= null && !"".equals(bean.getProtocol_date_end()))
        {
        	paramMap.put("protocol_date_end", bean.getProtocol_date_end());
        }
		if (bean.getBorrow_end_date_begin()!= null && !"".equals(bean.getBorrow_end_date_begin()))
        {
        	paramMap.put("borrow_end_date_begin", bean.getBorrow_end_date_begin());
        }
		if (bean.getBorrow_end_date_end()!= null && !"".equals(bean.getBorrow_end_date_end()))
        {
        	paramMap.put("borrow_end_date_end", bean.getBorrow_end_date_end());
        }
		if (bean.getIs_occupy()!= null && !"".equals(bean.getIs_occupy()))
        {
        	paramMap.put("is_occupy", bean.getIs_occupy());
        }
		if (bean.getPtp_or_wms()!= null && !"".equals(bean.getPtp_or_wms()))
        {
        	paramMap.put("ptp_or_wms", bean.getPtp_or_wms());
        }    
        paramMap.put("sortname", bean.getSortname());
        paramMap.put("sortorder", bean.getSortorder());
        paramMap.put("pagesize", bean.getPagesize());
        paramMap.put("offset", bean.getOffset());
        //俩种显示方式
        List<Map<String, Object>> list = wmsInveWarningDao.getInfo(paramMap);
        GridDataBean<Map<String, Object>> beangird = new GridDataBean<Map<String, Object>>(bean.getPage(),wmsInveWarningDao.findCount(paramMap),list);
        return beangird.getGridData();
	}
	/**
	 * 获取债权预警信息--getInfoExcel
	 * @param WmsInveWarningBeanVO
	 * @return map
	 * @author baisong
	 */
	@Override
	public Map<String, Object> getInfoExcel(WmsInveWarningBeanVO bean) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		if (bean.getCre_type() != null && !"".equals(bean.getCre_type()))
        {
        	paramMap.put("cre_type", bean.getCre_type());
        }
		if (bean.getProtocol_date_begin()!= null && !"".equals(bean.getProtocol_date_begin()))
        {
        	paramMap.put("protocol_date_begin", bean.getProtocol_date_begin());
        }
		if (bean.getProtocol_date_end()!= null && !"".equals(bean.getProtocol_date_end()))
        {
        	paramMap.put("protocol_date_end", bean.getProtocol_date_end());
        }
		if (bean.getBorrow_end_date_begin()!= null && !"".equals(bean.getBorrow_end_date_begin()))
        {
        	paramMap.put("borrow_end_date_begin", bean.getBorrow_end_date_begin());
        }
		if (bean.getBorrow_end_date_end()!= null && !"".equals(bean.getBorrow_end_date_end()))
        {
        	paramMap.put("borrow_end_date_end", bean.getBorrow_end_date_end());
        }
		if (bean.getIs_occupy()!= null && !"".equals(bean.getIs_occupy()))
        {
        	paramMap.put("is_occupy", bean.getIs_occupy());
        }
		if (bean.getPtp_or_wms()!= null && !"".equals(bean.getPtp_or_wms()))
        {
        	paramMap.put("ptp_or_wms", bean.getPtp_or_wms());
        }    
        paramMap.put("sortname", bean.getSortname());
        paramMap.put("sortorder", bean.getSortorder());
        paramMap.put("pagesize", bean.getPagesize());
        paramMap.put("offset", bean.getOffset());
        //俩种显示方式
        List<Map<String, Object>> list = wmsInveWarningDao.getInfo(paramMap);
        paramMap.clear();
        paramMap.put("Rows", list);
        return paramMap;
	}
	
	/**
	 * 获取债权预警信息统计
	 * @param 
	 * @return map
	 * @author baisong
	 */
	@Override
	public Map<String, Object> getSumInfo() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        //俩种显示方式
		paramMap = wmsInveWarningDao.getSumInfo();
		if(paramMap!=null){
			BigDecimal  total_creditor=new BigDecimal(0);//总债权
			total_creditor=new BigDecimal(paramMap.get("sum_matching_creditor").toString()).add(new BigDecimal(paramMap.get("sum_sum_creditor").toString()));
			paramMap.put("total_creditor", total_creditor);
			
			BigDecimal  proportion_value=new BigDecimal(0);//剩余债权占总债权的
			proportion_value=new BigDecimal(paramMap.get("sum_matching_creditor").toString()).divide(total_creditor,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
			paramMap.put("proportion_value", proportion_value);
		}
        return paramMap;
	}
}
