package com.easylive.entity.enums;


public enum PageSize {
	//定义了常用的分页大小（15、20、30、40、50）
	SIZE10(10),SIZE15(15), SIZE20(20), SIZE30(30), SIZE40(40), SIZE50(50);
	int size;

	private PageSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return this.size;
	}
}
