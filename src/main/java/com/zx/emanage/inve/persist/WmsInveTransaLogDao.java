package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveTransaLogImportVO;
import com.zx.emanage.util.gen.entity.WmsInveTransaLog;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveTransaLogDao extends BaseDao<WmsInveTransaLog> {
	/**
	 * @Title: addBatchWmsInveTransaLogs 
	 * @Description: 批量添加日志信息
	 * @param wmsInveTransaLogs
	 * @return int 
	 * @throws
	 * @author lvtu 
	 * @date 2015年8月28日 上午10:08:00
	 */
	public int addBatchWmsInveTransaLogs(List<WmsInveTransaLog> wmsInveTransaLogs);
	/**
	 * @Title: getWmsInveTransaLog 
	 * @Description: 查询日志信息
	 * @param paramap
	 * @return WmsInveTransaLog 
	 * @throws
	 * @author lvtu 
	 * @date 2015年9月1日 上午9:48:38
	 */
	public List<WmsInveTransaLog> getWmsInveTransaLog(Map<String, Object> paramap);
	
	/**
	 * @Title: delWmsInveTransaLogs 
	 * @Description: 根据上单信息表主键删除日志信息
	 * @param wms_inve_transa_id
	 * @return int 
	 * @throws
	 * @author lvtu 
	 * @date 2015年9月10日 下午7:05:20
	 */
	public int delWmsInveTransaLogs(Integer wms_inve_transa_id);
	
	/**
	 * 删除某个单据的某个时间的公益收益交易记录
	 * 
	 * @param paramMap
	 * @return
	 *
	 * @author 金志明
	 * @date 2017年10月11日 下午2:28:16
	 */
	public void deleteTransaLog(Map<String, Object> paramMap);
	
	/**
	 * 删除传入的理财单据主键集合的所有交易日志
	 * 
	 * @param list
	 *
	 * @author 金志明
	 * @date 2016年10月12日 下午4:42:07
	 */
	public void removeWmsInveTransaLogs(List<String> list);
	
	/**
	 * 删除所有收益中的理财单据的交易日志
	 * 
	 *
	 * @author 金志明
	 * @date 2016年10月12日 下午4:42:49
	 */
	public void removeWmsInveTransaLogsInIncome();

    /**
     * @Title: saveImportWmsInveTransaLog
     * @Description: 保存导入客户收益日志表
     * @param logList 
     * @author: jingzm
     * @time:2016年12月2日 下午1:10:35
     * history:
     * 1、2016年12月2日 Administrator 创建方法
    */
    public void saveImportWmsInveTransaLog(List<WmsInveTransaLogImportVO> logList);
}