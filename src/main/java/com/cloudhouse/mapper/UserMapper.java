package com.cloudhouse.mapper;

import com.cloudhouse.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;



@Mapper
@Repository
public interface UserMapper {
    public User queryByUser(User user);
    public int insertUser(User user);
    public int deleteUser(User user);
    public int updateUserChances(User user);
    public int updateAllChances(int chances);
    public int updateUserWeChat(User user);
}
