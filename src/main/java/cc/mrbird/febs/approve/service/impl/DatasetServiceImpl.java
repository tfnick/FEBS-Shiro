package cc.mrbird.febs.approve.service.impl;

import cc.mrbird.febs.approve.entity.Dataset;
import cc.mrbird.febs.approve.mapper.DatasetMapper;
import cc.mrbird.febs.approve.service.IDatasetService;
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

/**
 *  Service实现
 *
 * @author YangXiao
 * @date 2019-09-27 11:47:02
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DatasetServiceImpl extends ServiceImpl<DatasetMapper, Dataset> implements IDatasetService {

    @Autowired
    private DatasetMapper datasetMapper;

    @Override
    public IPage<Dataset> findDatasets(QueryRequest request, Dataset dataset) {
        QueryWrapper<Dataset> queryWrapper = new QueryWrapper<>();
        // TODO 设置查询条件
        Page<Dataset> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Dataset> findDatasets(Dataset dataset) {
        QueryWrapper<Dataset> queryWrapper = new QueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createDataset(Dataset dataset) {
        this.save(dataset);
    }

    @Override
    @Transactional
    public void updateDataset(Dataset dataset) {
        this.saveOrUpdate(dataset);
    }

    @Override
    @Transactional
    public void deleteDataset(Dataset dataset) {
        QueryWrapper<Dataset> wapper = new QueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
