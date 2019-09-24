package cc.mrbird.febs.approve.service.impl;

import cc.mrbird.febs.approve.entity.Output;
import cc.mrbird.febs.approve.mapper.OutputMapper;
import cc.mrbird.febs.approve.service.IOutputService;
import cc.mrbird.febs.common.entity.QueryRequest;
import org.springframework.stereotype.Service;
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
 * @author MrBird
 * @date 2019-09-24 18:05:41
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class OutputServiceImpl extends ServiceImpl<OutputMapper, Output> implements IOutputService {

    @Autowired
    private OutputMapper outputMapper;

    @Override
    public IPage<Output> findOutputs(QueryRequest request, Output output) {
        LambdaQueryWrapper<Output> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Output> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Output> findOutputs(Output output) {
	    LambdaQueryWrapper<Output> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createOutput(Output output) {
        this.save(output);
    }

    @Override
    @Transactional
    public void updateOutput(Output output) {
        this.saveOrUpdate(output);
    }

    @Override
    @Transactional
    public void deleteOutput(Output output) {
        LambdaQueryWrapper<Output> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
