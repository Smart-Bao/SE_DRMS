package com.nqff.drms.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlanKeywordRelation {
    private Integer id;
    @TableField("plan_id")
    private Integer planId;
    @TableField("keyword_id")
    private Integer keywordId;
    @TableLogic
    private Integer deleted;
}
