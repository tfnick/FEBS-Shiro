package cc.mrbird.febs.approve.service.impl;

import cc.mrbird.febs.approve.entity.Process;
import cc.mrbird.febs.approve.entity.Project;
import cc.mrbird.febs.approve.mapper.ProjectMapper;
import cc.mrbird.febs.approve.service.IProjectService;
import cc.mrbird.febs.common.utils.SortUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import cc.mrbird.febs.common.entity.QueryRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 *  Service实现
 *
 * @author YangXiao
 * @date 2019-09-25 23:37:35
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public IPage<Project> findProjects(QueryRequest request, Project project) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        // TODO 设置查询条件与排序
        if (StringUtils.isNotEmpty(project.getCode())) {
            queryWrapper.lambda().eq(Project::getCode, project.getCode());
        }
        if (StringUtils.isNotBlank(project.getCreateTimeFrom()) && StringUtils.isNotBlank(project.getCreateTimeTo())) {
            queryWrapper.lambda()
                    .ge(Project::getCreateTime, project.getCreateTimeFrom())
                    .le(Project::getCreateTime, project.getCreateTimeTo());
        }

        SortUtil.handleWrapperSort(request, queryWrapper);

        Page<Project> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Project> findProjects(Project project) {
	    LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createProject(Project project) {
        this.save(project);
    }

    @Override
    @Transactional
    public void updateProject(Project project) {
        this.saveOrUpdate(project);
    }

    @Override
    @Transactional
    public void deleteProject(Project project) {
        LambdaQueryWrapper<Project> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
