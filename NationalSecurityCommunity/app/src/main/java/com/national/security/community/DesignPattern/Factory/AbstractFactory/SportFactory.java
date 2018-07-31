package com.national.security.community.DesignPattern.Factory.AbstractFactory;

/**
 * @description: 具体工厂类--跑车族
 * @author: ljn
 * @time: 2018/7/30
 */
public class SportFactory extends SportAbstractFactory {
    @Override
    ISportCar getProductR8() {
        return new ProductR8();
    }
}
