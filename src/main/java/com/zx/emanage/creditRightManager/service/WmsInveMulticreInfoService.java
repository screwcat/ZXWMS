package com.zx.emanage.creditRightManager.service;



import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.zx.emanage.creditRightManager.vo.WmsInveMulticreInfoVO;
import com.zx.sframe.util.vo.UserBean;



/**
 * @ClassName: WmsInveMulticreInfoService
 * @Description: 内容摘要：债权采集Service
 * @author WangShuai
 * @date 2017年2月8日
 * @version V1.0
 * history:
 * 1、2017年2月8日 WangShuai 创建文件
 */
public interface WmsInveMulticreInfoService {

	/**
	 * @Title: creditCollectionlistall
	 * @Description: 债权采集查询
	 * @param wmsInveMulticreInfoVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月8日 上午10:44:55
	 * history:
	 * 1、2017年2月8日 WangShuai 创建方法
	*/
	Map<String, Object> creditCollectionlistall(
			WmsInveMulticreInfoVO wmsInveMulticreInfoVO);
	
	/**
	 * @Title: doCreditRightExcelImport
	 * @Description: 债权采集导入
	 * @param wmsInveMulticreInfoVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月8日 上午10:44:55
	 * history:
	 * 1、2017年2月8日 WangShuai 创建方法
	*/
	Map<String, Object> doCreditRightExcelImport(String name,MultipartFile file);

	/**
	 * @Title: wmsCredRightColData
	 * @Description:获取单据新增初始化数据
	 * @param user
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月21日 上午10:41:58
	 * history:
	 * 1、2017年2月21日 WangShuai 创建方法
	*/
	Map<String, Object> wmsCredRightColData(UserBean user);

	/**
	 * @Title: saveWmsCreditRightCollAddInfo
	 * @Description: 保存在全采集包
	 * @param user
	 * @param wmsInveMulticreInfoVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月21日 下午12:01:39
	 * history:
	 * 1、2017年2月21日 WangShuai 创建方法
	*/
	Map<String, Object> saveWmsCreditRightCollAddInfo(UserBean user,
			WmsInveMulticreInfoVO wmsInveMulticreInfoVO);

	/**
	 * @Title: getWmsCredRightColDataList
	 * @Description: 债权采集详情页面
	 * @param user
	 * @param wmsInveMulticreInfoVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月21日 下午3:09:33
	 * history:
	 * 1、2017年2月21日 WangShuai 创建方法
	*/
	Map<String, Object> getWmsCredRightColDataList(UserBean user,
			WmsInveMulticreInfoVO wmsInveMulticreInfoVO);

	/**
	 * @Title: wmsCredRightColDataEdit
	 * @Description: 采集包编辑页面信息获取
	 * @param user
	 * @param wmsInveMulticreInfoVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月22日 下午5:03:49
	 * history:
	 * 1、2017年2月22日 WangShuai 创建方法
	*/
	Map<String, Object> wmsCredRightColDataEdit(UserBean user,
			WmsInveMulticreInfoVO wmsInveMulticreInfoVO);

    /**
     * @Title: handleOfflineCredit
     * @Description: 处理线下导入债权
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年4月11日 上午8:52:13
     * history:
     * 1、2017年4月11日 jinzhm 创建方法
     */
    public void handleOfflineImportCredit(UserBean user);
}
