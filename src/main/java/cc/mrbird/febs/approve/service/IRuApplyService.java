package cc.mrbird.febs.approve.service;

import cc.mrbird.febs.approve.entity.RuApply;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author MrBird
 * @date 2019-09-24 18:00:32
 */
public interface IRuApplyService extends IService<RuApply> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param ruApply ruApply
     * @return IPage<RuApply>
     */
    IPage<RuApply> findRuApplys(QueryRequest request, RuApply ruApply);

    /**
     * 查询（所有）
     *
     * @param ruApply ruApply
     * @return List<RuApply>
     */
    List<RuApply> findRuApplys(RuApply ruApply);

    /**
     * 新增
     *
     * @param ruApply ruApply
     */
    void createRuApply(RuApply ruApply);

    /**
     * 修改
     *
     * @param ruApply ruApply
     */
    void updateRuApply(RuApply ruApply);

    /**
     * 删除
     *
     * @param ruApply ruApply
     */
    void deleteRuApply(RuApply ruApply);
}
