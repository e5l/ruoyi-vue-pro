package cn.iocoder.yudao.module.infra.service.demo.demo04

import cn.iocoder.yudao.framework.common.pojo.PageResult
import cn.iocoder.yudao.module.infra.controller.admin.demo.demo04.vo.Demo04ArticlePageReqVO
import cn.iocoder.yudao.module.infra.controller.admin.demo.demo04.vo.Demo04ArticleSaveReqVO
import cn.iocoder.yudao.module.infra.dal.dataobject.demo.demo04.Demo04ArticleDO
import jakarta.validation.Valid

/**
 * 示例文章 Service 接口
 *
 * @author 芋道源码
 */
interface Demo04ArticleService {

    /**
     * 创建示例文章
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    fun createDemo04Article(@Valid createReqVO: Demo04ArticleSaveReqVO): Long

    /**
     * 更新示例文章
     *
     * @param updateReqVO 更新信息
     */
    fun updateDemo04Article(@Valid updateReqVO: Demo04ArticleSaveReqVO)

    /**
     * 删除示例文章
     *
     * @param id 编号
     */
    fun deleteDemo04Article(id: Long)

    /**
     * 批量删除示例文章
     *
     * @param ids 编号
     */
    fun deleteDemo04ArticleList(ids: List<Long>)

    /**
     * 获得示例文章
     *
     * @param id 编号
     * @return 示例文章
     */
    fun getDemo04Article(id: Long): Demo04ArticleDO?

    /**
     * 获得示例文章分页
     *
     * @param pageReqVO 分页查询
     * @return 示例文章分页
     */
    fun getDemo04ArticlePage(pageReqVO: Demo04ArticlePageReqVO): PageResult<Demo04ArticleDO>

}
