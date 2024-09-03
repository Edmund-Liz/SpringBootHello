package com.springdemo.springboothello.Service;

import com.springdemo.springboothello.Model.Emoji;
import com.springdemo.springboothello.Exception.RequestException;
import com.springdemo.springboothello.Mapper.EmojiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmojiServiceImp implements EmojiService{

    private final EmojiMapper emojiMapper;

    @Autowired
    public EmojiServiceImp(EmojiMapper emojiMapper) {
        this.emojiMapper = emojiMapper;
    }

    @Override
    public Emoji uploadEmoji(Emoji emoji) {
        emojiMapper.insertEmoji(emoji);
        return getEmojiById(emoji.getId());
    }

    @Override
    public void deleteEmoji(int id) {
        try{
            getEmojiById(id);
            emojiMapper.deleteEmoji(id);
        }catch (RequestException e){
            throw new RequestException("deleteEmoji","Emoji with id '"+id+"' not found");
        }
    }

    @Override
    public Emoji getEmojiById(int id) {
        Emoji emoji=emojiMapper.getEmojiById(id);
        if (emoji==null){
            throw new RequestException("getEmojiById","Emoji with id '"+id+"' not found");
        }else {
            return emoji;
        }
    }

    @Override
    public List<Emoji> getEmojisByKey(String key) {
        List<Emoji> emojis=emojiMapper.getEmojisByKey(key);
        if (emojis==null||emojis.isEmpty()){
            throw new RequestException("getEmojisByName", "Emoji with key '" +key+"' not found");
        }else {
            return emojis;
        }
    }

    @Override
    public List<Emoji> getEmojisByCategory(int category_id) {
        List<Emoji> emojis=emojiMapper.getEmojisByCategoryId(category_id);
        if (emojis==null||emojis.isEmpty()){
            throw new RequestException("getEmojisByCategory","Emoji with category_id '"+category_id+"' not found");
        }else {
            return emojis;
        }
    }

    @Override
    public List<Emoji> getEmojisByUserId(int userId) {
        List<Emoji> emojis=emojiMapper.getEmojisByUserId(userId);
        if (emojis==null||emojis.isEmpty()){
            throw new RequestException("getEmojisByUserId","Emoji with user_id '"+userId+"' not found");
        }else {
            return emojis;
        }
    }

    @Override
    public List<Emoji> getEmojisByTime(int num) {
        List<Emoji> emojis=emojiMapper.getEmojisByTime(num);
        if (emojis!=null&& !emojis.isEmpty()){
            return emojis;
        }else {
            throw new RequestException("getEmojisByTime","does not found any emojis");
        }
    }

    @Override
    public Integer insertCategory(String name) {
        Integer id;
        id=emojiMapper.getCategoryIdByName(name);
        if (id!=null){
            throw new RequestException("insertCategory","Category with name '"+name+"' already exist");
        }else {
            emojiMapper.insertCategory(name);
            return emojiMapper.getCategoryIdByName(name);
        }
    }

    @Override
    public String getCategoryNameById(int id) {
        String name;
        name=emojiMapper.getCategoryNameById(id);
        if (name!=null&& !name.isEmpty()){
            return name;
        }else {
            throw new RequestException("getCategoryNameById","Category with id '"+id+"' not found");
        }
    }

    @Override
    public Integer getCategoryIdByName(String name) {
        Integer id;
        id=emojiMapper.getCategoryIdByName(name);
        if (id!=null){
            return id;
        }else {
            throw new RequestException("getCategoryIdByName","Category with name '"+name+"' not found");
        }
    }
}
