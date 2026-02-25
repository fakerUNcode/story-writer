package com.easylive.service.impl;

import com.easylive.component.RedisComponent;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.enums.PageSize;
import com.easylive.entity.po.CategoryInfo;
import com.easylive.entity.query.CategoryInfoQuery;
import com.easylive.entity.query.SimplePage;
import com.easylive.entity.query.VideoInfoQuery;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.exception.BusinessException;
import com.easylive.mappers.CategoryInfoMapper;
import com.easylive.service.CategoryInfoService;
import com.easylive.service.VideoInfoService;
import com.easylive.utils.StringTools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 分类信息 业务接口实现
 */
@Service("categoryInfoService")
public class CategoryInfoServiceImpl implements CategoryInfoService {
	@Resource
	RedisComponent redisComponent;

	@Resource
	private CategoryInfoMapper<CategoryInfo, CategoryInfoQuery> categoryInfoMapper;
	@Resource
	private VideoInfoService videoInfoService;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<CategoryInfo> findListByParam(CategoryInfoQuery param) {
		// 1. 数据库查询结果可能为NULL，先判空
		List<CategoryInfo> categoryInfoList = this.categoryInfoMapper.selectList(param);
		if (categoryInfoList == null) {
			categoryInfoList = new ArrayList<>();
		}
		System.out.println("Linear data: " + categoryInfoList);

		// 2. 树形转换前先判空，避免空列表转换
		if (param.getConvert2Tree() != null && param.getConvert2Tree()) {
			categoryInfoList = convertLine2Tree(categoryInfoList, Constants.ZERO);
		}
		System.out.println("Tree structure: " + categoryInfoList);
		return categoryInfoList;
	}

