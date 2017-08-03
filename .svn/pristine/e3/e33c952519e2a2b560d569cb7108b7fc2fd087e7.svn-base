package com.zx.emanage.loanappro.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerChangeHeadDao;
import com.zx.emanage.cremanage.persist.WmsCusCustomerChangeChildDao;
import com.zx.emanage.loanappro.service.IWmsCreCreditApproModelService;
import com.zx.emanage.loanappro.vo.WmsCreCreditApproModelScoreVO;
import com.zx.emanage.loanreview.persist.WmsCreRevCertificateModelDao;
import com.zx.emanage.loanreview.persist.WmsCreRevPhoneContactDao;
import com.zx.emanage.loanreview.persist.WmsCreRevPhoneMainDao;
import com.zx.emanage.loanreview.persist.WmsCreRevWaterModelDao;
import com.zx.emanage.sysmanage.persist.WmsSysDictDataDao;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead;
import com.zx.emanage.util.gen.entity.WmsCreRevCertificateModel;
import com.zx.emanage.util.gen.entity.WmsCreRevPhoneContact;
import com.zx.emanage.util.gen.entity.WmsCreRevPhoneMain;
import com.zx.emanage.util.gen.entity.WmsCreRevWaterModel;
import com.zx.emanage.util.gen.entity.WmsCusCustomerChangeChild;

@Service("wmscrecreditappromodelService")
public class WmsCreCreditApproModelServiceImpl implements IWmsCreCreditApproModelService
{

    @Autowired
    private WmsSysDictDataDao wmsSysDictDataDao;

    @Autowired
    private WmsCreCreditLineCustomerChangeHeadDao wmsCreCreditLineCustomerChangeHeadDao;

    @Autowired
    private WmsCusCustomerChangeChildDao wmsCusCustomerChangeChildDao;

    @Autowired
    private WmsCreRevCertificateModelDao wmsCreRevCertificateModelDao;

    @Autowired
    private WmsCreCreditHeadDao wmsCreCreditHeadDao;

    @Autowired
    private WmsCreRevPhoneMainDao wmsCreRevPhoneMainDao;

    @Autowired
    private WmsCreRevPhoneContactDao wmsCreRevPhoneContactDao;

    @Autowired
    private WmsCreRevWaterModelDao wmsCreRevWaterModelDao;

    /**
     * 根据获取的客户信息和四个审核组的信息，得到打分明细信息
     * 
     * @param WmsCreCreditApproModelScoreVO wmsCreCreditApproModelScoreVO
     * @author 张风山
     */
    @Override
    public java.math.BigDecimal score(WmsCreCreditApproModelScoreVO wmsCreCreditApproModelScoreVO)
    {
        java.math.BigDecimal s1;
        java.math.BigDecimal s2;

        s1 = wmsCreCreditApproModelScoreVO.getDkze().add(wmsCreCreditApproModelScoreVO.getDkzbs())
                                          .add(wmsCreCreditApproModelScoreVO.getYq3())
                                          .add(wmsCreCreditApproModelScoreVO.getYq6())
                                          .add(wmsCreCreditApproModelScoreVO.getYq2n())
                                          .add(wmsCreCreditApproModelScoreVO.getDqyq())
                                          .add(wmsCreCreditApproModelScoreVO.getDbje())
                                          .add(wmsCreCreditApproModelScoreVO.getY3nsq())
                                          .add(wmsCreCreditApproModelScoreVO.getY6nsq())
                                          .add(wmsCreCreditApproModelScoreVO.getN1nsq())
                                          .add(wmsCreCreditApproModelScoreVO.getZsxed())
                                          .add(wmsCreCreditApproModelScoreVO.getZs())
                                          .add(wmsCreCreditApproModelScoreVO.getZdsxed());

        if (s1.compareTo(new BigDecimal(255)) == 0)
        {
            s1 = new BigDecimal(127.5);
        }

        s2 = s1.add(wmsCreCreditApproModelScoreVO.getAge()).add(wmsCreCreditApproModelScoreVO.getHunyin())
               .add(wmsCreCreditApproModelScoreVO.getXingbie()).add(wmsCreCreditApproModelScoreVO.getEdu())
               .add(wmsCreCreditApproModelScoreVO.getDwxz()).add(wmsCreCreditApproModelScoreVO.getGznx())
               .add(wmsCreCreditApproModelScoreVO.getGzgw()).add(wmsCreCreditApproModelScoreVO.getSjhy())
               .add(wmsCreCreditApproModelScoreVO.getSjhysj()).add(wmsCreCreditApproModelScoreVO.getFcmj())
               .add(wmsCreCreditApproModelScoreVO.getFcwz()).add(wmsCreCreditApproModelScoreVO.getCcsz())
               .add(wmsCreCreditApproModelScoreVO.getCcygsj()).add(wmsCreCreditApproModelScoreVO.getDklx())
               .add(wmsCreCreditApproModelScoreVO.getQspj()).add(wmsCreCreditApproModelScoreVO.getTspypj())
               .add(wmsCreCreditApproModelScoreVO.getBrsz()).add(wmsCreCreditApproModelScoreVO.getQssz())
               .add(wmsCreCreditApproModelScoreVO.getFqdc()).add(wmsCreCreditApproModelScoreVO.getFmdc())
               .add(wmsCreCreditApproModelScoreVO.getZndc()).add(wmsCreCreditApproModelScoreVO.getDkyt())
               .add(wmsCreCreditApproModelScoreVO.getZxqsth()).add(wmsCreCreditApproModelScoreVO.getPxqsth())
               .add(wmsCreCreditApproModelScoreVO.getTspyth());

        return s2;
    }

