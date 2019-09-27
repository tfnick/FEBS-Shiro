package cc.mrbird.febs.approve.service;

import cc.mrbird.febs.approve.entity.Dataset;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author YangXiao
 * @date 2019-09-27 15:50:29
 */
public interface IDatasetService extends IService<Dataset> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param dataset dataset
     * @return IPage<Dataset>
     */
    IPage<Dataset> findDatasets(QueryRequest request, Dataset dataset);

    /**
     * 查询（所有）
     *
     * @param dataset dataset
     * @return List<Dataset>
     */
    List<Dataset> findDatasets(Dataset dataset);

    /**
     * 新增
     *
     * @param dataset dataset
     */
    void createDataset(Dataset dataset);

    /**
     * 修改
     *
     * @param dataset dataset
     */
    void updateDataset(Dataset dataset);

    /**
     * 删除
     *
     * @param dataset dataset
     */
    void deleteDataset(Dataset dataset);
}
