package com.lzf.ez4webcast.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author lizifan 695199262@qq.com
 * @since 2019.12.8 15:52
 */
@Configuration
public class FastJSONMessageConverter extends FastJsonHttpMessageConverter {

    public FastJSONMessageConverter() {
        super();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.BrowserCompatible,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.DisableCircularReferenceDetect);
        SerializeConfig sc = SerializeConfig.globalInstance;
        sc.put(BigInteger.class, ToStringSerializer.instance);
        sc.put(BigDecimal.class, ToStringSerializer.instance);
        sc.put(Long.class, ToStringSerializer.instance);
        sc.put(Long.TYPE, ToStringSerializer.instance);
        config.setSerializeConfig(sc);
        super.setFastJsonConfig(config);
    }


    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        super.writeInternal(object, outputMessage);
    }
}
