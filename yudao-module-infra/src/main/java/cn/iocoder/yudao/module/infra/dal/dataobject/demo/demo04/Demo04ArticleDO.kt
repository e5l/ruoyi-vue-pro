package cn.iocoder.yudao.module.infra.dal.dataobject.demo.demo04

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO
import com.baomidou.mybatisplus.annotation.KeySequence
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.time.LocalDateTime

/**
 * 示例文章 DO
 *
 * @author 芋道源码
 */
@TableName("yudao_demo04_article")
@KeySequence("yudao_demo04_article_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
data class Demo04ArticleDO(

    /**
     * 编号
     */
    @TableId
    var id: Long? = null,
    /**
     * 标题
     */
    var title: String,
    /**
     * 作者
     */
    var author: String,
    /**
     * 分类
     *
     * 枚举 {@link TODO infra_demo04_article_category 对应的类}
     */
    var category: Int,
    /**
     * 发布时间
     */
    var publishTime: LocalDateTime,
    /**
     * 浏览量
     */
    var viewCount: Int? = null,
    /**
     * 摘要
     */
    var summary: String? = null,
    /**
     * 封面
     */
    var coverUrl: String? = null,

) : BaseDO()
