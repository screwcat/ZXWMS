package com.zx.emanage.inve.util.transa;

import java.util.List;
import java.util.Map;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: SignApplication
 * 模块名称：
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年6月5日
 * @version V1.0
 * history:
 * 1、2017年6月5日 jinzhm 创建文件
 */
public interface SignTransa
{
    
    /**
     * @Title: transa
     * @Description: 签单
     * @param transaData
     * @return 
     * @author: jinzhm
     * @time:2017年6月5日 下午3:22:49
     * history:
     * 1、2017年6月5日 jinzhm 创建方法
     */
    public Map<String, Object> transa(SignTransaData transaData);
    
    /**
     * @Title: transa
     * @Description: 批量签单
     * @param transaData
     * @return 
     * @author: jinzhm
     * @time:2017年6月20日 上午10:41:41
     * history:
     * 1、2017年6月20日 jinzhm 创建方法
     */
    public List<Map<String, Object>> transa(List<SignTransaData> transaData);
}
