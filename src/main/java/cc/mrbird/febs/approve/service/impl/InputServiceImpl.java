package cc.mrbird.febs.approve.service.impl;

import cc.mrbird.febs.approve.entity.Input;
import cc.mrbird.febs.approve.mapper.InputMapper;
import cc.mrbird.febs.approve.service.IInputService;
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
 * @date 2019-09-24 18:05:43
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class InputServiceImpl extends ServiceImpl<InputMapper, Input> implements IInputService {

    @Autowired
    private InputMapper inputMapper;

    @Override
    public IPage<Input> findInputs(QueryRequest request, Input input) {
        LambdaQueryWrapper<Input> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Input> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Input> findInputs(Input input) {
	    LambdaQueryWrapper<Input> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createInput(Input input) {
        this.save(input);
    }

    @Override
    @Transactional
    public void updateInput(Input input) {
        this.saveOrUpdate(input);
    }

    @Override
    @Transactional
    public void deleteInput(Input input) {
        LambdaQueryWrapper<Input> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
