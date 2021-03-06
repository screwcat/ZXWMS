package com.zx.emanage.inve.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zx.emanage.inve.persist.WmsInveRedeemDao;
import com.zx.emanage.inve.persist.WmsInveRedeemDetailDao;
import com.zx.emanage.inve.persist.WmsInveTransaIncomeDao;
import com.zx.emanage.inve.persist.WmsInveTransaLogDao;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInvePruductRebateWay;
import com.zx.emanage.util.gen.entity.WmsInveRedeem;
import com.zx.emanage.util.gen.entity.WmsInveRedeemDetail;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaIncome;
import com.zx.emanage.util.gen.entity.WmsInveTransaLog;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * 具体计算收益及生成交易日志实现类的抽象父类。
 * 
 * @author 金志明
 * @date 2016年9月29日 下午5:13:38
 */
public abstract class CountIncomeAbstract implements CountIncome, CountIncomeGetBonusReturnRateInterface
{

	protected static Logger log = LoggerFactory.getLogger(CountIncomeAbstract.class);

    /**
     * 协议信息
     */
	protected WmsInveTransaProtocol protocol;

    /**
     * 老单据协议信息
     */
    protected WmsInveTransaProtocol oldProtocol;

    /**
     * 产品信息
     */
	protected WmsInvePruductCategory category;

    /**
     * 登录用户信息
     */
	protected UserBean user;

    /**
     * 上单信息
     */
    protected WmsInveTransa transa;

    /**
     * 上单产品信息
     */
	protected WmsInveTransaProd prod;

    /**
     * 产品利率
     */
    protected BigDecimal returnRate;

    /**
     * 公益收益利率
     */
    protected BigDecimal publicReturnRate;

    /**
     * 原始上单时间
     */
    protected Date oldDateOfPayment;

    /**
     * 续期时间
     */
    protected Date extendDate;

    /**
     * 老单据收益信息
     */
    protected List<WmsInveTransaIncome> oldTransaIncomeList = new ArrayList<WmsInveTransaIncome>();

	protected Map<String, Object> dataMap = new HashMap<String, Object>();

	@Override
	public void getIncomeAndLog(WmsInveTransaProtocol protocol, UserBean user)
	{
		packageIncomeAndLogData(protocol, user);// 收集计算收益和生成交易日志需要的数据
		Map<String, Object> result = handleIncomeAndLog();// 获得收益信息和交易日志结果
		persistIncomeAndLog(result);// 保存到数据库
	}

	@Override
	public void getIncomeAndLogForRedeem(WmsInveTransaProtocol protocol, UserBean user)
	{
		packageIncomeAndLogForRedeemData(protocol, user);
		Map<String, Object> result = handleIncomeAndLogForRedeem();
		persistIncomeAndLogForRedeem(result);
	}

	@Override
	public void getIncomeAndLogForAutoExtend(WmsInveTransaProtocol protocol, WmsInveTransaProtocol oldProtocol, Date extendDate,
			UserBean user)
	{
		packageIncomeAndLogForAutoExtend(protocol, oldProtocol, extendDate, user);
		Map<String, Object> result = handleIncomeAndLogForAutoExtend();
		persistIncomeAndLogForAutoExtend(result);
	}
	
    /**
     * @Title: reGenerateIncomeAndLog
     * @Description: 生成所有未支付收益（当前只有数据处理时使用此方法）
     * @param protocol 协议信息
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年1月6日 上午11:46:50
     * @see com.zx.emanage.inve.util.CountIncome#reGenerateIncomeAndLog(com.zx.emanage.util.gen.entity.WmsInveTransaProtocol, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年1月6日 jinzhm 创建方法
    */
    @Override
    public void reGenerateIncomeAndLog(WmsInveTransaProtocol protocol, UserBean user)
    {
        packageAndHandleIncomeAndLogData(protocol, user);// 收集计算收益和生成交易日志需要的数据
        Map<String, Object> result = reGenerateIncomeAndLog();// 获得收益信息和交易日志结果
        persistReGenerateIncomeAndLog(result);// 保存到数据库
    }

    /**
     * @Title: packageAndHandleIncomeAndLogData
     * @Description: 为重新生成收益准备需要数据
     * @param protocol 协议信息
     * @param user 登录用户信息
     * @return 
     * @author: jinzhm
     * @time:2017年1月6日 上午11:50:55
     * history:
     * 1、2017年1月6日 jinzhm 创建方法
     */
    protected void packageAndHandleIncomeAndLogData(WmsInveTransaProtocol protocol, UserBean user)
    {
        // 初始化数据
        initData(protocol, user);
        // 删除当前已有的收益信息和交易日志信息
        IncomeUtil.getWmsInveTransaIncomeDao().delWmsInveTransaIncomes(protocol.getWms_inve_transa_id());
        IncomeUtil.getWmsInveTransaLogDao().delWmsInveTransaLogs(protocol.getWms_inve_transa_id());
    }

    /**
     * @Title: initData
     * @Description: 初始化客户收益处理时需要用到的数据
     * @param protocol 协议信息
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年1月6日 下午5:51:32
     * history:
     * 1、2017年1月6日 jinzhm 创建方法
     */
    private void initData(WmsInveTransaProtocol protocol, UserBean user)
    {
        // 获得上单信息
        this.transa = IncomeUtil.getWmsInveTransaDao().get(protocol.getWms_inve_transa_id());
        // 获得上单产品信息
        this.prod = IncomeUtil.getWmsInveTransaProdDao().get(protocol.getWms_inve_transa_prod_id());
        // 根据上单产品表主键获得产品信息
        this.category = IncomeUtil.getWmsInvePruductCategoryByProdId(protocol.getWms_inve_transa_prod_id());
        // 登录用户
        this.user = user;
        // 协议信息
        this.protocol = protocol;
        // 原始单据支付时间
        this.oldDateOfPayment = transa.getOld_date_of_payment() == null ? transa.getDate_of_payment()
                                                                       : transa.getOld_date_of_payment();
    }

    /**
     * @Title: reGenerateIncomeAndLog
     * @Description: 生成单据收益（未支付状态）和单据交易日志
     *      这里给月付产品统一生成收益和交易日志，年付产品在子类中重写此方法
     * @return 新增的收益信息和交易日志集合
     * @author: jinzhm
     * @time:2017年1月6日 上午11:48:41
     * history:
     * 1、2017年1月6日 jinzhm 创建方法
     */
    protected Map<String, Object> reGenerateIncomeAndLog()
    {
        // 要返回的数据集合
        Map<String, Object> rMap = new HashMap<String, Object>();
        // 生成交易日志集合
        List<WmsInveTransaLog> wmsInveTransaLogs = new ArrayList<WmsInveTransaLog>();
        // 计算收益信息集合
        List<WmsInveTransaIncome> wmsInveTransaIncomes = new ArrayList<WmsInveTransaIncome>();

        // 相差月数
        int months = 0;
        // 每月收益天数
        int incomeDays = 0;
        // 当月实际天数
        int dayOfMonth = 0;

        // 应收奖励金额
        BigDecimal bonus = BigDecimal.ZERO;
        // 应收收益金额
        BigDecimal income = BigDecimal.ZERO;
        // 计算时金额
        BigDecimal productAccount = protocol.getProduct_account();

        // 支付日期作为理财开始日期
        Date startDate = protocol.getDate_of_payment();
        // 理财结束日期
        Date endDate = protocol.getEnd_of_date();
        // 收益日期
        Date returnDate = null;

        // 设置循环使用开始时间和结束时间
        Calendar cal = Calendar.getInstance();
        // cal设置理财支付日期，cal用作循环
        cal.setTime(startDate);
        Calendar calEnd = Calendar.getInstance();
        // 到期时间设置为循环结束时间
        calEnd.setTime(endDate);

        // 首先记录一条理财上单的交易日志
        wmsInveTransaLogs.add(setWmsInveTransaLog(startDate, TRANSA_START, bonus, income, protocol, user));

        // 从上单时间开始循环到结束时间
        while (cal.compareTo(calEnd) <= 0)
        {
            // 如果循环使用时间和理财开始时间在同一个月份，说明循环进入第一个月
            if (DateUtil.getLastDayOfMonth(cal.getTime()).compareTo(DateUtil.getLastDayOfMonth(startDate)) == 0)
            {
                incomeDays = DateUtil.getDaysOfInterval(cal.getTime());
                // 如果是第一月，需要判断是否是2016-07-01号或之后上单
                if (IncomeUtil.isLaterThanMagicDate(oldDateOfPayment))
                {
                    /**
                     * 2016-07-01 之前上单，上单当天无收益，赎回日有收益
                     * 2016-07-01 之后上单（含），上单当天有收益，赎回日无收益
                     */
                    // 如果是2016-07-01或之后上单，第一月收益天数需要+1
                    incomeDays += 1;
                }
                // 如果循环没有到最后一个月，返利日期就是当月最后一天
                returnDate = DateUtil.getLastDayOfMonth(cal.getTime());
            }
            // 如果循环使用时间和到期时间是在同一个月份，说明循环进入到了最后一个月
            else if (DateUtil.getLastDayOfMonth(cal.getTime()).compareTo(DateUtil.getLastDayOfMonth(endDate)) == 0)
            {
                // 最后一个月收益天数直接获得月初到结束日期的天数
                incomeDays = calEnd.get(Calendar.DAY_OF_MONTH);
                // 最后一个月满月数和产品期限相同
                months = category.getCategory_deadline();
                // 最后一个月返利日期是结束日期
                returnDate = endDate;
            }
            // 如果循环没有进入到最后一个月
            else
            {
                // 如果不是第一月或最后一个月，其他月份收益天数直接取当月天数
                incomeDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                // 不是最后一个月，且也不是第一个月的话，月数+1。
                months++;
                // 如果循环没有到最后一个月，返利日期就是当月最后一天
                returnDate = DateUtil.getLastDayOfMonth(cal.getTime());
            }
            // 获得当月实际天数
            dayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            // 计算基本收益
            income = IncomeUtil.computeIncome(incomeDays, dayOfMonth, productAccount, returnRate);

            // 通过满月数量计算获得奖励利率
            BigDecimal bonusReturnRate = getFullMonthBonusReturnRate(months);
            // 计算奖励收益
            bonus = IncomeUtil.computeBonusIncome(bonusReturnRate, productAccount);

            // 生成收益保存到集合中
            wmsInveTransaIncomes.add(generateTransaIncome(returnDate, null, income, bonus, returnRate, bonusReturnRate,
                                                          incomeDays, PAY_STATUS_NOT_PAY, months));

            // 将日期移到下个月1号
            cal.setTime(DateUtil.getFirstDayOfNextMonth(cal.getTime()));
        }

        // 将生成的收益集合和交易日志集合保存到要持久化的集合中
        rMap.put("wmsInveTransaIncomes", wmsInveTransaIncomes);
        rMap.put("wmsInveTransaLogs", wmsInveTransaLogs);
        return rMap;
    }

    /**
     * @Title: persistReGenerateIncomeAndLog
     * @Description: 持久化重新生成的收益信息和交易信息
     * @param result 生成的收益信息集合和交易信息集合
     * @author: jinzhm
     * @time:2017年1月6日 下午2:49:07
     * history:
     * 1、2017年1月6日 jinzhm 创建方法
     */
    @SuppressWarnings("unchecked")
    protected void persistReGenerateIncomeAndLog(Map<String, Object> result)
    {
        // 获得生成的收益信息集合和交易日志集合
        List<WmsInveTransaIncome> transaIncomeList = (List<WmsInveTransaIncome>) result.get("wmsInveTransaIncomes");
        List<WmsInveTransaLog> transaLogList = (List<WmsInveTransaLog>) result.get("wmsInveTransaLogs");

        // 如果有要保存的收益信息集合，批量保存
        if (!transaIncomeList.isEmpty())
        {
            IncomeUtil.getWmsInveTransaIncomeDao().addBatchWmsInveTransaIncomes(transaIncomeList);
        }
        // 如果有要保存的交易日志信息集合，批量保存
        if (!transaLogList.isEmpty())
        {
            IncomeUtil.getWmsInveTransaLogDao().addBatchWmsInveTransaLogs(transaLogList);
        }
    }

    /**
     * @Title: generateIncomeAndLogForNewExtend
     * @Description: 为新产品续期功能生成新单据收益信息及处理老单据收益信息
     * @param protocol 新单据协议信息
     * @param oldProtocol 老单据协议信息
     * @param extendDate 续期时间
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2016年12月30日 上午8:34:36
     * @see com.zx.emanage.inve.util.CountIncome#generateIncomeAndLogForNewExtend(com.zx.emanage.util.gen.entity.WmsInveTransaProtocol, com.zx.emanage.util.gen.entity.WmsInveTransaProtocol, java.util.Date, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2016年12月30日 jinzhm 创建方法
    */
    @Override
    public void generateIncomeAndLogForNewExtend(WmsInveTransaProtocol protocol, WmsInveTransaProtocol oldProtocol,
                                                 Date extendDate, UserBean user)
    {
        packageIncomeAndLogForNewExtend(protocol, oldProtocol, extendDate, user);
        Map<String, Object> resMap = handleIncomeAndLogForNewExtend();
        persistIncomeAndLogForNewExtend(resMap);
    }

