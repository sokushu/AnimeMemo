CREATE TABLE `users` (
  `uid` int(11) NOT NULL COMMENT '主键id',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `email` varchar(32) NOT NULL,
  `url` varchar(32) NOT NULL,
  `user_pic` varchar(45) DEFAULT NULL,
  `info` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `back_pic` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8