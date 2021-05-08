package com.uw.alice.common.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;
}



//默认情况下，Room 将类名称用作数据库表名称。如果您希望表具有不同的名称，请设置 @Entity 注释的 tableName 属性，如以下代码段所示
//    @Entity(tableName = "users")
//    public class User {
//        // ...
//    }

//    注意：SQLite 中的表名称不区分大小写。


//与 tableName 属性类似，Room 将字段名称用作数据库中的列名称。如果您希望列具有不同的名称，请将 @ColumnInfo 注释添加到字段，如以下代码段所示：
//    @Entity(tableName = "users")
//    public class User {
//        @PrimaryKey
//        public int id;
//
//        @ColumnInfo(name = "first_name")
//        public String firstName;
//
//        @ColumnInfo(name = "last_name")
//        public String lastName;
//    }
//
