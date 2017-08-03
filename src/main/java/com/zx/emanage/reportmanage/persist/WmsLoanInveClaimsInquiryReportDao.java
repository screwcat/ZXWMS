package com.zx.emanage.reportmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.reportmanage.vo.WmsInveCustomerMonthlyIncomeBeanVo;
import com.zx.emanage.reportmanage.vo.WmsLoanInveClaimsInquiryReportBeanVo;
import com.zx.emanage.util.gen.entity.WmsLoanInveClaimsInquiryReport;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;
/**
 * 版权所有：版权所有(C) 2015，卓信金控
 * 文件名称: WmsLoanInveClaimsInquiryReportDao.java
 * 系统名称：WMS
 * 模块名称：报表管理-财务口径-理财-债权查询报表
 * 完成日期：
 * 作    者：
 * 内容摘要：
 * 
 * 文件调用：
 * 修改记录：
 * 修改时间：
 * 修 改 人: 
 * 修改内容：
 * 关联BUG：
 * 修改方法：
 */
@MyBatisRepository
public interface WmsLoanInveClaimsInquiryReportDao extends BaseDao<WmsLoanInveClaimsInquiryReport>{
    /**
	 * 债权查询--报表
	 * @return List
	 * @author baisong
	 */
    List<Map<String ,Object>> getLoanInveClaims(Map<String, Object> paramMap);//获取报表需要的数据 每月收益
    /**
	 * 债权查询--报表--明细
	 * @return List
	 * @author baisong
	 */
    List<Map<String ,Object>> getLoanInveClaimsMx(Map<String, Object> paramMap);//获取报表需要的数据 每月收益
    /**
	 * 债权查询--报表--统计数量
	 * @return List
	 * @author baisong
	 */
	int findCount(Map<String, Object> paramMap);//获取报表需要的数据 每月收益
	/**
	 * 债权查询--报表--明细--统计数量
	 * @return List
	 * @author baisong
	 */
	int findCountMx(Map<String, Object> paramMap);//获取报表需要的数据 每月收益
	
   /**
	 * 债权查询--报表--投资产品
	 * @return List
	 * @author baisong
	 */
    List<Map<String ,Object>> getLoanInveClaimsExcel(Map<String, Object> paramMap);//
    
    /**
	 * 债权查询--报表--明细
	 * @return List
	 * @author baisong
	 */
    List<Map<String ,Object>> getLoanInveClaimsCategory(Map<String, Object> paramMap);//
    /**
     * 查询全部债权 
     * @author yangqiyu 
     * @return List
     */
    List<Map<String,Object>> getLoanInveClaimsCategoryAll();
}
