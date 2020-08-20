package cn.web.management.mapper;

import cn.web.management.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * from user where username=#{username} and password=#{password}")
    User login(String username, String password);
    @Select("SELECT * from user where username=#{username}")
    User getuser(String username);
    @Insert("insert into user (username,password) values (#{username},#{password})")
    void addUser(String username, String password);
}
