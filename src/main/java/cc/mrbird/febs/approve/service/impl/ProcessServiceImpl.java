package cc.mrbird.febs.approve.service.impl;

import cc.mrbird.febs.approve.entity.Process;
import cc.mrbird.febs.approve.entity.Project;
import cc.mrbird.febs.approve.mapper.ProcessMapper;
import cc.mrbird.febs.approve.mapper.ProjectMapper;
import cc.mrbird.febs.approve.service.IProcessService;
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
 * @date 2019-09-26 13:42:38
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ProcessServiceImpl extends ServiceImpl<ProcessMapper, Process> implements IProcessService {

    @Autowired
    private ProcessMapper processMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public IPage<Process> findProcesss(QueryRequest request, Process process) {
        LambdaQueryWrapper<Process> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Process> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Process> findProcesss(Process process) {
	    LambdaQueryWrapper<Process> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createProcess(Process process) {
        Project project = projectMapper.selectById(process.getProjectId());
        if (project == null) {
            throw new RuntimeException("流程必须关联某个项目");
        }
        process.setProjectCode(project.getCode());

        String[] breadCrumbs = process.getXmlProcessIdVersion().split("\\/");
        process.setXmlProcessIdVersion(breadCrumbs[1]);

        this.save(process);
    }

    @Override
    @Transactional
    public void updateProcess(Process process) {

        Project project = projectMapper.selectById(process.getProjectId());
        if (project == null) {
            throw new RuntimeException("流程必须关联某个项目");
        }
        process.setProjectCode(project.getCode());

        String[] breadCrumbs = process.getXmlProcessIdVersion().split("\\/");
        process.setXmlProcessIdVersion(breadCrumbs[1]);

        this.saveOrUpdate(process);
    }

    @Override
    @Transactional
    public void deleteProcess(Process process) {
        LambdaQueryWrapper<Process> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
