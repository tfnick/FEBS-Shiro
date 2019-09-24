package cc.mrbird.febs.approve.service;

import cc.mrbird.febs.approve.entity.RuAdvice;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author MrBird
 * @date 2019-09-24 18:00:30
 */
public interface IRuAdviceService extends IService<RuAdvice> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param ruAdvice ruAdvice
     * @return IPage<RuAdvice>
     */
    IPage<RuAdvice> findRuAdvices(QueryRequest request, RuAdvice ruAdvice);

    /**
     * 查询（所有）
     *
     * @param ruAdvice ruAdvice
     * @return List<RuAdvice>
     */
    List<RuAdvice> findRuAdvices(RuAdvice ruAdvice);

    /**
     * 新增
     *
     * @param ruAdvice ruAdvice
     */
    void createRuAdvice(RuAdvice ruAdvice);

    /**
     * 修改
     *
     * @param ruAdvice ruAdvice
     */
    void updateRuAdvice(RuAdvice ruAdvice);

    /**
     * 删除
     *
     * @param ruAdvice ruAdvice
     */
    void deleteRuAdvice(RuAdvice ruAdvice);
}
