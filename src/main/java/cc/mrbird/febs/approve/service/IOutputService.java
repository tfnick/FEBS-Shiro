package cc.mrbird.febs.approve.service;

import cc.mrbird.febs.approve.entity.Output;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author MrBird
 * @date 2019-09-24 18:05:41
 */
public interface IOutputService extends IService<Output> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param output output
     * @return IPage<Output>
     */
    IPage<Output> findOutputs(QueryRequest request, Output output);

    /**
     * 查询（所有）
     *
     * @param output output
     * @return List<Output>
     */
    List<Output> findOutputs(Output output);

    /**
     * 新增
     *
     * @param output output
     */
    void createOutput(Output output);

    /**
     * 修改
     *
     * @param output output
     */
    void updateOutput(Output output);

    /**
     * 删除
     *
     * @param output output
     */
    void deleteOutput(Output output);
}
