package com.zx.emanage.loanpost.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.loanpost.vo.WmsPostLoanMigrationSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsPostLoanMigration;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsPostLoanMigrationService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsPostLoanMigrationSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsPostLoanMigrationSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsPostLoanMigrationVO
     * @author auto_generator
     */
    public WmsPostLoanMigration getInfoByPK(Integer wms_post_loan_migration_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsPostLoanMigration bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsPostLoanMigration bean, UserBean user);

    /**
     * Description :获得转件管理的转件申请的分页数据
     * 
     * @param 合同编号、客户姓名、联系电话、客户专员、贷款产品、转件状态
     * @return 分页数据
     * @author ry
     */
    public Map<String, Object> getZJSQListWithPagingByQueryInfo(WmsPostLoanMigrationSearchBeanVO queryInfo,
                                                                UserBean user);

    /**
     * Description :获得转件管理的转件审核的分页数据
     * 
     * @param 合同编号、客户姓名、联系电话、客户专员、贷款产品、转件状态
     * @return 分页数据
     * @author ry
     */
    public Map<String, Object> getZJSHListWithPagingByQueryInfo(WmsPostLoanMigrationSearchBeanVO queryInfo,
                                                                UserBean user);

    /**
     * Description :获得转件管理的转件确认的分页数据
     * 
     * @param 合同编号、客户姓名、联系电话、客户专员、贷款产品、转件状态
     * @return 分页数据
     * @author ry
     */
    public Map<String, Object> getZJQRListWithPagingByQueryInfo(WmsPostLoanMigrationSearchBeanVO queryInfo,
                                                                UserBean user);

    /**
     * Description :获得转件管理的转件申请的无分页数据
     * 
     * @param 合同编号、客户姓名、联系电话、客户专员、贷款产品、转件状态
     * @return 无分页数据
     * @author ry
     */
    public Map<String, Object> getZJSQListWithOutPagingByQueryInfo(WmsPostLoanMigrationSearchBeanVO queryInfo,
                                                                   UserBean user);

    /**
     * Description :获得转件管理的转件审核的无分页数据
     * 
     * @param 合同编号、客户姓名、联系电话、客户专员、贷款产品、转件状态
     * @return 无分页数据
     * @author ry
     */
    public Map<String, Object> getZJSHListWithOutPagingByQueryInfo(WmsPostLoanMigrationSearchBeanVO queryInfo,
                                                                   UserBean user);

    /**
     * Description :获得转件管理的转件确认的无分页数据
     * 
     * @param 合同编号、客户姓名、联系电话、客户专员、贷款产品、转件状态
     * @return 分页数据
     * @author ry
     */
    public Map<String, Object> getZJQRListWithOutPagingByQueryInfo(WmsPostLoanMigrationSearchBeanVO queryInfo,
                                                                   UserBean user);

    /**
     * Description :通过贷款表ID获得还款、合同信息
     * 
     * @param wms_cre_credit_head_id
     * @return 单条数据
     * @author ry
     */
    public Map<String, Object> getMigrationInfoByMCCHID(String wms_cre_credit_head_id);

    /**
     * Description :转件申请提交
     * 
     * @param 上传文件、贷款ID
     * @return "success" or "error" or user defined
     * @author ry
     */
    public String doSQSave(String wms_cre_credit_head_id, String fileArr, UserBean user, String taskId);

    /**
     * Description :转件确认提交
     * 
     * @param 贷款ID
     * @return "success" or "error" or user defined
     * @author ry
     */
    public String doQRSave(String wms_cre_credit_head_id, UserBean user);
}