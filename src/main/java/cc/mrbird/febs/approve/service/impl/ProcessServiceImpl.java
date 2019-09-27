package cc.mrbird.febs.approve.service.impl;

import cc.mrbird.febs.approve.entity.Process;
import cc.mrbird.febs.approve.entity.Project;
import cc.mrbird.febs.approve.mapper.ProcessMapper;
import cc.mrbird.febs.approve.mapper.ProjectMapper;
import cc.mrbird.febs.approve.service.IProcessService;
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

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *  Service实现
 *
 * @author YangXiao
 * @date 2019-09-26 18:53:51
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
        QueryWrapper<Process> queryWrapper = new QueryWrapper<>();
        // TODO 设置查询条件
        queryWrapper.lambda().eq(Process::getProjectId, process.getProjectId());
        if (StringUtils.isNotBlank(process.getCreateTimeFrom()) && StringUtils.isNotBlank(process.getCreateTimeTo())) {
            queryWrapper.lambda()
                    .ge(Process::getCreateTime, process.getCreateTimeFrom())
                    .le(Process::getCreateTime, process.getCreateTimeTo());
        }

        Page<Process> page = new Page<>(request.getPageNum(), request.getPageSize());

        IPage<Process> ipage = this.page(page, queryWrapper);
        List<Process> records = ipage.getRecords();
        Iterator<Process> it = records.iterator();
        while (it.hasNext()) {
            Process p = it.next();
            p.transformViewFields();
        }

        return ipage;
    }

    @Override
    public List<Process> findProcesss(Process process) {
        QueryWrapper<Process> queryWrapper = new QueryWrapper<>();
		// TODO 设置查询条件
        List<Process> records = this.baseMapper.selectList(queryWrapper);

        Iterator<Process> it = records.iterator();
        while (it.hasNext()) {
            Process p = it.next();
            p.transformViewFields();
        }

        return records;
    }

    @Override
    @Transactional
    public void createProcess(Process process) {
        Project project = projectMapper.selectById(process.getProjectId());
        if (project == null) {
            throw new RuntimeException("流程必须关联某个项目");
        }

        process.setProjectCode(project.getCode());

        String[] breadCrumbs1 = process.getXmlProcessIdVersion().split("\\/");
        String[] breadCrumbs2 = breadCrumbs1[1].split("__");
        process.setXmlProcessId(breadCrumbs2[0]);
        process.setXmlProcessVersion(breadCrumbs2[1]);

        Date date = new Date();
        process.setCreateTime(date);
        process.setUpdateTime(date);

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

        String[] breadCrumbs1 = process.getXmlProcessIdVersion().split("\\/");
        String[] breadCrumbs2 = breadCrumbs1[1].split("__");
        process.setXmlProcessId(breadCrumbs2[0]);
        process.setXmlProcessVersion(breadCrumbs2[1]);

        process.setUpdateTime(new Date());

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