    /**
     * @Title: packageIncomeAndLogForNewExtend
     * @Description: 为新产品续期处理收益准备数据方法
     * @param protocol 新单据协议信息
     * @param oldProtocol 老单据协议信息
     * @param extendDate 续期时间
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2016年12月30日 上午8:36:04
     * history:
     * 1、2016年12月30日 jinzhm 创建方法
     */
    protected void packageIncomeAndLogForNewExtend(WmsInveTransaProtocol protocol, WmsInveTransaProtocol oldProtocol,
                                                   Date extendDate, UserBean user)
    {
        // 获得新单据生成收益需要的数据
        packageIncomeAndLogData(protocol, user);
        // 获得赎回信息
        WmsInveRedeem redeem = IncomeUtil.getWmsInveRedeemDao().get(oldProtocol.getWms_inve_redeem_id());
        // 查询赎回明细信息
        WmsInveRedeemDetail detail = new WmsInveRedeemDetail();
        detail.setWms_inve_redeem_id(oldProtocol.getWms_inve_redeem_id());
        List<WmsInveRedeemDetail> redeemDetailList = IncomeUtil.getWmsInveRedeemDetailDao().getListByEntity(detail);
        // 直接取第一个，因为续期时肯定有至少一个赎回
        detail = redeemDetailList.get(0);
        
        // 查询老单据公益收益信息，因为只需要处理公益收益
        List<WmsInveTransaIncome> oldTransaIncomeList = IncomeUtil.getTransaIncomeList(oldProtocol.getWms_inve_transa_id(),
                                                                                       "'" + PAY_TYPE_PUBLIC + "'");

        dataMap.put("oldProtocol", oldProtocol);
        dataMap.put("oldTransaIncomeList", oldTransaIncomeList);
        dataMap.put("wmsInveRedeem", redeem);
        dataMap.put("wmsInveRedeemDetail", detail);
    }

    /**
     * @Title: handleIncomeAndLogForNewExtend
     * @Description: 为新产品续期的新单据生成收益及交易日期，处理老单据公益收益
     * @return 要新增的收益信息集合及交易日志集合
     * @author: jinzhm
     * @time:2016年12月30日 上午8:37:46
     * history:
     * 1、2016年12月30日 jinzhm 创建方法<br/>
     * 2、2017年01月06日 jinzhm 修改<br/>
     *      将原来修改原单据的公益收益支付状态（修改成已终止）取消<br/>
     *      在预约时将原单据的公益收益设置成已终止，此处不处理原单据<br/>
     */
    protected Map<String, Object> handleIncomeAndLogForNewExtend()
    {

        WmsInveTransaProtocol oldProtocol = (WmsInveTransaProtocol) dataMap.get("oldProtocol");
        // 给新单据生成收益及交易日志
        Map<String, Object> resMap = handleIncomeAndLog();

        // 要修改的交易日志信息集合
        List<WmsInveTransaLog> transaLogList = new ArrayList<WmsInveTransaLog>();

        // 赎回日志
        WmsInveTransaLog transaLog = generateRedeemLog(oldProtocol.getWms_inve_transa_id(), 1);
        // 理财结束日志
        WmsInveTransaLog endLog = generateTransaLog(oldProtocol.getEnd_of_date(), oldProtocol, user, TRANSA_END);

        // 理财结束交易日志的操作日期设置成赎回交易日志的时间
        endLog.setChange_date(transaLog.getChange_date());

        transaLogList.add(transaLog);
        transaLogList.add(endLog);

        resMap.put("updTransaLogList", transaLogList);
        return resMap;
    }

    /**
     * @Title: persistIncomeAndLogForNewExtend
     * @Description: 持久化新产品续期生成的收益，交易日志，要修改的收益及要修改的交易日志
     * @param resMap 要持久化的数据集合对象
     * @author: jinzhm
     * @time:2016年12月30日 上午8:39:24
     * history:
     * 1、2016年12月30日 jinzhm 创建方法
     */
    @SuppressWarnings("unchecked")
    protected void persistIncomeAndLogForNewExtend(Map<String, Object> resMap)
    {
        // 新生成的收益集合和日志集合
        List<WmsInveTransaIncome> transaIncomeList = (List<WmsInveTransaIncome>) resMap.get("wmsInveTransaIncomes");
        List<WmsInveTransaLog> transaLogList = (List<WmsInveTransaLog>) resMap.get("wmsInveTransaLogs");
        // 要修改的收益集合和日志集合
        List<WmsInveTransaLog> updTransaLogList = (List<WmsInveTransaLog>) resMap.get("updTransaLogList");
        transaLogList.addAll(updTransaLogList);

        // 新增新单据收益信息
        if (transaIncomeList != null && transaIncomeList.size() > 0)
        {
            IncomeUtil.getWmsInveTransaIncomeDao().addBatchWmsInveTransaIncomes(transaIncomeList);
        }
        // 修改老单据收益信息
        // 新增新单据日志和老单据赎回日志和理财结束日志
        if (transaLogList != null && transaLogList.size() > 0)
        {
            IncomeUtil.getWmsInveTransaLogDao().addBatchWmsInveTransaLogs(transaLogList);
        }
    }

    /**
     * 收益自动续期时生成收益信息及交易日志需要的相关数据
     * 
     * @param protocol
     * @param user
     *
     * @author 金志明
     * @date 2017年10月12日 上午10:26:41
     */
	protected void packageIncomeAndLogForAutoExtend(WmsInveTransaProtocol protocol, WmsInveTransaProtocol oldProtocol, Date extendDate, UserBean user)
	{
        // 初始化一些要用到对象信息
        initData(protocol, user);
        this.oldProtocol = oldProtocol;
        this.publicReturnRate = IncomeUtil.getPublicProductReturnRate();
        this.oldTransaIncomeList = IncomeUtil.getTransaIncomeList(oldProtocol.getWms_inve_transa_id());
        this.extendDate = extendDate;
	}
	
