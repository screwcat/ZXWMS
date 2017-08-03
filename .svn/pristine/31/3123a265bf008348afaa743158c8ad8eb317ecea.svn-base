package com.zx.emanage.inve.util.credit;

/**
 *                             _ooOoo_
 *                            o8888888o
 *                            88" . "88
 *                            (| -_- |)
 *                            O\  =  /O
 *                         ____/`---'\____
 *                       .'  \\|     |//  `.
 *                      /  \\|||  :  |||//  \
 *                     /  _||||| -:- |||||-  \
 *                     |   | \\\  -  /// |   |
 *                     | \_|  ''\---/''  |   |
 *                     \  .-\__  `-`  ___/-. /
 *                   ___`. .'  /--.--\  `. . __
 *                ."" '<  `.___\_<|>_/___.'  >'"".
 *               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *               \  \ `-.   \_ __\ /__ _/   .-` /  /
 *          ======`-.____`-.___\_____/___.-`____.-'======
 *                             `=---='
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *                          佛祖保佑        永无BUG
/
/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CreditMatch
 * 模块名称：债权匹配类（债权匹配功能方法暴露）
 * @Description: 内容摘要：债权匹配，释放，确认等方法
 * @author jinzhm
 * @date 2017年2月10日
 * @version V1.0
 * history:
 * 1、2017年2月10日 jinzhm 创建文件
 */
public class CreditMatch
{
    /**
     * 签单流程匹配债权
     */
    public static final String FLOW_TRANSA = "TRANSA";

    /**
     * 续期流程匹配债权
     */
    public static final String FLOW_EXTEND = "EXTEND";

    /**
     * 赎回流程匹配债权
     */
    public static final String FLOW_REDEEM = "REDEEM";

    /**
     * 更换债权流程
     */
    public static final String FLOW_CHANGE_CREDIT_PACKAGE = "CHANGE_CREDIT_PACKAGE";

    // 定义类本身对象
    private static final CreditMatch cm = new CreditMatch();

    /**
     * 私有化默认构造方法，保证单例
     */
    private CreditMatch()
    {

    }

    /**
     * @Title: getInstance
     * @Description: 获得类本身的对象
     * @return 
     * @author: jinzhm
     * @time:2017年2月10日 下午4:36:43
     * history:
     * 1、2017年2月10日 jinzhm 创建方法
     */
    public static CreditMatch getInstance()
    {
        return cm;
    }

    /**
     * @Title: matchHold
     * @Description: 匹配并占用债权
     * @param requestData 债权匹配请求对象
     * @return 是否匹配成功
     * @author: jinzhm
     * @time:2017年2月10日 下午4:37:33
     * history:
     * 1、2017年2月10日 jinzhm 创建方法
     */
    private boolean matchHold(CreditMatchRequestData requestData)
    {
        // 生成债权匹配数据对象
        CreditMatchData matchData = new CreditMatchData(requestData.getTransaId(), requestData.getProtocolId(),
                                                        requestData.getCategoryId(), requestData.getProductAccount(),
                                                        requestData.getActDateOfPayment(),
                                                        requestData.getProtocolStartDate(), requestData.getUser());
        // 设置流程名称
        matchData.setFlowName(requestData.getFlowName());
        // 获得债权匹配实现
        CreditMatchInterface creditMatch = CreditMatchFactory.getCreditMatch(requestData.getFlowName());
        // 匹配债权并占用
        return creditMatch.match(matchData);
    }

    /**
     * @Title: matchEffect
     * @Description: 匹配并生效债权
     * @param requestData 债权匹配请求对象
     * @return 是否匹配成功
     * @author: jinzhm
     * @time:2017年2月10日 下午4:37:43
     * history:
     * 1、2017年2月10日 jinzhm 创建方法
     */
    private boolean matchEffect(CreditMatchRequestData requestData)
    {
        // 生成债权匹配数据对象
        CreditMatchData matchData = new CreditMatchData(requestData.getTransaId(), requestData.getProtocolId(),
                                                        requestData.getCategoryId(), requestData.getProductAccount(),
                                                        requestData.getActDateOfPayment(),
                                                        requestData.getProtocolStartDate(), requestData.getUser());
        // 设置流程名称
        matchData.setFlowName(requestData.getFlowName());
        // 获得债权匹配实现
        CreditMatchInterface creditMatch = CreditMatchFactory.getCreditMatch(requestData.getFlowName());
        // 匹配债权并生效
        return creditMatch.match(matchData);
    }

