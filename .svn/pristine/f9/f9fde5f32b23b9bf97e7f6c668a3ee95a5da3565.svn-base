package com.zx.emanage.creditRightManager.persist;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.ColumnInfo;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCredRightColRuleDao {

	List<Map<String, Object>> getWmsCredRightColRuleList();

	Map<String, Object> getRuleCoeffMap();

	List<Map<String, Object>> getPersonnelScopeVice(Map<String, Object> para);

	void deleteOtherConfig();

	void insertCurrConfig(Map<String, Object> para);

	void deleteOtherConfigDetail();

	void insertCurrConfigDetail(Map<String, Object> para);

	void updateCurrConfigDetailInfo();

	Map<String, Object> getNextGroupId();

    /**
     * @Title: getwmsCreditGroupNameList
     * @Description: 查询拆分规则组表 group_name list
     * @return 
     * @author: zhangyunfei
     * @time:2017年3月31日 下午3:40:50
     * history:
     * 1、2017年3月31日 Administrator 创建方法
    */
    LinkedList<ColumnInfo> getwmsCreditGroupNameList();
}