    /**
     * 根据贷款主表id获取的客户信息和四个审核组的信息
     * 
     * @param wms_cre_credit_head_id
     * @author 张风山
     */
    @Override
    public WmsCreCreditApproModelScoreVO getScoreInfo(int wms_cre_credit_head_id)
    {
        WmsCreCreditApproModelScoreVO wmsCreCreditApproModelScoreVO = new WmsCreCreditApproModelScoreVO();
        wmsCreCreditApproModelScoreVO = wmsSysDictDataDao.getDictDataById(wms_cre_credit_head_id).get(0);
        WmsCreCreditLineCustomerChangeHead wmsCreCreditLineCustomerChangeHead = new WmsCreCreditLineCustomerChangeHead();
        wmsCreCreditLineCustomerChangeHead = wmsCreCreditLineCustomerChangeHeadDao.selectScoreInfo(wms_cre_credit_head_id)
                                                                                  .get(0);
        // 获取征信模型表信息
        WmsCreRevCertificateModel wmsCreRevCertificateModel = new WmsCreRevCertificateModel();
        wmsCreRevCertificateModel.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        List<WmsCreRevCertificateModel> wmsCreRevCertificateModelList = wmsCreRevCertificateModelDao.getListByEntity(wmsCreRevCertificateModel);
        WmsCreRevCertificateModel mcrcm = wmsCreRevCertificateModelList.get(0);
        // 获取年龄打分值
        Calendar nowcalendar = Calendar.getInstance();
        int year = nowcalendar.get(Calendar.YEAR);
        nowcalendar.setTimeInMillis(wmsCreCreditLineCustomerChangeHead.getBirthday().getTime());
        int year2 = nowcalendar.get(Calendar.YEAR);
        int age = year - year2;
        if (age >= 20 && age <= 30)
        {
            wmsCreCreditApproModelScoreVO.setAge(new BigDecimal(5));
        }
        else if (age >= 31 && age <= 45)
        {
            wmsCreCreditApproModelScoreVO.setAge(new BigDecimal(8));
        }
        else if (age >= 46 && age <= 55)
        {
            wmsCreCreditApproModelScoreVO.setAge(new BigDecimal(6));
        }
        else if (age >= 55 && age <= 65)
        {
            wmsCreCreditApproModelScoreVO.setAge(new BigDecimal(3));
        }
        else
        {
            wmsCreCreditApproModelScoreVO.setAge(new BigDecimal(0));
        }

        // 获取婚姻情况打分值
        WmsCusCustomerChangeChild wmsCusCustomerChangeChild = new WmsCusCustomerChangeChild();
        wmsCusCustomerChangeChild.setWms_cre_credit_line_customer_change_head_id(wmsCreCreditLineCustomerChangeHead.getWms_cre_credit_line_customer_change_head_id());
        List<WmsCusCustomerChangeChild> wmsCusCustomerChangeChildList = wmsCusCustomerChangeChildDao.getListByEntity(wmsCusCustomerChangeChild);
        if (wmsCreCreditLineCustomerChangeHead.getHas_married().equals("yh"))
        {
            if (wmsCusCustomerChangeChildList.size() > 0)
            {
                wmsCreCreditApproModelScoreVO.setHunyin(new BigDecimal(14));
            }
            else
            {
                wmsCreCreditApproModelScoreVO.setHunyin(new BigDecimal(12));
            }
        }
        else if (wmsCreCreditLineCustomerChangeHead.getHas_married().equals("wh"))
        {
            wmsCreCreditApproModelScoreVO.setHunyin(new BigDecimal(10));
        }
        else if (wmsCreCreditLineCustomerChangeHead.getHas_married().equals("so"))
        {
            wmsCreCreditApproModelScoreVO.setHunyin(new BigDecimal(8));
        }
        else if (wmsCreCreditLineCustomerChangeHead.getHas_married().equals("ly"))
        {
            if (wmsCusCustomerChangeChildList.size() > 0)
            {
                wmsCreCreditApproModelScoreVO.setHunyin(new BigDecimal(6));
            }
            else
            {
                wmsCreCreditApproModelScoreVO.setHunyin(new BigDecimal(3));
            }
        }

        // 获取性别打分值
        if (wmsCreCreditLineCustomerChangeHead.getGender().equals("1"))
        {
            wmsCreCreditApproModelScoreVO.setXingbie(new BigDecimal(2));
        }
        else
        {
            wmsCreCreditApproModelScoreVO.setXingbie(new BigDecimal(3));
        }

        // 获取最高学历打分值
        if (wmsCreCreditLineCustomerChangeHead.getMax_degree().equals("5"))
        {
            wmsCreCreditApproModelScoreVO.setEdu(new BigDecimal(5));
        }
        else if (wmsCreCreditLineCustomerChangeHead.getMax_degree().equals("4"))
        {
            wmsCreCreditApproModelScoreVO.setEdu(new BigDecimal(4));
        }
        else if (wmsCreCreditLineCustomerChangeHead.getMax_degree().equals("3"))
        {
            wmsCreCreditApproModelScoreVO.setEdu(new BigDecimal(3));
        }
        else if (wmsCreCreditLineCustomerChangeHead.getMax_degree().equals("2"))
        {
            wmsCreCreditApproModelScoreVO.setEdu(new BigDecimal(2));
        }
        else if (wmsCreCreditLineCustomerChangeHead.getMax_degree().equals("1"))
        {
            wmsCreCreditApproModelScoreVO.setEdu(new BigDecimal(1));
        }

        // 获取贷款类型（贷款客户数量）打分值
        if (wmsCreCreditLineCustomerChangeHeadDao.getCount(wms_cre_credit_head_id) == 1)
        {
            wmsCreCreditApproModelScoreVO.setDklx(new BigDecimal(4));
        }
        else if (wmsCreCreditLineCustomerChangeHeadDao.getCount(wms_cre_credit_head_id) == 2)
        {
            wmsCreCreditApproModelScoreVO.setDklx(new BigDecimal(7));
        }
        else
        {
            wmsCreCreditApproModelScoreVO.setDklx(new BigDecimal(10));
        }

        // 获取贷款总额打分值
        if (mcrcm.getUnpay_loan_amount().compareTo(new BigDecimal(0)) == 0)
        {
            wmsCreCreditApproModelScoreVO.setDkze(new BigDecimal(60));
        }
        else if (mcrcm.getUnpay_loan_amount().compareTo(new BigDecimal(0)) > 0
                 && mcrcm.getUnpay_loan_amount().compareTo(new BigDecimal(100000)) < 0)
        {
            wmsCreCreditApproModelScoreVO.setDkze(new BigDecimal(30));
        }
        else if (mcrcm.getUnpay_loan_amount().compareTo(new BigDecimal(100000)) > 0
                 && mcrcm.getUnpay_loan_amount().compareTo(new BigDecimal(300000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setDkze(new BigDecimal(28));
        }
        else if (mcrcm.getUnpay_loan_amount().compareTo(new BigDecimal(300000)) > 0
                 && mcrcm.getUnpay_loan_amount().compareTo(new BigDecimal(500000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setDkze(new BigDecimal(26));
        }
        else if (mcrcm.getUnpay_loan_amount().compareTo(new BigDecimal(500000)) > 0
                 && mcrcm.getUnpay_loan_amount().compareTo(new BigDecimal(1000000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setDkze(new BigDecimal(22));
        }
        else if (mcrcm.getUnpay_loan_amount().compareTo(new BigDecimal(1000000)) > 0
                 && mcrcm.getUnpay_loan_amount().compareTo(new BigDecimal(1500000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setDkze(new BigDecimal(20));
        }
        else if (mcrcm.getUnpay_loan_amount().compareTo(new BigDecimal(1500000)) > 0
                 && mcrcm.getUnpay_loan_amount().compareTo(new BigDecimal(2000000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setDkze(new BigDecimal(18));
        }
        else if (mcrcm.getUnpay_loan_amount().compareTo(new BigDecimal(2000000)) > 0
                 && mcrcm.getUnpay_loan_amount().compareTo(new BigDecimal(3000000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setDkze(new BigDecimal(14));
        }
        else if (mcrcm.getUnpay_loan_amount().compareTo(new BigDecimal(3000000)) > 0
                 && mcrcm.getUnpay_loan_amount().compareTo(new BigDecimal(5000000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setDkze(new BigDecimal(12));
        }
        else
        {
            wmsCreCreditApproModelScoreVO.setDkze(new BigDecimal(10));
        }

        // 获取贷款总笔数打分值
        if (mcrcm.getTotal_loan_num() >= 0 && mcrcm.getTotal_loan_num() <= 3)
        {
            wmsCreCreditApproModelScoreVO.setDkzbs(new BigDecimal(10));
        }
        else if (mcrcm.getTotal_loan_num() > 3 && mcrcm.getTotal_loan_num() <= 6)
        {
            wmsCreCreditApproModelScoreVO.setDkzbs(new BigDecimal(8));
        }
        else if (mcrcm.getTotal_loan_num() > 6 && mcrcm.getTotal_loan_num() <= 10)
        {
            wmsCreCreditApproModelScoreVO.setDkzbs(new BigDecimal(6));
        }
        else if (mcrcm.getTotal_loan_num() > 10)
        {
            wmsCreCreditApproModelScoreVO.setDkzbs(new BigDecimal(4));
        }
        else
        {
            wmsCreCreditApproModelScoreVO.setDkzbs(new BigDecimal(0));
        }

        // 获取逾期3月内打分值
        if (mcrcm.getThree_overdue_card_num() == 0)
        {
            wmsCreCreditApproModelScoreVO.setYq3(new BigDecimal(35));
        }
        else if (mcrcm.getThree_overdue_card_num() > 0 && mcrcm.getThree_overdue_card_num() <= 3)
        {
            wmsCreCreditApproModelScoreVO.setYq3(new BigDecimal(30));
        }
        else if (mcrcm.getThree_overdue_card_num() > 3 && mcrcm.getThree_overdue_card_num() <= 6)
        {
            wmsCreCreditApproModelScoreVO.setYq3(new BigDecimal(25));
        }
        else if (mcrcm.getThree_overdue_card_num() > 6 && mcrcm.getThree_overdue_card_num() <= 10)
        {
            wmsCreCreditApproModelScoreVO.setYq3(new BigDecimal(20));
        }
        else if (mcrcm.getThree_overdue_card_num() > 10)
        {
            wmsCreCreditApproModelScoreVO.setYq3(new BigDecimal(10));
        }
        else
        {
            wmsCreCreditApproModelScoreVO.setYq3(new BigDecimal(0));
        }

        // 获取逾期6月内打分值
        if (mcrcm.getSix_overdue_card_num() == 0)
        {
            wmsCreCreditApproModelScoreVO.setYq6(new BigDecimal(25));
        }
        else if (mcrcm.getSix_overdue_card_num() > 0 && mcrcm.getSix_overdue_card_num() <= 3)
        {
            wmsCreCreditApproModelScoreVO.setYq6(new BigDecimal(20));
        }
        else if (mcrcm.getSix_overdue_card_num() > 3 && mcrcm.getSix_overdue_card_num() <= 6)
        {
            wmsCreCreditApproModelScoreVO.setYq6(new BigDecimal(15));
        }
        else if (mcrcm.getSix_overdue_card_num() > 6 && mcrcm.getSix_overdue_card_num() <= 10)
        {
            wmsCreCreditApproModelScoreVO.setYq6(new BigDecimal(10));
        }
        else if (mcrcm.getSix_overdue_card_num() > 10)
        {
            wmsCreCreditApproModelScoreVO.setYq6(new BigDecimal(5));
        }
        else
        {
            wmsCreCreditApproModelScoreVO.setYq6(new BigDecimal(0));
        }

        // 获取逾期2年内打分值
        if (mcrcm.getTwo_year_overdue_card_num() == 0)
        {
            wmsCreCreditApproModelScoreVO.setYq2n(new BigDecimal(20));
        }
        else if (mcrcm.getTwo_year_overdue_card_num() > 0 && mcrcm.getTwo_year_overdue_card_num() <= 3)
        {
            wmsCreCreditApproModelScoreVO.setYq2n(new BigDecimal(16));
        }
        else if (mcrcm.getTwo_year_overdue_card_num() > 3 && mcrcm.getTwo_year_overdue_card_num() <= 6)
        {
            wmsCreCreditApproModelScoreVO.setYq2n(new BigDecimal(13));
        }
        else if (mcrcm.getTwo_year_overdue_card_num() > 6 && mcrcm.getTwo_year_overdue_card_num() <= 10)
        {
            wmsCreCreditApproModelScoreVO.setYq2n(new BigDecimal(10));
        }
        else if (mcrcm.getTwo_year_overdue_card_num() > 10)
        {
            wmsCreCreditApproModelScoreVO.setYq2n(new BigDecimal(5));
        }
        else
        {
            wmsCreCreditApproModelScoreVO.setYq2n(new BigDecimal(0));
        }

        // 获取当期逾期总额打分值
        if (mcrcm.getCur_overdue_card_amount().compareTo(new BigDecimal(0)) == 0)
        {
            wmsCreCreditApproModelScoreVO.setDqyq(new BigDecimal(20));
        }
        else if (mcrcm.getCur_overdue_card_amount().compareTo(new BigDecimal(0)) > 0
                 && mcrcm.getCur_overdue_card_amount().compareTo(new BigDecimal(1000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setDqyq(new BigDecimal(16));
        }
        else if (mcrcm.getCur_overdue_card_amount().compareTo(new BigDecimal(1000)) > 0
                 && mcrcm.getCur_overdue_card_amount().compareTo(new BigDecimal(3000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setDqyq(new BigDecimal(12));
        }
        else if (mcrcm.getCur_overdue_card_amount().compareTo(new BigDecimal(3000)) > 0
                 && mcrcm.getCur_overdue_card_amount().compareTo(new BigDecimal(5000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setDqyq(new BigDecimal(8));
        }
        else if (mcrcm.getCur_overdue_card_amount().compareTo(new BigDecimal(5000)) > 0
                 && mcrcm.getCur_overdue_card_amount().compareTo(new BigDecimal(8000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setDqyq(new BigDecimal(5));
        }
        else if (mcrcm.getCur_overdue_card_amount().compareTo(new BigDecimal(8000)) > 0)
        {
            wmsCreCreditApproModelScoreVO.setDqyq(new BigDecimal(2));
        }
        else
        {
            wmsCreCreditApproModelScoreVO.setDqyq(new BigDecimal(0));
        }

        // 获取3个月内申请次数打分值
        if (mcrcm.getThree_apply_time() >= 0 && mcrcm.getThree_apply_time() < 1)
        {
            wmsCreCreditApproModelScoreVO.setY3nsq(new BigDecimal(20));
        }
        else if (mcrcm.getThree_apply_time() >= 1 && mcrcm.getThree_apply_time() <= 5)
        {
            wmsCreCreditApproModelScoreVO.setY3nsq(new BigDecimal(15));
        }
        else if (mcrcm.getThree_apply_time() > 5 && mcrcm.getThree_apply_time() <= 10)
        {
            wmsCreCreditApproModelScoreVO.setY3nsq(new BigDecimal(10));
        }
        else if (mcrcm.getThree_apply_time() > 10)
        {
            wmsCreCreditApproModelScoreVO.setY3nsq(new BigDecimal(5));
        }
        else
        {
            wmsCreCreditApproModelScoreVO.setY3nsq(new BigDecimal(0));
        }

        // 获取6个月内申请次数打分值
        if (mcrcm.getSix_apply_time() >= 0 && mcrcm.getSix_apply_time() < 1)
        {
            wmsCreCreditApproModelScoreVO.setY6nsq(new BigDecimal(10));
        }
        else if (mcrcm.getSix_apply_time() >= 1 && mcrcm.getSix_apply_time() <= 5)
        {
            wmsCreCreditApproModelScoreVO.setY6nsq(new BigDecimal(8));
        }
        else if (mcrcm.getSix_apply_time() > 5 && mcrcm.getSix_apply_time() <= 10)
        {
            wmsCreCreditApproModelScoreVO.setY6nsq(new BigDecimal(6));
        }
        else if (mcrcm.getSix_apply_time() > 10)
        {
            wmsCreCreditApproModelScoreVO.setY6nsq(new BigDecimal(4));
        }
        else
        {
            wmsCreCreditApproModelScoreVO.setY6nsq(new BigDecimal(0));
        }

        // 获取1年内申请次数打分值
        if (mcrcm.getYear_apply_time() >= 0 && mcrcm.getYear_apply_time() < 1)
        {
            wmsCreCreditApproModelScoreVO.setN1nsq(new BigDecimal(10));
            ;
        }
        else if (mcrcm.getYear_apply_time() >= 1 && mcrcm.getYear_apply_time() <= 5)
        {
            wmsCreCreditApproModelScoreVO.setN1nsq(new BigDecimal(8));
        }
        else if (mcrcm.getYear_apply_time() > 5 && mcrcm.getYear_apply_time() <= 10)
        {
            wmsCreCreditApproModelScoreVO.setN1nsq(new BigDecimal(6));
        }
        else if (mcrcm.getYear_apply_time() > 10)
        {
            wmsCreCreditApproModelScoreVO.setN1nsq(new BigDecimal(4));
        }
        else
        {
            wmsCreCreditApproModelScoreVO.setN1nsq(new BigDecimal(0));
        }

        // 总授信额度
        if (mcrcm.getCredit_card_total_amount().compareTo(new BigDecimal(0)) > 0
            && mcrcm.getCredit_card_total_amount().compareTo(new BigDecimal(10000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setZsxed(new BigDecimal(1));
        }
        else if (mcrcm.getCredit_card_total_amount().compareTo(new BigDecimal(10000)) > 0
                 && mcrcm.getCredit_card_total_amount().compareTo(new BigDecimal(30000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setZsxed(new BigDecimal(2));
        }
        else if (mcrcm.getCredit_card_total_amount().compareTo(new BigDecimal(30000)) > 0
                 && mcrcm.getCredit_card_total_amount().compareTo(new BigDecimal(50000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setZsxed(new BigDecimal(3));
        }
        else if (mcrcm.getCredit_card_total_amount().compareTo(new BigDecimal(50000)) > 0
                 && mcrcm.getCredit_card_total_amount().compareTo(new BigDecimal(100000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setZsxed(new BigDecimal(4));
        }
        else if (mcrcm.getCredit_card_total_amount().compareTo(new BigDecimal(100000)) > 0
                 && mcrcm.getCredit_card_total_amount().compareTo(new BigDecimal(150000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setZsxed(new BigDecimal(5));
        }
        else if (mcrcm.getCredit_card_total_amount().compareTo(new BigDecimal(150000)) > 0
                 && mcrcm.getCredit_card_total_amount().compareTo(new BigDecimal(200000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setZsxed(new BigDecimal(6));
        }
        else if (mcrcm.getCredit_card_total_amount().compareTo(new BigDecimal(200000)) > 0
                 && mcrcm.getCredit_card_total_amount().compareTo(new BigDecimal(300000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setZsxed(new BigDecimal(7));
        }
        else if (mcrcm.getCredit_card_total_amount().compareTo(new BigDecimal(300000)) > 0
                 && mcrcm.getCredit_card_total_amount().compareTo(new BigDecimal(500000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setZsxed(new BigDecimal(8));
        }
        else if (mcrcm.getCredit_card_total_amount().compareTo(new BigDecimal(500000)) > 0)
        {
            wmsCreCreditApproModelScoreVO.setZsxed(new BigDecimal(10));
        }
        else
        {
            wmsCreCreditApproModelScoreVO.setZsxed(new BigDecimal(0));
        }

        // 获取信用卡张数打分值
        if (mcrcm.getCredit_card_num() >= 1 && mcrcm.getCredit_card_num() <= 3)
        {
            wmsCreCreditApproModelScoreVO.setZs(new BigDecimal(5));
        }
        else if (mcrcm.getCredit_card_num() > 3 && mcrcm.getCredit_card_num() <= 5)
        {
            wmsCreCreditApproModelScoreVO.setZs(new BigDecimal(3));
        }
        else if (mcrcm.getCredit_card_num() > 5)
        {
            wmsCreCreditApproModelScoreVO.setZs(new BigDecimal(1));
        }
        else
        {
            wmsCreCreditApproModelScoreVO.setZs(new BigDecimal(0));
        }

        // 获取最大授信额度
        if (mcrcm.getCredit_card_most_amount().compareTo(new BigDecimal(0)) > 0
            && mcrcm.getCredit_card_most_amount().compareTo(new BigDecimal(10000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setZdsxed(new BigDecimal(1));
        }
        else if (mcrcm.getCredit_card_most_amount().compareTo(new BigDecimal(10000)) > 0
                 && mcrcm.getCredit_card_most_amount().compareTo(new BigDecimal(30000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setZdsxed(new BigDecimal(3));
        }
        else if (mcrcm.getCredit_card_most_amount().compareTo(new BigDecimal(30000)) > 0
                 && mcrcm.getCredit_card_most_amount().compareTo(new BigDecimal(50000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setZdsxed(new BigDecimal(4));
        }
        else if (mcrcm.getCredit_card_most_amount().compareTo(new BigDecimal(50000)) > 0
                 && mcrcm.getCredit_card_most_amount().compareTo(new BigDecimal(100000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setZdsxed(new BigDecimal(5));
        }
        else if (mcrcm.getCredit_card_most_amount().compareTo(new BigDecimal(100000)) > 0
                 && mcrcm.getCredit_card_most_amount().compareTo(new BigDecimal(150000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setZdsxed(new BigDecimal(6));
        }
        else if (mcrcm.getCredit_card_most_amount().compareTo(new BigDecimal(150000)) > 0
                 && mcrcm.getCredit_card_most_amount().compareTo(new BigDecimal(200000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setZdsxed(new BigDecimal(7));
        }
        else if (mcrcm.getCredit_card_most_amount().compareTo(new BigDecimal(200000)) > 0
                 && mcrcm.getCredit_card_most_amount().compareTo(new BigDecimal(300000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setZdsxed(new BigDecimal(8));
        }
        else if (mcrcm.getCredit_card_most_amount().compareTo(new BigDecimal(300000)) > 0
                 && mcrcm.getCredit_card_most_amount().compareTo(new BigDecimal(500000)) <= 0)
        {
            wmsCreCreditApproModelScoreVO.setZdsxed(new BigDecimal(9));
        }
        else if (mcrcm.getCredit_card_most_amount().compareTo(new BigDecimal(500000)) > 0)
        {
            wmsCreCreditApproModelScoreVO.setZdsxed(new BigDecimal(10));
        }
        else
        {
            wmsCreCreditApproModelScoreVO.setZdsxed(new BigDecimal(0));
        }
        return wmsCreCreditApproModelScoreVO;
    }

    /**
     * modify
     * 
     * @param WmsCreCreditApproModelScoreVO wmsCreCreditApproModelScoreVO
     * @author 张风山
     */
    @Override
    public java.math.BigDecimal modify(int wms_cre_credit_head_id)
    {
        WmsCreCreditHead wmsCreCreditHead = wmsCreCreditHeadDao.get(wms_cre_credit_head_id);
        WmsCreCreditApproModelScoreVO WmsCreCreditApproModelScoreVO = getScoreInfo(wms_cre_credit_head_id);
        WmsCreCreditLineCustomerChangeHead wmsCreCreditLineCustomerChangeHead = new WmsCreCreditLineCustomerChangeHead();
        wmsCreCreditLineCustomerChangeHead = wmsCreCreditLineCustomerChangeHeadDao.selectScoreInfo(wms_cre_credit_head_id)
                                                                                  .get(0);
        // 获取电审基础数据
        WmsCreRevPhoneMain wmsCreRevPhoneMain = new WmsCreRevPhoneMain();
        wmsCreRevPhoneMain.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        wmsCreRevPhoneMain.setWms_cre_credit_line_customer_change_head_id(wmsCreCreditLineCustomerChangeHead.getWms_cre_credit_line_customer_change_head_id());
        List<WmsCreRevPhoneMain> wmsCreRevPhoneMainList = wmsCreRevPhoneMainDao.getListByEntity(wmsCreRevPhoneMain);
        WmsCreRevPhoneMain mcrpm = wmsCreRevPhoneMainList.get(0);
        // 获取电审主贷人联系人信息
        WmsCreRevPhoneContact wmsCreRevPhoneContact = new WmsCreRevPhoneContact();
        wmsCreRevPhoneContact.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        wmsCreRevPhoneContact.setWms_cre_credit_line_customer_change_head_id(wmsCreCreditLineCustomerChangeHead.getWms_cre_credit_line_customer_change_head_id());
        List<WmsCreRevPhoneContact> wmsCreRevPhoneContactList = wmsCreRevPhoneContactDao.getListByEntity(wmsCreRevPhoneContact);
        // 获取征信审核重要数据
        WmsCreRevCertificateModel wmsCreRevCertificateModel = new WmsCreRevCertificateModel();
        wmsCreRevCertificateModel.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        List<WmsCreRevCertificateModel> wmsCreRevCertificateModelList = wmsCreRevCertificateModelDao.getListByEntity(wmsCreRevCertificateModel);
        WmsCreRevCertificateModel mcrcm = wmsCreRevCertificateModelList.get(0);
        // 获取流水审核重要数据
        WmsCreRevWaterModel wmsCreRevWaterModel = new WmsCreRevWaterModel();
        wmsCreRevWaterModel.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        List<WmsCreRevWaterModel> wmsCreRevWaterModelList = wmsCreRevWaterModelDao.getListByEntity(wmsCreRevWaterModel);
        WmsCreRevWaterModel mcrwm = wmsCreRevWaterModelList.get(0);

        java.math.BigDecimal p = new BigDecimal(0);

        if (wmsCreCreditHead != null
            && (wmsCreCreditHead.getCre_loan_type() == 111 || wmsCreCreditHead.getCre_loan_type() == 110))
        {
            int a = 0;
            if (WmsCreCreditApproModelScoreVO.getFqdc().compareTo(new BigDecimal(10)) == 0)
            {
                a = a + 1;
            }
            if (WmsCreCreditApproModelScoreVO.getFmdc().compareTo(new BigDecimal(3)) == 0)
            {
                a = a + 1;
            }
            if (WmsCreCreditApproModelScoreVO.getZndc().compareTo(new BigDecimal(3)) == 0)
            {
                a = a + 1;
            }
            if (a > 2)
            {
                p = p.subtract(new BigDecimal(0.5));
            }
            else if (a > 0)
            {
                p = p.subtract(new BigDecimal(0.2));
            }
            else
            {
                p = p;
            }

            if (Integer.parseInt(mcrpm.getOther_loan_num()) > 3)
            {
                p = p.subtract(new BigDecimal(1));
            }
            else if (Integer.parseInt(mcrpm.getOther_loan_num()) == 3)
            {
                p = p.subtract(new BigDecimal(0.5));
            }
            else if (Integer.parseInt(mcrpm.getOther_loan_num()) == 2)
            {
                p = p.subtract(new BigDecimal(0.3));
            }
            else if (Integer.parseInt(mcrpm.getOther_loan_num()) == 1)
            {
                p = p.subtract(new BigDecimal(0.1));
            }
            else
            {
                p = p;
            }

            BigDecimal xujiageshu = new BigDecimal(0);
            BigDecimal zxqs_geshu = new BigDecimal(0);
            if (wmsCreRevPhoneContactList.size() > 0)
            {
                for (int i = 0; i < wmsCreRevPhoneContactList.size(); i++)
                {
                    WmsCreRevPhoneContact mcrpc = wmsCreRevPhoneContactList.get(i);
                    if (!wmsSysDictDataDao.getDictById(mcrpc.getIs_authenticity()).getValue_meaning().equals("真实"))
                    {
                        if (wmsSysDictDataDao.getDictById(mcrpc.getIs_authenticity()).getValue_meaning().equals("虚假"))
                        {
                            xujiageshu = xujiageshu.add(new BigDecimal(1));
                            if (wmsSysDictDataDao.getDictById(mcrpc.getContact_relation_detail()).getValue_meaning()
                                                 .equals("直系亲属"))
                            {
                                p = p.subtract(new BigDecimal(1));
                            }
                            else if (wmsSysDictDataDao.getDictById(mcrpc.getContact_relation_detail())
                                                      .getValue_meaning().equals("旁系亲属"))
                            {
                                p = p.subtract(new BigDecimal(0.1));
                            }
                            else
                            {
                                p = p.subtract(new BigDecimal(0.05));
                            }
                        }
                        else
                        {
                            xujiageshu = xujiageshu.add(new BigDecimal(0.5));
                            if (wmsSysDictDataDao.getDictById(mcrpc.getContact_relation_detail()).getValue_meaning()
                                                 .equals("直系亲属"))
                            {
                                p = p.subtract(new BigDecimal(0.15));
                            }
                            else if (wmsSysDictDataDao.getDictById(mcrpc.getContact_relation_detail())
                                                      .getValue_meaning().equals("旁系亲属"))
                            {
                                p = p.subtract(new BigDecimal(0.05));
                            }
                            else
                            {
                                p = p;
                            }
                        }
                    }
                    if (wmsSysDictDataDao.getDictById(mcrpc.getContact_relation_detail()).getValue_meaning()
                                         .equals("直系亲属"))
                    {
                        zxqs_geshu = zxqs_geshu.add(new BigDecimal(1));
                    }
                }
            }

            if (zxqs_geshu.compareTo(new BigDecimal(1)) == 0 && wmsCreRevPhoneContactList.size() > 0)
            {
                for (int i = 0; i < wmsCreRevPhoneContactList.size(); i++)
                {
                    WmsCreRevPhoneContact mcrpc = wmsCreRevPhoneContactList.get(i);
                    if (mcrpc.getContact_relation_detail().equals("直系亲属")
                        && wmsSysDictDataDao.getDictById(mcrpc.getIs_authenticity()).getValue_meaning().equals("虚假"))
                    {
                        p = p.subtract(new BigDecimal(1));
                        break;
                    }
                }
            }

            if (xujiageshu.compareTo(new BigDecimal(3)) >= 0)
            {
                p = p.subtract(new BigDecimal(1));
            }

            BigDecimal y = mcrcm.getTwo_year_overdue_rate().divide(new BigDecimal(100));
            BigDecimal x = mcrcm.getOne_year_overdue_rate().divide(new BigDecimal(100));
            if (x.compareTo(new BigDecimal(0.25)) >= 0)
            {
                p = p.subtract(new BigDecimal(1));
            }
            else if (x.compareTo(new BigDecimal(0.15)) >= 0)
            {
                // p = p - ((x - 0.15) * (0.5 / 0.1) + 0.5)
                p = p.subtract((x.subtract(new BigDecimal(0.15))).multiply(new BigDecimal(0.5).divide(new BigDecimal(
                                                                                                                     0.1),
                                                                                                      8,
                                                                                                      BigDecimal.ROUND_HALF_EVEN)))
                     .subtract(new BigDecimal(0.5));
            }
            else if (x.compareTo(new BigDecimal(0.05)) >= 0)
            {
                // p = p - (x * (0.5 / 0.15))
                p = p.subtract(x.multiply(new BigDecimal(0.5).divide(new BigDecimal(0.15), 2,
                                                                     BigDecimal.ROUND_HALF_EVEN)));
            }
            else
            {
                p = p;
            }

            if (y.compareTo(new BigDecimal(0.3)) >= 0)
            {
                p = p.subtract(new BigDecimal(1));
            }
            else if (y.compareTo(new BigDecimal(0.2)) >= 0)
            {
                // p = p - ((y - 0.2) * (0.5 / 0.1) + 0.5)
                p = p.subtract((y.subtract(new BigDecimal(0.2))).multiply(new BigDecimal(0.5).divide(new BigDecimal(0.1),
                                                                                                     8,
                                                                                                     BigDecimal.ROUND_HALF_EVEN)))
                     .subtract(new BigDecimal(0.5));
            }
            else if (x.compareTo(new BigDecimal(0.05)) >= 0)
            {
                // p = p - (x * (0.5 / 0.15))
                p = p.subtract((y.subtract(new BigDecimal(0.05))).multiply(new BigDecimal(0.5).divide(new BigDecimal(
                                                                                                                     0.15),
                                                                                                      2,
                                                                                                      BigDecimal.ROUND_HALF_EVEN)));
            }
            else
            {
                p = p;
            }

            BigDecimal yjhde = mcrwm.getMonth_payment().add(mcrcm.getMonthly_payments())
                                    .add(mcrcm.getCredit_avg_amount().multiply(new BigDecimal(0.15)));
            BigDecimal zhsr = mcrwm.getAver_income();

            BigDecimal ratio = yjhde.divide(zhsr, 8, BigDecimal.ROUND_HALF_EVEN);
            if (ratio.compareTo(new BigDecimal(1)) >= 0)
            {
                p = p.subtract(new BigDecimal(1));
            }

        }
        else
        {

            int a = 0;
            if (WmsCreCreditApproModelScoreVO.getFqdc().compareTo(new BigDecimal(10)) == 0)
            {
                a = a + 1;
            }
            if (WmsCreCreditApproModelScoreVO.getFmdc().compareTo(new BigDecimal(3)) == 0)
            {
                a = a + 1;
            }
            if (WmsCreCreditApproModelScoreVO.getZndc().compareTo(new BigDecimal(3)) == 0)
            {
                a = a + 1;
            }
            if (a > 2)
            {
                p = p.subtract(new BigDecimal(0.5));
            }
            else if (a > 0)
            {
                p = p.subtract(new BigDecimal(0.2));
            }
            else
            {
                p = p;
            }

            if (Integer.parseInt(mcrpm.getOther_loan_num()) > 4)
            {
                p = p.subtract(new BigDecimal(1));
            }
            else if (Integer.parseInt(mcrpm.getOther_loan_num()) == 4)
            {
                p = p.subtract(new BigDecimal(0.4));
            }
            else if (Integer.parseInt(mcrpm.getOther_loan_num()) == 3)
            {
                p = p.subtract(new BigDecimal(0.3));
            }
            else if (Integer.parseInt(mcrpm.getOther_loan_num()) == 2)
            {
                p = p.subtract(new BigDecimal(0.2));
            }
            else
            {
                p = p;
            }

            BigDecimal xujiageshu = new BigDecimal(0);
            BigDecimal zxqs_geshu = new BigDecimal(0);
            if (wmsCreRevPhoneContactList.size() > 0)
            {
                for (int i = 0; i < wmsCreRevPhoneContactList.size(); i++)
                {
                    WmsCreRevPhoneContact mcrpc = wmsCreRevPhoneContactList.get(i);
                    if (!wmsSysDictDataDao.getDictById(mcrpc.getIs_authenticity()).getValue_meaning().equals("真实"))
                    {
                        if (wmsSysDictDataDao.getDictById(mcrpc.getIs_authenticity()).getValue_meaning().equals("虚假"))
                        {

                            xujiageshu = xujiageshu.add(new BigDecimal(1));
                            if (wmsSysDictDataDao.getDictById(mcrpc.getContact_relation_detail()).getValue_meaning()
                                                 .equals("直系亲属"))
                            {
                                if (wmsCreCreditLineCustomerChangeHeadDao.getCount(wms_cre_credit_head_id) != 1)
                                {
                                    p = p.subtract(new BigDecimal(0.2));
                                }
                                else
                                {
                                    p = p.subtract(new BigDecimal(0.3));
                                }
                            }
                            else if (wmsSysDictDataDao.getDictById(mcrpc.getContact_relation_detail())
                                                      .getValue_meaning().equals("旁系亲属"))
                            {
                                p = p.subtract(new BigDecimal(0.1));
                            }
                            else
                            {
                                p = p.subtract(new BigDecimal(0.05));
                            }
                        }
                        else
                        {
                            xujiageshu = xujiageshu.add(new BigDecimal(0.5));
                            if (wmsSysDictDataDao.getDictById(mcrpc.getContact_relation_detail()).getValue_meaning()
                                                 .equals("直系亲属"))
                            {
                                p = p.subtract(new BigDecimal(0.15));
                            }
                            else if (wmsSysDictDataDao.getDictById(mcrpc.getContact_relation_detail())
                                                      .getValue_meaning().equals("旁系亲属"))
                            {
                                p = p.subtract(new BigDecimal(0.05));
                            }
                            else
                            {
                                p = p;
                            }
                        }
                    }
                    if (wmsSysDictDataDao.getDictById(mcrpc.getContact_relation_detail()).getValue_meaning()
                                         .equals("直系亲属"))
                    {
                        zxqs_geshu = zxqs_geshu.add(new BigDecimal(1));
                    }
                }
            }

            if (zxqs_geshu.compareTo(new BigDecimal(1)) == 0 && wmsCreRevPhoneContactList.size() > 0)
            {
                for (int i = 0; i < wmsCreRevPhoneContactList.size(); i++)
                {
                    WmsCreRevPhoneContact mcrpc = wmsCreRevPhoneContactList.get(i);
                    if (mcrpc.getContact_relation_detail().equals("直系亲属")
                        && wmsSysDictDataDao.getDictById(mcrpc.getIs_authenticity()).getValue_meaning().equals("虚假"))
                    {
                        p = p.subtract(new BigDecimal(0.5));
                        break;
                    }
                }
            }

            if (xujiageshu.compareTo(new BigDecimal(4)) >= 0)
            {
                p = p.subtract(new BigDecimal(1));
            }

            BigDecimal y = mcrcm.getTwo_year_overdue_rate().divide(new BigDecimal(100));
            BigDecimal x = mcrcm.getOne_year_overdue_rate().divide(new BigDecimal(100));
            if (x.compareTo(new BigDecimal(0.3)) >= 0)
            {
                p = p.subtract(new BigDecimal(1));
            }
            else if (x.compareTo(new BigDecimal(0.2)) >= 0)
            {
                // p = p - ((x - 0.2) * (0.5 / 0.1) + 0.5)
                p = p.subtract((x.subtract(new BigDecimal(0.2))).multiply(new BigDecimal(0.5).divide(new BigDecimal(0.1),
                                                                                                     8,
                                                                                                     BigDecimal.ROUND_HALF_EVEN)))
                     .subtract(new BigDecimal(0.5));
            }
            else if (x.compareTo(new BigDecimal(0.05)) >= 0)
            {
                // p = p - ((x-0.05) * (0.5 / 0.15))
                p = p.subtract((x.subtract(new BigDecimal(0.05))).multiply(new BigDecimal(0.5).divide(new BigDecimal(
                                                                                                                     0.15),
                                                                                                      8,
                                                                                                      BigDecimal.ROUND_HALF_EVEN)));
            }
            else
            {
                p = p;
            }

            if (y.compareTo(new BigDecimal(0.35)) >= 0)
            {
                p = p.subtract(new BigDecimal(1));
            }
            else if (y.compareTo(new BigDecimal(0.25)) >= 0)
            {
                // p = p - ((y - 0.25) * (0.5 / 0.1) + 0.5)
                p = p.subtract((y.subtract(new BigDecimal(0.25))).multiply(new BigDecimal(0.5).divide(new BigDecimal(
                                                                                                                     0.1),
                                                                                                      8,
                                                                                                      BigDecimal.ROUND_HALF_EVEN)))
                     .subtract(new BigDecimal(0.5));
            }
            else if (x.compareTo(new BigDecimal(0.1)) >= 0)
            {
                // p = p - ((y-0.1) * (0.5 / 0.15))
                p = p.subtract((y.subtract(new BigDecimal(0.1))).multiply(new BigDecimal(0.5).divide(new BigDecimal(
                                                                                                                    0.15),
                                                                                                     2,
                                                                                                     BigDecimal.ROUND_HALF_EVEN)));
            }
            else
            {
                p = p;
            }

            BigDecimal yjhde = mcrwm.getMonth_payment().add(mcrcm.getMonthly_payments())
                                    .add(mcrcm.getCredit_avg_amount().multiply(new BigDecimal(0.15)));
            BigDecimal zhsr = mcrwm.getAver_income();

            BigDecimal ratio = yjhde.divide(zhsr, 8, BigDecimal.ROUND_HALF_EVEN);
            if (ratio.compareTo(new BigDecimal(1)) >= 0)
            {
                p = p.subtract(new BigDecimal(1));
            }

        }

        return p;
    }

    /**
     * 计算模型一
     * 
     * @param int wms_cre_credit_head_id
     * @author 张风山
     */
    @Override
    public BigDecimal model1(int wms_cre_credit_head_id)
    {

        BigDecimal score1 = score(getScoreInfo(wms_cre_credit_head_id));
        BigDecimal score2 = new BigDecimal(0);
        BigDecimal s1 = new BigDecimal(0);
        BigDecimal s2 = new BigDecimal(0);
        BigDecimal s3 = new BigDecimal(0);
        BigDecimal s4 = new BigDecimal(0);
        BigDecimal s5 = new BigDecimal(0);
        BigDecimal s7 = new BigDecimal(0);
        BigDecimal s8 = new BigDecimal(0);
        BigDecimal y1 = new BigDecimal(0);
        BigDecimal modify11 = new BigDecimal(0);
        if (score1.compareTo(new BigDecimal(413)) >= 0)
        {
            score2 = (score1.subtract(new BigDecimal(413))).multiply(new BigDecimal(0.3).divide(new BigDecimal(117),
                                                                                                8,
                                                                                                BigDecimal.ROUND_HALF_EVEN));
        }
        else
        {
            score2 = (score1.subtract(new BigDecimal(143))).multiply(new BigDecimal(0.3).divide(new BigDecimal(270),
                                                                                                8,
                                                                                                BigDecimal.ROUND_HALF_EVEN))
                                                           .subtract(new BigDecimal(0.3));
        }

        WmsCreCreditHead wmsCreCreditHead = wmsCreCreditHeadDao.get(wms_cre_credit_head_id);
        WmsCreCreditLineCustomerChangeHead wmsCreCreditLineCustomerChangeHead = new WmsCreCreditLineCustomerChangeHead();
        wmsCreCreditLineCustomerChangeHead = wmsCreCreditLineCustomerChangeHeadDao.selectScoreInfo(wms_cre_credit_head_id)
                                                                                  .get(0);
        // 获取电审基础数据
        WmsCreRevPhoneMain wmsCreRevPhoneMain = new WmsCreRevPhoneMain();
        wmsCreRevPhoneMain.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        wmsCreRevPhoneMain.setWms_cre_credit_line_customer_change_head_id(wmsCreCreditLineCustomerChangeHead.getWms_cre_credit_line_customer_change_head_id());
        List<WmsCreRevPhoneMain> wmsCreRevPhoneMainList = wmsCreRevPhoneMainDao.getListByEntity(wmsCreRevPhoneMain);
        WmsCreRevPhoneMain mcrpm = wmsCreRevPhoneMainList.get(0);
        // 获取征信审核重要数据
        WmsCreRevCertificateModel wmsCreRevCertificateModel = new WmsCreRevCertificateModel();
        wmsCreRevCertificateModel.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        List<WmsCreRevCertificateModel> wmsCreRevCertificateModelList = wmsCreRevCertificateModelDao.getListByEntity(wmsCreRevCertificateModel);
        WmsCreRevCertificateModel mcrcm = wmsCreRevCertificateModelList.get(0);
        // 获取流水审核重要数据
        WmsCreRevWaterModel wmsCreRevWaterModel = new WmsCreRevWaterModel();
        wmsCreRevWaterModel.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        List<WmsCreRevWaterModel> wmsCreRevWaterModelList = wmsCreRevWaterModelDao.getListByEntity(wmsCreRevWaterModel);
        WmsCreRevWaterModel mcrwm = wmsCreRevWaterModelList.get(0);
        BigDecimal s11 = new BigDecimal(0);
        BigDecimal s12 = new BigDecimal(0);
        if (mcrcm.getUnpay_loan_amount().compareTo(new BigDecimal(0)) > 0
            && mcrcm.getUnpay_loan_balance().compareTo(new BigDecimal(0)) > 0)
        {
            if (mcrcm.getMonthly_payments().compareTo(new BigDecimal(0)) > 0)
            {
                // s11=[流水审核]账户月均收入-[征信审核]平均月应还额-[征信审核]平均使用额度*0.15-[流水审核]月均还款额;
                s11 = mcrwm.getAver_income().subtract(mcrcm.getMonthly_payments())
                           .subtract(mcrcm.getCredit_avg_amount().multiply(new BigDecimal(0.15)))
                           .subtract(mcrwm.getMonth_payment());
            }
            else if (mcrcm.getMonthly_payments().compareTo(new BigDecimal(0)) == 0
                     || mcrcm.getMonthly_payments() == null)
            {
                // s11=[流水审核]账户月均收入-[征信审核]未结清贷款余额/18-[征信审核]平均使用额度* 0.15
                // -[流水审核]月均还款额 ;
                s11 = mcrwm.getAver_income()
                           .subtract(mcrcm.getUnpay_loan_balance().divide(new BigDecimal(18), 8,
                                                                          BigDecimal.ROUND_HALF_EVEN))
                           .subtract(mcrcm.getCredit_avg_amount().multiply(new BigDecimal(0.15)))
                           .subtract(mcrwm.getMonth_payment());
            }
        }
        else
        {
            // s11 = [流水审核]账户月均收入-[征信审核]平均使用额度* 0.15 -[流水审核]月均还款额 ;
            s11 = mcrwm.getAver_income().subtract(mcrcm.getCredit_avg_amount().multiply(new BigDecimal(0.15)))
                       .subtract(mcrwm.getMonth_payment());
        }
        // s12= [流水审核]账户平均余额 ;
        s12 = mcrwm.getAver_balance();

        if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning().equals("卓英贷"))
        {
            // s1 = (s11 * 0.5 + s12) / 2 ;
            s1 = (s11.multiply(new BigDecimal(0.5)).add(s12)).divide(new BigDecimal(2), 8, BigDecimal.ROUND_HALF_EVEN);
        }
        else if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning().equals("卓楼贷"))
        {
            // s1 = (s11 * 0.5 + s12) / 2 ;
            s1 = (s11.multiply(new BigDecimal(0.5)).add(s12)).divide(new BigDecimal(2), 8, BigDecimal.ROUND_HALF_EVEN);
        }
        else if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning().equals("卓薪贷"))
        {
            // s1 = (s11 * 0.1 + s12) / 2 ;
            s1 = (s11.multiply(new BigDecimal(0.1)).add(s12)).divide(new BigDecimal(2), 8, BigDecimal.ROUND_HALF_EVEN);
        }
        else
        {
            // s1 = (s11 * 0.1 + s12) / 2 ;
            s1 = (s11.multiply(new BigDecimal(0.1)).add(s12)).divide(new BigDecimal(2), 8, BigDecimal.ROUND_HALF_EVEN);
        }

        if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning().equals("卓英贷"))
        {
            // s2 = s1* 18 * (1 - 0.017 * 18) * (1 + score2) ;
            s2 = s1.multiply(new BigDecimal(18))
                   .multiply(new BigDecimal(1).subtract(new BigDecimal(0.017).multiply(new BigDecimal(18))))
                   .multiply(new BigDecimal(1).add(score2));
        }
        else if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning().equals("卓楼贷"))
        {
            // s2 = s1 *12 * (1 - 0.023 * 12) * (1 + score2) ;
            s2 = s1.multiply(new BigDecimal(12))
                   .multiply(new BigDecimal(1).subtract(new BigDecimal(0.023).multiply(new BigDecimal(12))))
                   .multiply(new BigDecimal(1).add(score2));
        }
        else if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning().equals("卓薪贷"))
        {
            // s2 = s1 * 6 * (1 - 0.027 * 6) * (1 + score2) ;
            s2 = s1.multiply(new BigDecimal(6))
                   .multiply(new BigDecimal(1).subtract(new BigDecimal(0.027).multiply(new BigDecimal(6))))
                   .multiply(new BigDecimal(1).add(score2));
        }
        else
        {
            // s2 = s1 *12 * (1 - 0.023 * 12) * (1 + score2) ;
            s2 = s1.multiply(new BigDecimal(12))
                   .multiply(new BigDecimal(1).subtract(new BigDecimal(0.023).multiply(new BigDecimal(12))))
                   .multiply(new BigDecimal(1).add(score2));
        }

        modify11 = modify(wms_cre_credit_head_id);
        s3 = s2.multiply(new BigDecimal(1).add(modify11));

        if (s3.compareTo(new BigDecimal(wmsCreCreditHead.getCredit_limit()).multiply(new BigDecimal(10000))) <= 0)
        {
            s4 = s3;
        }
        else
        {
            s4 = new BigDecimal(wmsCreCreditHead.getCredit_limit()).multiply(new BigDecimal(10000));
        }

        BigDecimal y = new BigDecimal(mcrpm.getMax_repayment_limit_per_month());

        if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning().equals("卓英贷"))
        {
            // y1 = s4 / (18 * (1 – 0.017 * 18));
            y1 = s4.divide(new BigDecimal(18).multiply(new BigDecimal(1).subtract(new BigDecimal(0.017).multiply(new BigDecimal(
                                                                                                                                18)))),
                           8, BigDecimal.ROUND_HALF_EVEN);
        }
        else if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning().equals("卓业贷"))
        {
            // y1 = s4/ (12 * (1- 0.023 * 12)) ;
            y1 = s4.divide(new BigDecimal(12).multiply(new BigDecimal(1).subtract(new BigDecimal(0.023).multiply(new BigDecimal(
                                                                                                                                12)))),
                           8, BigDecimal.ROUND_HALF_EVEN);
        }
        else if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning().equals("卓楼贷"))
        {
            // y1 = s4/ (12 * (1- 0.023 * 12)) ;
            y1 = s4.divide(new BigDecimal(12).multiply(new BigDecimal(1).subtract(new BigDecimal(0.023).multiply(new BigDecimal(
                                                                                                                                12)))),
                           8, BigDecimal.ROUND_HALF_EVEN);
        }
        else
        {
            // y1 = s4/ (6 * (1- 0.027 * 6)) ;
            y1 = s4.divide(new BigDecimal(6).multiply(new BigDecimal(1).subtract(new BigDecimal(0.027).multiply(new BigDecimal(
                                                                                                                               6)))),
                           8, BigDecimal.ROUND_HALF_EVEN);
        }

        if (y1.compareTo(y) > 0)
        {
            if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning().equals("卓英贷"))
            {
                // s5 = y* 18 * (1- 0.017 * 18);
                s5 = y.multiply(new BigDecimal(18))
                      .multiply(new BigDecimal(1).subtract(new BigDecimal(0.017).multiply(new BigDecimal(18))));
            }
            else if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning()
                                      .equals("卓业贷"))
            {
                // s5 = y * 12 * (1- 0.023 * 12) ;
                s5 = y.multiply(new BigDecimal(12))
                      .multiply(new BigDecimal(1).subtract(new BigDecimal(0.023).multiply(new BigDecimal(12))));
            }
            else if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning()
                                      .equals("卓楼贷"))
            {
                // s5 = y*12 * (1- 0.023 * 12) ;
                s5 = y.multiply(new BigDecimal(12))
                      .multiply(new BigDecimal(1).subtract(new BigDecimal(0.023).multiply(new BigDecimal(12))));
            }
            else
            {
                // s5 = y * 6 * (1- 0.027 * 6) ;
                s5 = y.multiply(new BigDecimal(6))
                      .multiply(new BigDecimal(1).subtract(new BigDecimal(0.027).multiply(new BigDecimal(6))));
            }
        }
        else
        {
            s5 = s4;
        }

        s7 = s5.divide(new BigDecimal(10000), 0, BigDecimal.ROUND_DOWN);

        if (s7.compareTo(new BigDecimal(2)) < 0)
        {
            s8 = new BigDecimal(0);
        }
        else
        {
            s8 = s7;
        }

        return s8;

    }

    /**
     * 计算模型三
     * 
     * @param int wms_cre_credit_head_id
     * @author 张风山
     */
    @Override
    public BigDecimal model3(int wms_cre_credit_head_id)
    {

        BigDecimal score1 = score(getScoreInfo(wms_cre_credit_head_id));
        BigDecimal score2 = new BigDecimal(0);

        if (score1.compareTo(new BigDecimal(413)) >= 0)
        {
            score2 = (score1.subtract(new BigDecimal(413))).multiply(new BigDecimal(0.3).divide(new BigDecimal(117),
                                                                                                8,
                                                                                                BigDecimal.ROUND_HALF_EVEN));
        }
        else
        {
            score2 = (score1.subtract(new BigDecimal(143))).multiply(new BigDecimal(0.3).divide(new BigDecimal(270),
                                                                                                8,
                                                                                                BigDecimal.ROUND_HALF_EVEN))
                                                           .subtract(new BigDecimal(0.3));
        }

        WmsCreCreditHead wmsCreCreditHead = wmsCreCreditHeadDao.get(wms_cre_credit_head_id);
        WmsCreCreditLineCustomerChangeHead wmsCreCreditLineCustomerChangeHead = new WmsCreCreditLineCustomerChangeHead();
        wmsCreCreditLineCustomerChangeHead = wmsCreCreditLineCustomerChangeHeadDao.selectScoreInfo(wms_cre_credit_head_id)
                                                                                  .get(0);
        // 获取电审基础数据
        WmsCreRevPhoneMain wmsCreRevPhoneMain = new WmsCreRevPhoneMain();
        wmsCreRevPhoneMain.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        wmsCreRevPhoneMain.setWms_cre_credit_line_customer_change_head_id(wmsCreCreditLineCustomerChangeHead.getWms_cre_credit_line_customer_change_head_id());
        List<WmsCreRevPhoneMain> wmsCreRevPhoneMainList = wmsCreRevPhoneMainDao.getListByEntity(wmsCreRevPhoneMain);
        WmsCreRevPhoneMain mcrpm = wmsCreRevPhoneMainList.get(0);
        // 获取征信审核重要数据
        WmsCreRevCertificateModel wmsCreRevCertificateModel = new WmsCreRevCertificateModel();
        wmsCreRevCertificateModel.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        List<WmsCreRevCertificateModel> wmsCreRevCertificateModelList = wmsCreRevCertificateModelDao.getListByEntity(wmsCreRevCertificateModel);
        WmsCreRevCertificateModel mcrcm = wmsCreRevCertificateModelList.get(0);
        // 获取流水审核重要数据
        WmsCreRevWaterModel wmsCreRevWaterModel = new WmsCreRevWaterModel();
        wmsCreRevWaterModel.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        List<WmsCreRevWaterModel> wmsCreRevWaterModelList = wmsCreRevWaterModelDao.getListByEntity(wmsCreRevWaterModel);
        WmsCreRevWaterModel mcrwm = wmsCreRevWaterModelList.get(0);
        BigDecimal s1 = new BigDecimal(0);
        BigDecimal s2 = new BigDecimal(0);
        BigDecimal s3 = new BigDecimal(0);
        BigDecimal s4 = new BigDecimal(0);
        BigDecimal s5 = new BigDecimal(0);
        BigDecimal s7 = new BigDecimal(0);
        BigDecimal s8 = new BigDecimal(0);
        BigDecimal y1 = new BigDecimal(0);
        if (mcrcm.getUnpay_loan_amount().compareTo(new BigDecimal(0)) > 0
            && mcrcm.getUnpay_loan_balance().compareTo(new BigDecimal(0)) > 0)
        {
            if (mcrcm.getMonthly_payments().compareTo(new BigDecimal(0)) > 0)
            {
                // s1=[流水审核]账户月均收入-[征信审核]平均月应还额-[征信审核]平均使用额度*0.15-[流水审核]月均还款额;
                s1 = mcrwm.getAver_income().subtract(mcrcm.getMonthly_payments())
                          .subtract(mcrcm.getCredit_avg_amount().multiply(new BigDecimal(0.15)))
                          .subtract(mcrwm.getMonth_payment());
            }
            else if (mcrcm.getMonthly_payments().compareTo(new BigDecimal(0)) == 0
                     || mcrcm.getMonthly_payments() == null)
            {
                // s1=[流水审核]账户月均收入-[征信审核]未结清贷款余额/18-[征信审核]平均使用额度* 0.15
                // -[流水审核]月均还款额 ;
                s1 = mcrwm.getAver_income()
                          .subtract(mcrcm.getUnpay_loan_balance().divide(new BigDecimal(18), 8,
                                                                         BigDecimal.ROUND_HALF_EVEN))
                          .subtract(mcrcm.getCredit_avg_amount().multiply(new BigDecimal(0.15)))
                          .subtract(mcrwm.getMonth_payment());
            }
        }
        else
        {
            // s1 = [流水审核]账户月均收入-[征信审核]平均使用额度* 0.15 -[流水审核]月均还款额 ;
            s1 = mcrwm.getAver_income().subtract(mcrcm.getCredit_avg_amount().multiply(new BigDecimal(0.15)))
                      .subtract(mcrwm.getMonth_payment());
        }

        if (s1.compareTo(new BigDecimal(0)) > 0)
        {
            BigDecimal i = new BigDecimal(Math.log(s1.doubleValue()) / Math.log(10));
            s2 = (new BigDecimal(5.922).multiply(i).subtract(new BigDecimal(20.229))).multiply(new BigDecimal(10000))
                                                                                     .multiply(new BigDecimal(1).add(score2));
        }
        else
        {
            s2 = new BigDecimal(0);
        }

        BigDecimal modify11 = modify(wms_cre_credit_head_id);

        s3 = s2.multiply(new BigDecimal(1).add(modify11));

        if (s3.compareTo(new BigDecimal(wmsCreCreditHead.getCredit_limit()).multiply(new BigDecimal(10000))) <= 0)
        {
            s4 = s3;
        }
        else
        {
            s4 = new BigDecimal(wmsCreCreditHead.getCredit_limit()).multiply(new BigDecimal(10000));
        }

        BigDecimal y = new BigDecimal(mcrpm.getMax_repayment_limit_per_month());

        if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning().equals("卓英贷"))
        {
            // y1 = s4 / (18 * (1 – 0.017 * 18));
            y1 = s4.divide(new BigDecimal(18).multiply(new BigDecimal(1).subtract(new BigDecimal(0.017).multiply(new BigDecimal(
                                                                                                                                18)))),
                           8, BigDecimal.ROUND_HALF_EVEN);
        }
        else if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning().equals("卓业贷"))
        {
            // y1 = s4/ (12 * (1- 0.023 * 12)) ;
            y1 = s4.divide(new BigDecimal(12).multiply(new BigDecimal(1).subtract(new BigDecimal(0.023).multiply(new BigDecimal(
                                                                                                                                12)))),
                           8, BigDecimal.ROUND_HALF_EVEN);
        }
        else if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning().equals("卓楼贷"))
        {
            // y1 = s4/ (12 * (1- 0.023 * 12)) ;
            y1 = s4.divide(new BigDecimal(12).multiply(new BigDecimal(1).subtract(new BigDecimal(0.023).multiply(new BigDecimal(
                                                                                                                                12)))),
                           8, BigDecimal.ROUND_HALF_EVEN);
        }
        else
        {
            // y1 = s4/ (6 * (1- 0.027 * 6)) ;
            y1 = s4.divide(new BigDecimal(6).multiply(new BigDecimal(1).subtract(new BigDecimal(0.027).multiply(new BigDecimal(
                                                                                                                               6)))),
                           8, BigDecimal.ROUND_HALF_EVEN);
        }

        if (y1.compareTo(y) > 0)
        {
            if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning().equals("卓英贷"))
            {
                // s5 = y* 18 * (1- 0.017 * 18);
                s5 = y.multiply(new BigDecimal(18))
                      .multiply(new BigDecimal(1).subtract(new BigDecimal(0.017).multiply(new BigDecimal(18))));
            }
            else if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning()
                                      .equals("卓业贷"))
            {
                // s5 = y * 12 * (1- 0.023 * 12) ;
                s5 = y.multiply(new BigDecimal(12))
                      .multiply(new BigDecimal(1).subtract(new BigDecimal(0.023).multiply(new BigDecimal(12))));
            }
            else if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning()
                                      .equals("卓楼贷"))
            {
                // s5 = y*12 * (1- 0.023 * 12) ;
                s5 = y.multiply(new BigDecimal(12))
                      .multiply(new BigDecimal(1).subtract(new BigDecimal(0.023).multiply(new BigDecimal(12))));
            }
            else
            {
                // s5 = y * 6 * (1- 0.027 * 6) ;
                s5 = y.multiply(new BigDecimal(6))
                      .multiply(new BigDecimal(1).subtract(new BigDecimal(0.027).multiply(new BigDecimal(6))));
            }
        }
        else
        {
            s5 = s4;
        }

        s7 = s5.divide(new BigDecimal(10000), 0, BigDecimal.ROUND_DOWN);

        if (s7.compareTo(new BigDecimal(2)) < 0)
        {
            s8 = new BigDecimal(0);
        }
        else
        {
            s8 = s7;
        }

        return s8;

    }

    /**
     * 计算模型四
     * 
     * @param int wms_cre_credit_head_id
     * @author 张风山
     */
    @Override
    public BigDecimal model4(int wms_cre_credit_head_id)
    {

        BigDecimal score1 = score(getScoreInfo(wms_cre_credit_head_id));
        BigDecimal score2 = new BigDecimal(0);

        if (score1.compareTo(new BigDecimal(413)) >= 0)
        {
            score2 = (score1.subtract(new BigDecimal(413))).multiply(new BigDecimal(0.3).divide(new BigDecimal(117),
                                                                                                8,
                                                                                                BigDecimal.ROUND_HALF_EVEN));
        }
        else
        {
            score2 = (score1.subtract(new BigDecimal(143))).multiply(new BigDecimal(0.3).divide(new BigDecimal(270),
                                                                                                8,
                                                                                                BigDecimal.ROUND_HALF_EVEN))
                                                           .subtract(new BigDecimal(0.3));
        }

        WmsCreCreditHead wmsCreCreditHead = wmsCreCreditHeadDao.get(wms_cre_credit_head_id);
        WmsCreCreditLineCustomerChangeHead wmsCreCreditLineCustomerChangeHead = new WmsCreCreditLineCustomerChangeHead();
        wmsCreCreditLineCustomerChangeHead = wmsCreCreditLineCustomerChangeHeadDao.selectScoreInfo(wms_cre_credit_head_id)
                                                                                  .get(0);
        // 获取电审基础数据
        WmsCreRevPhoneMain wmsCreRevPhoneMain = new WmsCreRevPhoneMain();
        wmsCreRevPhoneMain.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        wmsCreRevPhoneMain.setWms_cre_credit_line_customer_change_head_id(wmsCreCreditLineCustomerChangeHead.getWms_cre_credit_line_customer_change_head_id());
        List<WmsCreRevPhoneMain> wmsCreRevPhoneMainList = wmsCreRevPhoneMainDao.getListByEntity(wmsCreRevPhoneMain);
        WmsCreRevPhoneMain mcrpm = wmsCreRevPhoneMainList.get(0);
        // 获取征信审核重要数据
        WmsCreRevCertificateModel wmsCreRevCertificateModel = new WmsCreRevCertificateModel();
        wmsCreRevCertificateModel.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        List<WmsCreRevCertificateModel> wmsCreRevCertificateModelList = wmsCreRevCertificateModelDao.getListByEntity(wmsCreRevCertificateModel);
        WmsCreRevCertificateModel mcrcm = wmsCreRevCertificateModelList.get(0);
        // 获取流水审核重要数据
        WmsCreRevWaterModel wmsCreRevWaterModel = new WmsCreRevWaterModel();
        wmsCreRevWaterModel.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        List<WmsCreRevWaterModel> wmsCreRevWaterModelList = wmsCreRevWaterModelDao.getListByEntity(wmsCreRevWaterModel);
        WmsCreRevWaterModel mcrwm = wmsCreRevWaterModelList.get(0);
        BigDecimal s1 = new BigDecimal(0);
        BigDecimal s2 = new BigDecimal(0);
        BigDecimal s3 = new BigDecimal(0);
        BigDecimal s4 = new BigDecimal(0);
        BigDecimal s5 = new BigDecimal(0);
        BigDecimal s6 = new BigDecimal(0);
        BigDecimal s7 = new BigDecimal(0);
        BigDecimal s8 = new BigDecimal(0);
        BigDecimal y1 = new BigDecimal(0);
        BigDecimal modify11 = new BigDecimal(0);

        if (mcrcm.getUnpay_loan_amount().compareTo(new BigDecimal(0)) > 0
            && mcrcm.getUnpay_loan_balance().compareTo(new BigDecimal(0)) > 0)
        {
            if (mcrcm.getMonthly_payments().compareTo(new BigDecimal(0)) > 0)
            {
                // s1= [征信审核]平均月应还额+ [征信审核]平均使用额度* 0.15 + [流水审核]月均还款额;
                s1 = mcrcm.getMonthly_payments().add(mcrcm.getCredit_avg_amount().multiply(new BigDecimal(0.15)))
                          .add(mcrwm.getMonth_payment());
            }
            else if (mcrcm.getMonthly_payments().compareTo(new BigDecimal(0)) == 0
                     || mcrcm.getMonthly_payments() == null)
            {
                // s1= [征信审核]未结清贷款余额/ 18 + [征信审核]平均使用额度* 0.15 + [流水审核]月均还款额 ;
                s1 = mcrcm.getUnpay_loan_balance().divide(new BigDecimal(18), 8, BigDecimal.ROUND_HALF_EVEN)
                          .add(mcrcm.getCredit_avg_amount().multiply(new BigDecimal(0.15)))
                          .add(mcrwm.getMonth_payment());
            }
        }
        else
        {
            // s1 = [流水审核]账户月均收入-[征信审核]平均使用额度* 0.15 -[流水审核]月均还款额 ;
            s1 = mcrcm.getCredit_avg_amount().multiply(new BigDecimal(0.15)).add(mcrwm.getMonth_payment());
        }

        BigDecimal yue = mcrwm.getAver_balance();
        BigDecimal shouru = mcrwm.getAver_income();
        // s2 = (0.00008984 * yue + 0.000005002 * shouru + 0.00006085 * s1 +
        // 1.536) * 10000 ;
        s2 = new BigDecimal(10000).multiply(new BigDecimal(0.00008984).multiply(yue)
                                                                      .add(new BigDecimal(0.000005002).multiply(shouru))
                                                                      .add(new BigDecimal(0.00006085).multiply(s1))
                                                                      .add(new BigDecimal(1.536)));
        s3 = s2;

        modify11 = modify(wms_cre_credit_head_id);

        s4 = s3.multiply(new BigDecimal(1).add(modify11));

        if (s4.compareTo(new BigDecimal(wmsCreCreditHead.getCredit_limit()).multiply(new BigDecimal(10000))) <= 0)
        {
            s5 = s4;
        }
        else
        {
            s5 = new BigDecimal(wmsCreCreditHead.getCredit_limit()).multiply(new BigDecimal(10000));
        }

        BigDecimal y = new BigDecimal(mcrpm.getMax_repayment_limit_per_month());

        if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning().equals("卓英贷"))
        {
            // y1 = s5 / (18 * (1 – 0.017 * 18));
            y1 = s5.divide(new BigDecimal(18).multiply(new BigDecimal(1).subtract(new BigDecimal(0.017).multiply(new BigDecimal(
                                                                                                                                18)))),
                           8, BigDecimal.ROUND_HALF_EVEN);
        }
        else if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning().equals("卓业贷"))
        {
            // y1 = s5/ (12 * (1- 0.023 * 12)) ;
            y1 = s5.divide(new BigDecimal(12).multiply(new BigDecimal(1).subtract(new BigDecimal(0.023).multiply(new BigDecimal(
                                                                                                                                12)))),
                           8, BigDecimal.ROUND_HALF_EVEN);
        }
        else if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning().equals("卓楼贷"))
        {
            // y1 = s5/ (12 * (1- 0.023 * 12)) ;
            y1 = s5.divide(new BigDecimal(12).multiply(new BigDecimal(1).subtract(new BigDecimal(0.023).multiply(new BigDecimal(
                                                                                                                                12)))),
                           8, BigDecimal.ROUND_HALF_EVEN);
        }
        else
        {
            // y1 = s5/ (6 * (1- 0.027 * 6)) ;
            y1 = s5.divide(new BigDecimal(6).multiply(new BigDecimal(1).subtract(new BigDecimal(0.027).multiply(new BigDecimal(
                                                                                                                               6)))),
                           8, BigDecimal.ROUND_HALF_EVEN);
        }

        if (y1.compareTo(y) > 0)
        {
            if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning().equals("卓英贷"))
            {
                // s6 = y* 18 * (1- 0.017 * 18);
                s6 = y.multiply(new BigDecimal(18))
                      .multiply(new BigDecimal(1).subtract(new BigDecimal(0.017).multiply(new BigDecimal(18))));
            }
            else if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning()
                                      .equals("卓业贷"))
            {
                // s6 = y * 12 * (1- 0.023 * 12) ;
                s6 = y.multiply(new BigDecimal(12))
                      .multiply(new BigDecimal(1).subtract(new BigDecimal(0.023).multiply(new BigDecimal(12))));
            }
            else if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning()
                                      .equals("卓楼贷"))
            {
                // s6 = y*12 * (1- 0.023 * 12) ;
                s6 = y.multiply(new BigDecimal(12))
                      .multiply(new BigDecimal(1).subtract(new BigDecimal(0.023).multiply(new BigDecimal(12))));
            }
            else
            {
                // s6 = y * 6 * (1- 0.027 * 6) ;
                s6 = y.multiply(new BigDecimal(6))
                      .multiply(new BigDecimal(1).subtract(new BigDecimal(0.027).multiply(new BigDecimal(6))));
            }
        }
        else
        {
            s6 = s5;
        }

        s7 = s6.divide(new BigDecimal(10000), 0, BigDecimal.ROUND_DOWN);

        if (s7.compareTo(new BigDecimal(2)) < 0)
        {
            s8 = new BigDecimal(0);
        }
        else
        {
            s8 = s7;
        }

        return s8;

    }

    /**
     * 计算模型二
     * 
     * @param int wms_cre_credit_head_id
     * @author 张风山
     */
    @Override
    public BigDecimal model2(int wms_cre_credit_head_id)
    {

        WmsCreCreditApproModelScoreVO wmsCreCreditApproModelScoreVO = getScoreInfo(wms_cre_credit_head_id);

        BigDecimal zhye = new BigDecimal(0);
        BigDecimal zhsr = new BigDecimal(0);
        BigDecimal zhsr1 = new BigDecimal(0);
        BigDecimal yjhde = new BigDecimal(0);
        BigDecimal xykysy = new BigDecimal(0);

        WmsCreCreditHead wmsCreCreditHead = wmsCreCreditHeadDao.get(wms_cre_credit_head_id);
        WmsCreCreditLineCustomerChangeHead wmsCreCreditLineCustomerChangeHead = new WmsCreCreditLineCustomerChangeHead();
        wmsCreCreditLineCustomerChangeHead = wmsCreCreditLineCustomerChangeHeadDao.selectScoreInfo(wms_cre_credit_head_id)
                                                                                  .get(0);
        // 获取电审基础数据
        WmsCreRevPhoneMain wmsCreRevPhoneMain = new WmsCreRevPhoneMain();
        wmsCreRevPhoneMain.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        wmsCreRevPhoneMain.setWms_cre_credit_line_customer_change_head_id(wmsCreCreditLineCustomerChangeHead.getWms_cre_credit_line_customer_change_head_id());
        List<WmsCreRevPhoneMain> wmsCreRevPhoneMainList = wmsCreRevPhoneMainDao.getListByEntity(wmsCreRevPhoneMain);
        WmsCreRevPhoneMain mcrpm = wmsCreRevPhoneMainList.get(0);
        // 获取征信审核重要数据
        WmsCreRevCertificateModel wmsCreRevCertificateModel = new WmsCreRevCertificateModel();
        wmsCreRevCertificateModel.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        List<WmsCreRevCertificateModel> wmsCreRevCertificateModelList = wmsCreRevCertificateModelDao.getListByEntity(wmsCreRevCertificateModel);
        WmsCreRevCertificateModel mcrcm = wmsCreRevCertificateModelList.get(0);
        // 获取流水审核重要数据
        WmsCreRevWaterModel wmsCreRevWaterModel = new WmsCreRevWaterModel();
        wmsCreRevWaterModel.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        List<WmsCreRevWaterModel> wmsCreRevWaterModelList = wmsCreRevWaterModelDao.getListByEntity(wmsCreRevWaterModel);
        WmsCreRevWaterModel mcrwm = wmsCreRevWaterModelList.get(0);

        if (mcrwm.getAver_balance().compareTo(new BigDecimal(5000)) <= 0)
        {
            zhye = new BigDecimal(20);
        }
        else if (mcrwm.getAver_balance().compareTo(new BigDecimal(5000)) > 0
                 && mcrwm.getAver_balance().compareTo(new BigDecimal(8000)) <= 0)
        {
            zhye = new BigDecimal(40);
        }
        else if (mcrwm.getAver_balance().compareTo(new BigDecimal(8000)) > 0
                 && mcrwm.getAver_balance().compareTo(new BigDecimal(10000)) <= 0)
        {
            zhye = new BigDecimal(60);
        }
        else if (mcrwm.getAver_balance().compareTo(new BigDecimal(10000)) > 0
                 && mcrwm.getAver_balance().compareTo(new BigDecimal(15000)) <= 0)
        {
            zhye = new BigDecimal(80);
        }
        else if (mcrwm.getAver_balance().compareTo(new BigDecimal(15000)) > 0
                 && mcrwm.getAver_balance().compareTo(new BigDecimal(20000)) <= 0)
        {
            zhye = new BigDecimal(100);
        }
        else if (mcrwm.getAver_balance().compareTo(new BigDecimal(20000)) > 0
                 && mcrwm.getAver_balance().compareTo(new BigDecimal(40000)) <= 0)
        {
            zhye = new BigDecimal(120);
        }
        else if (mcrwm.getAver_balance().compareTo(new BigDecimal(40000)) > 0
                 && mcrwm.getAver_balance().compareTo(new BigDecimal(60000)) <= 0)
        {
            zhye = new BigDecimal(140);
        }
        else if (mcrwm.getAver_balance().compareTo(new BigDecimal(60000)) > 0
                 && mcrwm.getAver_balance().compareTo(new BigDecimal(80000)) <= 0)
        {
            zhye = new BigDecimal(160);
        }
        else if (mcrwm.getAver_balance().compareTo(new BigDecimal(80000)) > 0
                 && mcrwm.getAver_balance().compareTo(new BigDecimal(100000)) <= 0)
        {
            zhye = new BigDecimal(180);
        }
        else if (mcrwm.getAver_balance().compareTo(new BigDecimal(100000)) > 0)
        {
            zhye = new BigDecimal(200);
        }
        else
        {
            zhye = new BigDecimal(0);
        }

        if (mcrwm.getAver_income().compareTo(new BigDecimal(600000)) > 0)
        {
            zhsr1 = new BigDecimal(600000);
            zhsr = new BigDecimal(600000).multiply(new BigDecimal(250).divide(new BigDecimal(600000), 8,
                                                                              BigDecimal.ROUND_HALF_EVEN));
        }
        else
        {
            zhsr1 = mcrwm.getAver_income();
            zhsr = zhsr1.multiply(new BigDecimal(250).divide(new BigDecimal(600000), 8, BigDecimal.ROUND_HALF_EVEN));
        }

        // 月均还款额
        // yjhde = [流水审核]月均还款额 + [征信审核]平均月应还款额
        BigDecimal yjhde1 = mcrwm.getMonth_payment().add(mcrcm.getMonthly_payments());
        if (yjhde1.divide(zhsr1, 8, BigDecimal.ROUND_HALF_EVEN).compareTo(new BigDecimal(1)) > 0)
        {
            yjhde = new BigDecimal(0);
        }
        else
        {
            yjhde = (new BigDecimal(1).subtract(yjhde1.divide(zhsr1, 8, BigDecimal.ROUND_HALF_EVEN))).multiply(new BigDecimal(
                                                                                                                              100));
        }

        // 信用卡使用
        if ((mcrcm.getCredit_avg_amount().divide(zhsr1, 8, BigDecimal.ROUND_HALF_EVEN)).compareTo(new BigDecimal(0.2)) > 0)
        {
            xykysy = new BigDecimal(0);
        }
        else
        {
            xykysy = (new BigDecimal(0.2).subtract(mcrcm.getCredit_avg_amount().divide(zhsr1, 8,
                                                                                       BigDecimal.ROUND_HALF_EVEN))).multiply(new BigDecimal(
                                                                                                                                             100).divide(new BigDecimal(
                                                                                                                                                                        0.2),
                                                                                                                                                         8,
                                                                                                                                                         BigDecimal.ROUND_HALF_EVEN));
        }

        BigDecimal s1 = new BigDecimal(0);
        BigDecimal s2 = new BigDecimal(0);
        BigDecimal s3 = new BigDecimal(0);
        BigDecimal s4 = new BigDecimal(0);
        BigDecimal s5 = new BigDecimal(0);
        BigDecimal s6 = new BigDecimal(0);
        BigDecimal s7 = new BigDecimal(0);
        BigDecimal s8 = new BigDecimal(0);
        BigDecimal y1 = new BigDecimal(0);
        BigDecimal modify11 = new BigDecimal(0);

        s1 = wmsCreCreditApproModelScoreVO.getDkze().add(wmsCreCreditApproModelScoreVO.getDkzbs())
                                          .add(wmsCreCreditApproModelScoreVO.getYq3())
                                          .add(wmsCreCreditApproModelScoreVO.getYq6())
                                          .add(wmsCreCreditApproModelScoreVO.getYq2n())
                                          .add(wmsCreCreditApproModelScoreVO.getDqyq())
                                          .add(wmsCreCreditApproModelScoreVO.getDbje())
                                          .add(wmsCreCreditApproModelScoreVO.getY3nsq())
                                          .add(wmsCreCreditApproModelScoreVO.getY6nsq())
                                          .add(wmsCreCreditApproModelScoreVO.getN1nsq())
                                          .add(wmsCreCreditApproModelScoreVO.getZsxed())
                                          .add(wmsCreCreditApproModelScoreVO.getZs())
                                          .add(wmsCreCreditApproModelScoreVO.getZdsxed());

        if (s1.compareTo(new BigDecimal(255)) == 0)
        {
            s1 = new BigDecimal(127.5);
        }

        s2 = s1.add(wmsCreCreditApproModelScoreVO.getAge()).add(wmsCreCreditApproModelScoreVO.getHunyin())
               .add(wmsCreCreditApproModelScoreVO.getXingbie()).add(wmsCreCreditApproModelScoreVO.getEdu())
               .add(wmsCreCreditApproModelScoreVO.getDwxz()).add(wmsCreCreditApproModelScoreVO.getGznx())
               .add(wmsCreCreditApproModelScoreVO.getGzgw()).add(wmsCreCreditApproModelScoreVO.getSjhy())
               .add(wmsCreCreditApproModelScoreVO.getSjhysj()).add(wmsCreCreditApproModelScoreVO.getFcmj())
               .add(wmsCreCreditApproModelScoreVO.getFcwz()).add(wmsCreCreditApproModelScoreVO.getCcsz())
               .add(wmsCreCreditApproModelScoreVO.getCcygsj()).add(wmsCreCreditApproModelScoreVO.getDklx())
               .add(wmsCreCreditApproModelScoreVO.getQspj()).add(wmsCreCreditApproModelScoreVO.getTspypj())
               .add(wmsCreCreditApproModelScoreVO.getBrsz()).add(wmsCreCreditApproModelScoreVO.getQssz())
               .add(wmsCreCreditApproModelScoreVO.getFqdc()).add(wmsCreCreditApproModelScoreVO.getFmdc())
               .add(wmsCreCreditApproModelScoreVO.getZndc()).add(wmsCreCreditApproModelScoreVO.getDkyt())
               .add(wmsCreCreditApproModelScoreVO.getZxqsth()).add(wmsCreCreditApproModelScoreVO.getPxqsth())
               .add(wmsCreCreditApproModelScoreVO.getTspyth()).add(zhye).add(zhsr).add(yjhde).add(xykysy);

        double i = (Math.log(s2.doubleValue()) / Math.log(10)) * 2.3567 - 5.7857;
        s3 = new BigDecimal(Math.pow(10, i)).multiply(new BigDecimal(10000));

        modify11 = modify(wms_cre_credit_head_id);

        s4 = s3.multiply(new BigDecimal(1).add(modify11));

        if (s4.compareTo(new BigDecimal(wmsCreCreditHead.getCredit_limit()).multiply(new BigDecimal(10000))) <= 0)
        {
            s5 = s4;
        }
        else
        {
            s5 = new BigDecimal(wmsCreCreditHead.getCredit_limit()).multiply(new BigDecimal(10000));
        }

        BigDecimal y = new BigDecimal(mcrpm.getMax_repayment_limit_per_month());// 电审
                                                                                // 无压力还款额

        if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning().equals("卓英贷"))
        {
            // y1 = s5 / (18 * (1 – 0.017 * 18));
            y1 = s5.divide(new BigDecimal(18).multiply(new BigDecimal(1).subtract(new BigDecimal(0.017).multiply(new BigDecimal(
                                                                                                                                18)))),
                           8, BigDecimal.ROUND_HALF_EVEN);
        }
        else if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning().equals("卓业贷"))
        {
            // y1 = s5/ (12 * (1- 0.023 * 12)) ;
            y1 = s5.divide(new BigDecimal(12).multiply(new BigDecimal(1).subtract(new BigDecimal(0.023).multiply(new BigDecimal(
                                                                                                                                12)))),
                           8, BigDecimal.ROUND_HALF_EVEN);
        }
        else if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning().equals("卓楼贷"))
        {
            // y1 = s5/ (12 * (1- 0.023 * 12)) ;
            y1 = s5.divide(new BigDecimal(12).multiply(new BigDecimal(1).subtract(new BigDecimal(0.023).multiply(new BigDecimal(
                                                                                                                                12)))),
                           8, BigDecimal.ROUND_HALF_EVEN);
        }
        else
        {
            // y1 = s5/ (6 * (1- 0.027 * 6)) ;
            y1 = s5.divide(new BigDecimal(6).multiply(new BigDecimal(1).subtract(new BigDecimal(0.027).multiply(new BigDecimal(
                                                                                                                               6)))),
                           8, BigDecimal.ROUND_HALF_EVEN);
        }

        if (y1.compareTo(y) > 0)
        {
            if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning().equals("卓英贷"))
            {
                // s5 = y* 18 * (1- 0.017 * 18);
                s6 = y.multiply(new BigDecimal(18))
                      .multiply(new BigDecimal(1).subtract(new BigDecimal(0.017).multiply(new BigDecimal(18))));
            }
            else if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning()
                                      .equals("卓业贷"))
            {
                // s5 = y * 12 * (1- 0.023 * 12) ;
                s6 = y.multiply(new BigDecimal(12))
                      .multiply(new BigDecimal(1).subtract(new BigDecimal(0.023).multiply(new BigDecimal(12))));
            }
            else if (wmsSysDictDataDao.getDictById(wmsCreCreditHead.getCre_loan_type()).getValue_meaning()
                                      .equals("卓楼贷"))
            {
                // s5 = y*12 * (1- 0.023 * 12) ;
                s6 = y.multiply(new BigDecimal(12))
                      .multiply(new BigDecimal(1).subtract(new BigDecimal(0.023).multiply(new BigDecimal(12))));
            }
            else
            {
                // s5 = y * 6 * (1- 0.027 * 6) ;
                s6 = y.multiply(new BigDecimal(6))
                      .multiply(new BigDecimal(1).subtract(new BigDecimal(0.027).multiply(new BigDecimal(6))));
            }
        }
        else
        {
            s6 = s5;
        }

        s7 = s6.divide(new BigDecimal(10000), 0, BigDecimal.ROUND_DOWN);

        if (s7.compareTo(new BigDecimal(2)) < 0)
        {
            s8 = new BigDecimal(0);
        }
        else
        {
            s8 = s7;
        }

        return s8;
    }

    @Override
    public String getModelData(int wms_cre_credit_head_id)
    {
        try
        {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("model_one_amount", this.model1(wms_cre_credit_head_id));
            paramMap.put("model_two_amount", this.model2(wms_cre_credit_head_id));
            paramMap.put("model_three_amount", this.model3(wms_cre_credit_head_id));
            paramMap.put("model_four_amount", this.model4(wms_cre_credit_head_id));
            paramMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
            wmsCreCreditHeadDao.updateModel(paramMap);
        }
        catch (Exception e)
        {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("model_one_amount", 0);
            paramMap.put("model_two_amount", 0);
            paramMap.put("model_three_amount", 0);
            paramMap.put("model_four_amount", 0);
            paramMap.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
            wmsCreCreditHeadDao.updateModel(paramMap);
            return "success";
        }

        return "success";
    }

}
