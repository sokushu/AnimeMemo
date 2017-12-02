package xyz.bangumi.mysql.bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 动画
 * @author miri
 *
 */
public class Anime {
		/**
		 * 动画ID
		 */
		private Integer anime_id;
		/**
		 * 动画名字
		 */
		@NotEmpty(message = "还没有写动画名呢")
		private String anime_name;
		/**
		 * 动画集数
		 */
		@NotEmpty(message = "请填写动画集数")
		@Length(max = 5, message = "喂，你够了，哪有这么长的动画")
		private Integer anime_number;
		/**
		 * 类别，比如说TV版，OVA
		 */
		private String anime_class;
		/**
		 * 动画的封面图片
		 */
		private String anime_pic;
		/**
		 * 剧情简介
		 */
		@NotEmpty(message = "要填写剧情简介哦")
		private String anime_info;
		/**
		 * 对它的一些备注信息
		 */
		private String anime_info2;
		/**
		 * 监督，导演
		 */
		private String anime_info_daoyan;
		/**
		 * 动画的音乐制作
		 */
		private String anime_info_music;
		/**
		 * 角色设计（日语 KyaRaKuTa—— DeZaInn）
		 */
		private String anime_info_de;
		/**
		 * 作画监督
		 */
		private String anime_info_pic;
		/**
		 * 动画制作公司
		 */
		private String anime_info_anime;
		/**
		 * 动画的官方网站
		 */
		private String anime_info_site;
		/**
		 * 动画放送日期
		 */
		private String anime_info_date;
		/**
		 * 动画原作
		 */
		private String anime_info_from;
		/**
		 * 片头曲名字
		 */
		private String anime_info_op;
		/**
		 * 片头曲演唱者
		 */
		private String anime_info_opsonger;
		/**
		 * 片尾曲名字
		 */
		private String anime_info_ed;
		/**
		 * 片尾曲演唱者
		 */
		private String anime_info_edsonger;
		/**
		 * 动画更新日期（只用来读）
		 */
		private String date;
		/**
		 * 动画创建日期（只用来读）
		 */
		private String date_new;
//==================================================
		public Integer getAnime_id() {
			return anime_id;
		}
		public void setAnime_id(Integer anime_id) {
			this.anime_id = anime_id;
		}
		public String getAnime_name() {
			return anime_name;
		}
		public void setAnime_name(String anime_name) {
			this.anime_name = anime_name;
		}
		public Integer getAnime_number() {
			return anime_number;
		}
		public void setAnime_number(Integer anime_number) {
			this.anime_number = anime_number;
		}
		public String getAnime_class() {
			return anime_class;
		}
		public void setAnime_class(String anime_class) {
			this.anime_class = anime_class;
		}
		public String getAnime_pic() {
			return anime_pic;
		}
		public void setAnime_pic(String anime_pic) {
			this.anime_pic = anime_pic;
		}
		public String getAnime_info() {
			return anime_info;
		}
		public void setAnime_info(String anime_info) {
			this.anime_info = anime_info;
		}
		public String getAnime_info2() {
			return anime_info2;
		}
		public void setAnime_info2(String anime_info2) {
			this.anime_info2 = anime_info2;
		}
		public String getAnime_info_daoyan() {
			return anime_info_daoyan;
		}
		public void setAnime_info_daoyan(String anime_info_daoyan) {
			this.anime_info_daoyan = anime_info_daoyan;
		}
		public String getAnime_info_music() {
			return anime_info_music;
		}
		public void setAnime_info_music(String anime_info_music) {
			this.anime_info_music = anime_info_music;
		}
		public String getAnime_info_de() {
			return anime_info_de;
		}
		public void setAnime_info_de(String anime_info_de) {
			this.anime_info_de = anime_info_de;
		}
		public String getAnime_info_pic() {
			return anime_info_pic;
		}
		public void setAnime_info_pic(String anime_info_pic) {
			this.anime_info_pic = anime_info_pic;
		}
		public String getAnime_info_anime() {
			return anime_info_anime;
		}
		public void setAnime_info_anime(String anime_info_anime) {
			this.anime_info_anime = anime_info_anime;
		}
		public String getAnime_info_site() {
			return anime_info_site;
		}
		public void setAnime_info_site(String anime_info_site) {
			this.anime_info_site = anime_info_site;
		}
		public String getAnime_info_date() {
			return anime_info_date;
		}
		public void setAnime_info_date(String anime_info_date) {
			this.anime_info_date = anime_info_date;
		}
		public String getAnime_info_from() {
			return anime_info_from;
		}
		public void setAnime_info_from(String anime_info_from) {
			this.anime_info_from = anime_info_from;
		}
		public String getAnime_info_op() {
			return anime_info_op;
		}
		public void setAnime_info_op(String anime_info_op) {
			this.anime_info_op = anime_info_op;
		}
		public String getAnime_info_opsonger() {
			return anime_info_opsonger;
		}
		public void setAnime_info_opsonger(String anime_info_opsonger) {
			this.anime_info_opsonger = anime_info_opsonger;
		}
		public String getAnime_info_ed() {
			return anime_info_ed;
		}
		public void setAnime_info_ed(String anime_info_ed) {
			this.anime_info_ed = anime_info_ed;
		}
		public String getAnime_info_edsonger() {
			return anime_info_edsonger;
		}
		public void setAnime_info_edsonger(String anime_info_edsonger) {
			this.anime_info_edsonger = anime_info_edsonger;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getDate_new() {
			return date_new;
		}
		public void setDate_new(String date_new) {
			this.date_new = date_new;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((anime_class == null) ? 0 : anime_class.hashCode());
			result = prime * result + ((anime_id == null) ? 0 : anime_id.hashCode());
			result = prime * result + ((anime_info == null) ? 0 : anime_info.hashCode());
			result = prime * result + ((anime_info2 == null) ? 0 : anime_info2.hashCode());
			result = prime * result + ((anime_info_anime == null) ? 0 : anime_info_anime.hashCode());
			result = prime * result + ((anime_info_daoyan == null) ? 0 : anime_info_daoyan.hashCode());
			result = prime * result + ((anime_info_date == null) ? 0 : anime_info_date.hashCode());
			result = prime * result + ((anime_info_de == null) ? 0 : anime_info_de.hashCode());
			result = prime * result + ((anime_info_ed == null) ? 0 : anime_info_ed.hashCode());
			result = prime * result + ((anime_info_edsonger == null) ? 0 : anime_info_edsonger.hashCode());
			result = prime * result + ((anime_info_from == null) ? 0 : anime_info_from.hashCode());
			result = prime * result + ((anime_info_music == null) ? 0 : anime_info_music.hashCode());
			result = prime * result + ((anime_info_op == null) ? 0 : anime_info_op.hashCode());
			result = prime * result + ((anime_info_opsonger == null) ? 0 : anime_info_opsonger.hashCode());
			result = prime * result + ((anime_info_pic == null) ? 0 : anime_info_pic.hashCode());
			result = prime * result + ((anime_info_site == null) ? 0 : anime_info_site.hashCode());
			result = prime * result + ((anime_name == null) ? 0 : anime_name.hashCode());
			result = prime * result + ((anime_number == null) ? 0 : anime_number.hashCode());
			result = prime * result + ((anime_pic == null) ? 0 : anime_pic.hashCode());
			result = prime * result + ((date == null) ? 0 : date.hashCode());
			result = prime * result + ((date_new == null) ? 0 : date_new.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Anime other = (Anime) obj;
			if (anime_class == null) {
				if (other.anime_class != null)
					return false;
			} else if (!anime_class.equals(other.anime_class))
				return false;
			if (anime_id == null) {
				if (other.anime_id != null)
					return false;
			} else if (!anime_id.equals(other.anime_id))
				return false;
			if (anime_info == null) {
				if (other.anime_info != null)
					return false;
			} else if (!anime_info.equals(other.anime_info))
				return false;
			if (anime_info2 == null) {
				if (other.anime_info2 != null)
					return false;
			} else if (!anime_info2.equals(other.anime_info2))
				return false;
			if (anime_info_anime == null) {
				if (other.anime_info_anime != null)
					return false;
			} else if (!anime_info_anime.equals(other.anime_info_anime))
				return false;
			if (anime_info_daoyan == null) {
				if (other.anime_info_daoyan != null)
					return false;
			} else if (!anime_info_daoyan.equals(other.anime_info_daoyan))
				return false;
			if (anime_info_date == null) {
				if (other.anime_info_date != null)
					return false;
			} else if (!anime_info_date.equals(other.anime_info_date))
				return false;
			if (anime_info_de == null) {
				if (other.anime_info_de != null)
					return false;
			} else if (!anime_info_de.equals(other.anime_info_de))
				return false;
			if (anime_info_ed == null) {
				if (other.anime_info_ed != null)
					return false;
			} else if (!anime_info_ed.equals(other.anime_info_ed))
				return false;
			if (anime_info_edsonger == null) {
				if (other.anime_info_edsonger != null)
					return false;
			} else if (!anime_info_edsonger.equals(other.anime_info_edsonger))
				return false;
			if (anime_info_from == null) {
				if (other.anime_info_from != null)
					return false;
			} else if (!anime_info_from.equals(other.anime_info_from))
				return false;
			if (anime_info_music == null) {
				if (other.anime_info_music != null)
					return false;
			} else if (!anime_info_music.equals(other.anime_info_music))
				return false;
			if (anime_info_op == null) {
				if (other.anime_info_op != null)
					return false;
			} else if (!anime_info_op.equals(other.anime_info_op))
				return false;
			if (anime_info_opsonger == null) {
				if (other.anime_info_opsonger != null)
					return false;
			} else if (!anime_info_opsonger.equals(other.anime_info_opsonger))
				return false;
			if (anime_info_pic == null) {
				if (other.anime_info_pic != null)
					return false;
			} else if (!anime_info_pic.equals(other.anime_info_pic))
				return false;
			if (anime_info_site == null) {
				if (other.anime_info_site != null)
					return false;
			} else if (!anime_info_site.equals(other.anime_info_site))
				return false;
			if (anime_name == null) {
				if (other.anime_name != null)
					return false;
			} else if (!anime_name.equals(other.anime_name))
				return false;
			if (anime_number == null) {
				if (other.anime_number != null)
					return false;
			} else if (!anime_number.equals(other.anime_number))
				return false;
			if (anime_pic == null) {
				if (other.anime_pic != null)
					return false;
			} else if (!anime_pic.equals(other.anime_pic))
				return false;
			if (date == null) {
				if (other.date != null)
					return false;
			} else if (!date.equals(other.date))
				return false;
			if (date_new == null) {
				if (other.date_new != null)
					return false;
			} else if (!date_new.equals(other.date_new))
				return false;
			return true;
		}
		public Anime(Integer anime_id, String anime_name, Integer anime_number, String anime_class, String anime_pic,
				String anime_info, String anime_info2, String anime_info_daoyan, String anime_info_music,
				String anime_info_de, String anime_info_pic, String anime_info_anime, String anime_info_site,
				String anime_info_date, String anime_info_from, String anime_info_op, String anime_info_opsonger,
				String anime_info_ed, String anime_info_edsonger, String date, String date_new) {
			super();
			this.anime_id = anime_id;
			this.anime_name = anime_name;
			this.anime_number = anime_number;
			this.anime_class = anime_class;
			this.anime_pic = anime_pic;
			this.anime_info = anime_info;
			this.anime_info2 = anime_info2;
			this.anime_info_daoyan = anime_info_daoyan;
			this.anime_info_music = anime_info_music;
			this.anime_info_de = anime_info_de;
			this.anime_info_pic = anime_info_pic;
			this.anime_info_anime = anime_info_anime;
			this.anime_info_site = anime_info_site;
			this.anime_info_date = anime_info_date;
			this.anime_info_from = anime_info_from;
			this.anime_info_op = anime_info_op;
			this.anime_info_opsonger = anime_info_opsonger;
			this.anime_info_ed = anime_info_ed;
			this.anime_info_edsonger = anime_info_edsonger;
			this.date = date;
			this.date_new = date_new;
		}
		public Anime() {
			super();
		}
		@Override
		public String toString() {
			return "Anime [anime_id=" + anime_id + ", anime_name=" + anime_name + ", anime_number=" + anime_number
					+ ", anime_class=" + anime_class + ", anime_pic=" + anime_pic + ", anime_info=" + anime_info
					+ ", anime_info2=" + anime_info2 + ", anime_info_daoyan=" + anime_info_daoyan
					+ ", anime_info_music=" + anime_info_music + ", anime_info_de=" + anime_info_de
					+ ", anime_info_pic=" + anime_info_pic + ", anime_info_anime=" + anime_info_anime
					+ ", anime_info_site=" + anime_info_site + ", anime_info_date=" + anime_info_date
					+ ", anime_info_from=" + anime_info_from + ", anime_info_op=" + anime_info_op
					+ ", anime_info_opsonger=" + anime_info_opsonger + ", anime_info_ed=" + anime_info_ed
					+ ", anime_info_edsonger=" + anime_info_edsonger + ", date=" + date + ", date_new=" + date_new
					+ "]";
		}
		
}
