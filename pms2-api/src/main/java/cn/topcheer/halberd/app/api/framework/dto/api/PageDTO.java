package cn.topcheer.halberd.app.api.framework.dto.api;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import lombok.Data;

import java.util.Collections;
import java.util.List;
/**
 * 这个类主要用于高新的前端接口调用，因为高新调用的参数值和MybatisPlus的分页参数不一致，所以需要转换。
 * 原来想修改成继承自mybatisplus的PageDTO类，但考虑到传到前台去时，会导致同样的数据集传两份（rows和records），所以最终放弃。
 * 关于页数，总记录数这些值是int型还是long型，其实也是蛮坑的，毕竟jdbc的ResultSet定位是用int型的，所以这里也用int型吧。
 * 查询结果超过20亿条数据的记录还是不要用这个了吧。
 */
@Data
public class PageDTO<T> {
    private int totalPage;
    private int pageSize;
    private int totalCount;
    private int currentPage;
    private List<T> rows;
    List<OrderItem> orders;


    public PageDTO(int current, int pageSize) {
        this.rows = Collections.emptyList();
        this.currentPage = 1;
        if (current > 1L) {
            this.currentPage = current;
        }
        this.totalCount = 0;
        this.pageSize = pageSize;
    }

    public PageDTO(int current, int pageSize, int total) {
        this.rows = Collections.emptyList();
        this.currentPage = 1;
        if (current > 1L) {
            this.currentPage = current;
        }
        this.pageSize = pageSize;
        this.totalCount = total;
        if (total % pageSize == 0) {
            this.totalPage = total / pageSize;
        } else {
            this.totalPage = total / pageSize + 1;
        }
    }

    public static <T> PageDTO<T> of(int current, int size, int total) {
        return new PageDTO<T>(current, size, total);
    }

    public static <T> PageDTO<T> of(int current, int size) {
        return new PageDTO<T>(current, size);
    }

    public static  <T>  PageDTO<T> fromMybatisPlusPage(com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> page) {
        PageDTO<T> pageDTO = new PageDTO<T>((int)page.getCurrent(),(int) page.getSize(), (int)page.getTotal());
        pageDTO.setRows(page.getRecords());
        pageDTO.setOrders(page.orders());
        return pageDTO;
    }

    public static <T>   com.baomidou.mybatisplus.extension.plugins.pagination.Page<T>  toMybatisPlusPage(PageDTO<T> pageDTO) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<T>(pageDTO.getCurrentPage(), pageDTO.getPageSize(), pageDTO.getTotalCount());
        page.setRecords(pageDTO.getRows());
        page.setOrders(pageDTO.getOrders());
        return page;
    }

}
