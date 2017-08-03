package com.zx.emanage.inve.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.sframe.util.vo.UserBean;

/**
 * 上单，赎回，自动续期时计算收益信息，生成交易日志接口
 * 
 * @author 金志明
 * @date 2016年9月29日 下午4:57:27
 */
public interface CountIncome extends IncomeGlobal
{

    /**
     * @Title: computeIncome
     * @Description: 计算收益并返回
     * @param categoryId 产品id
     * @param productAccount 投资金额
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 收益情况
     * @author: jinzhm
     * @time:2017年2月21日 下午1:01:09
     * history:
     * 1、2017年2月21日 jinzhm 创建方法
     */
    public List<Map<String, Object>> computeIncome(int categoryId, BigDecimal productAccount, Date startDate,
                                                   Date endDate);

    /**
     * 理财上单及数据处理功能生成收益日志及交易日志调用方法。</br>
     * 收集数据，计算收益及生成交易日志，持久化到数据库。
     * 
     * @param protocol 理财协议对象
     * @param user 登录用户对象
     *
     * @author 金志明
     * @date 2016年9月29日 下午4:59:36
     */
    public void getIncomeAndLog(WmsInveTransaProtocol protocol, UserBean user);

    /**
     * @Title: reGenerateIncomeAndLog
     * @Description: 重新生成收益信息（当前只有数据处理时使用此方法）
     *      生成收益规则：
     *          根据修改上单时间配置规则进行处理
     *          例如配置如下：
     *              2016-12 2017-01-04 (说明2016-12-01到2017-01-04上单单据可以修改支付时间到12月份)
     *          比如1月2日上单单据修改支付时间为12月28日时新生成的12月份收益应该是未支付状态。
     *          <b>调用此方法已经对该规则进行完验证，直接生成都是未支付状态收益</b>
     * @param protocol 协议信息
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年1月5日 下午4:34:43
     * history:
     * 1、2017年1月5日 jinzhm 创建方法
     */
    public void reGenerateIncomeAndLog(WmsInveTransaProtocol protocol, UserBean user);

    /**
     * 理财赎回生成收益日志及交易日志方法。</br>
     * 收集数据，重新计算理财后的收益及生成理财交易日志，持久化到数据库。
     * 
     * @param protocol 理财协议对象
     * @param user 登录用户对象
     *
     * @author 金志明
     * @date 2016年9月29日 下午4:59:51
     */
    public void getIncomeAndLogForRedeem(WmsInveTransaProtocol protocol, UserBean user);

    /**
     * 自动续期生成收益日志及交易日志调用方法。</br>
     * 收集数据，计算收益及交易日志，持久化到数据库。
     * 
     * @param protocol 新单据协议对象
     * @param oldProtocol 老单据协议对象
     * @param extendDate 续期时间
     * @param user 登录用户对象
     *
     * @author 金志明
     * @date 2016年9月29日 下午5:00:05
     */
    public void getIncomeAndLogForAutoExtend(WmsInveTransaProtocol protocol, WmsInveTransaProtocol oldProtocol,
                                             Date extendDate, UserBean user);

    /**
     * @Title: generateIncomeAndLogForNewExtend
     * @Description: 为新产品续期生成收益且处理老单据收益信息
     * @param protocol 新单据协议信息
     * @param oldProtocol 老单据协议信息
     * @param extendDate 续期时间
     * @param user 登录用户
     * @author: jinzhm
     * @time:2016年12月30日 上午8:32:01
     * history:
     * 1、2016年12月30日 jinzhm 创建方法
     */
    public void generateIncomeAndLogForNewExtend(WmsInveTransaProtocol protocol, WmsInveTransaProtocol oldProtocol,
                                                 Date extendDate, UserBean user);

    /**
     * @Title: handleTransaIncomeForNewExtend
     * @Description: 新单据预约续期时收益处理方法
     *      新单据预约续期时，需要对当月未支付公益收益做处理（支付状态设置成已终止）
     * @param transaId 上单单据id
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年1月6日 下午3:08:18
     * history:
     * 1、2017年1月6日 jinzhm 创建方法
     */
    public void handleTransaIncomeForNewExtend(int transaId, UserBean user);

    /**
     * @Title: handleTransaIncomeForCancelNewExtend
     * @Description: 新单据预约续期取消预约时对收益处理方法
     *      在新单据预约时将当月未支付状态客户收益设置成了已终止，在取消预约时需要恢复（设置成未支付）
     * @param transaId 上单单据id
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年1月6日 下午3:09:37
     * history:
     * 1、2017年1月6日 jinzhm 创建方法
     */
    public void handleTransaIncomeForCancelNewExtend(int transaId, UserBean user);

}
