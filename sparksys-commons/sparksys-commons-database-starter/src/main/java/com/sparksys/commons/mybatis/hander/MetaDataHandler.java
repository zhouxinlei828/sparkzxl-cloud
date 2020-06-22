package com.sparksys.commons.mybatis.hander;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.sparksys.commons.core.context.BaseContextHandler;
import com.sparksys.commons.mybatis.entity.Entity;
import com.sparksys.commons.mybatis.entity.SuperEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

/**
 * description: mybatis-plus自动注入处理器
 *
 * @author zhouxinlei
 * @date 2020-05-24 13:22:30
 */
@Slf4j
public class MetaDataHandler implements MetaObjectHandler {

    private final long workerId;

    private final long dataCenterId;

    public MetaDataHandler(long workerId, long dataCenterId) {
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        boolean flag = true;
        Object idVal;
        if (metaObject.getOriginalObject() instanceof SuperEntity) {
            Object oldId = ((SuperEntity) metaObject.getOriginalObject()).getId();
            if (oldId != null) {
                flag = false;
            }
            SuperEntity entity = (SuperEntity) metaObject.getOriginalObject();
            if (entity.getCreateTime() == null) {
                this.setFieldValByName(SuperEntity.CREATE_TIME, LocalDateTime.now(), metaObject);
            }
            if (entity.getCreateUser() == null || entity.getCreateUser().equals(0)) {
                idVal = "java.lang.String".equals(metaObject.getGetterType("createUser").getName()) ?
                        String.valueOf(BaseContextHandler.getUserId()) : BaseContextHandler.getUserId();
                this.setFieldValByName("createUser", idVal, metaObject);
            }
        }

        if (metaObject.getOriginalObject() instanceof Entity) {
            Entity entity = (Entity) metaObject.getOriginalObject();
            this.update(metaObject, entity);
        }

        if (flag) {
            Snowflake snowflake = IdUtil.getSnowflake(this.workerId, this.dataCenterId);
            Long id = snowflake.nextId();
            if (metaObject.hasGetter(SuperEntity.FIELD_ID)) {
                idVal = "java.lang.String".equals(metaObject.getGetterType(SuperEntity.FIELD_ID).getName()) ? String.valueOf(id) : id;
                this.setFieldValByName("id", idVal, metaObject);
            } else {
                TableInfo tableInfo = metaObject.hasGetter("MP_OPTLOCK_ET_ORIGINAL") ? TableInfoHelper.getTableInfo(metaObject.getValue("MP_OPTLOCK_ET_ORIGINAL").getClass()) : TableInfoHelper.getTableInfo(metaObject.getOriginalObject().getClass());
                if (tableInfo != null) {
                    Class<?> keyType = tableInfo.getKeyType();
                    if (keyType != null) {
                        String keyProperty = tableInfo.getKeyProperty();
                        Field idField = ReflectUtil.getField(metaObject.getOriginalObject().getClass(), keyProperty);
                        Object fieldValue = ReflectUtil.getFieldValue(metaObject.getOriginalObject(), idField);
                        if (!ObjectUtil.isNotEmpty(fieldValue)) {
                            idVal = "java.lang.String".equalsIgnoreCase(keyType.getName()) ? String.valueOf(id) : id;
                            this.setFieldValByName(keyProperty, idVal, metaObject);
                        }
                    }
                }
            }
        }
    }

    private void update(MetaObject metaObject, Entity entity) {
        if (entity.getUpdateTime() == null) {
            this.setFieldValByName(Entity.UPDATE_TIME, LocalDateTime.now(), metaObject);
        }

        if (entity.getUpdateUser() == null || entity.getUpdateUser().equals(0)) {
            if ("java.lang.String".equals(metaObject.getGetterType(Entity.UPDATE_USER).getName())) {
                String.valueOf(BaseContextHandler.getUserId());
            } else {
                BaseContextHandler.getUserId();
            }
            this.setFieldValByName("updateUser", BaseContextHandler.getUserId(), metaObject);
        }

        if (entity.getUpdateTime() == null) {
            this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("start update fill ....");
        if (metaObject.getOriginalObject() instanceof Entity) {
            Entity entity = (Entity) metaObject.getOriginalObject();
            this.update(metaObject, entity);
        }
    }

}
