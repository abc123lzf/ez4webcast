package com.lzf.ez4webcast.image.dao;

import com.lzf.ez4webcast.common.AbstractJdbcDao;
import com.lzf.ez4webcast.image.model.Image;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.9 8:19
 */
@Repository
class ImageDaoImpl extends AbstractJdbcDao implements ImageDao {

    @Override
    public int add(Image image) {
        image.setUploadTime(new Timestamp(System.currentTimeMillis()));
        KeyHolder holder = new GeneratedKeyHolder();
        PreparedStatementCreator creator = (Connection con) -> {
            PreparedStatement ps = con.prepareStatement("insert into image_inf(image_path, image_type, image_upload_time) values(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            statementParams(ps, image.getPath(), image.getContentType(), image.getUploadTime());
            return ps;
        };

        jdbcTemplate.update(creator, holder);
        Number key = holder.getKey();
        if(key == null) {
            return -1;
        }

        return key.intValue();
    }

    @Override
    public Image fromId(int id) {
        return getFirst(jdbcTemplate.query("select image_id, image_path, image_type, image_upload_time from " +
                "image_inf where image_id = ?", parameters(id), Image.ROW_MAPPER));
    }

    @Override
    public boolean contains(int id) {
        Integer cnt = jdbcTemplate.queryForObject("select count(*) from image_inf where image_id = " + id, Integer.class);
        if(cnt == null) {
            return false;
        }

        return cnt > 0;
    }
}
