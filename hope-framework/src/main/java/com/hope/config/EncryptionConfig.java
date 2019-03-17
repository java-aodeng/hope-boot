package com.hope.config;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program:hope-plus
 * @ClassName:EncryptionConfig
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-03-15 20:31
 * @Description: 加解密的配置类，配置项目定义
 * @Version 1.0
 **/
@Data
public class EncryptionConfig {

    /**
     * AES加密Key，长度必须16
     */
    private String key = "d7b85f6e214abcda";

    /**
     * 需要对响应内容进行加密的接口URI<br>
     * 比如：/user/list<br>
     * 不支持@PathVariable格式的URI
     */
    private List<String> responseEncryptUriList = new ArrayList<String>();

    /**
     * 需要对请求内容进行解密的接口URI<br>
     * 比如：/user/list<br>
     * 不支持@PathVariable格式的URI
     */
    private List<String> requestDecyptUriList = new ArrayList<String>();

    /**
     * 响应数据编码
     */
    private String responseCharset = "UTF-8";

    /**
     * 开启调试模式，调试模式下不进行加解密操作，用于像Swagger这种在线API测试场景
     */
    private boolean debug = false;
}
