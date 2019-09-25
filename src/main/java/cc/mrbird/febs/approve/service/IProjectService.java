package cc.mrbird.febs.approve.service;

import cc.mrbird.febs.approve.entity.Project;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author YangXiao
 * @date 2019-09-25 23:37:35
 */
public interface IProjectService extends IService<Project> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param project project
     * @return IPage<Project>
     */
    IPage<Project> findProjects(QueryRequest request, Project project);

    /**
     * 查询（所有）
     *
     * @param project project
     * @return List<Project>
     */
    List<Project> findProjects(Project project);

    /**
     * 新增
     *
     * @param project project
     */
    void createProject(Project project);

    /**
     * 修改
     *
     * @param project project
     */
    void updateProject(Project project);

    /**
     * 删除
     *
     * @param project project
     */
    void deleteProject(Project project);
}
