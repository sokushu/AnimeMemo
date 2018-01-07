
--查询数据库中所有的表名

select table_name from information_schema.tables where table_schema = "the_bangumi";

--查询数据
SELECT * FROM the_bangumi.user_anime;
SELECT * FROM the_bangumi.anime;
SELECT * FROM the_bangumi.users;
SELECT * FROM the_bangumi.comments;