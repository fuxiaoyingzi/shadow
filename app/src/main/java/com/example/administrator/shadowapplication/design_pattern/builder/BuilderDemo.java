package com.example.administrator.shadowapplication.design_pattern.builder;

/**
 * Created by Administrator on 2017/6/22.
 */

public class BuilderDemo {
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String address;

    public BuilderDemo(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.address = builder.address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public static class UserBuilder {
        private static String firstName;
        private static String lastName;
        private static int age;
        private static String address;

        public UserBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public BuilderDemo builder() throws Exception {
            BuilderDemo demo = new BuilderDemo(this);
            if (demo.age > 125) {
                throw new Exception("age too larger");
            }
            return demo;
        }

    }
}
