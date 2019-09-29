package cc.mrbird.febs.approve.service.impl;

import cc.mrbird.febs.approve.entity.Input;
import cc.mrbird.febs.approve.mapper.InputMapper;
import cc.mrbird.febs.approve.service.IInputService;
import org.springframework.stereotype.Service;
import cc.mrbird.febs.common.entity.QueryRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Date;

/**
 *  Service实现
 *
 * @author YangXiao
 * @date 2019-09-29 10:43:35
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class InputServiceImpl extends ServiceImpl<InputMapper, Input> implements IInputService {

    @Autowired
    private InputMapper inputMapper;

    @Override
    public IPage<Input> findInputs(QueryRequest request, Input input) {
        QueryWrapper<Input> queryWrapper = new QueryWrapper<>();
        // TODO 设置查询条件
        queryWrapper.lambda().eq(Input::getDatasetId, input.getDatasetId());

        Page<Input> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Input> findInputs(Input input) {
        QueryWrapper<Input> queryWrapper = new QueryWrapper<>();
		// TODO 设置查询条件

		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createInput(Input input) {
        Date operationDate = new Date();
        input.setCreateTime(operationDate);
        input.setUpdateTime(operationDate);

        this.save(input);
    }

    @Override
    @Transactional
    public void updateInput(Input input) {
        Date operationDate = new Date();
        input.setUpdateTime(operationDate);

        this.saveOrUpdate(input);
    }

    @Override
    @Transactional
    public void deleteInput(Input input) {
        QueryWrapper<Input> wapper = new QueryWrapper<>();
	    // TODO 设置删除条件

	    this.remove(wapper);
	}
}
