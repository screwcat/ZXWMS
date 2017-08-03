package com.zx.emanage.reportmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsProviderForAppDao {

	List<Map<String, Object>> getPersonnelCommDeptStaOtherMonth(
			Map<String, Object> para);

	List<Map<String, Object>> getPersonnelcommCommItemSta(
			Map<String, Object> para);

	List<Map<String, Object>> getPersonnelCommInveSta_old(
			Map<String, Object> para);

	List<Map<String, Object>> getPersonnelCommInveSta_new_add(
			Map<String, Object> para);

	List<Map<String, Object>> getPersonnelCommInveSta_new_stock(
			Map<String, Object> para);

	List<Map<String, Object>> getAchCenterSta(Map<String, Object> para);

	List<Map<String, Object>> getAchDeptSta(Map<String, Object> para);

	List<Map<String, Object>> getAchTeamSta(Map<String, Object> para);

	List<Map<String, Object>> getAchPersonnelSta(Map<String, Object> para);

	List<Map<String, Object>> getAchInveSta(Map<String, Object> para);

	List<Map<String, Object>> getAchViceSta(Map<String, Object> para);

	List<Map<String, Object>> getAchInveSta_curr(Map<String, Object> para);

	List<Map<String, Object>> getAchPersonnelSta_curr(Map<String, Object> para);

	void deleteAchPersonnelStaCurr(Map<String, Object> para);

	void insertAchPersonnelStaStockCurr(Map<String, Object> para);

	void updateAchPersonnelStaAddCurr(Map<String, Object> para);

	List<Map<String, Object>> getAchTeamStaCurr(Map<String, Object> para);

	void updateTeamDeptAddDataCurr(Map<String, Object> para);

	void insertTeamDeptStockDataCurr(Map<String, Object> para);

	List<Map<String, Object>> getAchDeptStaCurr(Map<String, Object> para);

	void insertAchCerterManStockCurr(Map<String, Object> para);

	void insertAchViceManStockCurr(Map<String, Object> para);

	void insertAchGelManStockCurr(Map<String, Object> para);

	void insertAchGelChaManStockCurr(Map<String, Object> para);

	void updateAchCerterManAddCurr(Map<String, Object> para);

	void updateAchViceManAddCurr(Map<String, Object> para);

	void updateAchGelManAddCurr(Map<String, Object> para);

	void updateAchGelChaManAddCurr(Map<String, Object> para);

	List<Map<String, Object>> getAchViceStaCurr(Map<String, Object> para);

	List<Map<String, Object>> getAchCenterStaCurr(Map<String, Object> para);

	void insertAchAllPerStaCurr(Map<String, Object> para);

	void updateAchAllPerAddCurr(Map<String, Object> para);

	void updateAchPersonnelReinveCurr(Map<String, Object> para);

	void updateTeamReinveMonCurr(Map<String, Object> para);

	void updateAchCenterTeamReinveMon(Map<String, Object> para);

	void updateAchViceTeamReinveMon(Map<String, Object> para);


}
