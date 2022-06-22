package com.example.demo.tools;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/**
 * @author GYJ
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
        @Override
        public void insertFill(MetaObject metaObject) {
            log.info("start insert fill ....");
            this.strictInsertFill(metaObject, "create_time", LocalDateTime.class, LocalDateTime.now());
        }

        @Override
        public void updateFill(MetaObject metaObject) {
            log.info("start update fill ....");
            this.strictUpdateFill(metaObject, "update_time", LocalDateTime.class, LocalDateTime.now());
        }

 }
