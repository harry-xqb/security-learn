package com.security.learn.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * create by： harry
 * date:  2019/11/24 0024 下午 4:18
 **/
@Data
@ToString
@AllArgsConstructor
public class FileInfo {

    private Long id;

    private String path;

    private String originName;
}
