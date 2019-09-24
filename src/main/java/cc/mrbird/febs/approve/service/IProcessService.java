package cc.mrbird.febs.approve.service;

import cc.mrbird.febs.approve.entity.Process;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author MrBird
 * @date 2019-09-24 18:05:47
 */
public interface IProcessService extends IService<Process> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param process process
     * @return IPage<Process>
     */
    IPage<Process> findProcesss(QueryRequest request, Process process);

    /**
     * 查询（所有）
     *
     * @param process process
     * @return List<Process>
     */
    List<Process> findProcesss(Process process);

    /**
     * 新增
     *
     * @param process process
     */
    void createProcess(Process process);

    /**
     * 修改
     *
     * @param process process
     */
    void updateProcess(Process process);

    /**
     * 删除
     *
     * @param process process
     */
    void deleteProcess(Process process);
}
