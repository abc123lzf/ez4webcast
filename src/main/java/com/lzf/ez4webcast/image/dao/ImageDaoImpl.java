package com.lzf.ez4webcast.image.dao;

import com.lzf.ez4webcast.common.AbstractJdbcDao;
import com.lzf.ez4webcast.image.model.Image;
import org.springframework.stereotype.Repository;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.9 8:19
 */
@Repository
class ImageDaoImpl extends AbstractJdbcDao implements ImageDao {

    @Override
    public int add(Image image) {
        return jdbcTemplate.update("insert into image_inf(image_path, image_type, image_upload_time) values(?,?,?)",
                parameters(image.getPath(), image.getContentType(), image.getUploadTime()), Image.ROW_MAPPER);
    }

    @Override
    public Image fromId(int id) {
        return getFirst(jdbcTemplate.query("select image_id, image_path, image_type, image_upload_time from " +
                "image_inf where image_id = ?", parameters(id), Image.ROW_MAPPER));
    }
}
