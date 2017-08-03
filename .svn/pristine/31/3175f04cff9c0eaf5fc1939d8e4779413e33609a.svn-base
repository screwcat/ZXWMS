package com.zx.emanage.cremanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreHousingPayment;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreHousingPaymentDao
 * 模块名称：缴费
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 baisong 创建文件
 */
@MyBatisRepository
public interface WmsCreHousingPaymentDao extends BaseDao <WmsCreHousingPayment> {
    /**
     * 
     * 房产核查缴费查询列表
     * @param paramMap
     * @return
     */
	List<Map<String, Object>> getSeachForPaymentVerification(
			Map<String, Object> paramMap);

    /**
     * 
     * 房产核查缴费裂变条数
     * @param paramMap
     * @return
     */
int getSeachCountForPaymentVerification(Map<String, Object> paramMap);

    /**
     * 
     * 缴费查询导出
     * @param paramMap
     * @return
     */
List<Map<String,Object>> getSeachForPaymentVerificationout(Map<String, Object> paramMap);

	    /**
     * 缴费确认页面初始化数据
     */
	public Map<String, Object> housingPaymentVerificationDisp(Map<String, Object> paramMap);

    /**
     * @Title: getAllByHeadId
     * @Description: TODO(根据headid查询flag为1的全部数据)
     * @param wms_cre_credit_head_id
     * @return 
     * @author: jiaodelong
     * @time:2016年12月7日 上午11:32:35
     * history:
     * 1、2016年12月7日 jiaodelong 创建方法
    */
    WmsCreHousingPayment getAllByHeadId(Integer wms_cre_credit_head_id);
	
    /**
    * 
    * @Title: getCopyInfo
    * @Description: TODO(查询当前表的所有信息--用于复制)
    * @param id
    * @return 
    * @author: baisong
    * @time:2016年12月23日 下午5:18:59
    * history:
    * 1、2016年12月23日 baisong 创建方法
    */
    WmsCreHousingPayment getCopyInfo(Integer id);

}