	/**
	 * 自动续期计算收益及生成交易日志
	 * 
	 * @return
	 *
	 * @author 金志明
	 * @date 2017年10月12日 上午10:27:48
	 */
    protected Map<String, Object> handleIncomeAndLogForAutoExtend()
    {
        // 最后要返回的数据集合
        Map<String, Object> rMap = new HashMap<String, Object>();

        // 生成交易日志集合
        List<WmsInveTransaLog> transaLogList = new ArrayList<WmsInveTransaLog>();
        // 计算收益信息集合
        List<WmsInveTransaIncome> transaIncomeList = new ArrayList<WmsInveTransaIncome>();

        // 当前时间
        Date nowDate = DateUtil.getDate10(new Date());
        // 理财开始时间
        Date startDate = protocol.getDate_of_payment();
        // 理财结束时间
        Date endDate = protocol.getEnd_of_date();
        // 每个月收益应付日期
        Date returnDate = null;
        // 实际支付日期
        Date actReturnDate = null;

        // 循环开始时间
        Calendar calStart = Calendar.getInstance();
        calStart.setTime(startDate);
        // 循环结束时间
        Calendar calEnd = getEndCalendar(endDate, oldTransaIncomeList);

        // 基本收益
        BigDecimal income = BigDecimal.ZERO;
        // 奖励收益
        BigDecimal bonus = BigDecimal.ZERO;
        
        // 月数
        int months = 0;
        // 每月收益天数
        int incomeDays = 0;
        // 当月自然天数
        int dayOfMonth = 0;

        // 要生成的收益类型
        String payType = PAY_TYPE_NORMAL;
        // 要生成的收益状态
        String payStatus = PAY_STATUS_NOT_PAY;

        // 首先要生成一个上单的交易日志记录
        transaLogList.add(setWmsInveTransaLog(startDate, TRANSA_START, bonus, income, protocol, user));

        // 从循环开始时间循环到循环结束时间
        while (calStart.compareTo(calEnd) <= 0)
        {
            // 获得当月实际天数
            dayOfMonth = calStart.getActualMaximum(Calendar.DAY_OF_MONTH);
            // 如果要生成的是正常收益
            if (PAY_TYPE_NORMAL.equals(payType))
            {
                // 如果循环生成的是最后一个月的正常收益
                if (DateUtil.getLastDayOfMonth(endDate).compareTo(DateUtil.getLastDayOfMonth(calStart.getTime())) == 0)
                {
                    // 最后一个月收益天数直接获得月初到结束日期的天数
                    incomeDays = DateUtil.getDaysOfIntervalBegin(endDate);
                    // 最后一个月满月数和产品期限相同
                    months = category.getCategory_deadline();
                    // 最后一个月返利日期是结束日期
                    returnDate = endDate;
                }
                else
                {
                    // 如果循环正在生成的是第一个月的正常收益
                    if (startDate.compareTo(calStart.getTime()) == 0)
                    {
                        // 收益天数是当月1月号月末最后一天的天数
                        incomeDays = DateUtil.getDaysOfInterval(calStart.getTime());
                        // 如果是第一月，需要判断是否是2016-07-01号或之后上单
                        if (IncomeUtil.isLaterThanMagicDate(oldDateOfPayment))
                        {
                            // 如果是2016-07-01或之后上单，第一月收益天数需要+1
                            incomeDays += 1;
                        }
                    }
                    // 如果循环正常生成的是中间月份（不是第一月不是最后一月）
                    else
                    {
                        // 如果不是第一月或最后一个月，其他月份收益天数直接取当月天数
                        incomeDays = calStart.getActualMaximum(Calendar.DAY_OF_MONTH);
                        // 不是最后一个月，且也不是第一个月的话，月数+1。
                        months++;
                    }
                    // 如果循环没有到最后一个月，返利日期就是当月最后一天
                    returnDate = DateUtil.getLastDayOfMonth(calStart.getTime());
                }

                // 计算正常收益
                income = IncomeUtil.computeIncome(incomeDays, dayOfMonth, protocol.getProduct_account(), returnRate);
                
                // 获得奖励利率
                BigDecimal bonusReturnRate = getFullMonthBonusReturnRate(months);
                // 计算奖励利率
                bonus = IncomeUtil.computeBonusIncome(bonusReturnRate, protocol.getProduct_account());

                // 如果生成收益的时间（年月，不计较日）小于生成收益的当前时间的（年月），生成收益记录的支付状态为已支付
                payStatus = DateUtil.getLastDayOfMonth(returnDate).compareTo(DateUtil.getLastDayOfMonth(nowDate)) < 0 ? PAY_STATUS_EXTEND_ALREADY_PAY
                                                                                                                     : PAY_STATUS_NOT_PAY;
                // 如果有老单据收益
                if (!oldTransaIncomeList.isEmpty())
                {
                    // 处理第一个月收益，老单据最后一个月肯定有公益收益，不需要判断是否有直接处理
                    if (startDate.compareTo(calStart.getTime()) == 0)
                    {
                        // 获得公益收益收益天数（从到期日期到月末最后一天就是到期当月的公益收益天数）
                        int publicIncomeDays = DateUtil.getDaysOfInterval(endDate);
                        // 计算到期当月公益收益
                        BigDecimal publicIncome = computePublicIncome(protocol.getProduct_account(), publicIncomeDays,
                                                                      publicReturnRate);
                        // 获得正常自然月收益
                        BigDecimal fullMonthIncome = IncomeUtil.setScale(IncomeUtil.computeIncome(dayOfMonth,
                                                                                                  dayOfMonth,
                                                                                                  protocol.getProduct_account(),
                                                                                                  returnRate));
                        // 获得老单据所有在同年同月的收益金额
                        BigDecimal oldTotalIncome = IncomeUtil.getOldTransaIncomeHasSpecifyMonthValidIncome(transa,
                                                                                                            returnDate);

                        // 正常应付收益 正常-公益
                        income = IncomeUtil.setScale(income.subtract(publicIncome));
                        // 如果正常收益加公益收益后向上取整+老单家居最后一个公益收益向上取整大于正常一个自然月应付收益金额需要减差额
                        if (income.add(oldTotalIncome).compareTo(fullMonthIncome) > 0)
                        {
                            // 在当前收益中减去差额
                            income = income.subtract(income.add(oldTotalIncome).subtract(fullMonthIncome));
                        }
                        // 循环老单据收益信息
                        for (WmsInveTransaIncome oldTransaIncome : oldTransaIncomeList)
                        {
                            // 如果找到老单据和新单据收益应付日期是同年同月且是正常收益
                            if (DateUtil.getLastDayOfMonth(oldTransaIncome.getReturn_date())
                                        .compareTo(DateUtil.getLastDayOfMonth(returnDate)) == 0
                                && PAY_TYPE_NORMAL.equals(oldTransaIncome.getPay_type()))
                            {
                                payStatus = DateUtil.getLastDayOfMonth(returnDate)
                                                    .compareTo(DateUtil.getLastDayOfMonth(DateUtil.getDate10(new Date()))) == 0 ? PAY_STATUS_NOT_PAY
                                                                                                                               : PAY_STATUS_EXTEND_ALREADY_PAY;
                            }
                        }
                    }
                    // 处理最后一个月收益
                    else if (returnDate.compareTo(endDate) == 0)
                    {
                        // 处理最后一个月收益时老单据中可能没有该时间的公益，需要判断是否有公益
                        // 循环老单据收益信息
                        for (WmsInveTransaIncome oldTransaIncome : oldTransaIncomeList)
                        {
                            // 查看老单据在新单据最后一个月收益的月份是否有公益收益
                            // 如果找到老单据和新单据收益应付日期是同年同月且是公益收益
                            if (DateUtil.getLastDayOfMonth(oldTransaIncome.getReturn_date())
                                        .compareTo(DateUtil.getLastDayOfMonth(returnDate)) == 0
                                && PAY_TYPE_PUBLIC.equals(oldTransaIncome.getPay_type()))
                            {
                                // 公益收益收益天数
                                int publicIncomeDays = DateUtil.getDaysOfIntervalBegin(returnDate);
                                // 计算公益收益
                                BigDecimal publicIncome = computePublicIncome(protocol.getProduct_account(),
                                                                              publicIncomeDays, publicReturnRate);
                                // 获得老单据所有在同年同月的收益金额
                                BigDecimal oldTotalIncome = IncomeUtil.getOldTransaIncomeHasSpecifyMonthValidIncome(transa,
                                                                                                                    returnDate);
                                // 获得正常自然月收益
                                BigDecimal fullMonthIncome = IncomeUtil.setScale(IncomeUtil.computeIncome(dayOfMonth,
                                                                                                          dayOfMonth,
                                                                                                          protocol.getProduct_account(),
                                                                                                          returnRate));
                                // 正常应付收益 正常-公益
                                income = IncomeUtil.setScale(income.subtract(publicIncome));
                                // 如果正常收益加公益收益后向上取整+老单家居最后一个公益收益向上取整大于正常一个自然月应付收益金额需要减差额
                                if (income.add(oldTotalIncome).compareTo(fullMonthIncome) > 0)
                                {
                                    // 在当前收益中减去差额
                                    income = income.subtract(income.add(oldTotalIncome).subtract(fullMonthIncome));
                                }
                                payStatus = DateUtil.getLastDayOfMonth(returnDate)
                                                    .compareTo(DateUtil.getLastDayOfMonth(DateUtil.getDate10(new Date()))) == 0 ? PAY_STATUS_NOT_PAY
                                                                                                                               : PAY_STATUS_EXTEND_ALREADY_PAY;
                            }
                        }
                    }
                    // 处理其他月份收益
                    else
                    {
                        // 循环老单据收益信息
                        for (WmsInveTransaIncome oldTransaIncome : oldTransaIncomeList)
                        {
                            // 老单据的收益信息和新单据要生成的收益信息的收益应付日期相同且是公益收益
                            if (oldTransaIncome.getReturn_date().compareTo(returnDate) == 0
                                && PAY_TYPE_PUBLIC.equals(oldTransaIncome.getPay_type()))
                            {
                                // 如果公益收益是已支付或未支付状态时处理；已终止状态不处理
                                if (IncomeUtil.isIncomeAlreadyPaid(oldTransaIncome)
                                    || IncomeUtil.isIncomeNotPaid(oldTransaIncome))
                                {
                                    // 因为要处理小数点问题，所以不能直接使用老单据的公益收益金额
                                    BigDecimal publicIncome = IncomeUtil.computePublicIncome(oldTransaIncome.getDays_extend_income(),
                                                                                             oldTransaIncome.getProduct_account(),
                                                                                             publicReturnRate);

                                    // 获得正常自然月收益
                                    BigDecimal fullMonthIncome = IncomeUtil.setScale(IncomeUtil.computeIncome(dayOfMonth,
                                                                                                              dayOfMonth,
                                                                                                              protocol.getProduct_account(),
                                                                                                              returnRate));
                                    // 正常应付收益 正常-公益
                                    income = IncomeUtil.setScale(income.subtract(publicIncome));
                                    // 如果正常收益加公益收益后向上取整+老单家居最后一个公益收益向上取整大于正常一个自然月应付收益金额需要减差额
                                    if (income.add(IncomeUtil.setScale(publicIncome)).compareTo(fullMonthIncome) > 0)
                                    {
                                        // 在当前收益中减去差额
                                        income = income.subtract(income.add(IncomeUtil.setScale(publicIncome))
                                                                       .subtract(fullMonthIncome));
                                    }
                                    payStatus = DateUtil.getLastDayOfMonth(returnDate)
                                                        .compareTo(DateUtil.getLastDayOfMonth(DateUtil.getDate10(new Date()))) == 0 ? PAY_STATUS_NOT_PAY
                                                                                                                                   : PAY_STATUS_EXTEND_ALREADY_PAY;
                                }
                            }
                        }
                    }
                }

                // 实际支付日期
                actReturnDate = IncomeUtil.isIncomeAlreadyPaid(payStatus) ? DateUtil.getLastDayOfMonth(returnDate)
                                                                               : null;

                // 封装数据
                transaIncomeList.add(generateTransaIncome(returnDate, actReturnDate, income, bonus, returnRate,
                                                          bonusReturnRate, incomeDays, payStatus, months));
                // 如果是已支付状态，需要生成交易日志
                if (IncomeUtil.isIncomeAlreadyPaid(payStatus))
                {
                    transaLogList.add(setWmsInveTransaLog(returnDate, TRANSA_INCOME, bonus, income, protocol, user));
                }

                // 将收益支付状态重新设置成未支付
                payStatus = PAY_STATUS_NOT_PAY;
            }
            // 如果要生成的公益收益
            else if (PAY_TYPE_PUBLIC.equals(payType))
            {
                // 如果收益应付日期不是到期月份收益或是月末最后一天
                if (returnDate.compareTo(endDate) != 0
                    || returnDate.compareTo(DateUtil.getLastDayOfMonth(returnDate)) == 0)
                {
                    returnDate = DateUtil.getLastDayOfMonth(calStart.getTime());
                }
                // 月份每次生成+1。该月份已经没有意义（因为到期月份有可能有正常+公益）
                months++;
                for (WmsInveTransaIncome oldTransaIncome : oldTransaIncomeList)
                {
                    // 收益应付日期是结束日期，收益应付日期和老单据收益中的收益应付日期是同一个月，是公益收益
                    if (returnDate.compareTo(endDate) == 0
                        && returnDate.compareTo(DateUtil.getLastDayOfMonth(returnDate)) != 0
                        && DateUtil.getLastDayOfMonth(returnDate)
                                   .compareTo(DateUtil.getLastDayOfMonth(oldTransaIncome.getReturn_date())) == 0
                        && PAY_TYPE_PUBLIC.equals(oldTransaIncome.getPay_type()))
                    {
                        // 公益收益收益天数
                        int publicIncomeDays = DateUtil.getDaysOfInterval(returnDate);
                        // 计算公益收益
                        BigDecimal publicIncome = IncomeUtil.computePublicIncome(publicIncomeDays,
                                                                                 protocol.getProduct_account(),
                                                                                 publicReturnRate);
                        WmsInveTransaIncome transaIncome = generateTransaIncome(DateUtil.getLastDayOfMonth(returnDate),
                                                                                null, BigDecimal.ZERO, BigDecimal.ZERO,
                                                                                BigDecimal.ZERO, BigDecimal.ZERO, 0,
                                                                                PAY_STATUS_NOT_PAY, months);
                        transaIncome.setPay_type(PAY_TYPE_PUBLIC);
                        IncomeUtil.setTransaIncomePublicRemarks(transaIncome, null);
                        transaIncome.setDays_extend_income(publicIncomeDays);
                        transaIncome.setExtend_income(IncomeUtil.setScale(publicIncome));
                        transaIncome.setProduct_interest_account(IncomeUtil.setScale(publicIncome));
                        // 新单据公益收益设置成不需要支付的状态
                        transaIncome.setPay_status(PAY_STATUS_EXTEND_NO_NEED_PAY);
                        // 如果老单据公益收益是已支付状态
                        if (IncomeUtil.isIncomeAlreadyPaid(oldTransaIncome))
                        {
                            transaIncome.setAct_return_date(new java.sql.Date(DateUtil.getLastDayOfMonth(returnDate)
                                                                                      .getTime()));
                        }

                        transaIncomeList.add(transaIncome);

                        // 如果是已支付状态，需要生成交易日志
                        if (IncomeUtil.isIncomeAlreadyPaid(payStatus))
                        {
                            transaLogList.add(setWmsInveTransaLog(returnDate, TRANSA_INCOME, BigDecimal.ZERO,
                                                                  publicIncome, protocol, user));
                        }

                        returnDate = DateUtil.getLastDayOfMonth(calStart.getTime());
                        break;
                    }
                    // 如果是同年同月的公益收益且不是到期日的收益
                    else if (returnDate.compareTo(endDate) != 0
                             && DateUtil.getLastDayOfMonth(oldTransaIncome.getReturn_date()).compareTo(returnDate) == 0
                        && PAY_TYPE_PUBLIC.equals(oldTransaIncome.getPay_type()))
                    {
                        // 如果是已支付公益收益或未支付公益收益
                        if (IncomeUtil.isIncomeAlreadyPaid(oldTransaIncome)
                            || IncomeUtil.isIncomeNotPaid(oldTransaIncome))
                        {

                            // 直接将老单据公益收益修改部分信息后保存
                            WmsInveTransaIncome transaIncome = oldTransaIncome;
                            transaIncome.setWms_inve_transa_income_id(null);
                            transaIncome.setWms_inve_transa_id(protocol.getWms_inve_transa_id());
                            transaIncome.setWms_inve_transa_prod_id(protocol.getWms_inve_transa_prod_id());
                            transaIncome.setWms_inve_pruduct_category_id(category.getWms_inve_pruduct_category_id());
                            transaIncome.setPay_status(PAY_STATUS_EXTEND_NO_NEED_PAY);
                            // 如果老单据公益收益是已支付状态
                            if (IncomeUtil.isIncomeAlreadyPaid(transaIncome))
                            {
                                transaIncome.setAct_return_date(new java.sql.Date(
                                                                                  DateUtil.getLastDayOfMonth(returnDate)
                                                                                          .getTime()));
                            }
                            transaIncome.setCreate_user_id(user.getUserId());
                            transaIncome.setCreate_user_name(user.getRealname());
                            transaIncome.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
                            transaIncome.setLast_update_timestamp(null);
                            transaIncome.setLast_update_user_id(null);
                            transaIncomeList.add(transaIncome);
                            // 如果是已支付状态，需要生成交易日志
                            if (IncomeUtil.isIncomeAlreadyPaid(payStatus))
                            {
                                transaLogList.add(setWmsInveTransaLog(returnDate, TRANSA_INCOME, BigDecimal.ZERO,
                                                                      oldTransaIncome.getProduct_interest_account(),
                                                                      protocol, user));
                            }
                            break;
                        }
                    }
                }
            }

            // 如果循环月数等于产品的到期期限月数,之后生成收益应该生成公益收益
            if (months == category.getCategory_deadline())
            {
                payType = PAY_TYPE_PUBLIC;
                continue;
            }
            // 将循环时间设置成下个月月初
            calStart.setTime(DateUtil.getFirstDayOfNextMonth(calStart.getTime()));
        }

        // 如果循环结束日期和理财到期时间相同且理财到期时间小于当前时间的月份且到期日期不是月末最后一天，生成公益收益
        // if (endDate.compareTo(calEnd.getTime()) == 0 &&
        // endDate.compareTo(DateUtil.getLastDayOfMonth(endDate)) != 0
        // &&
        // DateUtil.getLastDayOfMonth(endDate).compareTo(DateUtil.getLastDayOfMonth(nowDate))
        // <= 0)
        // {
        // // 公益收益天数
        // int publicIncomeDays = DateUtil.getDaysOfInterval(endDate);
        // // 计算公益
        // BigDecimal publicIncome =
        // IncomeUtil.computePublicIncome(publicIncomeDays,
        // protocol.getProduct_account(),
        // publicReturnRate);
        // // 先生成一个正常收益
        // WmsInveTransaIncome transaIncome =
        // generateTransaIncome(DateUtil.getLastDayOfMonth(endDate), null,
        // BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
        // BigDecimal.ZERO, 0, PAY_STATUS_NOT_PAY, 0);
        // // 将正常收益相关信息修改成公益收益
        // transaIncome.setProduct_interest_account(IncomeUtil.setScale(publicIncome));
        // transaIncome.setExtend_income(IncomeUtil.setScale(publicIncome));
        // transaIncome.setDays_extend_income(publicIncomeDays);
        // transaIncome.setPay_type(PAY_TYPE_PUBLIC);
        // IncomeUtil.setTransaIncomePublicRemarks(transaIncome, null);
        // transaIncomeList.add(transaIncome);
        // }

        rMap.put("wmsInveTransaIncomes", transaIncomeList);
        rMap.put("wmsInveTransaLogs", transaLogList);

        return rMap;
    }
	
