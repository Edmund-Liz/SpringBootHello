package com.springdemo.springboothello.Mapper;

import com.springdemo.springboothello.Model.Emoji;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmojiMapper {

    void insertEmoji(Emoji emoji);

    void deleteEmoji(@Param("id") int id);

    Emoji getEmojiById(@Param("id") int id);

    List<Emoji> getEmojisByKey(@Param("key") String key);

    List<Emoji> getEmojisByCategoryId(@Param("id") int category_id);

    List<Emoji> getEmojisByUserId(@Param("id") int user_id);

    List<Emoji> getEmojisByTime(@Param("num")int num);


    void insertCategory(@Param("name") String name);

    String getCategoryNameById(@Param("id") int id);

    Integer getCategoryIdByName(@Param("name") String name);

}
