package me.zhengjie.modules.system.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import me.zhengjie.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name="weixin_user")
public class WeixinUser implements Serializable {
    @Id
    @NotNull(groups = BaseEntity.Update.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID", hidden = true)
    private Long id;

    @NotBlank
    @Column(unique = true, name = "open_id")
    @ApiModelProperty(value = "微信的open_id")
    private String openId;

    @OneToOne
    @JoinColumn(name = "user_id")
    @ApiModelProperty(value = "系统用户的id")
    private User user;
}
