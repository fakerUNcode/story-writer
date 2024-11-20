package com.easylive.entity.query;



/**
 * 分类信息参数
 */
public class CategoryInfoQuery extends BaseParam {


	/**
	 * 自增分类
	 */
	private Integer categoryId;

	/**
	 * 分类编码
	 */
	private String categoryCode;

	private String categoryCodeFuzzy;

	/**
	 * 分类名称
	 */
	private String categoryName;

	private String categoryNameFuzzy;

	/**
	 * 父类分级ID
	 */
	private Integer pCategoryId;

	/**
	 * 图标
	 */
	private String icon;

	private String iconFuzzy;

	/**
	 * 背景图
	 */
	private String background;

	private String backgroundFuzzy;

	/**
	 * 排序号
	 */
	private Integer sort;

	//父类或自身id
	private Integer categoryIdOrPCategoryId;
	// 转化为树形结构 更好地示意父子级category
	private Boolean convert2Tree;

	public Boolean getConvert2Tree() {
		return convert2Tree;
	}

	public void setConvert2Tree(Boolean convert2Tree) {
		this.convert2Tree = convert2Tree;
	}

	public Integer getCategoryIdOrPCategoryId() {
		return categoryIdOrPCategoryId;
	}

	public void setCategoryIdOrPCategoryId(Integer categoryIdOrPCategoryId) {
		this.categoryIdOrPCategoryId = categoryIdOrPCategoryId;
	}

	public void setCategoryId(Integer categoryId){
		this.categoryId = categoryId;
	}

	public Integer getCategoryId(){
		return this.categoryId;
	}

	public void setCategoryCode(String categoryCode){
		this.categoryCode = categoryCode;
	}

	public String getCategoryCode(){
		return this.categoryCode;
	}

	public void setCategoryCodeFuzzy(String categoryCodeFuzzy){
		this.categoryCodeFuzzy = categoryCodeFuzzy;
	}

	public String getCategoryCodeFuzzy(){
		return this.categoryCodeFuzzy;
	}

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryName(){
		return this.categoryName;
	}

	public void setCategoryNameFuzzy(String categoryNameFuzzy){
		this.categoryNameFuzzy = categoryNameFuzzy;
	}

	public String getCategoryNameFuzzy(){
		return this.categoryNameFuzzy;
	}

	public void setpCategoryId(Integer pCategoryId){
		this.pCategoryId = pCategoryId;
	}

	public Integer getpCategoryId(){
		return this.pCategoryId;
	}

	public void setIcon(String icon){
		this.icon = icon;
	}

	public String getIcon(){
		return this.icon;
	}

	public void setIconFuzzy(String iconFuzzy){
		this.iconFuzzy = iconFuzzy;
	}

	public String getIconFuzzy(){
		return this.iconFuzzy;
	}

	public void setBackground(String background){
		this.background = background;
	}

	public String getBackground(){
		return this.background;
	}

	public void setBackgroundFuzzy(String backgroundFuzzy){
		this.backgroundFuzzy = backgroundFuzzy;
	}

	public String getBackgroundFuzzy(){
		return this.backgroundFuzzy;
	}

	public void setSort(Integer sort){
		this.sort = sort;
	}

	public Integer getSort(){
		return this.sort;
	}

}
