package com.example.springbootdemo123.mapper;

import com.example.springbootdemo123.entity.UserBean;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    //查询，实现登陆功能
    @Select("SELECT * FROM user WHERE name = #{name} AND password = #{password}")
    UserBean  getUserByNameAndPassword(@Param("name") String name, @Param("password") String password);

    //增加 实现注册功能
    @Insert("insert into user(name,password,mail,phoneNumber)values(#{name},#{password},#{mail},#{phoneNumber})")
    void saveUser(@Param("name") String name, @Param("password") String password, @Param("mail") String mail,
                  @Param("phoneNumber") String phoneNumber);

    //增加 存入token
    @Insert("insert into user_token (token)values(#{token})")
    void saveToken (@Param("token") String token);

    //登陆后存储账户邮箱
    @Update("UPDATE user SET mail =#{mail} where name =#{name}")
    void saveMail(@Param("name") String name, @Param("mail") String mail);

    //登陆后存储手机号
    @Update("UPDATE user SET mail =#{PhoneNumber} where name =#{name}")
    void savePhoneNumber(@Param("name") String name, @Param("PhoneNumber") String PhoneNumber);

    //查询token
    @Select("SELECT count(*) FROM user_token WHERE token = #{token}")
    int searchToken(@Param("token") String token);

}
