package com.zx.emanage.sysmanage.service;

import java.util.Map;

import com.zx.emanage.sysmanage.vo.WmsInveWarningBeanVO;

public interface IWmsInveWarningService {
		/**
		 * 获取债权预警信息
		 * @param WmsInveWarningBeanVO
		 * @return map
		 * @author baisong
		 */
		public Map<String,Object> getInfo(WmsInveWarningBeanVO bean);
		/**
		 * 获取债权预警信息-Excel
		 * @param WmsInveWarningBeanVO
		 * @return map
		 * @author baisong
		 */
		public Map<String,Object> getInfoExcel(WmsInveWarningBeanVO bean);
		/**
		 * 获取债权预警信息统计
		 * @param 
		 * @return map
		 * @author baisong
		 */
		public Map<String,Object> getSumInfo();
}
