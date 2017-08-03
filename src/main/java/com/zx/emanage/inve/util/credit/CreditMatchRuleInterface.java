package com.zx.emanage.inve.util.credit;


/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CreditMatchRuleInterface
 * 模块名称：
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年2月11日
 * @version V1.0
 * history:
 * 1、2017年2月11日 jinzhm 创建文件
 */
public interface CreditMatchRuleInterface
{
    /**
     * @Title: match
     * @Description: 匹配债权
     * @param matchData 债权匹配对象
     * @return 是否匹配成功
     * @author: jinzhm
     * @time:2017年2月11日 下午3:24:23
     * history:
     * 1、2017年2月11日 jinzhm 创建方法
     */
    public boolean match(CreditMatchData matchData);
}
