package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveSalarySearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveSalary;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveSalaryDao extends BaseDao<WmsInveSalary> {

	/**
	 * 根据日期获取对应的工资信息
	 * 
	 * @param date
	 * @return
	 */
	List<Map<String, Object>> getWmsInveSalaryInfoByDate(String date);
	
	

    /**
     * 
     * @Title: getSalaryPersonnelPerformanceByDate
     * @Description: 获取个人业绩 static_month
     * @param date
     * @return 
     * @author: zhangyunfei
     * @time:2017年1月5日 下午1:23:49
     * history:
     * 1、2017年1月5日 Administrator 创建方法
     */

	List<Map<String, Object>> getSalaryPersonnelPerformanceByDate(String date);
	
    /**
     * 
     * @Title: getSalaryDeptPerformanceByDate
     * @Description: 获取部门业绩 根据static_month和static_season 
     * @param pmap
     * @return 
     * @author: zhangyunfei
     * @time:2017年1月5日 下午1:22:12
     * history:
     * 1、2017年1月5日 Administrator 创建方法
     */
    List<Map<String, Object>> getSalaryDeptPerformanceByDate(Map<String, Object> pmap);

    /**
     * 
     * @Title: selectWmsInveSalaryInfoByDate
     * @Description: 查询工资情况列表
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年5月10日 下午4:13:10
     * history:
     * 1、2017年5月10日 zhangmingjian 创建方法
     */
    List<Map<String, Object>> selectWmsInveSalaryInfoByDate(WmsInveSalarySearchBeanVO bean);
    Integer countWmsInveSalaryInfoByDate(WmsInveSalarySearchBeanVO bean);
}