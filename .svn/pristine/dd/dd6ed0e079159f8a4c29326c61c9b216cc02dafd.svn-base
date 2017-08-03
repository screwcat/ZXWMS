package com.zx.emanage.loanfina.persist;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsFinaCrePeriodRepay;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsFinaCrePeriodRepayDao extends BaseDao<WmsFinaCrePeriodRepay>
{
    public WmsFinaCrePeriodRepay getinfobyid(Integer wms_cre_credit_head_id);

    public WmsFinaCrePeriodRepay getbyobject(WmsFinaCrePeriodRepay bean);// 根据
                                                                         // wms_cre_credit_head_id获取期还款台账表

    public List<WmsFinaCrePeriodRepay> getbyfk(Integer wms_cre_credit_head_id);// 根据条件查询出多条台账数据（逾期还款确认查询）

    public WmsFinaCrePeriodRepay getbyfkfornor(Integer wms_cre_credit_head_id);// 根据条件查询出一条台账数据（正常还款确认查询）

    public List<WmsFinaCrePeriodRepay> getbyno(Integer wms_cre_credit_head_id);// 根据贷款主表id
                                                                               // 获取到申请提前还款的所有期的数据
    
	public void updateForYQ(WmsFinaCrePeriodRepay wmsFinaCrePeriodRepay);//根据主贷款ID以及已还期数 重新修订之前更新的数据

	public Map<String,Object> totalDay(Map<String, Object> param);//获取总逾期天数

	public Map<String,Object> totalPeriod(Map<String, Object> param);//获取总逾期期数

	public WmsFinaCrePeriodRepay getCurrentRepayDay(Map<String, Object> param);//获取相应的实际还款时间

	/**
	 * 
	 * @Title: updatePhoneRemind 
	 * @Description: (更新台帐表电话提醒记录) 
	 * @param param
	 * @return    
	 * @return int    返回类型
	 * @throws
	 * @author lvtu
	 */
	public int updatePhoneRemind(Map<String, Object> param);
	
	/**
	 * 
	 * @Title: updatePhoneRemind 
	 * @Description: (更新台帐表电话提醒记录延期) 
	 * @param param
	 * @return    
	 * @return int    返回类型
	 * @throws
	 * @author lvtu
	 */
	public int updatePhoneRemindno3(Map<String, Object> param);
	/**
	 * 
	 * @Title: getThreeReminderTime 
	 * @Description: (获取前三天和前一天应提醒时间 ) 
	 * @param param
	 * @return    
	 * @return Date    返回类型
	 * @throws
	 * @author lvtu
	 */
	public Map<String, Date> getReminderTime(Map<String, Object> param);
	

	public void updateForjme(WmsFinaCrePeriodRepay wmsFinaCrePeriodRepay);//计算减免额
	
	/**
	 * 	<!-- 电话催缴还款情况中每期信息-->
	 * @param wms_cre_credit_head_id
	 * @return
	 * baisong
	 */
	public List<Map<String, Object>> getinfoforphone(Integer wms_cre_credit_head_id);
	
	
	/**
	 * @Title: updateforv 
	 * @Description: 根据id更新期还款台账
	 * @param param
	 * @return int    返回类型
	 * @author baisong
	 */
	public int updateforv(WmsFinaCrePeriodRepay wmsFinaCrePeriodRepay);
    /**
     * @Title: getListByYiPeriod 
	 * @Description: 根据先关台账信息获取,当前应该还的期信息 (更正逾期数据使用)
	 * @param param
	 * @return  list<T> 
	 * @author hancd 
     */
	public List<WmsFinaCrePeriodRepay> getListByYiPeriod(
			WmsFinaCrePeriodRepay wCrePeriodRepay);
	/**
	 * 实现更新台账信息表数据 (更正逾期数据使用)
	 * @param paramMap
	 * @author hancd
	 */
	public void updateforperiodrepay(Map<String, Object> paramMap);

	public List<WmsFinaCrePeriodRepay> searchfordata(
			Map<String, Object> paramMap);
	
      
}