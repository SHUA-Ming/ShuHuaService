/**   
 *    
 * 项目名称：ShuHuaService   
 * 类名称：PageSet   
 * 类描述：   
 * 创建人：Echo   
 * 创建时间：2014年9月13日 下午10:06:34   
 * 修改人：Echo   
 * 修改时间：2014年9月13日 下午10:06:34   
 * 修改备注：   
 * @version    
 *    
 */

package com.lst.utils;

import java.util.List;

/**
 * @ClassName: PageSet
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Echo
 * @date 2014年8月30日 下午10:33:29
 * 
 */

@SuppressWarnings("unused")
public class PageSet<T> {

	/**
	 * 计算当前页,若为0或者请求的URL中没有"?page=",则用1代替
	 * 
	 * @param page
	 *            传入的参数(可能为空,即0,则返回1)
	 * @return 当前页
	 */
	public static int countCurrentPage(int page) {
		final int curPage = (page == 0 ? 1 : page);
		return curPage;
	}
	/**
	 * 计算当前页开始记录
	 * 
	 * @param pageSize
	 *            每页记录数
	 * @param currentPage
	 *            当前第几页
	 * @return 当前页起始记录号的偏移量
	 */
	public static int countOffset(final int pageSize, final int currentPage) {
		final int offset = pageSize * (currentPage - 1);
		return offset;
	}

	/**
	 * 计算总页数,静态方法,供外部直接通过类名调用
	 * 
	 * @param pageSize
	 *            每页记录数
	 * @param allRow
	 *            总记录数
	 * @return 总页数
	 */
	public static int countTotalPage(final int pageSize, final int allRow) {
		int totalPage;
		if (allRow != 0) {
			totalPage = allRow % pageSize == 0 ? allRow / pageSize : allRow
					/ pageSize + 1;
		} else {
			totalPage = 1;
		}
		return totalPage;
	}
	private List<T> list; // 要返回的当前查询页的记录列表
	@SuppressWarnings("rawtypes")
	private List userdefinedlist; // 用户自定义的列表参数，及提供了2个list向页面传递数据
	private int allRow; // 总记录数
	private int totalPage; // 总页数
	private int currentPage = 1; // 当前页

	private int numPerPage; // 每页记录数pagesize
	private int nextPage; // 下一页
	private int previousPage; // 上一页
	private boolean isFirstPage; // 是否为第一页

	private boolean isLastPage; // 是否为最后一页

	private boolean hasPreviousPage; // 是否有前一页

	private boolean hasNextPage; // 是否有下一页

	public int getAllRow() {
		return allRow;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<T> getList() {
		return list;
	}

	public int getNextPage() {

		int nextPage = currentPage + 1;
		if (nextPage >= totalPage) {
			return currentPage;
		} else {
			return nextPage;
		}
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public int getPreviousPage() {

		int previousPage = currentPage - 1;
		if (previousPage <= 0) {
			return currentPage;
		} else {
			return previousPage;
		}
	}

	public int getTotalPage() {
		return totalPage;
	}

	@SuppressWarnings("rawtypes")
	public List getUserdefinedlist() {
		return userdefinedlist;
	}

	/**
	 * 初始化分页信息
	 */
	public void init() {
		this.isFirstPage = isFirstPage();
		this.isLastPage = isLastPage();
		this.hasPreviousPage = isHasPreviousPage();
		this.hasNextPage = isHasNextPage();
		this.nextPage = getNextPage();
		this.previousPage = getPreviousPage();
	}

	/**
	 * 以下判断页面的信息,只需getter方法(is方法)即可
	 * 
	 * @return
	 */

	public boolean isFirstPage() {
		return currentPage == 1; // 如是当前页是第1页
	}

	public boolean isHasNextPage() {
		return currentPage != totalPage; // 只要当前页不是最后1页
	}

	public boolean isHasPreviousPage() {
		return currentPage != 1; // 只要当前页不是第1页
	}

	public boolean isLastPage() {
		return currentPage == totalPage; // 如果当前页是最后一页

	}

	/** 数据库中该记录的总条数 */
	public void setAllRow(int allRow) {
		this.allRow = allRow;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	@SuppressWarnings("rawtypes")
	/**允许用户通过组装定义的数据集传回页面*/
	public void setUserdefinedlist(List userdefinedlist) {
		this.userdefinedlist = userdefinedlist;
	}

}
