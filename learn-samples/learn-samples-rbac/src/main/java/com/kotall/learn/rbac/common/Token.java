package com.kotall.learn.rbac.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/5/19 16:03
 */
@Data
public class Token implements Serializable {

    private String username;
    private String password;
    private String code;

}
