package com.zx.emanage.loanappro.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 版权所有：版权所有(C) 2017，卓信金控 系统名称：财富管理平台
 * 
 * @ClassName: WmsCreNotarizationOtherDao 模块名称：
 * @Description: 内容摘要：
 * @author ZhangWei
 * @date 2017年7月6日
 * @version V1.0 history: 1、2017年7月6日 ZhangWei 创建文件
 */
@MyBatisRepository
public interface WmsCreNotarizationOtherDao extends BaseDao<WmsCreCreditHead> {
	List<Map<String, Object>> getNotarizationOtherListWithPaging(Map<String, Object> paramMap);

	int getNotarizationOtherCount(Map<String, Object> paramMap);
	
	List<Map<String, Object>> getNotarizationOtherListWithoutPaging(Map<String, Object> paramMap);
	
	List<Map<String, Object>> GetFinanceLoanAppInfo(Map<String, Object> parameters);

	int updateNotarizationOther(Map<String, Object> paramMap);

    /**
     * @Title: getClaimsPackageInfo
     * @Description: 查询债权包信息
     * @param paramMap
     * @return 
     * @author: baisong
     * @time:2017年7月20日 下午5:12:22
     * history:
     * 1、2017年7月20日 baisong 创建方法
    */
    List<Map<String, Object>> getClaimsPackageInfo(Map<String, Object> paramMap);
}