	@SuppressWarnings("unchecked")
	protected void persistIncomeAndLogForAutoExtend(Map<String, Object> result)
	{

		List<WmsInveTransaIncome> wmsInveTransaIncomes = (List<WmsInveTransaIncome>) result.get("wmsInveTransaIncomes");
		List<WmsInveTransaLog> wmsInveTransaLogs = (List<WmsInveTransaLog>) result.get("wmsInveTransaLogs");
		
		//在原单据的交易日志中生成理财续期和理财结束日志
		WmsInveTransaLog extendLog = generateTransaLog(extendDate, oldProtocol, user, TRANSA_EXTEND);
		extendLog.setRemark("自动续期&lt;"+transa.getBill_code()+"&gt;");
		WmsInveTransaLog endLog = generateTransaLog(extendDate, oldProtocol, user, TRANSA_END);
		wmsInveTransaLogs.add(extendLog);
		wmsInveTransaLogs.add(endLog);
		
		// 保存收益信息
		if (wmsInveTransaIncomes != null && wmsInveTransaIncomes.size() > 0)
		{
            IncomeUtil.getWmsInveTransaIncomeDao().addBatchWmsInveTransaIncomes(wmsInveTransaIncomes);
		}
		// 保存交易日志信息
		if (wmsInveTransaLogs != null && wmsInveTransaLogs.size() > 0)
		{
            IncomeUtil.getWmsInveTransaLogDao().addBatchWmsInveTransaLogs(wmsInveTransaLogs);
		}
	}
	
	/**
	 * 收集生成收益信息及交易日志需要的相关数据。<br/>
	 * 年付，月付-满月，月付-累计存量公共获取数据方法。<b>如公共方法不满足，在具体实现类中重写此方法。</b>
	 * 
	 * @param protocol
	 *            理财协议对象
	 * @param user
	 *            登录用户对象
	 * @return 在计算收益和生成交易日志时需要的数据map
	 * 
	 * @author 金志明
	 * @date 2016年9月29日 下午5:17:21
	 */
	protected void packageIncomeAndLogData(WmsInveTransaProtocol protocol, UserBean user)
	{
        // 初始化一些要用到对象信息
        initData(protocol, user);
	}

	/**
	 * 计算收益，生成交易详情日志
	 * 
	 * @param data
	 *            计算收益和生成交易日志需要的数据
	 * @return 包含收益信息和交易日志的map
	 * 
	 * @author 金志明
	 * @date 2016年9月29日 下午5:24:51
	 */
	protected abstract Map<String, Object> handleIncomeAndLog();

	/**
	 * 将收益信息和交易日志保存到库中
	 * 
	 * @param result
	 *            包含收益信息和交易日志的map
	 * 
	 * @author 金志明
	 * @date 2016年9月29日 下午5:28:32
	 */
	@SuppressWarnings("unchecked")
	protected void persistIncomeAndLog(Map<String, Object> result)
	{
		// 获得收益信息dao对象和交易日志dao对象
		WmsInveTransaIncomeDao wmsInveTransaIncomeDao = (WmsInveTransaIncomeDao) GlobalVal.ctx
				.getBean("wmsInveTransaIncomeDao");
		WmsInveTransaLogDao wmsInveTransaLogDao = (WmsInveTransaLogDao) GlobalVal.ctx.getBean("wmsInveTransaLogDao");

		// 保存收益信息
		List<WmsInveTransaIncome> wmsInveTransaIncomes = (List<WmsInveTransaIncome>) result.get("wmsInveTransaIncomes");
		if (wmsInveTransaIncomes != null && wmsInveTransaIncomes.size() > 0)
		{
			wmsInveTransaIncomeDao.addBatchWmsInveTransaIncomes(wmsInveTransaIncomes);
		}
		// 保存交易日志信息
		List<WmsInveTransaLog> wmsInveTransaLogs = (List<WmsInveTransaLog>) result.get("wmsInveTransaLogs");
		if (wmsInveTransaLogs != null && wmsInveTransaLogs.size() > 0)
		{
			wmsInveTransaLogDao.addBatchWmsInveTransaLogs(wmsInveTransaLogs);
		}
	}

	/**
	 * 为赎回重新计算收益和生成日志收集数据
	 * 
	 * @param protocol
	 *            理财协议对象
	 * @param user
	 *            登录用户对象
	 * 
	 * @author 金志明
	 * @date 2016年9月30日 上午9:46:53
	 */
	protected void packageIncomeAndLogForRedeemData(WmsInveTransaProtocol protocol, UserBean user)
	{
        // 初始化通用数据
        initData(protocol, user);
		// 获得dao对象
		WmsInveRedeemDao redeemDao = (WmsInveRedeemDao) GlobalVal.ctx.getBean("wmsInveRedeemDao");
		WmsInveRedeemDetailDao wmsinveredeemdetailDao = (WmsInveRedeemDetailDao) GlobalVal.ctx
				.getBean("wmsInveRedeemDetailDao");
		WmsInveTransaLogDao wmsInveTransaLogDao = (WmsInveTransaLogDao) GlobalVal.ctx.getBean("wmsInveTransaLogDao");

		// 赎回信息
		WmsInveRedeem redeem = redeemDao.get(protocol.getWms_inve_redeem_id());
		// 赎回明细
		WmsInveRedeemDetail wmsinveredeemdetail = new WmsInveRedeemDetail();
		wmsinveredeemdetail.setWms_inve_redeem_id(protocol.getWms_inve_redeem_id());
		wmsinveredeemdetail.setEnable_flag("1");
		List<WmsInveRedeemDetail> detailList = wmsinveredeemdetailDao.getListByEntity(wmsinveredeemdetail);
		// 初始化收益信息集合
        List<WmsInveTransaIncome> incomeList = IncomeUtil.getTransaIncomeList(prod.getWms_inve_transa_id());
		// 理财——上单日志表wms_inve_transa_log
		WmsInveTransaLog wmsinvetransalog = new WmsInveTransaLog();
        wmsinvetransalog.setWms_inve_transa_id(prod.getWms_inve_transa_id());
		wmsinvetransalog.setOperate_item("'"+CountIncome.TRANSA_INCOME+"','"+CountIncome.TRANSA_START+"'");
		// wmsinvetransalog.setEnable_flag("1");
		List<WmsInveTransaLog> logList = wmsInveTransaLogDao.getListByEntity(wmsinvetransalog);

        this.publicReturnRate = IncomeUtil.getPublicProductReturnRate();

		dataMap.put("wmsInveTransaIncomes", incomeList);// 初始化收益信息集合
		dataMap.put("wmsInveTransaLogs", logList);// 初始化日志信息集合
		dataMap.put("wmsInveRedeem", redeem);// 赎回信息
		dataMap.put("wmsInveRedeemDetail", detailList.get(0));// 赎回明细
		dataMap.put("redeemDate", redeem.getRedeem_date());// 赎回时间

        // 保存当前收益的支付状态
        Map<Integer, String> incomeStatusMap = new HashMap<Integer, String>();
        // 循环当前收益
        for (int i = 0; i < incomeList.size(); i++)
        {
            // 保存到map中 key是id，value是支付状态
            WmsInveTransaIncome income = incomeList.get(i);
            incomeStatusMap.put(income.getWms_inve_transa_income_id(), income.getPay_status());
        }
        // 保存到准备数据map中
        dataMap.put("statusMap", incomeStatusMap);

        this.extendDate = IncomeUtil.getTransaExtendDate(transa.getOld_last_wms_inve_transa_id());

	}

	/**
	 * 赎回后重新计算收益和生成日志
	 * 
	 * @return
	 * 
	 * @author 金志明
	 * @date 2016年9月30日 上午9:48:22
	 */
	protected abstract Map<String, Object> handleIncomeAndLogForRedeem();

	/**
	 * 赎回后将重新计算的收益和生成日志保存到库中
	 * 
	 * @param result
	 *            重新计算的收益和生成的日志集合
	 * 
	 * @author 金志明
	 * @date 2016年9月30日 上午9:48:48
	 */
	@SuppressWarnings("unchecked")
	protected void persistIncomeAndLogForRedeem(Map<String, Object> result)
	{
		WmsInveTransaIncomeDao wmsInveTransaIncomeDao = (WmsInveTransaIncomeDao) GlobalVal.ctx
				.getBean("wmsInveTransaIncomeDao");
		WmsInveTransaLogDao wmsInveTransaLogDao = (WmsInveTransaLogDao) GlobalVal.ctx.getBean("wmsInveTransaLogDao");

		Date redeemDate = DateUtil.getDate10((Date) dataMap.get("redeemDate"));// 赎回时间
		
		if (dataMap.containsKey("redeemIncome"))
		{// 赎回收益肯定会有
			wmsInveTransaIncomeDao.save((WmsInveTransaIncome) dataMap.get("redeemIncome"));
		}
		if (dataMap.containsKey("redeemLog"))
		{// 赎回日志肯定会有
			wmsInveTransaLogDao.save((WmsInveTransaLog) dataMap.get("redeemLog"));
		}
		if (dataMap.containsKey("transaEndLog"))
		{// 如果有理财结束日志
			wmsInveTransaLogDao.save((WmsInveTransaLog) dataMap.get("transaEndLog"));
		}
        // 如果有老单据收益数据需要处理
        if (dataMap.containsKey("oldUpdTransaIncomeList"))
        {
            // 需要修改的老单据对应的收益信息集合
            List<WmsInveTransaIncome> oldTransaIncomeList = (List<WmsInveTransaIncome>) dataMap.get("oldUpdTransaIncomeList");
            // 如果集合不是空集合
            if (!oldTransaIncomeList.isEmpty())
            {
                // 循环修改老单据对应收益信息
                for (int i = 0; i < oldTransaIncomeList.size(); i++)
                {
                    wmsInveTransaIncomeDao.update(oldTransaIncomeList.get(i));
                }
            }
        }

		// 获取收益信息集合
		List<WmsInveTransaIncome> wmsInveTransaIncomes = (List<WmsInveTransaIncome>) result.get("wmsInveTransaIncomes");
		if (wmsInveTransaIncomes != null && wmsInveTransaIncomes.size() > 0)
		{
            // 修改之前收益的状态
            Map<Integer, String> statusMap = (Map<Integer, String>) dataMap.get("statusMap");
            for (int i = 0; i < wmsInveTransaIncomes.size(); i++)
            {
                WmsInveTransaIncome transaIncome = wmsInveTransaIncomes.get(i);
                /*
                 *  修改收益应付日期的月份大于或等于赎回日期的月份的收益信息。
                 *  但是月份相同时可能会有已经支付收益，已支付收益不能修改。
                 *  因此需要判断修改之前收益状态是不是已经支付。
                 *  因为判断修改之后收益状态的话，有可能在修改时给修改成已支付。数据不正确，所以需要和修改之前的状态比较。
                 */
                if (DateUtil.getLastDayOfMonth(transaIncome.getReturn_date())
                            .compareTo(DateUtil.getLastDayOfMonth(redeemDate)) >= 0)
                {
                    // 获得修改之前收益信息的状态
                    String payStatus = statusMap.get(transaIncome.getWms_inve_transa_income_id());
                    // 修改之前收益信息状态是未支付时进行修改
                    if (!IncomeUtil.isIncomeAlreadyPaid(payStatus))
                    {
                        wmsInveTransaIncomeDao.update(wmsInveTransaIncomes.get(i));
                    }
                }
			}
		}

	}
	
	/**
	 * @Title: setWmsInveTransaIncome
	 * @Description: 保存收益信息
	 * @return WmsInveTransaIncome
	 * @throws
	 * @author lvtu
	 * @date 2015年8月27日 下午3:55:31
	 */
    protected WmsInveTransaIncome setWmsInveTransaIncome(java.sql.Date returnDate, java.sql.Date actReturnDate,
                                                         BigDecimal bonusRate, BigDecimal award, BigDecimal incomeRate,
                                                         BigDecimal income, int daysOfCalculation, String payStatus,
                                                         WmsInveTransaProtocol protocol,
                                                         WmsInvePruductCategory category, UserBean user)
    {
		WmsInveTransaIncome wmsInveTransaIncome = new WmsInveTransaIncome();
		wmsInveTransaIncome.setWms_inve_transa_id(protocol.getWms_inve_transa_id());
		wmsInveTransaIncome.setWms_inve_transa_prod_id(protocol.getWms_inve_transa_prod_id());
		wmsInveTransaIncome.setWms_inve_pruduct_category_id(category.getWms_inve_pruduct_category_id());
		wmsInveTransaIncome.setId_card(protocol.getB_id_card());
		wmsInveTransaIncome.setCus_name(protocol.getB_name());
		wmsInveTransaIncome.setProduct_deadline_month(category.getCategory_deadline());
        wmsInveTransaIncome.setReturn_date(returnDate);
        wmsInveTransaIncome.setAct_return_date(actReturnDate);
		wmsInveTransaIncome.setProduct_account(setScale(protocol.getProduct_account()));
		wmsInveTransaIncome.setProduct_interest_account(setScale(income.add(award)));
        wmsInveTransaIncome.setPay_status(payStatus);
		wmsInveTransaIncome.setCreate_user_id(user.getUserId());
		wmsInveTransaIncome.setCreate_user_name(user.getRealname());
		wmsInveTransaIncome.setCreate_timestamp(new Timestamp(new Date().getTime()));
		wmsInveTransaIncome.setDays_of_calculation(daysOfCalculation);
		wmsInveTransaIncome.setBonus_rate(setScale(bonusRate));
		wmsInveTransaIncome.setIncome_rate(incomeRate);
		wmsInveTransaIncome.setPayable_reward_income(setScale(award));
		wmsInveTransaIncome.setPayable_basic_income(setScale(income));
		wmsInveTransaIncome.setExtend_income(BigDecimal.ZERO);
		wmsInveTransaIncome.setDays_extend_income(0);
        wmsInveTransaIncome.setWms_inve_customer_card_id(prod.getWms_inve_customer_card_id());
        // wmsInveTransaIncome.setRemarks("卓信" +
        // String.valueOf(DateUtil.getYearMonthInt(returnDate)) + "收益");
		wmsInveTransaIncome.setPay_type(PAY_TYPE_NORMAL);// 收益类型
		return wmsInveTransaIncome;
	}
    
