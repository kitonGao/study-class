package com.study.core.base.tips;

/**
 * 操作成功返回前台的信息
 */
public class SuccessTip extends Tip{

    public SuccessTip(){
        super.code = 200;
        super.message = "操作成功";
    }


}
