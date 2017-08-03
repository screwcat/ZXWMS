package com.zx.emanage.creditRightManager.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveMulticreInfoDao {

	List<Map<String, Object>> creditCollectionlistall(Map<String, Object> para);

	int creditCollectionlistall_findcount(Map<String, Object> para);

	Map<String, Object> getWmsCredRightColData(Map<String, Object> para);

	int insertWmsInveMulticreInfo(Map<String, Object> para);

	void insertWmsInveCreditPackage(Map<String, Object> pakpara);

	void updateCurrMultiInfo(Map<String, Object> pakpara);

	Map<String, Object> getWmsInveMulticreInfoDetail(Map<String, Object> para);

	List<Map<String, Object>> getWmsInveCreditPackageListByMK(
			Map<String, Object> para);

	List<Map<String, Object>> getLocalListForChange();

	Map<String, Object> getWmsInveMulticreInfo(Map<String, Object> para);

	void deleteWmsInveMulticreById(Map<String, Object> tpMulMap);

	void deleteWmsInvePkgById(Map<String, Object> tpMulMap);

	List<Map<String, Object>> checkProtocolNumIsExist(Map<String, Object> tpm);

}
