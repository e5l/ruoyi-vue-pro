package cn.iocoder.yudao.module.infra.dal.mysql.demo.demo04

import cn.iocoder.yudao.framework.common.pojo.PageResult
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX
import cn.iocoder.yudao.module.infra.controller.admin.demo.demo04.vo.Demo04ArticlePageReqVO
import cn.iocoder.yudao.module.infra.dal.dataobject.demo.demo04.Demo04ArticleDO
import org.apache.ibatis.annotations.Mapper

/**
 * 示例文章 Mapper
 *
 * @author 芋道源码
 */
@Mapper
interface Demo04ArticleMapper : BaseMapperX<Demo04ArticleDO> {

    fun selectPage(reqVO: Demo04ArticlePageReqVO): PageResult<Demo04ArticleDO> = selectPage(
        reqVO,
        LambdaQueryWrapperX<Demo04ArticleDO>()
            .likeIfPresent(Demo04ArticleDO::title, reqVO.title)
            .likeIfPresent(Demo04ArticleDO::author, reqVO.author)
            .eqIfPresent(Demo04ArticleDO::category, reqVO.category)
            .betweenIfPresent(BaseDO::getCreateTime, reqVO.createTime)
            .orderByDesc(Demo04ArticleDO::id)
    )

}
