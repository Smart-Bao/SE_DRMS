package com.nqff.drms.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Plan {
    private Integer id;
    private String name;
    private String description;
    @TableField("category_id")
    private Integer categoryId;
    @TableField("user_id")
    private Integer userId;
    @TableLogic
    private Integer deleted;
}
