package com.zx.emanage.sysmanage.persist;

import java.util.List;

import com.zx.emanage.util.gen.entity.WmsSysAssessmentAdviceInfo;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 版权所有：版权所有(C) 2017，卓信金控 系统名称：财富管理平台
 * 
 * @ClassName: IWmsSysAssessmentAdviceInfoDao 模块名称：
 * @Description: 内容摘要：
 * @author ZhangWei
 * @date 2017年5月18日
 * @version V1.0 history: 1、2017年5月18日 ZhangWei 创建文件
 */

@MyBatisRepository
public interface WmsSysAssessmentAdviceInfoDao extends BaseDao<WmsSysAssessmentAdviceInfo> {
	/**
	 * 
	 * @Title: getAdviceInfo
	 * @Description: 获取审批意见选项
	 * @return 
	 * @author: ZhangWei
	 * @time:2017年5月18日 上午11:17:39
	 * history:
	 * 1、2017年5月18日 ZhangWei 创建方法
	 */
	List<WmsSysAssessmentAdviceInfo> getAdviceInfo();
}
