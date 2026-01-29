package com.sp001.mapper;

import com.sp001.pojo.Students;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface StudentsMapper {
    Students findById(@Param("id") int id);
    Students findByNickname(@Param("nickname") String nickname);
    int insert(Students students);
    int update(Students students);
    int deleteById(@Param("id") int id);

}
