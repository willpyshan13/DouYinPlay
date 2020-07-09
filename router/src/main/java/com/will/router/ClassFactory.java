package com.will.router;

/**
 * Desc:类工厂
 * <p>
 * @Author: will
 */
public interface ClassFactory {

    Class getTargetClass(String uri);
}
