package com.zx.emanage.loanfina.persist;

import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsFinaCreLoanAppAtt;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsFinaCreLoanAppAttDao extends BaseDao<WmsFinaCreLoanAppAtt>
{
    /**
     * 根据放款申请记录ID删除放款申请附件记录
     * 
     * @param wms_fina_cre_loan_app 放款申请记录ID
     */
    void deleteByFk(Integer wms_fina_cre_loan_app);
    
    /**
     * 
     * @Title: deleteByMap
     * @Description: TODO(删除数据)
     * @param paramMap 
     * @author: wangyihan
     * @time:2016年12月27日 上午9:01:31
     * history:
     * 1、2016年12月27日 wangyihan 创建方法
     */
    void deleteByMap(Map<String, Object> paramMap);
    
    
    
}