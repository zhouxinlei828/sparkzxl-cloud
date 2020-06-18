package com.sparksys.commons.mybatis.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sparksys.commons.core.base.api.result.ApiPageResult;

import java.util.List;

/**
 * description: mybatis分页
 *
 * @author zhouxinlei
 * @date 2020-05-24 13:22:54
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class PageResult extends ApiPageResult {

    public static  ApiPageResult resetPage(Page tPage) {
        PageResult pageResult = new PageResult();
        processPage(tPage, pageResult);
        pageResult.setList(tPage.getRecords());
        return pageResult;
    }

    /**
     * 分页数据组装处理
     *
     * @param tPage
     * @return PageBean<T>
     * @author zhouxinlei
     * @date 2019-09-09 18:08:40
     */
    public static <T> ApiPageResult resetPage(Page tPage, List<T> doList) {
        PageResult pageResult = new PageResult();
        processPage(tPage, pageResult);
        pageResult.setList(doList);
        return pageResult;
    }

    private static  void processPage(Page tPage, PageResult pageResult) {
        long pageNum = tPage.getCurrent();
        long pageSize = tPage.getSize();
        long totalNum = new Long(tPage.getTotal()).intValue();
        long totalPage = tPage.getPages();
        pageResult.setPageNum((int) pageNum);
        pageResult.setTotalNum((int) totalNum);
        pageResult.setTotalPage((int) totalPage);
        pageResult.setPageSize((int) pageSize);
    }

}
