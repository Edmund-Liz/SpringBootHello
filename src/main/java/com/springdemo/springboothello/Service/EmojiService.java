package com.springdemo.springboothello.Service;

import com.springdemo.springboothello.Model.Emoji;

import java.util.List;

public interface EmojiService {

    Emoji uploadEmoji(Emoji emoji);

    void deleteEmoji(int id);

    Emoji getEmojiById(int id);

    List<Emoji> getEmojisByKey(String name);

    List<Emoji> getEmojisByCategory(int category_id);

    List<Emoji> getEmojisByUserId(int userId);

    List<Emoji> getEmojisByTime(int num);

    Integer insertCategory(String name);

    String getCategoryNameById(int id);

    Integer getCategoryIdByName(String name);
}
