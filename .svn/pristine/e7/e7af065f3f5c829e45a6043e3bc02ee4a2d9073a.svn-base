package com.zx.emanage.loanappro.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreApproProtocolAttach;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreApproProtocolAttachDao extends BaseDao<WmsCreApproProtocolAttach>
{
    /**
     * 根据主表id查询 合同中附件表的内容 返回map
     * 
     * @author Administrator
     * @param parameters
     * @return
     * @since JDK 1.6
     */
    List<Map<String, Object>> searchforhouse(Integer wms_cre_credit_head_id);

	int updateAttach(WmsCreApproProtocolAttach bean);

	Integer updateWmsCreApproProtocolAttach(WmsCreApproProtocolAttach attach);

}