package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveTransaProdDao extends BaseDao<WmsInveTransaProd>
{
    /**
     * get:根据主键获取实体类. <br/>
     * 
     * @author ry
     * @param id
     * @return
     */
    WmsInveTransaProd getForJEGL(Integer wms_inve_transa_id);

    /**
     * @param paramMap
     * @return
     */
    int calRecordNum(Map<String, Object> paramMap);

    int updateRedeem(WmsInveTransaProd wmsInveTransaProd);
    
    /**
     * @Title: saveBak 
     * @Description: 备份上单产品
     * @param wmsInveTransaProd
     * @return int 
     * @throws
     * @author lvtu 
     * @date 2015年9月8日 下午3:54:39
     */
    int saveBak(WmsInveTransaProd wmsInveTransaProd);
    /**
     * 此sql中如果上单金额为空 会更新到数据库
     * @param wmsInveTransaProd
     * @return
     */
    int updateAll(WmsInveTransaProd wmsInveTransaProd);
    
    int updateIncomeCard(WmsInveTransaProd wmsInveTransaProd);
    
    /**
     * 清空验证码
     * @param wmsInveTransaProd
     * @return
     */
    int updateSmsCard(WmsInveTransaProd wmsInveTransaProd);

	/**
	 * @Title getHistoryCustomerBankInfo
	 * @Description 根据客户的姓名获取客户存在系统中的收益卡信息
	 * @param params
	 *            条件参数集合
	 * @return 返回map集合
	 * @author DongHao
	 * @date 2016年11月1日11:19:43
	 */
	List<Map<String, Object>> getHistoryCustomerBankInfo(
			Map<String, Object> params);

    /**
     * @Title: getWmsInveTransaProdInfos
     * @Description: 根据客户姓名和身份证号获取对应的收益卡信息
     * @param params 参数集合
     * @return 返回list集合
     * @author: DongHao
     * @time:2016年12月16日 上午11:16:27
     * history:
     * 1、2016年12月16日 DongHao 创建方法
    */
    List<Map<String, Object>> getWmsInveTransaProdInfos(Map<String, Object> params);

    /**
     * @Title: getIncomeCardInfo
     * @Description: 根据上单表主键获取收益卡信息
     * @param wms_inve_transa_prod_id 上单产品表主键
     * @return 返回map集合
     * @author: DongHao
     * @time:2017年4月7日 下午5:48:47
     * history:
     * 1、2017年4月7日 DongHao 创建方法
    */
    Map<String, Object> getIncomeCardInfo(Integer wms_inve_transa_prod_id);
}