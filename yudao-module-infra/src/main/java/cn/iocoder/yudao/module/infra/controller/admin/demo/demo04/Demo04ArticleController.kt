package cn.iocoder.yudao.module.infra.controller.admin.demo.demo04

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog
import cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT
import cn.iocoder.yudao.framework.common.pojo.CommonResult
import cn.iocoder.yudao.framework.common.pojo.CommonResult.success
import cn.iocoder.yudao.framework.common.pojo.PageParam
import cn.iocoder.yudao.framework.common.pojo.PageResult
import cn.iocoder.yudao.framework.common.util.`object`.BeanUtils
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils
import cn.iocoder.yudao.module.infra.controller.admin.demo.demo04.vo.Demo04ArticlePageReqVO
import cn.iocoder.yudao.module.infra.controller.admin.demo.demo04.vo.Demo04ArticleRespVO
import cn.iocoder.yudao.module.infra.controller.admin.demo.demo04.vo.Demo04ArticleSaveReqVO
import cn.iocoder.yudao.module.infra.service.demo.demo04.Demo04ArticleService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "管理后台 - 示例文章")
@RestController
@RequestMapping("/infra/demo04-article")
@Validated
class Demo04ArticleController(
    private val demo04ArticleService: Demo04ArticleService,
) {

    @PostMapping("/create")
    @Operation(summary = "创建示例文章")
    @PreAuthorize("@ss.hasPermission('infra:demo04-article:create')")
    fun createDemo04Article(@Valid @RequestBody createReqVO: Demo04ArticleSaveReqVO): CommonResult<Long> =
        success(demo04ArticleService.createDemo04Article(createReqVO))

    @PutMapping("/update")
    @Operation(summary = "更新示例文章")
    @PreAuthorize("@ss.hasPermission('infra:demo04-article:update')")
    fun updateDemo04Article(@Valid @RequestBody updateReqVO: Demo04ArticleSaveReqVO): CommonResult<Boolean> {
        demo04ArticleService.updateDemo04Article(updateReqVO)
        return success(true)
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除示例文章")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('infra:demo04-article:delete')")
    fun deleteDemo04Article(@RequestParam("id") id: Long): CommonResult<Boolean> {
        demo04ArticleService.deleteDemo04Article(id)
        return success(true)
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除示例文章")
    @Parameter(name = "ids", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('infra:demo04-article:delete')")
    fun deleteDemo04ArticleList(@RequestParam("ids") ids: List<Long>): CommonResult<Boolean> {
        demo04ArticleService.deleteDemo04ArticleList(ids)
        return success(true)
    }

    @GetMapping("/get")
    @Operation(summary = "获得示例文章")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('infra:demo04-article:query')")
    fun getDemo04Article(@RequestParam("id") id: Long): CommonResult<Demo04ArticleRespVO> {
        val demo04Article = demo04ArticleService.getDemo04Article(id)
        return success(BeanUtils.toBean(demo04Article, Demo04ArticleRespVO::class.java))
    }

    @GetMapping("/page")
    @Operation(summary = "获得示例文章分页")
    @PreAuthorize("@ss.hasPermission('infra:demo04-article:query')")
    fun getDemo04ArticlePage(@Valid pageReqVO: Demo04ArticlePageReqVO): CommonResult<PageResult<Demo04ArticleRespVO>> {
        val pageResult = demo04ArticleService.getDemo04ArticlePage(pageReqVO)
        return success(BeanUtils.toBean(pageResult, Demo04ArticleRespVO::class.java))
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出示例文章 Excel")
    @PreAuthorize("@ss.hasPermission('infra:demo04-article:export')")
    @ApiAccessLog(operateType = [EXPORT])
    fun exportDemo04ArticleExcel(@Valid pageReqVO: Demo04ArticlePageReqVO, response: HttpServletResponse) {
        pageReqVO.pageSize = PageParam.PAGE_SIZE_NONE
        val list = demo04ArticleService.getDemo04ArticlePage(pageReqVO).list
        // 导出 Excel
        ExcelUtils.write(response, "示例文章.xls", "数据", Demo04ArticleRespVO::class.java,
            BeanUtils.toBean(list, Demo04ArticleRespVO::class.java))
    }

}
