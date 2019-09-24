package cc.mrbird.febs.approve.service.impl;

import cc.mrbird.febs.approve.entity.RuFact;
import cc.mrbird.febs.approve.mapper.RuFactMapper;
import cc.mrbird.febs.approve.service.IRuFactService;
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
 * @date 2019-09-24 18:00:42
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RuFactServiceImpl extends ServiceImpl<RuFactMapper, RuFact> implements IRuFactService {

    @Autowired
    private RuFactMapper ruFactMapper;

    @Override
    public IPage<RuFact> findRuFacts(QueryRequest request, RuFact ruFact) {
        LambdaQueryWrapper<RuFact> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<RuFact> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<RuFact> findRuFacts(RuFact ruFact) {
	    LambdaQueryWrapper<RuFact> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createRuFact(RuFact ruFact) {
        this.save(ruFact);
    }

    @Override
    @Transactional
    public void updateRuFact(RuFact ruFact) {
        this.saveOrUpdate(ruFact);
    }

    @Override
    @Transactional
    public void deleteRuFact(RuFact ruFact) {
        LambdaQueryWrapper<RuFact> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
