package com.wangyz.jetpack.room;

/**
 * @author wangyz
 * @time 2020/4/15 17:06
 * @description StudentWithAddress
 */
public class StudentWithAddress {

    public int id;

    public String name;

    public int age;

    public int addressId;

    public String address;

    @Override
    public String toString() {
        return "StudentWithAddress{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", addressId=" + addressId +
                ", address='" + address + '\'' +
                '}';
    }
}
