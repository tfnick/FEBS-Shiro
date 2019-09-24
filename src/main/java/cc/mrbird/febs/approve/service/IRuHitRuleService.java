package cc.mrbird.febs.approve.service;

import cc.mrbird.febs.approve.entity.RuHitRule;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author MrBird
 * @date 2019-09-24 18:08:33
 */
public interface IRuHitRuleService extends IService<RuHitRule> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param ruHitRule ruHitRule
     * @return IPage<RuHitRule>
     */
    IPage<RuHitRule> findRuHitRules(QueryRequest request, RuHitRule ruHitRule);

    /**
     * 查询（所有）
     *
     * @param ruHitRule ruHitRule
     * @return List<RuHitRule>
     */
    List<RuHitRule> findRuHitRules(RuHitRule ruHitRule);

    /**
     * 新增
     *
     * @param ruHitRule ruHitRule
     */
    void createRuHitRule(RuHitRule ruHitRule);

    /**
     * 修改
     *
     * @param ruHitRule ruHitRule
     */
    void updateRuHitRule(RuHitRule ruHitRule);

    /**
     * 删除
     *
     * @param ruHitRule ruHitRule
     */
    void deleteRuHitRule(RuHitRule ruHitRule);
}