    /**
     * @Title: generateTransaIncome
     * @Description: 生成收益信息
     * @param returnDate 应付收益日期
     * @param actReturnDate  实付收益日期
     * @param income 收益金额
     * @param bonus 奖励金额
     * @param incomeRate 收益计算利率
     * @param bonusRate 奖励计算利率
     * @param daysOfCalculation 收益天数
     * @param payStatus 支付状态
     * @param months 满月数量
     * @return 收益信息
     * @author: jinzhm
     * @time:2017年1月6日 下午2:18:34
     * history:
     * 1、2017年1月6日 jinzhm 创建方法
     */
    protected WmsInveTransaIncome generateTransaIncome(Date returnDate, Date actReturnDate, BigDecimal income,
                                                       BigDecimal bonus,
                                                       BigDecimal incomeRate, BigDecimal bonusRate,
                                                       int daysOfCalculation, String payStatus, int months)
    {
        // 生成收益信息
        WmsInveTransaIncome transaIncome = setWmsInveTransaIncome(new java.sql.Date(returnDate.getTime()),
                                                                  actReturnDate == null ? null
                                                                                       : new java.sql.Date(
                                                                                                           actReturnDate.getTime()),
                                                                  bonusRate, bonus, incomeRate, income,
                                                                  daysOfCalculation, payStatus, protocol, category,
                                                                  user);
        // 生成收益备注信息
        IncomeUtil.setTransaIncomeRemarks(transaIncome, bonusRate, returnDate, months, category);
        return transaIncome;
    }
    
	/**
	 * 设置收益备注信息
	 * 
	 * @param transaIncome 收益对象
	 * @param bonus 奖励金额
	 * @param date 收益应付时间
	 * @param months 满月数
	 * @return
	 *
	 * @author 金志明
	 * @date 2017年10月12日 下午1:47:05
	 */
    protected WmsInveTransaIncome setWmsInveTransaIncomeRemarks(WmsInveTransaIncome transaIncome, BigDecimal bonus,
                                                                Date date, int months)
    {
        // 如果有奖励收益
        if (bonus.compareTo(BigDecimal.ZERO) > 0)
        {
            // 当有奖励的时候备注中正常收益和奖励收益用逗号隔开
            transaIncome.setRemarks(DateUtil.date2String(date, "yyyy年MM月") + "客户正常收益" + ","
                                    + DateUtil.date2String(date, "yyyy年MM月") + category.getCategory_name() + "存满"
                                    + (months == 6 ? "半年" : months == 12 ? "一年" : months + "月") + "奖励收益");
        }
        // 如果没有奖励收益
        else
        {
            transaIncome.setRemarks(DateUtil.date2String(date, "yyyy年MM月") + "客户正常收益");
        }
        return transaIncome;
    }

	/**
	 * @Title: setWmsInveTransaLog
	 * @Description: 保存日志信息
	 * @return WmsInveTransaLog
	 * @throws
	 * @author lvtu
	 * @date 2015年8月27日 下午6:15:51
	 */
	protected WmsInveTransaLog setWmsInveTransaLog(Date changeDate, String operateItem, BigDecimal award,
			BigDecimal income, WmsInveTransaProtocol protocol, UserBean user)
	{
		WmsInveTransaLog wmsInveTransaLog = new WmsInveTransaLog();
		wmsInveTransaLog.setWms_inve_transa_id(protocol.getWms_inve_transa_id());
		wmsInveTransaLog.setChange_date(new java.sql.Date(changeDate.getTime()));
		wmsInveTransaLog.setOperate_item(operateItem);

		wmsInveTransaLog.setProduct_account(setScale(protocol.getProduct_account()));
		wmsInveTransaLog.setProduct_interest_account(setScale(income.add(award)));

        if (TRANSA_INCOME.equals(operateItem))
        {
            wmsInveTransaLog.setRemark(DateUtil.date2String(changeDate, "yyyy年MM月") + "客户收益");
        }

		wmsInveTransaLog.setCreate_user_id(user.getUserId());
		wmsInveTransaLog.setCreate_user_name(user.getRealname());
		wmsInveTransaLog.setCreate_timestamp(new Timestamp(new Date().getTime()));

		return wmsInveTransaLog;
	}
	
    /**
     * @Title: handleExtendRedeemData
     * @Description: 续期后赎回时处理老单据的收益信息
     *      主要处理：
     *          1. 当月收益未支付后全部赎回
     *          2. 当月收益未支付后部分赎回
     * @param wmsInveTransaIncome 收益信息
     * @author: jinzhm
     * @time:2016年12月5日 上午11:40:53
     * history:
     * 1、2016年12月5日 jinzhm 创建方法
     */
    protected void handleExtendRedeemData(WmsInveTransaIncome wmsInveTransaIncome)
    {
        // 如果数据集合中没有extendDate，说明不是续期过来的单据，不需要做任何处理，直接返回。
        if (extendDate == null)
        {
            return;
        }

        // 赎回时间
        Date redeemDate = (Date) dataMap.get("redeemDate");
        
        // 如果赎回时间和续期时间在同一个月中，进行老单据收益处理
        if (DateUtil.getLastDayOfMonth(extendDate).compareTo(DateUtil.getLastDayOfMonth(redeemDate)) == 0)
        {
            // 获得赎回信息
            WmsInveRedeem redeem = (WmsInveRedeem) dataMap.get("wmsInveRedeem");
            // 获得老单据收益信息
            WmsInveTransa oldTransa = IncomeUtil.getWmsInveTransaDao().get(transa.getOld_last_wms_inve_transa_id());
            // 老单据对应需要修改的收益信息集合
            List<WmsInveTransaIncome> oldUpdTransaIncomeList = handleOldTransaIncomeForRedeem(oldTransa,
                                                                                              redeemDate,
                                                                                              "1".equals(redeem.getIs_fully_redeemed()),
                                                                                              wmsInveTransaIncome);
            // 将需要修改老单据对应的收益信息保存到map中
            dataMap.put("oldUpdTransaIncomeList", oldUpdTransaIncomeList);
        }

    }

    /**
     * @Title: handleOldTransaIncomeForRedeem
     * @Description: 递归处理老单据收益信息（因为可能跨年续期）
     * @param transa 老单据收益信息
     * @param returnDate 应处理的收益应付日期
     * @param isFullRedeem 是否全部赎回
     * @param transaIncome 新单据收益信息
     * @return 要处理的收益信息集合
     * @author: jinzhm
     * @time:2017年1月13日 下午5:48:11
     * history:
     * 1、2017年1月13日 jinzhm 创建方法
     */
    private List<WmsInveTransaIncome> handleOldTransaIncomeForRedeem(WmsInveTransa transa, Date returnDate,
                                                                     boolean isFullRedeem,
                                                                     WmsInveTransaIncome transaIncome)
    {
        // 要修改的老单据收益信息
        List<WmsInveTransaIncome> oldUpdTransaIncomeList = new ArrayList<WmsInveTransaIncome>();

        // 获得老单据收益信息
        List<WmsInveTransaIncome> oldTransaIncomeList = IncomeUtil.getTransaIncomeList(transa.getWms_inve_transa_id());


        // 循环所有老单据收益信息
        for (WmsInveTransaIncome oldTransaIncome : oldTransaIncomeList)
        {
            // 如果是同年同月的收益信息
            if (DateUtil.getLastDayOfMonth(returnDate)
                        .compareTo(DateUtil.getLastDayOfMonth(oldTransaIncome.getReturn_date())) == 0)
            {
                // 如果是未支付状态收益需要处理
                if (IncomeUtil.isIncomeNotPaid(oldTransaIncome))
                {
                    // 如果是全部赎回
                    if (isFullRedeem)
                    {
                        // 老单据设置成已终止
                        oldTransaIncome.setPay_status(PAY_STATUS_TERMINATE);
                        // 将修改的收益保存到集合中
                        oldUpdTransaIncomeList.add(oldTransaIncome);
                    }
                    // 如果是部分赎回
                    else
                    {
                        // 如果是正常收益
                        if (PAY_TYPE_NORMAL.equals(oldTransaIncome.getPay_type()))
                        {
                            // 重新计算基本收益
                            BigDecimal income = IncomeUtil.computeIncome(oldTransaIncome.getDays_of_calculation(),
                                                                         DateUtil.getDaysOfMonth(oldTransaIncome.getReturn_date()),
                                                                         transaIncome.getProduct_account(),
                                                                         IncomeUtil.getCategoryReturnRate(category));
                            // 重新计算奖励
                            BigDecimal bonus = oldTransaIncome.getBonus_rate()
                                                              .divide(new BigDecimal(100), 8, RoundingMode.HALF_UP)
                                                              .multiply(transaIncome.getProduct_account());

                            // 如果上一个单据收益信息中有当月的公益，需要减去公益
                            if (IncomeUtil.isOldTransaIncomeHasPublicIncome(transa, returnDate))
                            {
                                // 获得上一个单据信息
                                WmsInveTransa oldTransa = IncomeUtil.getWmsInveTransaDao()
                                                                    .get(transa.getOld_last_wms_inve_transa_id());
                                // 递归调用处理
                                oldUpdTransaIncomeList.addAll(handleOldTransaIncomeForRedeem(oldTransa, returnDate,
                                                                                             isFullRedeem, transaIncome));
                                // 计算公益
                                BigDecimal publicIncome = IncomeUtil.computePublicIncome(oldTransaIncome.getDays_of_calculation(),
                                                                                         transaIncome.getProduct_account(),
                                                                                         IncomeUtil.getPublicProductReturnRate());
                                // 正常收益减去公益收益
                                income = income.subtract(publicIncome);
                            }

                            // 给老单据收益设置赎回后的投资金额
                            oldTransaIncome.setProduct_account(transaIncome.getProduct_account());
                            // 设置基本收益
                            oldTransaIncome.setPayable_basic_income(IncomeUtil.setScale(income));
                            // 设置奖励收益
                            oldTransaIncome.setPayable_reward_income(IncomeUtil.setScale(bonus));
                            // 设置总收益
                            oldTransaIncome.setProduct_interest_account(IncomeUtil.setScale(income.add(bonus)));
                        }
                        // 如果是公益收益
                        else if (PAY_TYPE_PUBLIC.equals(oldTransaIncome.getPay_type()))
                        {
                            // 重新计算公益收益
                            BigDecimal publicIncome = computePublicIncome(transaIncome.getProduct_account(),
                                                                          transaIncome.getDays_of_calculation(),
                                                                          IncomeUtil.getPublicProductReturnRate());
                            // 设置赎回后的投资金额
                            oldTransaIncome.setProduct_account(transaIncome.getProduct_account());
                            // 设置总收益
                            oldTransaIncome.setProduct_interest_account(IncomeUtil.setScale(publicIncome));
                            // 设置公益收益
                            oldTransaIncome.setExtend_income(IncomeUtil.setScale(publicIncome));
                            // 修改新单据的总收益金额（减去老单据对应月份的公益收益）
                            transaIncome.setProduct_interest_account(IncomeUtil.setScale(transaIncome.getProduct_interest_account()
                                                                                                     .subtract(publicIncome)));
                            // 修改新单据的基本收益金额（减去老单据对应月份的公益收益）
                            transaIncome.setPayable_basic_income(IncomeUtil.setScale(transaIncome.getPayable_basic_income()
                                                                                                 .subtract(publicIncome)));
                        }
                        oldUpdTransaIncomeList.add(oldTransaIncome);
                    }
                }
                // else if
                // (PAY_STATUS_EXTEND_NO_NEED_PAY.equals(oldTransaIncome.getPay_status()))
                // {
                // // 获得上一个单据信息
                // WmsInveTransa oldTransa = IncomeUtil.getWmsInveTransaDao()
                // .get(transa.getOld_last_wms_inve_transa_id());
                // // 递归调用处理
                // oldUpdTransaIncomeList.addAll(handleOldTransaIncomeForRedeem(oldTransa,
                // returnDate, isFullRedeem,
                // transaIncome));
                //
                // // 计算公益
                // BigDecimal publicIncome =
                // IncomeUtil.computePublicIncome(oldTransaIncome.getDays_extend_income(),
                // transaIncome.getProduct_account(),
                // IncomeUtil.getPublicProductReturnRate());
                // // 修改投资金额
                // oldTransaIncome.setProduct_account(transaIncome.getProduct_account());
                // // 修改总收益
                // oldTransaIncome.setProduct_interest_account(IncomeUtil.setScale(publicIncome));
                // // 修改公益收益
                // oldTransaIncome.setExtend_income(IncomeUtil.setScale(publicIncome));
                // oldUpdTransaIncomeList.add(oldTransaIncome);
                // }
            }
        }

        return oldUpdTransaIncomeList;
    }

