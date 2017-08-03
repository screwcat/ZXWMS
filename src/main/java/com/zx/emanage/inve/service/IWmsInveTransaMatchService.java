package com.zx.emanage.inve.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.List;


import com.zx.emanage.util.gen.entity.WmsInveTransaMatch;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.emanage.inve.vo.WmsInveTransaMatchSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveTransaMatchService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsInveTransaMatchSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsInveTransaMatchSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInveTransaMatchVO
     * @author auto_generator
     */
    public WmsInveTransaMatch getInfoByPK(Integer wms_inve_transa_match);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsInveTransaMatch bean, UserBean user);

    /**
     * 保存债权匹配信息
     * @param transaMatch 抵押类债权信息(房贷债权和车贷债权)
     * @param wmsInveTransaProd 上单产品信息
     * @param wms_inve_redeem_id 赎回ID
     * @param category_type 产品类型
     * @param bean
     * @return "success" or "error" or user defined
     * @author hancd
     */
    public String update(WmsInveTransaMatch bean, UserBean user);

    public String saveMatch(String transaMatch, WmsInveTransaProd wmsInveTransaProd, String wms_inve_redeem_id,
                            UserBean user,String category_type);

    /**
     * Description :理财 债权匹配表的查询 是根聚产品上单表的id查询的
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author baisong
     */
    public List<Map<String, Object>> getMapForLc(Integer wms_inve_transa_prod_id, Integer wms_inve_redeem_id);
    
    public List<Map<String, Object>> getMapForHeadid(Integer wms_inve_debt_head_id);
    

    public Map<String, Object> getInveTransaMatchList(Integer wms_inve_redeem_id);

    public String saveAllMatch(String transaMatch, String wms_inve_redeem_id, UserBean user);

    public List<Map<String, Object>> getMapForLcSh(Integer wms_inve_transa_prod_id);

    /**
     * 自动债权匹配公共方法
     * 
     * @param queryInfo
     * @return record list
     * @author 张风山
     */
    public String autoTransaMatch(Integer wms_inve_transa_id, BigDecimal tzje, UserBean user);
    
    /**
     * Description :自动债权匹配
     * @param wms_inve_transa_prod_id 上单产品表主键
     * @param tzje 剩余的投资金额
     * @param user 当前登录信息
     * @return "success" or "error" or user defined
     * @author wangyihan 2015-11-30
     */
    public String autoTransaMatchNew(Integer wms_inve_transa_prod_id, BigDecimal tzje, Integer wms_inve_debt_head_id, UserBean user);

    /**
     * 保存债权匹配规则并重新匹配
     * 
     * @param queryInfo
     * @return record list
     * @author 张风山
     */
    public String saveMatchRule(String match_rule, String match_ratio, UserBean user);
    
    /**
     * 债权匹配表中根据债权调整的对应协议表id查询对应信息
     * @date 2015-12-3
     * @param wms_inve_transa_protocol_id  
     * @return record list
     * @exception 
     * @author baisongs
     */
    public List<Map<String, Object>> getMapByProtocolid(Integer wms_inve_transa_protocol_id);
}