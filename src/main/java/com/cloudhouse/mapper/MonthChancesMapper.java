package com.cloudhouse.mapper;

import com.cloudhouse.pojo.MonthChances;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MonthChancesMapper {
    public MonthChances query();
    public int update(MonthChances monthChances);
}
