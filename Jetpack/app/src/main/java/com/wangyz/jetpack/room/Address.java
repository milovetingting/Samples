package com.wangyz.jetpack.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author wangyz
 * @time 2020/4/15 16:33
 * @description Address
 */
@Entity
public class Address {

    @PrimaryKey
    public int aid;

    @ColumnInfo(name = "address")
    public String address;

    public Address(int aid, String address) {
        this.aid = aid;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "aid=" + aid +
                ", address='" + address + '\'' +
                '}';
    }
}
