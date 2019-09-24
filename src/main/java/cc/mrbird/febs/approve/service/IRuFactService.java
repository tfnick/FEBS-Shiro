package cc.mrbird.febs.approve.service;

import cc.mrbird.febs.approve.entity.RuFact;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author MrBird
 * @date 2019-09-24 18:00:42
 */
public interface IRuFactService extends IService<RuFact> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param ruFact ruFact
     * @return IPage<RuFact>
     */
    IPage<RuFact> findRuFacts(QueryRequest request, RuFact ruFact);

    /**
     * 查询（所有）
     *
     * @param ruFact ruFact
     * @return List<RuFact>
     */
    List<RuFact> findRuFacts(RuFact ruFact);

    /**
     * 新增
     *
     * @param ruFact ruFact
     */
    void createRuFact(RuFact ruFact);

    /**
     * 修改
     *
     * @param ruFact ruFact
     */
    void updateRuFact(RuFact ruFact);

    /**
     * 删除
     *
     * @param ruFact ruFact
     */
    void deleteRuFact(RuFact ruFact);
}
