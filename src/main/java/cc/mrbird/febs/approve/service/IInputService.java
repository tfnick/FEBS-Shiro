package cc.mrbird.febs.approve.service;

import cc.mrbird.febs.approve.entity.Input;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author YangXiao
 * @date 2019-09-29 10:43:35
 */
public interface IInputService extends IService<Input> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param input input
     * @return IPage<Input>
     */
    IPage<Input> findInputs(QueryRequest request, Input input);

    /**
     * 查询（所有）
     *
     * @param input input
     * @return List<Input>
     */
    List<Input> findInputs(Input input);

    /**
     * 新增
     *
     * @param input input
     */
    void createInput(Input input);

    /**
     * 修改
     *
     * @param input input
     */
    void updateInput(Input input);

    /**
     * 删除
     *
     * @param input input
     */
    void deleteInput(Input input);
}
