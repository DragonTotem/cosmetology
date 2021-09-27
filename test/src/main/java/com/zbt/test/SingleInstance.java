package com.zbt.test;

/**
 * Author: zbt
 * Time: 2021/5/23 22:24
 * Description: This is SingleInstance
 * @author loong
 */
public enum SingleInstance {
    INSTANCE;

    public SingleInstance getInstance() {
        return INSTANCE;
    }
}
