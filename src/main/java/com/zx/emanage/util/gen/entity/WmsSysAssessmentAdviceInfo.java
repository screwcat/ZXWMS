package com.zx.emanage.util.gen.entity;

import java.sql.Timestamp;

import com.zx.sframe.util.mybatis.BaseEntity;

/**
 * 版权所有：版权所有(C) 2017，卓信金控 系统名称：财富管理平台
 * 
 * @ClassName: Wms_sys_assessment_advice_info 模块名称：
 * @Description: 内容摘要：
 * @author ZhangWei
 * @date 2017年5月18日
 * @version V1.0 history: 1、2017年5月18日 ZhangWei 创建文件
 */
public class WmsSysAssessmentAdviceInfo implements BaseEntity {
	
	private Integer wms_sys_assessment_advice_info_id;// 主键

	private String assessment_advice_titile;// 意见描述

	private String assessment_advice_info;// 意见内容

	private Integer create_user_id;// 创建人id

	private Timestamp create_timestamp;// 创建时间

	private String create_user_name;// 创建人姓名

	private Integer last_update_user_id;// 上次修改人

	private Timestamp last_update_timestamp;// 上次修改时间

	private String enable_flag;//

	/**
	 * @return the wms_sys_assessment_advice_info_id
	 */
	public Integer getWms_sys_assessment_advice_info_id() {
		return wms_sys_assessment_advice_info_id;
	}

	/**
	 * @param wms_sys_assessment_advice_info_id
	 *            the wms_sys_assessment_advice_info_id to set
	 */
	public void setWms_sys_assessment_advice_info_id(Integer wms_sys_assessment_advice_info_id) {
		this.wms_sys_assessment_advice_info_id = wms_sys_assessment_advice_info_id;
	}

	/**
	 * @return the assessment_advice_titile
	 */
	public String getAssessment_advice_titile() {
		return assessment_advice_titile;
	}

	/**
	 * @param assessment_advice_titile
	 *            the assessment_advice_titile to set
	 */
	public void setAssessment_advice_titile(String assessment_advice_titile) {
		this.assessment_advice_titile = assessment_advice_titile;
	}

	/**
	 * @return the assessment_advice_info
	 */
	public String getAssessment_advice_info() {
		return assessment_advice_info;
	}

	/**
	 * @param assessment_advice_info
	 *            the assessment_advice_info to set
	 */
	public void setAssessment_advice_info(String assessment_advice_info) {
		this.assessment_advice_info = assessment_advice_info;
	}

	/**
	 * @return the create_user_id
	 */
	public Integer getCreate_user_id() {
		return create_user_id;
	}

	/**
	 * @param create_user_id
	 *            the create_user_id to set
	 */
	public void setCreate_user_id(Integer create_user_id) {
		this.create_user_id = create_user_id;
	}

	/**
	 * @return the create_timestamp
	 */
	public Timestamp getCreate_timestamp() {
		return create_timestamp;
	}

	/**
	 * @param create_timestamp
	 *            the create_timestamp to set
	 */
	public void setCreate_timestamp(Timestamp create_timestamp) {
		this.create_timestamp = create_timestamp;
	}

	/**
	 * @return the create_user_name
	 */
	public String getCreate_user_name() {
		return create_user_name;
	}

	/**
	 * @param create_user_name
	 *            the create_user_name to set
	 */
	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}

	/**
	 * @return the last_update_user_id
	 */
	public Integer getLast_update_user_id() {
		return last_update_user_id;
	}

	/**
	 * @param last_update_user_id
	 *            the last_update_user_id to set
	 */
	public void setLast_update_user_id(Integer last_update_user_id) {
		this.last_update_user_id = last_update_user_id;
	}

	/**
	 * @return the last_update_timestamp
	 */
	public Timestamp getLast_update_timestamp() {
		return last_update_timestamp;
	}

	/**
	 * @param last_update_timestamp
	 *            the last_update_timestamp to set
	 */
	public void setLast_update_timestamp(Timestamp last_update_timestamp) {
		this.last_update_timestamp = last_update_timestamp;
	}

	/**
	 * @return the enable_flag
	 */
	public String getEnable_flag() {
		return enable_flag;
	}

	/**
	 * @param enable_flag
	 *            the enable_flag to set
	 */
	public void setEnable_flag(String enable_flag) {
		this.enable_flag = enable_flag;
	}

}