    /**
     * 处理赎回时相关数据，并生成赎回收益信息及赎回交易日志
     * 
     * @param wmsInveTransaIncome 赎回月份的基本收益
     *
     * @author 金志明
     * @date 2017年10月12日 上午9:25:23
     */
	protected void handleAndGenerateRedeemIncome(WmsInveTransaIncome wmsInveTransaIncome)
	{
		WmsInveRedeem redeem = (WmsInveRedeem) dataMap.get("wmsInveRedeem");// 获得赎回信息
		Date redeemDate = DateUtil.getDate10((Date) dataMap.get("redeemDate"));// 赎回时间
		Date endDate = protocol.getEnd_of_date();
		
		// 当是全部赎回时，根据到期月份当月赎回，跨越赎回，未到期赎回等进行处理
        // 如果是全部赎回
		if ("1".equals(redeem.getIs_fully_redeemed()))
        {
            // 如果是到期月份全部赎回
			if(DateUtil.getLastDayOfMonth(redeemDate).compareTo(DateUtil.getLastDayOfMonth(endDate)) == 0)
            {
				/**
				 * 2016-07-01之前的单据，上单当日没有收益，赎回日有收益
				 * 2016-07-01（含）之后的单据，上单日有收益，赎回日没有收益
				 * 2016-05-10上单，2017-05-10结束，2017-05-10赎回是到期赎回
				 * 2016-07-10上单，2017-07-09结束，2017-07-09赎回是未到期赎回
				 */
                // 到期当月，到期或超期全部赎回
                if ((IncomeUtil.isLaterThanMagicDate(oldDateOfPayment) && redeemDate.compareTo(endDate) > 0)
                    || (!IncomeUtil.isLaterThanMagicDate(oldDateOfPayment) && redeemDate.compareTo(endDate) >= 0))
				{

                    // 判断该收益是否已经支付
                    // 未支付
                    if (!IncomeUtil.isIncomeAlreadyPaid(wmsInveTransaIncome))
                    {
                        /*
                         * 该收益已经支付的时候
                         * 1. 基本收益信息不动
                         * 2. 公益收益信息不动
                         * 3. 新增赎回收益信息
                         * 4. 新增赎回交易日志
                         * 5. 新增理财结束日志
                         * 该收益未支付的时候
                         * 1. 基本收益设置成已支付
                         * 2. 基本收益支付类型设置成赎回支付
                         * 3. 公益收益设置成已终止（该处理不在这里处理）
                         * 4. 新增赎回收益
                         * 5. 新增赎回日志
                         * 6. 新增理财结束
                         */
                        wmsInveTransaIncome.setPay_status(PAY_STATUS_ALREADY_PAY);
                        wmsInveTransaIncome.setPay_type(PAY_TYPE_REDEEM);
                        wmsInveTransaIncome.setAct_return_date(redeem.getRedeem_date());
                        wmsInveTransaIncome.setAct_return_date(new java.sql.Date(redeemDate.getTime()));
                        // 生成赎回收益信息及一些其他处理
                        dataMap.put("redeemIncome", generateRedeemIncome(0));
                    }
                    // 已支付后赎回
                    else
                    {
                        // 生成赎回收益信息及一些其他处理
                        dataMap.put("redeemIncome", generateRedeemIncome(1));
                    }
                    // 生成赎回交易日志
                    dataMap.put("redeemLog", generateRedeemLog(protocol.getWms_inve_transa_id(), 1));
                    // 理财结束日志
                    dataMap.put("transaEndLog", generateTransaLog(redeemDate, protocol, user, TRANSA_END));
				}
                // 到期当月，未到期全部赎回
				else
                {
                    // 该收益还未支付
                    if (!IncomeUtil.isIncomeAlreadyPaid(wmsInveTransaIncome))
                    {
                        /*
                         * 该收益已经支付
                         * 1. 基本收益不动
                         * 2. 公益收益不动
                         * 3. 新增赎回收益信息
                         * 4. 新增赎回日志
                         * 5. 新增理财结束
                         * 该收益没有支付
                         * 1. 修改基本收益为已终止
                         * 2. 公益收益设置为已终止
                         * 3. 新增赎回收益信息
                         * 4. 新增赎回日志
                         * 5. 新增理财结束
                         */
                        wmsInveTransaIncome.setPay_status(PAY_STATUS_TERMINATE);
                    }
                    // 生成赎回收益信息及一些其他处理
                    dataMap.put("redeemIncome", generateRedeemIncome(2));
                    // 生成赎回交易日志
                    dataMap.put("redeemLog", generateRedeemLog(protocol.getWms_inve_transa_id(), 2));
                    // 理财结束日志
                    dataMap.put("transaEndLog", generateTransaLog(redeemDate, protocol, user, TRANSA_END));
				}
			}
            // 如果赎回月份大于到期月份（到期跨月全部赎回）
			else if(DateUtil.getLastDayOfMonth(redeemDate).compareTo(DateUtil.getLastDayOfMonth(endDate)) > 0)
            {
                // 如果收益信息没有支付
                if (!IncomeUtil.isIncomeAlreadyPaid(wmsInveTransaIncome))
                {
                    /*
                     * 如果收益信息已经支付
                     * 1. 公益收益不动
                     * 2. 新增赎回收益
                     * 3. 新增赎回日志
                     * 4. 新增理财结束
                     * 如果收益信息没有支付
                     * 1. 公益收益设置已终止
                     * 2. 新增赎回收益
                     * 3. 新增赎回日志
                     * 4. 新增理财结束
                     */
                    wmsInveTransaIncome.setPay_status(PAY_STATUS_TERMINATE);
                    /**
                     * jinzhm 2017-04-19 添加
                     * 公益收益只给到期后一个月。
                     * 如4月5日到期，公益收益给到5月5日。
                     * 2016-07-01之前上单单据，赎回当天有收益，5月5日或之后赎回，5天公益收益设置成赎回收益已支付，且给几天的央行活期利率；5月赎回，日期小于5号，公益收益设置成已终止，给几天赎回公益收益
                     * 2016-07-01之后上单单据，赎回当天无收益，5月5日之后赎回，5天公益收益设置成赎回收益已支付，且给几天央行活期利率；5月赎回，日期小于等于5号，公益收益设置成已终止，给几天赎回公益收益
                     */
                    // 如果到期日期+1个月后的月末最后一天等于赎回日期的月末最后一天，说明到期月份+1个月=赎回月份
                    if(DateUtil.getLastDayOfMonth(DateUtils.addMonths(endDate, 1)).compareTo(DateUtil.getLastDayOfMonth(redeemDate)) == 0)
                    {
                        /*
                         * 判断是2016-07-01之后（含）单据，且赎回日期大于到期日期一个月后的日期
                         * 或
                         * 判断是2016-07-01之前单据，且赎回日期大于等于到期日期一个月后的日期
                         */
                        if ((IncomeUtil.isLaterThanMagicDate(oldDateOfPayment) && redeemDate.compareTo(DateUtils.addMonths(endDate,
                                                                                                                           1)) > 0)
                            || (!IncomeUtil.isLaterThanMagicDate(oldDateOfPayment) && redeemDate.compareTo(DateUtils.addMonths(endDate,
                                                                                                                               1)) >= 0))
                        {
                            // 公益收益需要设置成已支付的赎回收益
                            wmsInveTransaIncome.setPay_status(PAY_STATUS_ALREADY_PAY);
                            wmsInveTransaIncome.setPay_type(PAY_TYPE_REDEEM);
                            wmsInveTransaIncome.setAct_return_date(redeem.getRedeem_date());
                            wmsInveTransaIncome.setAct_return_date(new java.sql.Date(redeemDate.getTime()));
                            // 生成赎回收益信息及一些其他处理
                            dataMap.put("redeemIncome", generateRedeemIncome(6));
                        }
                        else
                        {
                            // 公益收益设置成已终止
                            wmsInveTransaIncome.setPay_status(PAY_STATUS_TERMINATE);
                            // 生成赎回收益信息及一些其他处理
                            dataMap.put("redeemIncome", generateRedeemIncome(3));
                        }
                    }
                }
                // 生成赎回交易日志
                dataMap.put("redeemLog", generateRedeemLog(protocol.getWms_inve_transa_id(), 3));
                // 理财结束日志
                dataMap.put("transaEndLog", generateTransaLog(redeemDate, protocol, user, TRANSA_END));
			}
            // 未到期全部赎回
			else
            {
                /*
                 * 如果该收益是已支付收益
                 * 1. 基本收益不动
                 * 2. 新增赎回收益
                 * 3. 新增赎回日志
                 * 4. 新增理财结束
                 * 如果该收益是未支付收益
                 * 1. 基本收益设置成已支付
                 * 2. 新增赎回收益
                 * 3. 新增赎回日志
                 * 4. 新增理财结束
                 */
                // 如果是未支付收益
                if (!IncomeUtil.isIncomeAlreadyPaid(wmsInveTransaIncome))
                {
                    wmsInveTransaIncome.setPay_status(PAY_STATUS_TERMINATE);
                }
                // 生成赎回收益信息及一些其他处理
                dataMap.put("redeemIncome", generateRedeemIncome(4));
                // 生成赎回交易日志
                dataMap.put("redeemLog", generateRedeemLog(protocol.getWms_inve_transa_id(), 4));
                // 理财结束日志
                dataMap.put("transaEndLog", generateTransaLog(redeemDate, protocol, user, TRANSA_END));
			}
		}
        // 如果是部分赎回
		else
        {
			/*
			 * 1. 新增一条赎回收益信息
			 * 2. 新增一条赎回交易日志
			 */
			dataMap.put("redeemIncome", generateRedeemIncome(5));//生成赎回收益信息及一些其他处理
            dataMap.put("redeemLog", generateRedeemLog(protocol.getWms_inve_transa_id(), 5));// 生成赎回交易日志
		}
	}
	
	/**
	 * 生成理财结束日志
	 * 
	 * @return
	 *
	 * @author 金志明
	 * @date 2017年10月12日 上午8:27:50
	 */
	protected WmsInveTransaLog generateTransaLog(Date endDate, WmsInveTransaProtocol protocol, UserBean user, String operateItem)
	{
		WmsInveTransaLog log = new WmsInveTransaLog();
		log.setOperate_item(operateItem);//理财结束
		log.setChange_date(new java.sql.Date(endDate.getTime()));
		log.setWms_inve_transa_id(protocol.getWms_inve_transa_id());
		log.setProduct_account(protocol.getProduct_account());
		log.setProduct_interest_account(BigDecimal.ZERO);
		log.setCreate_user_id(user.getUserId());
		log.setCreate_user_name(user.getRealname());
		log.setCreate_timestamp(new Timestamp(new Date().getTime()));
		return log;
	}

	/**
	 * 生成赎回交易日志
	 * 
	 * @return
	 *
	 * @author 金志明
	 * @date 2017年10月12日 上午8:23:52
	 */
    protected WmsInveTransaLog generateRedeemLog(Integer transaId, int flag)
	{
		WmsInveRedeemDetail redeemDetail = (WmsInveRedeemDetail) dataMap.get("wmsInveRedeemDetail");
		WmsInveRedeem redeem = (WmsInveRedeem) dataMap.get("wmsInveRedeem");
		
		// 新增一条赎回的日志
		WmsInveTransaLog wmsInveTransaLog = new WmsInveTransaLog();
        wmsInveTransaLog.setWms_inve_transa_id(transaId);// 上单信息表主键
		wmsInveTransaLog.setProduct_account(redeemDetail.getRedeem_amount());// 本金变化

        // 已付收益
        BigDecimal paidIncome = redeemDetail.getPaid_income() == null ? BigDecimal.ZERO : redeemDetail.getPaid_income();

        BigDecimal payableBasicIncome = redeemDetail.getPayable_basic_income() == null ? BigDecimal.ZERO
                                                                                      : redeemDetail.getPayable_basic_income();
        BigDecimal payableRewardIncome = redeemDetail.getPayable_reward_income() == null ? BigDecimal.ZERO
                                                                                        : redeemDetail.getPayable_reward_income();
        BigDecimal extendIncome = redeemDetail.getExtend_income() == null ? BigDecimal.ZERO
                                                                         : redeemDetail.getExtend_income();

        switch (flag)
        {
            case 1:
                // 到期月份赎回，且赎回日期大于等于到期日期
                wmsInveTransaLog.setProduct_interest_account(payableBasicIncome.add(payableRewardIncome)
                                                                               .add(extendIncome).subtract(paidIncome));// 收益变化
                break;
            case 2:
                // 到期月份赎回，且赎回日期小于到期日期
                wmsInveTransaLog.setProduct_interest_account(payableBasicIncome.add(payableRewardIncome)
                                                                               .subtract(paidIncome));// 收益变化
                break;
            case 3:
                // 赎回月份大于到期月份（跨越赎回）
                wmsInveTransaLog.setProduct_interest_account(extendIncome.add(redeemDetail.getCurrent_income()).subtract(paidIncome));// 收益变化
                break;
            case 4:
                // 未到期且不是到期月份全部赎回
                wmsInveTransaLog.setProduct_interest_account(payableBasicIncome.add(payableRewardIncome)
                                                                               .subtract(paidIncome));// 收益变化
                break;
            case 5:
                // 部分赎回
                wmsInveTransaLog.setProduct_interest_account(payableBasicIncome.add(payableRewardIncome)
                                                                               .subtract(paidIncome));// 收益变化
                break;
        }
		wmsInveTransaLog.setOperate_item(CountIncome.TRANSA_REDEEM);// 操作事项
		wmsInveTransaLog.setChange_date(redeem.getRedeem_date());// 变化日期
		wmsInveTransaLog.setRemark("赎回单据&lt;" + redeem.getBill_code() + "&gt;");
		wmsInveTransaLog.setCreate_user_id(user.getUserId());// 创建人
		wmsInveTransaLog.setCreate_user_name(user.getRealname());// 创建人姓名
		wmsInveTransaLog.setCreate_timestamp(new Timestamp(new Date().getTime()));// 创建时间
		return wmsInveTransaLog;
	}

