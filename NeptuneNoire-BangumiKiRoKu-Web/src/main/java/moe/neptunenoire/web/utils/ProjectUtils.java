package moe.neptunenoire.web.utils;

import moe.neptunenoire.web.controller.error.BangumiNotFoundException;

public class ProjectUtils {

	/**
	 *
	 * @param bangumiID
	 * @throws BangumiNotFoundException
	 */
	public static void CheckBangumiID(String bangumiID) throws BangumiNotFoundException {
		if (StringUtils.isNotNumber(bangumiID)) {
			throw new BangumiNotFoundException(bangumiID + " not number");
		}
	}

	/**
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	public static String passWordCoding(String username, String password) {
		UserID id = new UserID(username);
		return MD5Coding.coding(username, password, id.GetlongCode());
	}

}
