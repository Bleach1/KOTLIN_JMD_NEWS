package com.national.security.community.DesignPattern.Factory.AbstractFactory;

/**
 * @description: 具体工厂类--商务族
 * @author: ljn
 * @time: 2018/7/30
 */
public class BusinessFactory extends BusinessAbstractFactory {
    @SuppressWarnings("SpellCheckingInspection")
    @Override
    IBusinessCar getProductHongQi() {
        return new ProductHongQi();
    }
}
