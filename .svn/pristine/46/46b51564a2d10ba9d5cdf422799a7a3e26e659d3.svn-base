package com.zx.emanage.inve.persist;

import java.util.LinkedList;
import java.util.List;

import com.zx.emanage.inve.vo.WmsInveSaleLimitVo;
import com.zx.emanage.util.gen.entity.ColumnInfo;
import com.zx.emanage.util.gen.entity.WmsInveClerkRegion;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveClerkRegionDao extends BaseDao<WmsInveClerkRegion> {

    /**
     * @Title: getWmsInveClerkRegionList
     * @Description: 查询柜员区域表(Cloumn)集合
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月20日 上午11:12:25
     * history:
     * 1、2017年2月20日 Administrator 创建方法
    */
    public LinkedList<ColumnInfo> getWmsInveClerkRegionColumnList();

    /**
     * @Title: getWmsInveSaleLimitVoList
     * @Description: 查询柜员区域表返回每日销售限额集合
     * @return 
     * @author: zhangyunfei
     * @time:2017年4月20日 上午9:35:46
     * history:
     * 1、2017年4月20日 Administrator 创建方法
    */
    public List<WmsInveSaleLimitVo> getWmsInveSaleLimitVoList();

    /**
     * @Title: getWmsInveClerkRegionByRegion
     * @Description: 根据地区编号获得柜员地区信息
     * @param region 地区编号
     * @return 柜员地区信息
     * @author: jinzhm
     * @time:2017年4月7日 下午5:10:04
     * history:
     * 1、2017年4月7日 jinzhm 创建方法
     */
    public WmsInveClerkRegion getWmsInveClerkRegionByRegion(String region);

}