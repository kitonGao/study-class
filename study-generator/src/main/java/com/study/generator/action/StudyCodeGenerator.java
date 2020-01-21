package com.study.generator.action;

import com.study.generator.action.config.StudyGeneratorConfig;

/**
 * 代码生成器，可以生成实体，dao,service, controller, html,js
 */
public class StudyCodeGenerator {

    public static void main(String[] args) {

       /**
         * Mybatis-Plus的代码生成器:
         *      mp的代码生成器可以生成实体,mapper,mapper对应的xml,service
                */
        StudyGeneratorConfig gunsGeneratorConfig = new StudyGeneratorConfig();
        gunsGeneratorConfig.doMpGeneration();

        /**
         * guns的生成器:
         *      guns的代码生成器可以生成controller,html页面,页面对应的js
         */
        gunsGeneratorConfig.doGunsGeneration();

    }




}
