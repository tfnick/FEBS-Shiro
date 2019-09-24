package cc.mrbird.febs.approve.service.impl;

import cc.mrbird.febs.approve.entity.RuApply;
import cc.mrbird.febs.approve.mapper.RuApplyMapper;
import cc.mrbird.febs.approve.service.IRuApplyService;
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
 * @date 2019-09-24 18:00:32
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RuApplyServiceImpl extends ServiceImpl<RuApplyMapper, RuApply> implements IRuApplyService {

    @Autowired
    private RuApplyMapper ruApplyMapper;

    @Override
    public IPage<RuApply> findRuApplys(QueryRequest request, RuApply ruApply) {
        LambdaQueryWrapper<RuApply> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<RuApply> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<RuApply> findRuApplys(RuApply ruApply) {
	    LambdaQueryWrapper<RuApply> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createRuApply(RuApply ruApply) {
        this.save(ruApply);
    }

    @Override
    @Transactional
    public void updateRuApply(RuApply ruApply) {
        this.saveOrUpdate(ruApply);
    }

    @Override
    @Transactional
    public void deleteRuApply(RuApply ruApply) {
        LambdaQueryWrapper<RuApply> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
