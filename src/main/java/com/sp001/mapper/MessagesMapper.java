package com.sp001.mapper;

import com.sp001.pojo.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface MessagesMapper {

    // 插入留言
    @Insert("INSERT INTO messages (name, email, is_secret, message_type, content) " +
            "VALUES (#{name}, #{email}, #{isSecret}, #{messageType}, #{content})")
    int insertMessage(Message message);

    // 查询所有留言（按时间倒序）
    @Select("SELECT * FROM messages WHERE status = 1 ORDER BY created_time DESC")
    List<Message> selectAllMessages();

    // 查询公开留言（不保密的）
    @Select("SELECT * FROM messages WHERE status = 1 AND is_secret = 0 ORDER BY created_time DESC")
    List<Message> selectPublicMessages();
}