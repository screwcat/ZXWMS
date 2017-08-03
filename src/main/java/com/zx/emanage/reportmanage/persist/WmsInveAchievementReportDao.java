package com.zx.emanage.reportmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveAchievementReportDao {

	List<Map<String, Object>> searchBaseAchievementPerList(
			Map<String, Object> paramMap);

	int findPerCount(Map<String, Object> paramMap);

	Map<String, Object> getSpecialMonthPersonnelMap(Map<String, Object> baseMap);

	List<Map<String, Object>> searchBaseAchievementTeamList(
			Map<String, Object> paramMap);

	Map<String, Object> getSpecialMonthTeamMap(Map<String, Object> baseMap);

	int findteamCount(Map<String, Object> paramMap);

	List<Map<String, Object>> getPerAddList(Map<String, Object> paramMap);

	List<Map<String, Object>> getPerStockList(Map<String, Object> paramMap);

	List<Map<String, Object>> getTeamAddList(Map<String, Object> paramMap);

	List<Map<String, Object>> getTeamStockList(Map<String, Object> paramMap);

	List<Map<String, Object>> getCenterAddList(Map<String, Object> paramMap);

	List<Map<String, Object>> getCenterStockList(Map<String, Object> paramMap);

	List<Map<String, Object>> getViceAddList(Map<String, Object> paramMap);

	List<Map<String, Object>> getViceStockList(Map<String, Object> paramMap);

	List<Map<String, Object>> getGenAddList(Map<String, Object> paramMap);

	List<Map<String, Object>> getGenStockList(Map<String, Object> paramMap);

	List<Map<String, Object>> getCurrMonthDetailInve1(
			Map<String, Object> paramMap);

	List<Map<String, Object>> getCurrMonthDetailInve2(
			Map<String, Object> paramMap);

	List<Map<String, Object>> getOtherMonthDetailInve1(
			Map<String, Object> paramMap);

	List<Map<String, Object>> getOtherMonthDetailInve2(
			Map<String, Object> paramMap);

	void deleteExistsData(Map<String, Object> para);

	void insertPersonnelStockData_daySta(Map<String, Object> para);

	void insertPersonnelStockData_daySta_com(Map<String, Object> para);

	void updatePerAllAddData_daySta(Map<String, Object> para);

	void updatePerAllAddData_daySta_com(Map<String, Object> para);

	void updateTeamDeptStockData_daySta(Map<String, Object> para);

	void updateTeamViceStockData_daySta(Map<String, Object> para);

	void updateTeamCenterStockData_daySta(Map<String, Object> para);

	void updateTeamGelStockData_daySta(Map<String, Object> para);

	void updateTeamGelStockData_daySta_com(Map<String, Object> para);

	void updateTeamDeptAddData_daySta(Map<String, Object> para);

	void updateTeamViceAddData_daySta(Map<String, Object> para);

	void updateTeamCenterAddData_daySta(Map<String, Object> para);

	void updateTeamGelAddData_daySta(Map<String, Object> para);

	void updateTeamGelAddData_daySta_com(Map<String, Object> para);

	void updateTeamDeptAddData_withoutRenInve_daySta(Map<String, Object> para);

	void updateTeamViceAddData_withoutRenInve_daySta(Map<String, Object> para);

	void updateTeamCenterAddData_withoutRenInve_daySta(Map<String, Object> para);

	void updateTeamGelAddData_withoutRenInve_daySta(Map<String, Object> para);

	void updateTeamGelAddData_com_withoutRenInve_daySta(Map<String, Object> para);

	void updateTeamDeptAddData_withoutRenInve_op_daySta(Map<String, Object> para);

	void updateTeamViceAddData_withoutRenInve_op_daySta(Map<String, Object> para);

	void updateTeamCenterAddData_withoutRenInve_op_daySta(Map<String, Object> para);

	void updateTeamGelAddData_withoutRenInve_op_daySta(Map<String, Object> para);

	void updateTeamGelAddData_com_withoutRenInve_op_daySta(Map<String, Object> para);

	void updateTeamClearAdd_TeamStuffData_daySta(Map<String, Object> para);

	void updateTeamChangeStateNum_daySta(Map<String, Object> para);

	List<Map<String, Object>> searchBaseAchievementPerList_currmonth(Map<String, Object> paramMap);

	int findPerCount_currmonth(Map<String, Object> paramMap);

	List<Map<String, Object>> searchBaseAchievementTeamList_currmonth(Map<String, Object> paramMap);

	int findteamCount_currmonth(Map<String, Object> paramMap);

	Map<String, String> getNewProListByRule(int rULEMODULE_ADD);

    void batchInsertShareHolderCommission(List<Map<String, Object>> list);

	void updateSpecialProPerSta_daySta(Map<String, Object> para);

	void updateSpecialProPerSta_daySta_com(Map<String, Object> para);

	void updatePersonnelReinveMon(Map<String, Object> para);

	void updateTeamReinveMon(Map<String, Object> para);

	void updatePersonnelReinveMon_com(Map<String, Object> para);

	void updateCenterReinveMon(Map<String, Object> para);

	void updateViceReinveMon(Map<String, Object> para);

    /**
     * @Title: getCurrMonthDetailInve3
     * @Description: 冯总报表附录3数据（本金续投金额）-时时数据
     * @param paramMap
     * @return 
     * @author: Guanxu
     * @time:2017年1月19日 下午2:50:26
     * history:
     * 1、2017年1月19日 Guanxu 创建方法
    */
    List<Map<String, Object>> getCurrMonthDetailInve3(Map<String, Object> paramMap);

    /**
     * @Title: getOtherMonthDetailInve3
     * @Description: 冯总报表附录3数据（本金续投金额）-时时数据
     * @param paramMap
     * @return 
     * @author: Guanxu
     * @time:2017年1月19日 下午2:50:31
     * history:
     * 1、2017年1月19日 Guanxu 创建方法
    */
    List<Map<String, Object>> getOtherMonthDetailInve3(Map<String, Object> paramMap);

	void insertTeamDeptStockData_noManagerId_daySta(Map<String, Object> para);

    /**
     * @Title: getHisPerStockList
     * @Description: 冯总报表获取历史个人存量信息
     * @param paramMap
     * @return 
     * @author: jinzhm
     * @time:2017年6月8日 上午10:13:53
     * history:
     * 1、2017年6月8日 jinzhm 创建方法
     */
    List<Map<String, Object>> getHisPerStockList(Map<String, Object> paramMap);

    /**
     * @Title: getDaystaPerStockList
     * @Description: 冯总报表获取实时历史个人存量信息
     * @param paramMap 
     * @return 
     * @author: jinzhm
     * @time:2017年6月8日 上午10:17:20
     * history:
     * 1、2017年6月8日 jinzhm 创建方法
     */
    List<Map<String, Object>> getDaystaPerStockList(Map<String, Object> paramMap);
}
