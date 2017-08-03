package com.zx.emanage.cremanage.vo;

import com.zx.platform.syscontext.vo.GridParamBean;
/*
 * @version 2.0
 */

public class WmsCreCreditHeadHouseSearchBeanVO extends GridParamBean
{

    private static final long serialVersionUID = 1L;
    
    private String deletefileArrId;
    private String deletefileArrkhId;
    private  String linkmaninfo;// 
    private  String personArr;
    private  String modifyJsonCus;
    private  String house_use;
    private  String zdrID;
    private  String mcclhid;
    private  String fileArr;
    private  String fileArrkh;
    private  String isComOrZC;
    private  String edition_num;//房贷流程版本号1为旧2为新
    private  String taskId;
    private  String wms_cre_credit_head_id;
    private String version_number;//数据来源1为pc 2为移动端
    
    private Integer[] deleteAttIds;
	
	public String getFileArrkh() {
		return fileArrkh;
	}
	public void setFileArrkh(String fileArrkh) {
		this.fileArrkh = fileArrkh;
	}
	public String getLinkmaninfo() {
		return linkmaninfo;
	}
	public void setLinkmaninfo(String linkmaninfo) {
		this.linkmaninfo = linkmaninfo;
	}
	public String getPersonArr() {
		return personArr;
	}
	public void setPersonArr(String personArr) {
		this.personArr = personArr;
	}
	public String getModifyJsonCus() {
		return modifyJsonCus;
	}
	public void setModifyJsonCus(String modifyJsonCus) {
		this.modifyJsonCus = modifyJsonCus;
	}
	public String getHouse_use() {
		return house_use;
	}
	public void setHouse_use(String house_use) {
		this.house_use = house_use;
	}
	public String getZdrID() {
		return zdrID;
	}
	public void setZdrID(String zdrID) {
		this.zdrID = zdrID;
	}
	public String getMcclhid() {
		return mcclhid;
	}
	public void setMcclhid(String mcclhid) {
		this.mcclhid = mcclhid;
	}
	public String getFileArr() {
		return fileArr;
	}
	public void setFileArr(String fileArr) {
		this.fileArr = fileArr;
	}
	public String getIsComOrZC() {
		return isComOrZC;
	}
	public void setIsComOrZC(String isComOrZC) {
		this.isComOrZC = isComOrZC;
	}
	public String getEdition_num() {
		return edition_num;
	}
	public void setEdition_num(String edition_num) {
		this.edition_num = edition_num;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getWms_cre_credit_head_id() {
		return wms_cre_credit_head_id;
	}
	public void setWms_cre_credit_head_id(String wms_cre_credit_head_id) {
		this.wms_cre_credit_head_id = wms_cre_credit_head_id;
	}
	public String getDeletefileArrId() {
		return deletefileArrId;
	}
	public void setDeletefileArrId(String deletefileArrId) {
		this.deletefileArrId = deletefileArrId;
	}
	public String getDeletefileArrkhId() {
		return deletefileArrkhId;
	}
	public void setDeletefileArrkhId(String deletefileArrkhId) {
		this.deletefileArrkhId = deletefileArrkhId;
	}
	public String getVersion_number() {
		return version_number;
	}
	public void setVersion_number(String version_number) {
		this.version_number = version_number;
	}
    public Integer[] getDeleteAttIds() {
        return deleteAttIds;
    }
    public void setDeleteAttIds(Integer[] deleteAttIds) {
        this.deleteAttIds = deleteAttIds;
    }
	
	
	
}