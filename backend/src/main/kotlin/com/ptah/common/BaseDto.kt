package com.ptah.common

import cn.hutool.core.bean.BeanUtil
import kotlin.reflect.KClass

abstract class BaseDto {
    fun <T> fromEntity(entity: BaseEntity?): T = BeanUtil.toBean(entity, this.javaClass) as T
    fun <E : BaseEntity> toEntity(entityClass: KClass<out E>): E = BeanUtil.toBean(this, entityClass.java)
}
