package com.lzf.ez4webcast.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 16:59
 */
public abstract class AbstractJdbcDao {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    protected NamedParameterJdbcTemplate namedJdbcTemplate;

    protected static Object[] parameters(Object... args) {
        return args;
    }

    protected static <T> T getFirst(List<T> list) {
        return list.size() != 0 ? list.get(0) : null;
    }

}
