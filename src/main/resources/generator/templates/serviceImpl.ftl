package ${basePackage}.${serviceImplPackage};

import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${mapperPackage}.${className}Mapper;
import ${basePackage}.${servicePackage}.I${className}Service;
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
 * ${tableComment} Service实现
 *
 * @author ${author}
 * @date ${date}
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ${className}ServiceImpl extends ServiceImpl<${className}Mapper, ${className}> implements I${className}Service {

    @Autowired
    private ${className}Mapper ${className?uncap_first}Mapper;

    @Override
    public IPage<${className}> find${className}s(QueryRequest request, ${className} ${className?uncap_first}) {
        QueryWrapper<${className}> queryWrapper = new QueryWrapper<>();
        // TODO 设置查询条件

        Page<${className}> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<${className}> find${className}s(${className} ${className?uncap_first}) {
        QueryWrapper<${className}> queryWrapper = new QueryWrapper<>();
		// TODO 设置查询条件

		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void create${className}(${className} ${className?uncap_first}) {
        Date operationDate = new Date();
        ${className?uncap_first}.setCreateTime(operationDate);
        ${className?uncap_first}.setUpdateTime(operationDate);

        this.save(${className?uncap_first});
    }

    @Override
    @Transactional
    public void update${className}(${className} ${className?uncap_first}) {
        Date operationDate = new Date();
        ${className?uncap_first}.setUpdateTime(operationDate);

        this.saveOrUpdate(${className?uncap_first});
    }

    @Override
    @Transactional
    public void delete${className}(${className} ${className?uncap_first}) {
        QueryWrapper<${className}> wapper = new QueryWrapper<>();
	    // TODO 设置删除条件

	    this.remove(wapper);
	}
}
