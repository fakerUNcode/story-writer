package com.easylive.service.impl;

import com.easylive.component.RedisComponent;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.enums.PageSize;
import com.easylive.entity.po.CategoryInfo;
import com.easylive.entity.query.CategoryInfoQuery;
import com.easylive.entity.query.SimplePage;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.exception.BusinessException;
import com.easylive.mappers.CategoryInfoMapper;
import com.easylive.service.CategoryInfoService;
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

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<CategoryInfo> findListByParam(CategoryInfoQuery param) {
		List<CategoryInfo> categoryInfoList = this.categoryInfoMapper.selectList(param);
		System.out.println("Linear data: " + categoryInfoList);
		if (param.getConvert2Tree() != null && param.getConvert2Tree()) {
			categoryInfoList = convertLine2Tree(categoryInfoList, Constants.ZERO);
		}
		System.out.println("Tree structure: " + categoryInfoList);
		return categoryInfoList;
	}


	//线性结构——>树形结构转换の辅助方法
	private List<CategoryInfo> convertLine2Tree(List<CategoryInfo> dataList, Integer pid) {
		List<CategoryInfo> children = new ArrayList<>();
		for (CategoryInfo m : dataList) {
			if (m.getpCategoryId() != null && m.getpCategoryId().equals(pid)) {
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
/*		新建分类时（bean.getCategoryId() == null）
		如果 dbBean != null，说明分类编号已存在，直接抛出异常：
		编辑分类时（bean.getCategoryId() != null）
		首先检查 dbBean != null，说明数据库中已存在相同分类编号的记录。
		接着比较 bean.getCategoryId()（当前分类 ID）和 dbBean.getCategoryId()：
		如果两者不相等，说明当前分类的分类 ID 与数据库中记录的分类 ID 不一致，抛出异常。*/
		if(bean.getCategoryId()==null && dbBean!=null ||
		    bean.getCategoryId()!=null && dbBean!=null && !bean.getCategoryId().equals(dbBean.getCategoryId())){
			throw new BusinessException("分类编号已经存在或分类编号冲突");
		}
		//没有异常则正常插入新分类或修改旧分类
		if(bean.getCategoryId()==null){
			//如果是新分类，前端未传入sort参数，需要从数据库手动取
			Integer maxSort = this.categoryInfoMapper.selectMaxSort(bean.getpCategoryId());
			//新插入的分类设为该层级分类的最大排序+1
			bean.setSort(maxSort + 1);
			this.categoryInfoMapper.insert(bean);
		}else{
			this.categoryInfoMapper.updateByCategoryId(bean,bean.getCategoryId());
		}
		//刷新缓存中的内容
		save2Redis();
	}

	@Override
	public void delCategory(Integer categoryId) {
		// TODO 查询分类项是否有视频，有视频的话不能删除
		CategoryInfoQuery categoryInfoQuery = new CategoryInfoQuery();
		categoryInfoQuery.setCategoryIdOrPCategoryId(categoryId);
		categoryInfoMapper.deleteByParam(categoryInfoQuery);
		//刷新缓存中的内容
		save2Redis();
	}

	//  主要任务：
	//	重新设置指定父分类（pCategoryId）下分类的排序顺序。
	//	对传入的分类 ID 列表按照顺序进行排序更新。
	@Override
	public void changeSort(Integer pCategoryId, String categoryIds) {
		//将逗号分隔的分类 ID 字符串（如 "1,2,3"）分割成数组。
		String[] categoryIdArray = categoryIds.split(",");
		//用于存储所有需要更新排序的分类对象
		List<CategoryInfo> categoryInfoList = new ArrayList<>();

		Integer sort = 0;
		for (String categoryId:categoryIdArray){
			CategoryInfo categoryInfo = new CategoryInfo();
			categoryInfo.setCategoryId(Integer.parseInt(categoryId));
			categoryInfo.setpCategoryId(pCategoryId);
			//设置排序号，递增
			categoryInfo.setSort(++sort);
			categoryInfoList.add(categoryInfo);
		}
		//我们选择使用mybatis的mapper一次性做完对数据库的操作，避免了java的for循环中每次循环都建立一次数据库连接的性能浪费
		categoryInfoMapper.updateSortBatch(categoryInfoList);

		//刷新缓存中的内容
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
		List<CategoryInfo> categoryInfoList = redisComponent.getCategoryList();
		//若未取到目录列表则刷新一次缓存中的内容
		if(categoryInfoList.isEmpty()){
			save2Redis();
		}
		return redisComponent.getCategoryList();
	}
}