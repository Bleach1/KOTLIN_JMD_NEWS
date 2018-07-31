package com.national.security.community.DesignPattern.Builder;

/**
 * @description: 构造者模式
 * @author: ljn
 * @time: 2018/7/30
 */
public class Person {
    private String name;
    private int age;
    private double height;

    private Person(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.height = builder.height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    static class Builder {
        private String name;
        private int age;
        private double height;

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder height(double height) {
            this.height = height;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