    /**
     * @Title: matchChange
     * @Description: 匹配并更换债权
     * @param requestData 债权匹配请求数据对象
     * @return 是否匹配成功
     * @author: jinzhm
     * @time:2017年2月13日 下午6:45:34
     * history:
     * 1、2017年2月13日 jinzhm 创建方法
     */
    private boolean matchChange(CreditMatchRequestData requestData)
    {
        // 生成债权匹配数据对象
        CreditMatchData matchData = new CreditMatchData(requestData.getTransaId(), requestData.getProtocolId(),
                                                        requestData.getCategoryId(), requestData.getProductAccount(),
                                                        requestData.getActDateOfPayment(),
                                                        requestData.getProtocolStartDate(), requestData.getUser());
        // 设置流程名称
        matchData.setFlowName(requestData.getFlowName());
        // 获得债权匹配实现
        CreditMatchInterface creditMatch = CreditMatchFactory.getCreditMatch(requestData.getFlowName());
        // 匹配债权并生效
        return creditMatch.match(matchData);
    }

    /**
     * @Title: match
     * @Description: 债权匹配
     * @param requestData 债权匹配请求数据对象</br>
     *      <b>签单流程：匹配并占用债权时必须设置流程类型，上单主键，实际支付日期，要匹配债权的理财投资金额，登录用户信息</b></br>
     *      <b>赎回流程及续期流程：匹配并生效债权时必须设置流程类型，上单主键，合同主键，要匹配债权的理财投资金额，登录用户信息</b>
     * @return 是否匹配成功
     * @author: jinzhm
     * @time:2017年2月13日 下午6:44:58
     * history:
     * 1、2017年2月13日 jinzhm 创建方法
     */
    public synchronized boolean match(CreditMatchRequestData requestData)
    {
        // 判断流程，根据流程调用具体方法
        if (FLOW_TRANSA.equals(requestData.getFlowName()))
        {
            return matchHold(requestData);
        }
        else if (FLOW_REDEEM.equals(requestData.getFlowName()))
        {
            return matchEffect(requestData);
        }
        else if (FLOW_EXTEND.equals(requestData.getFlowName()))
        {
            return matchEffect(requestData);
        }
        else if (FLOW_CHANGE_CREDIT_PACKAGE.equals(requestData.getFlowName()))
        {
            return matchChange(requestData);
        }
        else
        {
            throw new IllegalArgumentException("流程参数不正确！");
        }
    }

    /**
     * @Title: release
     * @Description: 释放债权
     * @param requestData 债权匹配请求对象</br>
     *      <b>必须设置流程类型，上单主键，登录用户信息</b></br>
     *      <b>在签单流程因为没有合同主键，可以不用设置；赎回和续期流程释放债权需要设置合同主键</b>
     * @author: jinzhm
     * @time:2017年2月11日 下午12:54:37
     * history:
     * 1、2017年2月11日 jinzhm 创建方法
     */
    public void release(CreditMatchRequestData requestData)
    {
        // 生成债权匹配数据对象
        CreditMatchData matchData = new CreditMatchData(requestData.getTransaId(), requestData.getProtocolId(), null,
                                                        null, null, null, requestData.getUser());

        // 获得债权匹配实现
        CreditMatchInterface creditMatch = CreditMatchFactory.getCreditMatch(requestData.getFlowName());

        // 释放债权
        creditMatch.release(matchData);
    }

    /**
     * @Title: disableMatchedCredit
     * @Description: 失效债权
     * @param requestData 债权匹配请求对象
     * @author: jinzhm
     * @time:2017年2月21日 下午3:03:56
     * history:
     * 1、2017年2月21日 jinzhm 创建方法
     */
    public void disableMatchedCredit(CreditMatchRequestData requestData)
    {
        // 生成债权匹配数据对象
        CreditMatchData matchData = new CreditMatchData(requestData.getTransaId(), requestData.getProtocolId(), null,
                                                        null, null, null, requestData.getUser());

        // 获得债权匹配实现
        CreditMatchInterface creditMatch = CreditMatchFactory.getCreditMatch(requestData.getFlowName());

        // 释放债权
        creditMatch.disableMatchedCredit(matchData);
    }

}
