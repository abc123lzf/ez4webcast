package com.lzf.ez4webcast.image.dao;

import com.lzf.ez4webcast.image.model.Image;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.UUID;

/**
 * @author lzf abc123lzf@126.com
 * @since 2019/12/9 12:46
 * 图像文件管理器
 */
@Component
@Log4j2
@PropertySource(value = "image.properties")
class ImageFileManagerImpl implements ImageFileManager, InitializingBean {

    @Value("${ez4webcast.image.path}")
    private String path;

    @Value("${ez4webcast.image.maxsize:2000000}")
    private Integer sizeLimit;

    @Override
    public void afterPropertiesSet() {
        if(path == null) {
            throw new IllegalArgumentException("ez4webcast.image.path should not be null");
        }

        File file = new File(path);
        if(!file.exists() && !file.mkdirs()) {
            throw new IllegalArgumentException("Can not create folder at " + path);
        } else if(file.exists() && file.isFile()) {
            throw new IllegalArgumentException("Illagal ez4webcast.image.path argument: " + path);
        }

        this.path = file.getAbsolutePath();

        if(sizeLimit <= 0) {
            throw new IllegalArgumentException("Illgal argument ez4webcast.image.maxsize: " + sizeLimit);
        }
    }

    @Override
    public boolean write(Image image, byte[] bytes) {
        if(bytes.length > sizeLimit) {
            return false;
        }

        String fileName = UUID.randomUUID().toString();
        try(FileOutputStream fos = new FileOutputStream(new File(path, fileName))) {
            fos.write(bytes);
        } catch (IOException e) {
            log.error(e);
            throw new ImageApiException("IOException", e);
        }

        return true;
    }

    @Override
    public boolean read(Image image, OutputStream os) {
        File f = new File(path, image.getPath());
        if(!f.exists()) {
            log.error("Image file {} is not exists.", image.getPath());
            return false;
        }

        try(FileInputStream fis = new FileInputStream(f)) {
            IOUtils.copy(fis, os);
        } catch (IOException e) {
            log.error(e);
            throw new ImageApiException("IOException", e);
        }

        return true;
    }

    @Override
    public boolean delete(Image image) {
        File f = new File(path, image.getPath());
        return f.isFile() && f.delete();
    }
}
