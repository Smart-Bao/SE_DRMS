package com.nqff.drms.controller;

import com.nqff.drms.algorithm.Algorithm;
import com.nqff.drms.pojo.Plan;
import com.nqff.drms.service.PlanService;
import com.nqff.drms.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/plans")
@Tag(name = "方案接口")
public class PlanController {
    @Autowired
    private PlanService planService;
    @Autowired
    private Algorithm algorithm;

    @Operation(summary = "新增方案", security = {@SecurityRequirement(name = "Authorization")})
    @PostMapping(path = "/create/{subproject_id}&{user_id}")
    public Result createNewProject(@PathVariable Integer subproject_id,@PathVariable Integer user_id,@RequestBody List<Plan> plans) {
        if(planService.isPlanExist(subproject_id)){
            return Result.FAIL("this subproject has plan");
        }
        Plan plan = algorithm.createNewPlan(plans,subproject_id,user_id);
        planService.insertPlan(plan);
        return Result.SUCCESS(null);
    }

    @Operation(summary = "根据 id 删除方案", security = {@SecurityRequirement(name = "Authorization")})
    @DeleteMapping(path = "/{id}")
    public Result deletePlanById(@PathVariable Integer id) {
        planService.removeById(id);
        return Result.SUCCESS(null);
    }

    @Operation(summary = "根据 id 获取指定方案信息", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping(path = "/{id}")
    public Result getPlanById(@PathVariable Integer id) {
        Plan plan = planService.selectPlanById(id);
        if (plan == null) {
            return Result.FAIL("not found", null);
        }
        return Result.SUCCESS(plan);
    }

    @Operation(summary = "更新方案信息", security = {@SecurityRequirement(name = "Authorization")})
    @PutMapping()
    public Result updateProjectInfo(@RequestBody Plan plan) {
        planService.updateById(plan);
        return Result.SUCCESS(null);
    }

    @Operation(summary = "根据子项目id获取相似方案", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping(path = "/similar/{id}")
    public Result getSimilarPlans(@PathVariable Integer id) {
        List<Plan> plans = algorithm.getSimilarPlans(id);
        return Result.SUCCESS(plans);
    }


}
