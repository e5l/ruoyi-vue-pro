package cn.iocoder.yudao.module.infra.controller.admin.demo.demo04.vo

import cn.idev.excel.annotation.ExcelIgnoreUnannotated
import cn.idev.excel.annotation.ExcelProperty
import cn.iocoder.yudao.framework.common.pojo.PageParam
import cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import lombok.EqualsAndHashCode
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

@Schema(description = "管理后台 - 示例文章新增/修改 Request VO")
@EqualsAndHashCode
class Demo04ArticleSaveReqVO(

    @Schema(description = "编号", example = "21555")
    var id: Long? = null,

    @Schema(description = "标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "Kotlin 的空安全")
    @NotBlank(message = "标题不能为空")
    var title: String? = null,

    @Schema(description = "作者", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋道源码")
    @NotBlank(message = "作者不能为空")
    var author: String? = null,

    @Schema(description = "分类", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "分类不能为空")
    var category: Int? = null,

    @Schema(description = "发布时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "发布时间不能为空")
    var publishTime: LocalDateTime? = null,

    @Schema(description = "摘要", example = "本文介绍 Kotlin 的空安全体系")
    var summary: String? = null,

    @Schema(description = "封面")
    var coverUrl: String? = null,

)

@Schema(description = "管理后台 - 示例文章 Response VO")
@ExcelIgnoreUnannotated
@EqualsAndHashCode
data class Demo04ArticleRespVO(

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "21555")
    @ExcelProperty("编号")
    var id: Long,

    @Schema(description = "标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "Kotlin 的空安全")
    @ExcelProperty("标题")
    var title: String,

    @Schema(description = "作者", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋道源码")
    @ExcelProperty("作者")
    var author: String,

    @Schema(description = "分类", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty(value = ["分类"], converter = DictConvert::class)
    @DictFormat("infra_demo04_article_category") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    var category: Int,

    @Schema(description = "发布时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发布时间")
    var publishTime: LocalDateTime,

    @Schema(description = "浏览量", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @ExcelProperty("浏览量")
    var viewCount: Int,

    @Schema(description = "摘要")
    @ExcelProperty("摘要")
    var summary: String? = null,

    @Schema(description = "封面")
    @ExcelProperty("封面")
    var coverUrl: String? = null,

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    var createTime: LocalDateTime? = null,

)

@Schema(description = "管理后台 - 示例文章分页 Request VO")
@EqualsAndHashCode
class Demo04ArticlePageReqVO(

    @Schema(description = "标题", example = "Kotlin")
    var title: String? = null,

    @Schema(description = "作者", example = "芋道源码")
    var author: String? = null,

    @Schema(description = "分类", example = "1")
    var category: Int? = null,

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    var createTime: Array<LocalDateTime>? = null,

) : PageParam()
