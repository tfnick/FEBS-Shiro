package cc.mrbird.febs.approve.service.impl;

import cc.mrbird.febs.approve.entity.DataSource;
import cc.mrbird.febs.approve.mapper.DataSourceMapper;
import cc.mrbird.febs.approve.service.IDataSourceService;
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
 * @date 2019-09-27 15:50:33
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DataSourceServiceImpl extends ServiceImpl<DataSourceMapper, DataSource> implements IDataSourceService {

    @Autowired
    private DataSourceMapper dataSourceMapper;

    @Override
    public IPage<DataSource> findDataSources(QueryRequest request, DataSource dataSource) {
        QueryWrapper<DataSource> queryWrapper = new QueryWrapper<>();
        // TODO 设置查询条件

        Page<DataSource> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<DataSource> findDataSources(DataSource dataSource) {
        QueryWrapper<DataSource> queryWrapper = new QueryWrapper<>();
		// TODO 设置查询条件

		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createDataSource(DataSource dataSource) {
        Date operationDate = new Date();
        dataSource.setCreateTime(operationDate);
        dataSource.setUpdateTime(operationDate);

        this.save(dataSource);
    }

    @Override
    @Transactional
    public void updateDataSource(DataSource dataSource) {
        Date operationDate = new Date();
        dataSource.setUpdateTime(operationDate);

        this.saveOrUpdate(dataSource);
    }

    @Override
    @Transactional
    public void deleteDataSource(DataSource dataSource) {
        QueryWrapper<DataSource> wapper = new QueryWrapper<>();
	    // TODO 设置删除条件

	    this.remove(wapper);
	}
}
