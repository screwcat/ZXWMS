package com.zx.emanage.inve.util;

import com.zx.emanage.inve.persist.WmsInvePruductCategoryDao;
import com.zx.emanage.inve.persist.WmsInveTransaProdDao;
import com.zx.emanage.inve.util.transa.SignHelper;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.sframe.util.GlobalVal;

/**
 * 收益计算及交易日志生成对象工程类
 * 
 * @author 金志明
 * @date 2016年9月29日 下午4:46:32
 */
public class CountIncomeFactory
{

	/**
	 * 根据理财协议对象返回收益计算及交易日志生成接口</br>
	 * 通过理财协议对象中的上单产品主键查询产品信息，根据产品信息的付息方式及奖励方式返回具体的收益计算及交易日志生成对象
	 * 
	 * @param protocol 理财协议对象
	 * @return 收益计算及交易日志生成接口对象
	 *
	 * @author 金志明
	 * @date 2016年9月29日 下午4:52:58
	 */
	public static CountIncome getCountIncome(WmsInveTransaProtocol protocol)
	{
		WmsInvePruductCategoryDao categoryDao = (WmsInvePruductCategoryDao) GlobalVal.ctx
				.getBean("wmsInvePruductCategoryDao");// 获得理财产品dao对象
		WmsInveTransaProdDao prodDao = (WmsInveTransaProdDao) GlobalVal.ctx
				.getBean("wmsInveTransaProdDao");// 获得上单产品dao对象

		WmsInvePruductCategory category = categoryDao.get(prodDao.get(
				protocol.getWms_inve_transa_prod_id())
				.getWms_inve_pruduct_category_id());// 获得产品信息
		
        return getCountIncome(category.getWms_inve_pruduct_category_id());
	}

    /**
     * 
     * @Title: getCountIncome
     * @Description: 
     * 根据理财协议对象返回收益计算及交易日志生成接口</br>
     * 通过理财协议对象中的上单产品主键查询产品信息，根据产品信息的付息方式及奖励方式返回具体的收益计算及交易日志生成对象
     * @param categoryId 产品id
     * @return 收益计算及交易日志生成接口对象
     * @author 金志明
     * @time:2017年2月21日 下午1:35:35
     * history:
     * 1、2017年2月21日 jinzhm 创建方法
     */
    public static CountIncome getCountIncome(int categoryId)
    {
        if (categoryId == SignHelper.PTP_CATEGORY_ID)
        {
            return new CountIncomeForPTP();
        }

        CountIncome countIncome = null;

        WmsInvePruductCategory category = IncomeUtil.getWmsInvePruductCategoryDao().get(categoryId);

        if (category.getCategory_interest_pay_method() == 1)
        {// 付息方式：月付
            if (category.getCategory_rebate_way() == 1)
            {// 奖励方式：满月
                countIncome = new CountIncomeForFullMonth();
            }
            else if (category.getCategory_rebate_way() == 2)
            {// 奖励方式：累计存量
                countIncome = new CountIncomeForTotal();
            }
        }
        else if (category.getCategory_interest_pay_method() == 2)
        {// 付息方式：年付
            countIncome = new CountIncomeForFullYear();
        }
        return countIncome;
    }

}
