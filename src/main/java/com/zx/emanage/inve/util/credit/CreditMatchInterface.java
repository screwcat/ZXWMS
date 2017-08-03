package com.zx.emanage.inve.util.credit;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CreditMatchInterface
 * 模块名称：匹配债权接口
 * @Description: 内容摘要：匹配债权，确认债权，释放债权等方法定义
 * @author jinzhm
 * @date 2017年2月10日
 * @version V1.0
 * history:
 * 1、2017年2月10日 jinzhm 创建文件
 */
public interface CreditMatchInterface
{

    /**
     * 单据债权关联信息匹配状态：生效中
     */
    public static final String ACL_STATE_ENABLE = "1";

    /**
     * 单据债权关联信息匹配状态：已到期
     */
    public static final String ACL_STATE_END = "2";

    /**
     * 单据债权关联信息匹配状态：已赎回
     */
    public static final String ACL_STATE_REDEEM = "3";

    /**
     * 单据债权关联信息匹配状态：占用中
     */
    public static final String ACL_STATE_HOLD = "4";

    /**
     * 在已匹配中的债权中继续匹配状态
     * 原匹配1万，因其他债权更换导致在该债权中多匹配1万
     * 将原匹配的数据信息状态设置成5后新增一条新的2万的数据信息
     */
    public static final String ACL_STATE_CHANGE_IN_MATCHED = "5";

    /**
     * @Title: match
     * @Description: 债权匹配
     * @param creditMatchData 
     * @return 是否匹配成功
     * @author: jinzhm
     * @time:2017年2月13日 下午2:18:56
     * history:
     * 1、2017年2月13日 jinzhm 创建方法
     */
    public boolean match(CreditMatchData creditMatchData);


    /**
     * @Title: release
     * @Description: 释放债权匹配，生效或占用都释放
     * @param creditMatchData 债权匹配数据对象
     * @author: jinzhm
     * @time:2017年2月10日 下午1:24:03
     * history:
     * 1、2017年2月10日 jinzhm 创建方法
     */
    public void release(CreditMatchData creditMatchData);

    /**
     * @Title: disableMatchedCredit
     * @Description: 失效债权
     * @param creditMatchData 债权匹配数据对象
     * @author: jinzhm
     * @time:2017年2月21日 下午2:59:42
     * history:
     * 1、2017年2月21日 jinzhm 创建方法
     */
    public void disableMatchedCredit(CreditMatchData creditMatchData);
}
