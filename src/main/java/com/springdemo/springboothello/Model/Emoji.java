package com.springdemo.springboothello.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class Emoji {
    private int id;
    private String name;
    private String url;

    private int category_id;
    private int upload_user_id;
    private Timestamp upload_time;



    public Emoji(String emoji_name, String emoji_url, int emoji_category_id, int upload_user_id) {
        this.name = emoji_name;
        this.url = emoji_url;
        this.category_id = emoji_category_id;
        this.upload_user_id = upload_user_id;
        this.upload_time = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "Emoji{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", category_id=" + category_id +
                ", upload_user_id=" + upload_user_id +
                ", upload_time=" + upload_time +
                '}';
    }

//    public void URLEncode(){
//        this.url= URLEncoder.encode(this.url,StandardCharsets.UTF_8);
//    }
//    public void URLDecode(){
//        this.url= URLDecoder.decode(this.url, StandardCharsets.UTF_8);
//    }
}
