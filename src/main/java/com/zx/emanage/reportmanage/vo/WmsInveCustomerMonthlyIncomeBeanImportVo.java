package com.zx.emanage.reportmanage.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/**
 * 版权所有：版权所有(C) 2015，卓信金控 文件名称: WmsInveCustomerMonthlyIncomeBeanVo.java 系统名称：WMS
 * 模块名称：理财客户每月收益报表 完成日期： 作 者： 内容摘要：
 * 
 * 文件调用： 修改记录： 修改时间： 修 改 人: 修改内容： 关联BUG： 修改方法：
 */
public class WmsInveCustomerMonthlyIncomeBeanImportVo extends GridParamBean {
	private static final long serialVersionUID = 1L;

	// 协议编号
	private String prot_code;
	// 产品名称
	private String category_name;
	// 客户名称
	private String cus_name;
	// 签单日期 开始
	private String signing_date_begin;
	// 签单日期 结束
	private String signing_date_end;
	// 应支付日期 开始
	private String return_date_begin;
	// 应支付日期 结束
	private String return_date_end;

	private String wms_inve_transa_prod_id;

	// 应支付日期--如 2015-10
	private String return_date;
	// 是否有奖励利率 1代表有奖励利率 2代表没有奖励利率
	private Integer is_bonus_rate;
	// 单据编号
	private String bill_code;
	
	private Integer is_extent_rate;

	private Integer is_query_data_type;// 查询数据的范围 1为全部数据,2为打款数据

    // 导入数据结果
    private String result;

    // 格式错误行数
    private int err_num;

    // 返回标志 成功：true 失败：false
    private String flag;

	public String getProt_code() {
		return prot_code;
	}

	public void setProt_code(String prot_code) {
		this.prot_code = prot_code;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCus_name() {
		return cus_name;
	}

	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}

	public String getSigning_date_begin() {
		return signing_date_begin;
	}

	public void setSigning_date_begin(String signing_date_begin) {
		this.signing_date_begin = signing_date_begin;
	}

	public String getSigning_date_end() {
		return signing_date_end;
	}

	public void setSigning_date_end(String signing_date_end) {
		this.signing_date_end = signing_date_end;
	}

	public String getReturn_date_begin() {
		return return_date_begin;
	}

	public void setReturn_date_begin(String return_date_begin) {
		this.return_date_begin = return_date_begin;
	}

	public String getReturn_date_end() {
		return return_date_end;
	}

	public void setReturn_date_end(String return_date_end) {
		this.return_date_end = return_date_end;
	}

	public String getWms_inve_transa_prod_id() {
		return wms_inve_transa_prod_id;
	}

	public void setWms_inve_transa_prod_id(String wms_inve_transa_prod_id) {
		this.wms_inve_transa_prod_id = wms_inve_transa_prod_id;
	}

	public String getReturn_date() {
		return return_date;
	}

	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}

	public Integer getIs_bonus_rate() {
		return is_bonus_rate;
	}

	public void setIs_bonus_rate(Integer is_bonus_rate) {
		this.is_bonus_rate = is_bonus_rate;
	}

	public String getBill_code() {
		return bill_code;
	}

	public void setBill_code(String bill_code) {
		this.bill_code = bill_code;
	}

	public Integer getIs_extent_rate() {
		return is_extent_rate;
	}

	public void setIs_extent_rate(Integer is_extent_rate) {
		this.is_extent_rate = is_extent_rate;
	}

	public Integer getIs_query_data_type() {
		return is_query_data_type;
	}

	public void setIs_query_data_type(Integer is_query_data_type) {
		this.is_query_data_type = is_query_data_type;
	}

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public int getErr_num()
    {
        return err_num;
    }

    public void setErr_num(int err_num)
    {
        this.err_num = err_num;
    }

    public String getFlag()
    {
        return flag;
    }

    public void setFlag(String flag)
    {
        this.flag = flag;
    }
}
