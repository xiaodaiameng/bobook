package com.sp001.mapper;

import com.sp001.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MessagesMapper {
    int insertMessage(Message message);
    List<Message> selectAllPublicMessages();
    List<Message> selectAllMessages();
    int updateMessageStatus(int id, int status);
    int deleteMessage(int id);

}