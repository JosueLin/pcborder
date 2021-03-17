package com.cloudhouse.mapper;

import com.cloudhouse.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    public User queryByUser(User user);
    public int insertUser(User user);
    public int deleteUser(User user);
    public int updateUser(User user);
    public int updateAllChances(int chances);
}
