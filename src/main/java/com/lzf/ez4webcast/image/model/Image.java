package com.lzf.ez4webcast.image.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 19:23
 */
@Data
@Builder
public class Image {

    public static final RowMapper<Image> ROW_MAPPER = (rs, rowNum) -> {
        Image image = new Image();
        image.setId(rs.getInt("image_id"));
        image.setPath(rs.getString("image_path"));
        image.setUploadTime(rs.getTimestamp("image_upload_time"));
        image.setContentType(rs.getString("image_type"));
        return image;
    };

    private Integer id;

    private String path;

    private String contentType;

    private Timestamp uploadTime;

    @Tolerate
    public Image() { }
}
