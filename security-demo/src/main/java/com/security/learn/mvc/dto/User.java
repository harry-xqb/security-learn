package com.security.learn.mvc.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.security.learn.mvc.validator.anotation.NotExist;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Future;
import java.util.Date;

/**
 * create by： harry
 * date:  2019/11/23 0023 下午 10:26
 **/
@Data
@ToString
public class User {

    public interface SimpleUserInfo {};

    public interface DetailUserInfo extends SimpleUserInfo{};

    @JsonView(SimpleUserInfo.class)
    private String id;

    @NotExist(message = "名称已存在")
    @JsonView(SimpleUserInfo.class)
    private String name;

    @JsonView(DetailUserInfo.class)
    @NotBlank(message = "密码不能为空")
    private String password;

    @JsonView(SimpleUserInfo.class)
    @Future(message = "生日必须是未来")
    private Date birthday;
}
