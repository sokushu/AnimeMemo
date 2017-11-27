
--查询数据库中所有的表名

select table_name from information_schema.tables where table_schema = "thebangumi";

--查询动画数据

SELECT * FROM thebangumi.anime;
SELECT * FROM thebangumi.anime WHERE anime_id = 16;