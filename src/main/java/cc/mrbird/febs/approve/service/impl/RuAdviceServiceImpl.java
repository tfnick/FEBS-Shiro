package cc.mrbird.febs.approve.service.impl;

import cc.mrbird.febs.approve.entity.RuAdvice;
import cc.mrbird.febs.approve.mapper.RuAdviceMapper;
import cc.mrbird.febs.approve.service.IRuAdviceService;
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
 * @date 2019-09-24 18:00:30
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RuAdviceServiceImpl extends ServiceImpl<RuAdviceMapper, RuAdvice> implements IRuAdviceService {

    @Autowired
    private RuAdviceMapper ruAdviceMapper;

    @Override
    public IPage<RuAdvice> findRuAdvices(QueryRequest request, RuAdvice ruAdvice) {
        LambdaQueryWrapper<RuAdvice> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<RuAdvice> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<RuAdvice> findRuAdvices(RuAdvice ruAdvice) {
	    LambdaQueryWrapper<RuAdvice> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createRuAdvice(RuAdvice ruAdvice) {
        this.save(ruAdvice);
    }

    @Override
    @Transactional
    public void updateRuAdvice(RuAdvice ruAdvice) {
        this.saveOrUpdate(ruAdvice);
    }

    @Override
    @Transactional
    public void deleteRuAdvice(RuAdvice ruAdvice) {
        LambdaQueryWrapper<RuAdvice> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