	/**
	 * 生成赎回收益信息
	 * 
	 * @param flag
	 *            生成标记
     *              0 = 到期月份赎回，且赎回日期大于等于到期日期 未支付状态下赎回
     *              1 = 到期月份赎回，且赎回日期大于等于到期日期 已支付状态下赎回
	 *            	2 = 到期月份赎回，且赎回日期小于到期日期
	 *              3 = 赎回月份大于到期月份（跨越赎回）
	 *              4 = 未到期且不是到期月份的全部赎回
	 *              5 = 部分赎回
	 * @return
	 * 
	 * @author 金志明
	 * @date 2017年10月12日 上午8:09:20
	 */
	protected WmsInveTransaIncome generateRedeemIncome(int flag)
	{
		WmsInveRedeemDetail redeemDetail = (WmsInveRedeemDetail) dataMap.get("wmsInveRedeemDetail");
		WmsInveRedeem redeem = (WmsInveRedeem) dataMap.get("wmsInveRedeem");

		WmsInveTransaIncome redeemIncome = new WmsInveTransaIncome();
		redeemIncome.setWms_inve_transa_id(redeemDetail.getWms_inve_transa_id());// 上单信息表主键
		redeemIncome.setWms_inve_transa_prod_id(redeemDetail.getWms_inve_transa_prod_id());// 上单产品信息表主键
		redeemIncome.setWms_inve_pruduct_category_id(category.getWms_inve_pruduct_category_id());// 产品信息主键
		redeemIncome.setId_card(protocol.getB_id_card());// 客户身份证号
		redeemIncome.setCus_name(protocol.getB_name());// 客户名称
		redeemIncome.setProduct_deadline_month(category.getCategory_deadline());// 产品期限
		redeemIncome.setReturn_date(redeem.getRedeem_date());// 应支付时间
		redeemIncome.setProduct_account(redeemDetail.getRedeem_amount());// 赎回金额

        // 已付收益
        BigDecimal paidIncome = redeemDetail.getPaid_income() == null ? BigDecimal.ZERO : redeemDetail.getPaid_income();
		
        switch (flag)
        {
            case 0:
                // 未打款赎回时，赎回收益就是公益收益-已付收益
                redeemIncome.setProduct_interest_account(redeemDetail.getExtend_income().subtract(paidIncome));
                break;
            case 1:
                // 到期月份赎回，且赎回日期大于等于到期日期，应付收益 = 赎回时的公益收益 - 已付金额
                redeemIncome.setProduct_interest_account(redeemDetail.getPayable_basic_income()
                                                                     .add(redeemDetail.getPayable_reward_income())
                                                                     .add(redeemDetail.getExtend_income())
                                                                     .subtract(paidIncome));
                break;
            case 2:
                // 到期月份赎回，且赎回日期小于到期日期，应付收益 = 赎回收益 - 已付金额
                redeemIncome.setProduct_interest_account(redeemDetail.getPayable_basic_income()
                                                                     .add(redeemDetail.getPayable_reward_income())
                                                                     .subtract(paidIncome));
                break;
            case 3:
                // 赎回月份大于到期月份（跨越赎回），应付收益 = 赎回金额 = 公益收益 + 活期收益 - 已付金额
                redeemIncome.setProduct_interest_account(redeemDetail.getExtend_income().add(redeemDetail.getCurrent_income()).subtract(paidIncome));
                break;
            case 4:
                // 未到期且不是到期月份全部赎回，应付收益=赎回收益 - 已付金额
                redeemIncome.setProduct_interest_account(redeemDetail.getPayable_basic_income()
                                                                     .add(redeemDetail.getPayable_reward_income())
                                                                     .subtract(paidIncome));
                break;
            case 5:
                // 部分赎回，应付收益=赎回收益 - 已付金额
                redeemIncome.setProduct_interest_account(redeemDetail.getPayable_basic_income()
                                                                     .add(redeemDetail.getPayable_reward_income())
                                                                     .subtract(paidIncome));
                break;
            case 6:
                // 超期赎回，且赎回日期大于到期日期+1个月; 应付收益=活期收益-已付收益
                redeemIncome.setProduct_interest_account(redeemDetail.getCurrent_income().subtract(paidIncome));
        }

		redeemIncome.setAct_return_date(redeem.getRedeem_date());// 实际支付时间
		redeemIncome.setPay_status(PAY_STATUS_ALREADY_PAY);// 支付状态为已支付
		redeemIncome.setCreate_user_id(user.getUserId());// 创建人
		redeemIncome.setCreate_user_name(user.getRealname());
		redeemIncome.setCreate_timestamp(new Timestamp(new Date().getTime()));// 创建时间
		redeemIncome.setDays_of_calculation(redeemDetail.getDays_of_basic_income() == null ? 0 : redeemDetail.getDays_of_basic_income());// 赎回收益天数
		redeemIncome.setBonus_rate(redeemDetail.getReward_income_rate() == null ? BigDecimal.ZERO : redeemDetail.getReward_income_rate());// 奖励利率
		redeemIncome.setPayable_reward_income(redeemDetail.getPayable_reward_income() == null ? BigDecimal.ZERO : redeemDetail.getPayable_reward_income());// 应付奖励收益
		redeemIncome.setPayable_basic_income(redeemDetail.getPayable_basic_income() == null ? BigDecimal.ZERO : redeemDetail.getPayable_basic_income());// 应付基本收益
		redeemIncome.setExtend_income(redeemDetail.getExtend_income() == null ? BigDecimal.ZERO : redeemDetail.getExtend_income());// 公益收益
		redeemIncome.setDays_extend_income(redeemDetail.getDays_extend_income() == null ? 0 : redeemDetail.getDays_extend_income());// 公益收益计算天数
		redeemIncome.setIncome_rate(redeemDetail.getBasic_inceom_rate() == null ? BigDecimal.ZERO : redeemDetail.getBasic_inceom_rate());// 基本收益利率
		redeemIncome.setPay_type(PAY_TYPE_REDEEM);// 收益类型
		// 活期收益
		redeemIncome.setCurrent_income(redeemDetail.getCurrent_income() == null ? BigDecimal.ZERO : redeemDetail.getCurrent_income());
		// 活期收益利率
		redeemIncome.setCurrent_income_rate(redeemDetail.getCurrent_income_rate() == null ? BigDecimal.ZERO : redeemDetail.getCurrent_income_rate());
		// 活期收益天数
		redeemIncome.setDays_current_income(redeemDetail.getDays_current_income() == null ? 0 : redeemDetail.getDays_current_income());
        // 设置客户收益卡主键
        redeemIncome.setWms_inve_customer_card_id(prod.getWms_inve_customer_card_id());
		return redeemIncome;
	}
	
	/**
	 * @Title: setScale
	 * @Description: 四舍五入保留8位小数
	 * @param bigDecimal
	 * @return BigDecimal
	 * @throws
	 * @author lvtu
	 * @date 2015年9月10日 下午4:07:23
	 */
	protected BigDecimal setScale(BigDecimal bigDecimal)
	{
        return IncomeUtil.setScale(bigDecimal);
	}

    /**
     * @Title: getPublicIncome
     * @Description: 计算公益收益
     * @param productAccount 投资金额
     * @param publicIncomeDays 公益收益天数
     * @param publicRate 公益收益利率
     * @return 公益收益
     * @author: jinzhm
     * @time:2016年12月2日 下午3:25:22
     * history:
     * 1、2016年12月2日 jinzhm 创建方法
     */
    protected BigDecimal computePublicIncome(BigDecimal productAccount, int publicIncomeDays, BigDecimal publicRate)
    {
        return new BigDecimal(publicIncomeDays).divide(new BigDecimal(365), 8, RoundingMode.HALF_UP)
                                               .multiply(productAccount).multiply(publicRate);
    }

    /**
     * @Title: getBonusReturnRate
     * @Description: 为各产品返回奖励利率，具体获得在子类中重写此方法实现
     * @param dataMap 获得奖励利率需要用到的数据集合
     * @return 返回0
     * @author: jinzhm
     * @time:2017年1月6日 下午2:37:33
     * @see com.zx.emanage.inve.util.CountIncomeGetBonusReturnRateInterface#getBonusReturnRate(java.util.Map)
     * history:
     * 1、2017年1月6日 jinzhm 创建方法
    */
    @Override
    public BigDecimal getBonusReturnRate(Map<String, Object> dataMap)
    {
        return BigDecimal.ZERO;
    }

    /**
     * @Title: handleTransaIncomeForNewExtend
     * @Description: 为新产品在做预约续期时处理收益
     *      新产品续期只能是到期日前才可以预约续期或续期，因此不需要考虑跨月等情况
     *      新产品续期如果当月收益已经支付也不能进行续期或预约续期，也不考虑这种情况
     *      
     *      新产品续期（调用此方法都是新产品续期），上单日期是2016-07-01之前单据
     *      需要将公益收益设置成已终止
     *      需要将正常收益-1天
     * @param transaId 上单单据id
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年1月6日 下午3:12:42
     * @see com.zx.emanage.inve.util.CountIncome#handleTransaIncomeForNewExtend(int)
     * history:
     * 1、2017年1月6日 jinzhm 创建方法
    */
    @Override
    public void handleTransaIncomeForNewExtend(int transaId, UserBean user)
    {
        // 获得产品正常收益+公益收益信息
        List<WmsInveTransaIncome> transaIncomeList = IncomeUtil.getTransaIncomeList(transaId);
        // 获得产品到期时间
        Date endDate = IncomeUtil.getTransaProtocol(transaId).getEnd_of_date();

        // 循环公益收益集合
        for (WmsInveTransaIncome transaIncome : transaIncomeList)
        {
            // 收益应付时间月份和到期时间月份相同
            if (DateUtil.getLastDayOfMonth(transaIncome.getReturn_date())
                        .compareTo(DateUtil.getLastDayOfMonth(DateUtil.getDate10(endDate))) == 0)
            {
                // 如果是正常收益
                if (PAY_TYPE_NORMAL.equals(transaIncome.getPay_type()))
                {
                    // 如果是未支付状态
                    if (IncomeUtil.isIncomeNotPaid(transaIncome))
                    {
                        // 获得上单表信息
                        WmsInveTransa transa = IncomeUtil.getWmsInveTransaDao().get(transaId);
                        // 获得原始上单时间
                        Date oldDateOfPayment = transa.getOld_date_of_payment() == null ? transa.getDate_of_payment() : transa.getOld_date_of_payment();
                        // 如果原是上单时间小于2016-07-01
                        if (!IncomeUtil.isLaterThanMagicDate(oldDateOfPayment))
                        {
                            // 收益天数减一天后重新计算收益信息
                            BigDecimal income = IncomeUtil.computeIncome(transaIncome.getDays_of_calculation() - 1,
                                                                         DateUtil.getDaysOfMonth(transaIncome.getReturn_date()),
                                                                         transaIncome.getProduct_account(),
                                                                         transaIncome.getIncome_rate());
                            // 计算奖励收益
                            BigDecimal bonus = IncomeUtil.computeBonusIncome(transaIncome.getBonus_rate(),
                                                                             transaIncome.getProduct_account());

                            // 设置要修改信息
                            transaIncome.setPayable_basic_income(IncomeUtil.setScale(income));
                            transaIncome.setDays_of_calculation(transaIncome.getDays_of_calculation() - 1);
                            transaIncome.setProduct_interest_account(IncomeUtil.setScale(income.add(bonus)));
                            IncomeUtil.setTransaIncomeUpdateData(transaIncome, user);
                            IncomeUtil.getWmsInveTransaIncomeDao().update(transaIncome);
                        }
                    }
                }
                // 如果是公益收益
                else if (PAY_TYPE_PUBLIC.equals(transaIncome.getPay_type()))
                {
                    // 如果该收益是未支付状态
                    if (IncomeUtil.isIncomeNotPaid(transaIncome))
                    {
                        // 设置成已终止
                        transaIncome.setPay_status(PAY_STATUS_TERMINATE);
                        IncomeUtil.setTransaIncomeUpdateData(transaIncome, user);
                        IncomeUtil.getWmsInveTransaIncomeDao().update(transaIncome);
                    }
                }
            }
        }
    }

