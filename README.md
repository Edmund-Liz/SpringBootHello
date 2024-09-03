# 表情包管理网站
  期末小学期完成的一个小项目，只有一些比较基础的功能。可以上传下载删除图片，使用七牛云作为图床来避免大量图片存储在本地。
## Emoji
  字段有：id,name,url, category_id,upload_user_id,upload_time。
  id是int自增，url是一个网址而不是本地路径（因为本地不存储文件，而是缓存后发往七牛云，获得返回的url。
## User
  字段有：id，username，password;
  密码是明文存储，没写加密，id是自增int。name也添加了唯一性约束。
## 框架
  SpringBoot，Mabatis，Mysql
# 小总结
  一个后端方向的浅尝，感觉跟前端相比有一些其他的趣味。
