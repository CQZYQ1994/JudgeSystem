package com.judgesystem.page;

/*import com.github.pagehelper.Page;*/



import com.github.pagehelper.Page;

import java.util.List;

/**
 * 分页工具类
 *
 * @author dgb
 */
public interface PageUtil {

    /**
     * list转化为PageInfo
     *
     * @param <T>
     * @param list
     * @return
     */
    static <T> PageInfo<T> toPageInfo(List<T> list) {

        PageInfo<T> pageInfo = new PageInfo<T>();
        if (list == null) {
            return pageInfo;
        }
        pageInfo.setList(list);
        if (list instanceof Page) {
            pageInfo.setTotal(((Page<T>) list).getTotal());
        } else {
            pageInfo.setTotal(list.size());
        }
        return pageInfo;
    }

}
