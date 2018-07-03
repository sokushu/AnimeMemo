package moe.neptunenoire.web.util;

/**
 * 何の役も立たないので
 * 普通使わない
 * @author M
 *
 */
public class MuDa {

	/**
	 * charPlus,
	 * 次の数字や英字を出力
	 * <pre>
	 *  charPlus("aa") 出力：ab
	 *  charPlus("a1") 出力：a2
	 *  charPlus("10") 出力：11
	 *  charPlus("zz") 出力：aaa
	 *  charPlus("AA") 出力：AB
	 * </pre>
	 * @param str
	 * @return
	 */
	public static String charPlus(String str) {
		char[] charArray = str.toCharArray();
		char[] newArray = new char[charArray.length];
		int j = 0;
		for (int i = charArray.length-1; i >= 0; i--) {
			newArray[j] = charArray[i];
			j++;
		}
		char[] itemArray = new char[newArray.length];
		int z = 0;
		boolean goOne = true;
		for (char c : newArray) {
			if (c >= 'a' && c < 'z' || c >= 'A' && c<'Z' ||c >= '0' && c <'9' ) {
				if (goOne) {
					itemArray[z] = (char)(c+1);
					goOne = false;
				}else {
					itemArray[z] = c;
				}
			}
			if (c == 'z') {
				itemArray[z] = 'a';
				goOne = true;
			}
			if (c == 'Z') {
				itemArray[z] = 'A';
				goOne = true;
			}
			if (c == '9') {
				itemArray[z] = '0';
				goOne = true;
			}
			z++;
		}
		if (goOne) {
			int llp = 0;
			char[] returnArray = new char[itemArray.length + 1];
			for (int i = returnArray.length -1; i >= 0; i--) {
				if (llp > itemArray.length -1) {
					if (itemArray[itemArray.length-1] == 'a') {
						returnArray[i] = 'a';
					}
					if (itemArray[itemArray.length-1] == 'A') {
						returnArray[i] = 'A';
					}
					if (itemArray[itemArray.length-1] == '0') {
						returnArray[i] = '1';
					}
					break;
				}
				returnArray[i] = itemArray[llp];
				llp++;
			}
			return new String(returnArray);
		}else {
			int llp = itemArray.length -1;
			char[] returnArray = new char[itemArray.length];
			for (int i = 0; i < returnArray.length; i++) {
				if (llp < 0) {
					break;
				}
				returnArray[i] = itemArray[llp];
				llp--;
			}
			return new String(returnArray);
		}
	}


	public static String charPlus(String str, boolean goOne) {
		return "";
	}

	/**
	 *
	 */
	public static String addSpace(String str, int num) {
		StringBuilder sb = new StringBuilder();
		int loop = 0;
		byte[] aa = new byte[num];
		byte[] bb = str.getBytes();
		for (@SuppressWarnings("unused") byte b : aa) {
			if (loop <= bb.length - 1) {
				aa[loop] = bb[loop];
			}else {
				aa[loop] = 0;
			}
			loop ++;
		}
		sb.append(new String(aa));
		return sb.toString();
	}
}