	//线性结构——>树形结构转换の辅助方法
	private List<CategoryInfo> convertLine2Tree(List<CategoryInfo> dataList, Integer pid) {
		List<CategoryInfo> children = new ArrayList<>();
		// 先判空：避免dataList为NULL时遍历
		if (dataList == null || dataList.isEmpty()) {
			return children;
		}
		for (CategoryInfo m : dataList) {
			// 增加判空：避免m.getpCategoryId()为NULL
			if (m != null && pid != null && pid.equals(m.getpCategoryId())) {
				m.setChildren(convertLine2Tree(dataList, m.getCategoryId()));
				children.add(m);
			}
		}
		System.out.println("Children for pid " + pid + ": " + children);
		return children;
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(CategoryInfoQuery param) {
		return this.categoryInfoMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<CategoryInfo> findListByPage(CategoryInfoQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<CategoryInfo> list = this.findListByParam(param);
		PaginationResultVO<CategoryInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(CategoryInfo bean) {
		return this.categoryInfoMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<CategoryInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.categoryInfoMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<CategoryInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.categoryInfoMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(CategoryInfo bean, CategoryInfoQuery param) {
		StringTools.checkParam(param);
		return this.categoryInfoMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(CategoryInfoQuery param) {
		StringTools.checkParam(param);
		return this.categoryInfoMapper.deleteByParam(param);
	}

	/**
	 * 根据CategoryId获取对象
	 */
	@Override
	public CategoryInfo getCategoryInfoByCategoryId(Integer categoryId) {
		return this.categoryInfoMapper.selectByCategoryId(categoryId);
	}

	/**
	 * 根据CategoryId修改
	 */
	@Override
	public Integer updateCategoryInfoByCategoryId(CategoryInfo bean, Integer categoryId) {
		return this.categoryInfoMapper.updateByCategoryId(bean, categoryId);
	}

	/**
	 * 根据CategoryId删除
	 */
	@Override
	public Integer deleteCategoryInfoByCategoryId(Integer categoryId) {
		return this.categoryInfoMapper.deleteByCategoryId(categoryId);
	}

	/**
	 * 根据CategoryCode获取对象
	 */
	@Override
	public CategoryInfo getCategoryInfoByCategoryCode(String categoryCode) {
		return this.categoryInfoMapper.selectByCategoryCode(categoryCode);
	}

	/**
	 * 根据CategoryCode修改
	 */
	@Override
	public Integer updateCategoryInfoByCategoryCode(CategoryInfo bean, String categoryCode) {
		return this.categoryInfoMapper.updateByCategoryCode(bean, categoryCode);
	}

	/**
	 * 根据CategoryCode删除
	 */
	@Override
	public Integer deleteCategoryInfoByCategoryCode(String categoryCode) {
		return this.categoryInfoMapper.deleteByCategoryCode(categoryCode);
	}

	@Override
	public void saveCategory(CategoryInfo bean) {
		CategoryInfo dbBean = this.categoryInfoMapper.selectByCategoryCode(bean.getCategoryCode());
		if(bean.getCategoryId()==null && dbBean!=null ||
				bean.getCategoryId()!=null && dbBean!=null && !bean.getCategoryId().equals(dbBean.getCategoryId())){
			throw new BusinessException("分类编号已经存在或分类编号冲突");
		}
		if(bean.getCategoryId()==null){
			Integer maxSort = this.categoryInfoMapper.selectMaxSort(bean.getpCategoryId());
			bean.setSort(maxSort + 1);
			this.categoryInfoMapper.insert(bean);
		}else{
			this.categoryInfoMapper.updateByCategoryId(bean,bean.getCategoryId());
		}
		save2Redis();
	}

	@Override
	public void delCategory(Integer categoryId) {
		VideoInfoQuery videoInfoQuery = new VideoInfoQuery();
		videoInfoQuery.setCategoryIdOrPCategoryId(categoryId);
		Integer count = videoInfoService.findCountByParam(videoInfoQuery);
		if(count>0){
			throw new BusinessException("分类下有视频无法删除！");
		}
		CategoryInfoQuery categoryInfoQuery = new CategoryInfoQuery();
		categoryInfoQuery.setCategoryIdOrPCategoryId(categoryId);
		categoryInfoMapper.deleteByParam(categoryInfoQuery);
		save2Redis();
	}

	@Override
	public void changeSort(Integer pCategoryId, String categoryIds) {
		String[] categoryIdArray = categoryIds.split(",");
		List<CategoryInfo> categoryInfoList = new ArrayList<>();

		Integer sort = 0;
		for (String categoryId:categoryIdArray){
			CategoryInfo categoryInfo = new CategoryInfo();
			categoryInfo.setCategoryId(Integer.parseInt(categoryId));
			categoryInfo.setpCategoryId(pCategoryId);
			categoryInfo.setSort(++sort);
			categoryInfoList.add(categoryInfo);
		}
		categoryInfoMapper.updateSortBatch(categoryInfoList);
		save2Redis();
	}

	//使用Redis缓存信息
	private void save2Redis(){
		CategoryInfoQuery query = new CategoryInfoQuery();
		query.setOrderBy("sort asc");
		query.setConvert2Tree(true);
		List<CategoryInfo> categoryInfoList = findListByParam(query);
		//Category信息存入redis缓存内
		redisComponent.saveCategoryList(categoryInfoList);
	}

	@Override
	public List<CategoryInfo> getAllCategoryList() {
		// 1. 从Redis获取分类列表
		List<CategoryInfo> categoryInfoList = redisComponent.getCategoryList();

		// 2. 正确的判空逻辑：先判断NULL，再判断空列表
		if (categoryInfoList == null || categoryInfoList.isEmpty()) {
			// 缓存为空/NULL，重建缓存
			save2Redis();
			// 重新从Redis获取
			categoryInfoList = redisComponent.getCategoryList();
			// 兜底：避免Redis返回NULL
			if (categoryInfoList == null) {
				categoryInfoList = new ArrayList<>();
			}
		}
		return categoryInfoList;
	}
}