    /**
     * @Title: handleTransaIncomeForCancelNewExtend
     * @Description: 为新产品在做取消预约续期时处理公益收益
     *      新产品续期只能是到期日前才可以预约续期或续期，因此不需要考虑跨月等情况
     *      新产品续期如果当月收益已经支付也不能进行续期或预约续期，也不考虑这种情况
     *      
     *      新产品续期（调用此方法都是新产品续期），上单日期是2016-07-01之前单据
     *      需要将公益收益设置成未支付
     *      需要将正常收益+1天
     *      如果正常收益已打款，需要将公益收益+1天，已支付正常收益不变
     * @param transaId 上单表主键id
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年1月6日 下午3:12:42
     * @see com.zx.emanage.inve.util.CountIncome#handleTransaIncomeForCancelNewExtend(int)
     * history:
     * 1、2017年1月6日 jinzhm 创建方法
    */
    @Override
    public void handleTransaIncomeForCancelNewExtend(int transaId, UserBean user)
    {
        // 获得产品公益收益信息
        List<WmsInveTransaIncome> transaIncomeList = IncomeUtil.getTransaIncomeList(transaId);
        // 获得产品到期时间
        Date endDate = IncomeUtil.getTransaProtocol(transaId).getEnd_of_date();

        WmsInveTransaIncome nIncome = null;
        WmsInveTransaIncome pIncome = null;
        // 循环公益收益集合
        for (WmsInveTransaIncome transaIncome : transaIncomeList)
        {
            // 收益应付时间月份和到期时间月份相同
            if (DateUtil.getLastDayOfMonth(transaIncome.getReturn_date())
                        .compareTo(DateUtil.getLastDayOfMonth(DateUtil.getDate10(endDate))) == 0)
            {
                // 如果是正常收益
                if (PAY_TYPE_NORMAL.equals(transaIncome.getPay_type()))
                {
                    nIncome = transaIncome;
                }
                // 如果是公益收益
                else if (PAY_TYPE_PUBLIC.equals(transaIncome.getPay_type()))
                {
                    pIncome = transaIncome;
                }
            }
        }

        // 是不是2016-07-01之前上单的单据标记
        boolean flag = false;
        // 获得上单表信息
        WmsInveTransa transa = IncomeUtil.getWmsInveTransaDao().get(transaId);
        // 获得原始上单时间
        Date oldDateOfPayment = transa.getOld_date_of_payment() == null ? transa.getDate_of_payment()
                                                                       : transa.getOld_date_of_payment();
        // 如果原是上单时间小于2016-07-01
        if (!IncomeUtil.isLaterThanMagicDate(oldDateOfPayment))
        {
            flag = true;
        }

        // 如果正常收益已经支付
        if (IncomeUtil.isIncomeAlreadyPaid(nIncome))
        {
            // 如果有公益收益
            if (pIncome != null)
            {
                // 是2016-07-01之前上单
                if (flag)
                {
                    // 公益收益天数+1后重新计算公益收益
                    BigDecimal publicIncome = IncomeUtil.computePublicIncome(pIncome.getDays_extend_income() + 1,
                                                                             pIncome.getProduct_account(),
                                                                             IncomeUtil.getPublicProductReturnRate());
                    // 设置要修改信息并修改
                    pIncome.setProduct_interest_account(IncomeUtil.setScale(publicIncome));
                    pIncome.setExtend_income(IncomeUtil.setScale(publicIncome));
                    pIncome.setDays_extend_income(pIncome.getDays_extend_income() + 1);
                    pIncome.setPay_status(PAY_STATUS_NOT_PAY);
                    IncomeUtil.setTransaIncomeUpdateData(pIncome, user);
                    IncomeUtil.getWmsInveTransaIncomeDao().update(pIncome);
                }
                // 不是2016-07-01之前上单
                else
                {
                    // 设置成未支付
                    pIncome.setPay_status(PAY_STATUS_NOT_PAY);
                    IncomeUtil.setTransaIncomeUpdateData(pIncome, user);
                    IncomeUtil.getWmsInveTransaIncomeDao().update(pIncome);
                }
            }
        }
        // 如果正常收益未支付
        else
        {
            // 是新产品（2016-07-01之前上单）
            if (flag)
            {
                // 收益天数减一天后重新计算收益信息
                BigDecimal income = IncomeUtil.computeIncome(nIncome.getDays_of_calculation() + 1,
                                                             DateUtil.getDaysOfMonth(nIncome.getReturn_date()),
                                                             nIncome.getProduct_account(), nIncome.getIncome_rate());
                // 计算奖励收益
                BigDecimal bonus = IncomeUtil.computeBonusIncome(nIncome.getBonus_rate(), nIncome.getProduct_account());

                // 设置要修改信息
                nIncome.setPayable_basic_income(IncomeUtil.setScale(income));
                nIncome.setDays_of_calculation(nIncome.getDays_of_calculation() + 1);
                nIncome.setProduct_interest_account(IncomeUtil.setScale(income.add(bonus)));
                IncomeUtil.setTransaIncomeUpdateData(nIncome, user);
                IncomeUtil.getWmsInveTransaIncomeDao().update(nIncome);
            }

            // 如果有公益收益
            if (pIncome != null)
            {
                // 设置成未支付
                pIncome.setPay_status(PAY_STATUS_NOT_PAY);
                IncomeUtil.setTransaIncomeUpdateData(pIncome, user);
                IncomeUtil.getWmsInveTransaIncomeDao().update(pIncome);
            }
        }
    }

    /**
     * @Title: getEndCalendar
     * @Description: 续期时获得循环结束时间，用作生成收益
     * @param endDate 理财结束日期
     * @param oldTransaIncomeList 老单据收益信息
     * @return 循环结束时间
     * @author: jinzhm
     * @time:2017年1月12日 下午2:35:15
     * history:
     * 1、2017年1月12日 jinzhm 创建方法
     */
    protected Calendar getEndCalendar(Date endDate, List<WmsInveTransaIncome> oldTransaIncomeList)
    {
        Calendar endCal = Calendar.getInstance();
        // 如果到期时间的月份小于老单据最后一条收益的收益应付日期的月份
        if (DateUtil.getLastDayOfMonth(endDate)
                    .compareTo(DateUtil.getLastDayOfMonth(oldTransaIncomeList.get(oldTransaIncomeList.size() - 1)
                                                                             .getReturn_date())) < 0)
        {
            // 返回老单据最后一天收益应付日期的月末最后一天
            endCal.setTime(DateUtil.getLastDayOfMonth(oldTransaIncomeList.get(oldTransaIncomeList.size() - 1)
                                                                         .getReturn_date()));
        }
        else
        {
            // 返回理财到期时间
            endCal.setTime(endDate);
        }
        return endCal;
    }

    /**
     * @Title: getFullMonthBonusReturnRate
     * @Description: 获得奖励利率
     * @param month 满月数量
     * @return 奖励利率
     * @author: jinzhm
     * @time:2017年1月12日 下午3:57:42
     * history:
     * 1、2017年1月12日 jinzhm 创建方法
     */
    protected abstract BigDecimal getFullMonthBonusReturnRate(int month);

    /**
     * @Title: getFullMonthBonusReturnRate
     * @Description: 获得奖励利率
     * @param month 满月数量
     * @param rebateWayList 月付奖励设置信息集合
     * @return 奖励利率
     * @author: jinzhm
     * @time:2017年2月21日 下午1:19:54
     * history:
     * 1、2017年2月21日 jinzhm 创建方法
     */
    protected abstract BigDecimal getFullMonthBonusReturnRate(int month, List<WmsInvePruductRebateWay> rebateWayList);
    
    /**
     * @Title: computeIncome
     * @Description: 计算某个产品投资多少金额时的收益情况
     * @param categoryId 产品id
     * @param productAccount 投资金额（单位：万元）
     * @return 返回收益情况
     * @author: jinzhm
     * @time:2017年2月21日 下午1:02:05
     * @see com.zx.emanage.inve.util.CountIncome#computeIncome(int, java.math.BigDecimal, java.util.Date, java.util.Date)
     * history:
     * 1、2017年2月21日 jinzhm 创建方法
    */
    @Override
    public List<Map<String, Object>> computeIncome(int categoryId, BigDecimal productAccount, Date startDate,
                                                   Date endDate)
    {
        // 获得产品信息
        WmsInvePruductCategory category = IncomeUtil.getWmsInvePruductCategoryDao().get(categoryId);
        // 产品奖励利率设置信息集合
        List<WmsInvePruductRebateWay> rebateWayList = IncomeUtil.getWmsInvePruductRebateWayList(categoryId);
        // 收益情况集合
        List<Map<String, Object>> rMapList = new ArrayList<Map<String, Object>>();
        // 产品利率
        BigDecimal categoryReturnRate = IncomeUtil.getCategoryReturnRate(IncomeUtil.getWmsInvePruductCategoryDao()
                                                                                   .get(categoryId));

        int months = 0;// 相差月数
        int incomeDays = 0;// 每月收益天数
        int dayOfMonth = 0;// 当月实际天数

        BigDecimal bonusReturnRate = BigDecimal.ZERO;// 满月奖励利率（%）
        BigDecimal bonus = BigDecimal.ZERO;// 应收奖励金额
        BigDecimal income = BigDecimal.ZERO;// 应收收益金额
        BigDecimal totalIncome = BigDecimal.ZERO;// 每月计算后的总收益
        // 客户总应得收益
        BigDecimal totalDueIncome = productAccount
                                               .multiply(category.getCategory_return_rate())
                                               .multiply(new BigDecimal(category.getCategory_deadline()))
                                               .divide(new BigDecimal(12), 8, RoundingMode.HALF_UP)
                                               .divide(new BigDecimal(100), 8, RoundingMode.HALF_UP)
                                               .setScale(2, RoundingMode.UP);

        Calendar cal = Calendar.getInstance();
        // cal设置理财支付日期，cal用作循环
        cal.setTime(startDate);
        Calendar calEnd = Calendar.getInstance();
        // cal设置理财结束日期
        calEnd.setTime(endDate);

        Map<String, Object> incomeMap = null;

        while (cal.compareTo(calEnd) <= 0)
        {
            incomeMap = new HashMap<String, Object>();
            // 如果是循环日期和理财结束日期是同年同月，说明循环到了最后一个月
            if (cal.get(Calendar.YEAR) == calEnd.get(Calendar.YEAR)
                && cal.get(Calendar.MONTH) == calEnd.get(Calendar.MONTH))
            {
                // 最后一个月收益天数直接获得月初到结束日期的天数
                incomeDays = calEnd.get(Calendar.DAY_OF_MONTH);
                // 最后一个月满月数和产品期限相同
                months = category.getCategory_deadline();
                // 最后一个月返利日期是结束日期
                incomeMap.put("returnDate", endDate);
            }
            else
            {
                // 如果循环没有到最后一个月，收益天数直接获得cal(每次循环都将cal移到下一个1号)当前时间到月末的天数
                // DateUtil.getDaysOfInterval方法获取当前日期到月末的天数时，不包含当天。
                // 判断循环是否在处理第一个月
                if (cal.getTime().compareTo(startDate) == 0)
                {
                    incomeDays = DateUtil.getDaysOfInterval(cal.getTime());
                    // 如果是第一月，需要判断是否是2016-07-01号或之后上单
                    if (IncomeUtil.isLaterThanMagicDate(startDate))
                    {
                        // 如果是2016-07-01或之后上单，第一月收益天数需要+1
                        incomeDays += 1;
                    }
                }
                else
                {
                    // 如果不是第一月或最后一个月，其他月份收益天数直接取当月天数
                    incomeDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                    // 不是最后一个月，且也不是第一个月的话，月数+1。
                    months++;
                }
                // 如果循环没有到最后一个月，返利日期就是当月最后一天
                incomeMap.put("returnDate", DateUtil.getLastDayOfMonth(cal.getTime()));
            }
            dayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);// 获得当月实际天数

            // 收益 = 收益天数 / 当月实际天数 * 金额 * （年华收益利率 / 100 ）
            income = IncomeUtil.computeIncome(incomeDays, dayOfMonth, productAccount, categoryReturnRate);

            // 获得奖励利率
            bonusReturnRate = getFullMonthBonusReturnRate(months, rebateWayList);
            // 计算奖励金额
            bonus = bonusReturnRate.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO
                                                                   : IncomeUtil.computeBonusIncome(bonusReturnRate,
                                                                                                   productAccount);
            // 如果是最后一个月收益，收益像下取整
            if (months == category.getCategory_deadline())
            {
                incomeMap.put("dueIncome", IncomeUtil.setScaleDown(income));
                // 如果实际按月计算后的总收益不等于客户应得收益时，最后一个月收益为总收益减去前面所有月份收益
                if (totalIncome.add(IncomeUtil.setScaleDown(income)).compareTo(totalDueIncome) != 0)
                {
                    incomeMap.put("dueIncome",
                    		totalDueIncome.subtract(totalIncome));
                }
            }
            else
            {
                incomeMap.put("dueIncome", IncomeUtil.setScale(income));
                totalIncome = totalIncome.add(IncomeUtil.setScale(income));
            }

            incomeMap.put("bonusIncome", IncomeUtil.setScale(bonus));

            rMapList.add(incomeMap);

            // 将日期移到下个月1号
            cal.setTime(DateUtil.getFirstDayOfNextMonth(cal.getTime()));
        }


        return rMapList;
    }

}
