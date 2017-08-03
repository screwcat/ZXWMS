package com.zx.emanage.sysmanage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.emanage.sysmanage.persist.WmsSysAssessmentAdviceInfoDao;
import com.zx.emanage.sysmanage.service.IWmsSysAssessmentAdviceInfoService;
import com.zx.emanage.util.gen.entity.WmsSysAssessmentAdviceInfo;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: IWmsSysAssessmentAdviceInfoServiceImpl
 * 模块名称：
 * @Description: 内容摘要：
 * @author ZhangWei
 * @date 2017年5月18日
 * @version V1.0
 * history:
 * 1、2017年5月18日 ZhangWei 创建文件
 */
@Service("wmsSysAssessmentAdviceInfoService")
public class WmsSysAssessmentAdviceInfoServiceImpl implements  IWmsSysAssessmentAdviceInfoService{
	@Autowired
    private WmsSysAssessmentAdviceInfoDao wmsSysAssessmentAdviceInfoDao;
	        
	/**
	 * @Title: getAssessmentAdviceInfo
	 * @Description: 获取审批意见选项
	 * @return 
	 * @author: ZhangWei
	 * @time:2017年5月18日 上午11:07:10
	 * @see com.zx.emanage.sysmanage.service.IWmsSysAssessmentAdviceInfoService#getAssessmentAdviceInfo()
	 * history:
	 * 1、2017年5月18日 ZhangWei 创建方法
	*/
	@Override
	public List<WmsSysAssessmentAdviceInfo> getAssessmentAdviceInfo() {
		return wmsSysAssessmentAdviceInfoDao.getAdviceInfo();
	}

}
