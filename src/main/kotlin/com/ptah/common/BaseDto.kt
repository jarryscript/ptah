package com.ptah.common

import cn.hutool.core.bean.BeanUtil

abstract class BaseDto<T> {
    fun fromEntity(entity: BaseEntity?): T {
        return BeanUtil.toBean(entity, this.javaClass) as T
    }

    fun toEntity(entityClass: Class<T>?): T {
        return BeanUtil.toBean(this, entityClass)
    }
}
