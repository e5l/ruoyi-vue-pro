package cn.iocoder.yudao.module.infra.service.demo.demo04

import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception
import cn.iocoder.yudao.framework.common.pojo.PageResult
import cn.iocoder.yudao.framework.common.util.`object`.BeanUtils
import cn.iocoder.yudao.module.infra.controller.admin.demo.demo04.vo.Demo04ArticlePageReqVO
import cn.iocoder.yudao.module.infra.controller.admin.demo.demo04.vo.Demo04ArticleSaveReqVO
import cn.iocoder.yudao.module.infra.dal.dataobject.demo.demo04.Demo04ArticleDO
import cn.iocoder.yudao.module.infra.dal.mysql.demo.demo04.Demo04ArticleMapper
import cn.iocoder.yudao.module.infra.enums.ErrorCodeConstants.DEMO04_ARTICLE_NOT_EXISTS
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

/**
 * 示例文章 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
class Demo04ArticleServiceImpl(
    private val demo04ArticleMapper: Demo04ArticleMapper,
) : Demo04ArticleService {

    override fun createDemo04Article(createReqVO: Demo04ArticleSaveReqVO): Long {
        // 插入
        val demo04Article = BeanUtils.toBean(createReqVO, Demo04ArticleDO::class.java)
        demo04ArticleMapper.insert(demo04Article)
        // 返回
        return demo04Article.id!!
    }

    override fun updateDemo04Article(updateReqVO: Demo04ArticleSaveReqVO) {
        // 校验存在
        validateDemo04ArticleExists(updateReqVO.id)
        // 更新
        val updateObj = BeanUtils.toBean(updateReqVO, Demo04ArticleDO::class.java)
        demo04ArticleMapper.updateById(updateObj)
    }

    override fun deleteDemo04Article(id: Long) {
        // 校验存在
        validateDemo04ArticleExists(id)
        // 删除
        demo04ArticleMapper.deleteById(id)
    }

    override fun deleteDemo04ArticleList(ids: List<Long>) {
        // 校验存在
        validateDemo04ArticleExists(ids)
        // 删除
        demo04ArticleMapper.deleteByIds(ids)
    }

    private fun validateDemo04ArticleExists(ids: List<Long>) {
        val list = demo04ArticleMapper.selectByIds(ids)
        if (list.isEmpty() || list.size != ids.size) {
            throw exception(DEMO04_ARTICLE_NOT_EXISTS)
        }
    }

    private fun validateDemo04ArticleExists(id: Long?) {
        id?.let { demo04ArticleMapper.selectById(it) } ?: throw exception(DEMO04_ARTICLE_NOT_EXISTS)
    }

    override fun getDemo04Article(id: Long): Demo04ArticleDO? = demo04ArticleMapper.selectById(id)

    override fun getDemo04ArticlePage(pageReqVO: Demo04ArticlePageReqVO): PageResult<Demo04ArticleDO> =
        demo04ArticleMapper.selectPage(pageReqVO)

}
