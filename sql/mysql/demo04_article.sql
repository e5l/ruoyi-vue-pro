-- ----------------------------
-- demo04：Kotlin 版本的单表（增删改查）示例
--
-- 这是一个增量脚本，请在已导入 ruoyi-vue-pro.sql 之后执行。
-- 其它数据库（PostgreSQL / Oracle / DM 等）请按对应方言自行调整类型：
--   bigint -> int8/number，datetime -> timestamp，bit(1) -> boolean/number(1)
-- ----------------------------

-- ----------------------------
-- Table structure for yudao_demo04_article
--
-- 注意 tenant_id：项目开启了多租户（yudao.tenant.enable=true）且 ignore-tables 为空，
-- 因此租户插件会给所有表的 SQL 自动追加 tenant_id 条件，这一列必须存在。
-- ----------------------------
DROP TABLE IF EXISTS `yudao_demo04_article`;
CREATE TABLE `yudao_demo04_article`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `author` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '作者',
  `category` tinyint NOT NULL COMMENT '分类',
  `publish_time` datetime NOT NULL COMMENT '发布时间',
  `view_count` int NOT NULL DEFAULT 0 COMMENT '浏览量',
  `summary` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '摘要',
  `cover_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '封面',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '示例文章表（Kotlin）';

-- ----------------------------
-- Records of yudao_demo04_article
--
-- 第一行 summary/cover_url 有值，第二行为 NULL，方便验证可空字段
-- ----------------------------
BEGIN;
INSERT INTO `yudao_demo04_article` (`title`, `author`, `category`, `publish_time`, `view_count`, `summary`, `cover_url`, `creator`, `updater`, `tenant_id`) VALUES ('Kotlin 的空安全到底省了什么', '芋道源码', 1, '2026-08-01 10:00:00', 1024, '把「可不可以为 null」从注释和注解里搬进类型系统', 'https://kotlinlang.org/assets/images/open-graph/general.png', '1', '1', 1);
INSERT INTO `yudao_demo04_article` (`title`, `author`, `category`, `publish_time`, `view_count`, `summary`, `cover_url`, `creator`, `updater`, `tenant_id`) VALUES ('data class 与 Lombok 的六个注解', '芋道源码', 2, '2026-08-02 10:00:00', 512, NULL, NULL, '1', '1', 1);
COMMIT;

-- ----------------------------
-- Records of system_menu
--
-- 2478 是 demo01 的菜单 id，1070 是「代码生成示例」目录，这里挂在同一个目录下。
-- 菜单 id 取 2490+，避免与内置数据冲突。
-- ----------------------------
BEGIN;
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `updater`, `deleted`) VALUES (2490, '单表（Kotlin）', '', 2, 6, 1070, 'demo04-article', 'ep:document', 'infra/demo/demo04/index', 'Demo04Article', 0, b'1', b'1', b'1', '1', '1', b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `updater`, `deleted`) VALUES (2491, '示例文章查询', 'infra:demo04-article:query', 3, 1, 2490, '', '', '', NULL, 0, b'1', b'1', b'1', '1', '1', b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `updater`, `deleted`) VALUES (2492, '示例文章创建', 'infra:demo04-article:create', 3, 2, 2490, '', '', '', NULL, 0, b'1', b'1', b'1', '1', '1', b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `updater`, `deleted`) VALUES (2493, '示例文章更新', 'infra:demo04-article:update', 3, 3, 2490, '', '', '', NULL, 0, b'1', b'1', b'1', '1', '1', b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `updater`, `deleted`) VALUES (2494, '示例文章删除', 'infra:demo04-article:delete', 3, 4, 2490, '', '', '', NULL, 0, b'1', b'1', b'1', '1', '1', b'0');
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `visible`, `keep_alive`, `always_show`, `creator`, `updater`, `deleted`) VALUES (2495, '示例文章导出', 'infra:demo04-article:export', 3, 5, 2490, '', '', '', NULL, 0, b'1', b'1', b'1', '1', '1', b'0');
COMMIT;

-- ----------------------------
-- Records of system_dict_type / system_dict_data
--
-- RespVO.category 上标了 @DictFormat("infra_demo04_article_category")，导出 Excel 时需要它
-- ----------------------------
BEGIN;
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `updater`, `deleted`, `deleted_time`) VALUES ('示例文章分类', 'infra_demo04_article_category', 0, 'demo04 使用', '1', '1', b'0', NULL);
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `updater`, `deleted`) VALUES (1, '技术', '1', 'infra_demo04_article_category', 0, 'primary', '', '', '1', '1', b'0');
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `updater`, `deleted`) VALUES (2, '随笔', '2', 'infra_demo04_article_category', 0, 'success', '', '', '1', '1', b'0');
COMMIT;
