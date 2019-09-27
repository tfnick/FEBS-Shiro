package cc.mrbird.febs.approve.service;

import cc.mrbird.febs.approve.entity.DataSource;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author YangXiao
 * @date 2019-09-27 15:50:33
 */
public interface IDataSourceService extends IService<DataSource> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param dataSource dataSource
     * @return IPage<DataSource>
     */
    IPage<DataSource> findDataSources(QueryRequest request, DataSource dataSource);

    /**
     * 查询（所有）
     *
     * @param dataSource dataSource
     * @return List<DataSource>
     */
    List<DataSource> findDataSources(DataSource dataSource);

    /**
     * 新增
     *
     * @param dataSource dataSource
     */
    void createDataSource(DataSource dataSource);

    /**
     * 修改
     *
     * @param dataSource dataSource
     */
    void updateDataSource(DataSource dataSource);

    /**
     * 删除
     *
     * @param dataSource dataSource
     */
    void deleteDataSource(DataSource dataSource);
}
