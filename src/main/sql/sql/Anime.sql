--=================================================================
--这段是本地的sql语句
--=================================================================
CREATE TABLE `anime` (
  `anime_id` int(11) NOT NULL AUTO_INCREMENT,
  `anime_name` varchar(45) NOT NULL,
  `anime_number` int(5) NOT NULL DEFAULT '0',
  `anime_class` varchar(64) NOT NULL DEFAULT '未填写',
  `anime_pic` varchar(200) DEFAULT '未填写',
  `anime_info` varchar(2000) DEFAULT '未填写',
  `anime_info2` varchar(2000) DEFAULT '未填写',
  `anime_info_daoyan` varchar(45) DEFAULT '未填写',
  `anime_info_music` varchar(45) DEFAULT '未填写',
  `anime_info_de` varchar(45) DEFAULT '未填写',
  `anime_info_pic` varchar(45) DEFAULT '未填写',
  `anime_info_anime` varchar(45) DEFAULT '未填写',
  `anime_info_site` varchar(200) DEFAULT '未填写',
  `anime_info_from` varchar(45) DEFAULT '未填写',
  `anime_info_op` varchar(45) DEFAULT '未填写',
  `anime_info_opsonger` varchar(45) DEFAULT '未填写',
  `anime_info_ed` varchar(45) DEFAULT '未填写',
  `anime_info_edsonger` varchar(45) DEFAULT '未填写',
  `anime_tag` varchar(1000) DEFAULT '未填写',
  `anime_info_date` datetime DEFAULT NULL,
  `date_new` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `data_updata` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`anime_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8
--=================================================================
--
--=================================================================