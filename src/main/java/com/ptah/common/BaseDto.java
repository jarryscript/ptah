package com.ptah.common;

import cn.hutool.core.bean.BeanUtil;

public abstract class BaseDto<T> {
    public T fromEntity(BaseEntity entity) {
        return (T) BeanUtil.toBean(entity, this.getClass());
    }
}
