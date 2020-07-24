package com.sparksys.oauth.infrastructure.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * description: 用户通知
 *
 * @author zhouxinlei
 * @date 2020-05-24 12:24:03
 */
@Data
@ApiModel(value = "UserNotices对象", description = "用户通知")
public class UserNotices {

    private static final long serialVersionUID = 1849014360372480614L;
    private String id;
    private String title;
    private String logo;
    private String description;
    private String updatedAt;
    private String member;
    private String href;
    private String memberLink;
}
