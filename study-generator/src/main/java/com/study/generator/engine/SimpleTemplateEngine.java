package com.study.generator.engine;

import com.study.core.util.ToolUtil;
import com.study.generator.engine.base.StudyTemplateEngine;

/**
 * 通用模板生成引擎
 */
public class SimpleTemplateEngine extends StudyTemplateEngine{

    @Override
    protected void generatePageEditHtml() {
        String path = ToolUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPageEditPathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/page_edit.html.btl", path);
        System.out.println("生成编辑页面成功!");
    }

    @Override
    protected void generatePageAddHtml() {
        String path = ToolUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPageAddPathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/page_add.html.btl", path);
        System.out.println("生成添加页面成功!");
    }

    @Override
    protected void generatePageInfoJs() {
        String path = ToolUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPageInfoJsPathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/page_info.js.btl", path);
        System.out.println("生成页面详情js成功!");
    }

    @Override
    protected void generatePageJs() {
        String path = ToolUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPageJsPathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/page.js.btl", path);
        System.out.println("生成页面js成功!");
    }

    @Override
    protected void generatePageHtml() {
        String path = ToolUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPagePathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/page.html.btl", path);
        System.out.println("生成页面成功!");
    }

    @Override
    protected void generateController() {
        String controllerPath = ToolUtil.format(super.getContextConfig().getProjectPath() + super.getControllerConfig().getControllerPathTemplate(),
                ToolUtil.firstLetterToUpper(super.getContextConfig().getBizEnName()));
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/Controller.java.btl", controllerPath);
        System.out.println("生成控制器成功!");
    }

    @Override
    protected void generateSqls() {
        String path = ToolUtil.format(super.getContextConfig().getProjectPath() + super.sqlConfig.getSqlPathTemplate(),
                ToolUtil.firstLetterToUpper(super.getContextConfig().getBizEnName()));
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/menu_sql.sql.btl", path);
        System.out.println("生成sql成功!");
    }
}
