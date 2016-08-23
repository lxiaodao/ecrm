/**
 * 
 */
package com.weheros.framework.core.pagination;

/**
 * 分页查询
 * <p>
 * 知道总数，每页显示条数，分页后知道所有其它参数，如总页数。
 * 
 * @author Yang
 *
 */
public class Pagination {

	private Integer pageSize;// 每页显示数量
	private Integer pageCurrent;// 当前要展示第几页

	private Integer totalPageSizes;// 总页数
	private Integer totalResults; // 总结果数
	
	private Integer beginIndex;

	public Pagination(Integer totalResults, Integer pageSize, Integer pageCurrent) {

		this.pageSize = (pageSize==null||pageSize == 0 )? 20 : pageSize;
		this.pageCurrent = pageCurrent;
		this.totalResults = totalResults;
		count();

	}

	private void count() {

		this.totalPageSizes = (this.totalResults % this.pageSize == 0) ? this.totalResults / this.pageSize
				: (this.totalResults / this.pageSize + 1);
		this.pageCurrent = (pageCurrent > totalPageSizes) ? this.totalPageSizes : this.pageCurrent;
		
		this.beginIndex=(pageCurrent-1)>0?(pageCurrent-1)*this.pageSize:0;

	}

	public Integer getPageSize() {
		return pageSize;
	}

	public Integer getPageCurrent() {
		return pageCurrent;
	}

	public Integer getTotalPageSizes() {
		return totalPageSizes;
	}

	public Integer getTotalResults() {
		return totalResults;
	}

	public Integer getBeginIndex() {
		return beginIndex;
	}
	

}
