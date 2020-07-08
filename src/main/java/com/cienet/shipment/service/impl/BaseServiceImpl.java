package com.cienet.shipment.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cienet.shipment.vo.param.OrderQueryParam;
import com.cienet.shipment.vo.param.QueryParam;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IService<T> {

    protected Page setPageParam(QueryParam queryParam) {
        return setPageParam(queryParam, null);
    }

    protected Page setPageParam(QueryParam queryParam, OrderItem defaultOrder) {
        Page page = new Page();
        // 设置当前页码
        page.setCurrent(queryParam.getCurrent());
        // 设置页大小
        page.setSize(queryParam.getSize());
        /**
         * 如果是queryParam是OrderQueryParam，并且不为空，则使用前端排序
         * 否则使用默认排序
         */
        if (queryParam instanceof OrderQueryParam) {
            OrderQueryParam orderQueryParam = (OrderQueryParam) queryParam;
            List<OrderItem> orderItems = orderQueryParam.getOrders();
            if (isEmpty(orderItems)) {
                page.setOrders(Arrays.asList(defaultOrder));
            } else {
                page.setOrders(orderItems);
            }
        } else {
            page.setOrders(Arrays.asList(defaultOrder));
        }

        return page;
    }

    private boolean isEmpty(Collection coll) {
        return coll == null || coll.isEmpty();
    }
}
