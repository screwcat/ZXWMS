package com.zx.emanage.inve.util.transa;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: SignApplicationFactory
 * 模块名称：
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年6月5日
 * @version V1.0
 * history:
 * 1、2017年6月5日 jinzhm 创建文件
 */
public class SignTransaFactory
{
    /**
     * @Title: getSignApplication
     * @Description: 根据签单单据类型获得具体实现类对象
     * @param transaType 签单类型
     * @return 签单实现对象
     * @author: jinzhm
     * @time:2017年6月5日 下午3:48:32
     * history:
     * 1、2017年6月5日 jinzhm 创建方法
     */
    public static SignTransa getSignApplication(String transaType)
    {
        if (SignHelper.NOMARL_TRANSA.equals(transaType))
        {
            // return new SignNormalTransa();
            return null;
        }
        // else if (SignHelper.SHARE_HOLDER_TRANSA.equals(transaType))
        // {
        // // return new SignShareHolderTransa();
        // return null;
        // }
        else if (SignHelper.PTP_TRANSA.equals(transaType))
        {
            return new SignPTPTransa();
        }
        else
        {
            throw new IllegalArgumentException("传入单据类型没有找到对应实现类！！！");
        }
    }
}
