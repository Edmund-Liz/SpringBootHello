package com.springdemo.springboothello.Controller;

import com.springdemo.springboothello.Mapper.EmojiMapper;
import com.springdemo.springboothello.Model.Emoji;
import com.springdemo.springboothello.Exception.RequestException;
import com.springdemo.springboothello.Service.EmojiServiceImp;
import com.springdemo.springboothello.Service.UploadImageServiceImp;
import com.springdemo.springboothello.Model.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/emojis")
public class EmojiController {

    private final EmojiServiceImp emojiServiceImp;
    private final UploadImageServiceImp uploadImageServiceImp;

    @Autowired
    public EmojiController(EmojiServiceImp emojiServiceImp, UploadImageServiceImp uploadImageServiceImp) {
        this.emojiServiceImp = emojiServiceImp;
        this.uploadImageServiceImp = uploadImageServiceImp;
    }

    @PostMapping("/upload")
    public ResponseResult<Emoji> uploadEmoji
            (@RequestParam String name, @RequestParam MultipartFile file
                    , @RequestParam Integer category_id, @RequestParam Integer upload_user_id) {
        String url = uploadImageServiceImp.getURL(file);

        Emoji emoji = new Emoji(name, url, category_id, upload_user_id);

        Emoji emojiReturn = emojiServiceImp.uploadEmoji(emoji);
        return ResponseResult.success(emojiReturn);
    }

    @DeleteMapping("/delete")
    public ResponseResult<String> deleteEmoji(@RequestParam Integer id) {
        emojiServiceImp.deleteEmoji(id);
        return ResponseResult.success("Deleted emoji successfully");
    }

    @PostMapping("/get/id")
    public ResponseResult<Emoji> getEmojiById(@RequestParam Integer id) {
        Emoji emoji = emojiServiceImp.getEmojiById(id);
        return ResponseResult.success(emoji);
    }

    @GetMapping("/gets")
    public ResponseResult<List<Emoji>> getEmojis(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String key) {

        List<Emoji> emojis = new ArrayList<>();
        if (!key.trim().isEmpty()) {
            emojis = emojiServiceImp.getEmojisByKey(key);
        } else if (id != null) {
            emojis = emojiServiceImp.getEmojisByCategory(id);
        }
        if (emojis.isEmpty()){
            throw new RequestException("getEmojis","Either 'id' or 'key' must be provided");
        }

        return ResponseResult.success(emojis);
    }

    @GetMapping("/get/user_id")
    public ResponseResult<List<Emoji>> getEmojiByUserId(@RequestParam Integer user_id) {
        if (user_id == null||user_id <= 0) {
            throw new RequestException("getEmojiByUserId","user_id is invalid");
        }
        List<Emoji> emojis = emojiServiceImp.getEmojisByUserId(user_id);
        return ResponseResult.success(emojis);

    }

    @GetMapping("get/time")
    public ResponseResult<List<Emoji>> getEmojiByTime(@RequestParam Integer num){
        if (num <= 0) {
            throw new RequestException("getEmojiByTime","num is invalid");
        }
        List<Emoji> emojis = emojiServiceImp.getEmojisByTime(num);
        return ResponseResult.success(emojis);
    }

    @PostMapping("create_category")
    public ResponseResult<Integer> createCategory(@RequestParam String category_name) {
        int id=emojiServiceImp.insertCategory(category_name);
        return ResponseResult.success(id);
    }

    @GetMapping("get_category/id")
    public ResponseResult<String> getCategoryById(@RequestParam Integer id) {
        String name=emojiServiceImp.getCategoryNameById(id);
        return ResponseResult.success(name);
    }

    @GetMapping("get_category/name")
    public ResponseResult<Integer> getCategoryByName(@RequestParam String name) {
        int id=emojiServiceImp.getCategoryIdByName(name);
        return ResponseResult.success(id);
    }

    @ExceptionHandler(RequestException.class)
    public ResponseResult<String> handleRequestException(RequestException e) {
        return ResponseResult.fail(e.getMessage());
    }

}
