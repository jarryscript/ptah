package com.ptah.common;

import cn.hutool.core.bean.BeanUtil;

public abstract class BaseDto<T> {

    public BaseDto() {
    }

    public T fromEntity(BaseEntity entity) {
        return (T) BeanUtil.toBean(entity, this.getClass());
    }

    public T toEntity(Class<T> entityClass) {
        return BeanUtil.toBean(this, entityClass);
    }
}
