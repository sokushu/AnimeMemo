package xyz.server.home;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import xyz.bangumi.mysql.dao.CommentsDao;
import xyz.bangumi.mysql.dao.UserDao;

@Controller
@EnableAutoConfiguration
public class CommentsController {
	
	@Autowired
	private UserDao user;
	@Autowired
	private CommentsDao comm;

	@RequestMapping(value = "/id/{url}/comm", method = RequestMethod.POST)
	@ResponseBody
	public String addcomm(String comm, HttpSession session, @PathVariable("url")String url) {
		String commuid = session.getAttribute("USERUID").toString();
		Map<String, Object>map = user.showUserByURL(url);
		String uid = map.get("uid").toString();
		String pic = map.get("user_pic").toString();
		String name = map.get("name").toString();
		this.comm.addComm(uid, commuid, comm, name, pic);
		return "true";
	}
	
	@RequestMapping(value = "/id/{url}/comm", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> returnComm(@PathVariable("url")String url,String page){
		Map<String, Object>returndata = new HashMap<>();
		int Page = StringToInt(page);
		
		Map<String, Object>map = user.showUserByURL(url);
		String uid = map.get("uid").toString();
		
		PageHelper.startPage(Page, 5);
		List<Map<String, Object>>list = comm.showComm(uid);
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(list);
		
		returndata.put("commdata", list);//留言数据
		//分页信息
		returndata.put("pagefirst", 1);
		returndata.put("pageone", pageinfo.getPrePage());
		returndata.put("pagenow", pageinfo.getPageNum());
		returndata.put("pagetwo", pageinfo.getNextPage());
		returndata.put("pagelast", pageinfo.getNavigateLastPage());
		returndata.put("kazu", pageinfo.getTotal());
		returndata.put("isnext", pageinfo.isHasNextPage());
		returndata.put("ispre", pageinfo.isHasPreviousPage());
		return returndata;
	}
	
	private int StringToInt(String page) {
		try {
			Integer a = new Integer(page);
			return a;
		} catch (Exception e) {
			// TODO: handle exception
			return 1;
		}
	}
}
