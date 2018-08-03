package moe.neptunenoire.web.utils;

import java.util.ArrayList;

public class StringUtils {
	/**
     * <p>The empty String <code>""</code>.</p>
     * <p>代表空的字符串<code>""</code></p>
     * @since 1.0
     */
    public static final String EMPTY = "";


    /* ***************************************
     * Empty checks
     * 检查是否为空字符
     * ***************************************/
    /**
     * <p>Checks if a String is empty ("") or null.</p>
     * <p>检查字符串是否为empty ("") 或 null.</p>
     *
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     * @param str  the String to check, may be null
     * @return
     * @since 1.0
     */
    public static boolean isEmpty(String str) {
    	return str == null || str.length() == 0;
    }

    /**
     * <p>Checks if a String is not empty ("") and not null.</p>
     * <p>如果字符串不是 empty ("") 或者 null.则返回true</p>
     * <pre>
     * StringUtils.isNotEmpty(null)      = false
     * StringUtils.isNotEmpty("")        = false
     * StringUtils.isNotEmpty(" ")       = true
     * StringUtils.isNotEmpty("bob")     = true
     * StringUtils.isNotEmpty("  bob  ") = true
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is not empty and not null
     */
    public static boolean isNotEmpty(String str) {
    	return !isEmpty(str);
    }

    /**
     * <p>Checks if a String is whitespace, empty ("") or null.</p>
     *
     *<pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     * @since 1.0
     */
    public static boolean isBlank(String str) {
    	int strLen;
    	if (str == null || (strLen = str.length()) == 0) {
    		return true;
		}
    	for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
    	return true;
    }

    /**
     * <p>Checks if a String is not empty (""), not null and not whitespace only.</p>
     *
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is
     *  not empty and not null and not whitespace
     * @since 1.0
     */
    public static boolean isNotBlank(String str) {
    	return !isBlank(str);
    }

    /* ***************************************
     * Trim
     * 删除字符串空格
     * ***************************************/
    /**
     *
     * @param str
     * @return
     */
    public static String trimToNull(String str) {
    	String ts = trim(str);
    	return isEmpty(ts) ? null : ts;
    }

    /**
     *
     * @param str
     * @return
     */
    public static String trimToEmpty(String str) {
    	return str == null ? EMPTY : trim(str);
    }

    /**
     * <p>去处文字列头部的空白</p>
     *
     * <pre>
     * StringUtils.trimEnd(null)      = null
     * StringUtils.trimEnd("")        = ""
     * StringUtils.trimEnd(" ")       = ""
     * StringUtils.trimEnd("bob")     = "bob"
     * StringUtils.trimEnd("  bob  ") = "bob  "
     * </pre>
     *
     * @param str
     * @return
     */
    public static String trimStart(String str) {
    	if (str == null) return null;
    	for (int i = 0; i < str.length(); i++) {
    		if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return str.substring(i);
            }
		}
    	return EMPTY;
    }

    /**
     * <p>去处文字列末尾的空白</p>
     *
     *
     * <pre>
     * StringUtils.trimEnd(null)      = null
     * StringUtils.trimEnd("")        = ""
     * StringUtils.trimEnd(" ")       = ""
     * StringUtils.trimEnd("bob")     = "bob"
     * StringUtils.trimEnd("  bob  ") = "  bob"
     * </pre>
     *
     * @param str
     * @return
     */
    public static String trimEnd(String str) {
    	if (str == null) return null;
    	for (int i = str.length() - 1; i > 0; i--) {
			if (Character.isWhitespace(str.charAt(i)) == false) {
				return str.substring(0, i);
			}
		}
    	return EMPTY;
    }

    /**
     *
     * @param str
     * @return
     */
    private static String trim(String str) {
    	return str == null ? null : str.trim();
    }

    /* ***************************************
     * Defaults
     * 得到默认的字符串
     * ***************************************/

    /**
     *
     * @param str
     * @return
     */
    public static String defaultString(String str) {
    	return str == null ? EMPTY : str;
    }

    /**
     *
     * @param str
     * @param defaultStr
     * @return
     */
    public static String defaultString(String str, String defaultStr) {
    	return str == null ? defaultStr : str;
    }

    /**
     *
     * @param str
     * @param defaultStr
     * @return
     */
    public static String defaultIfBlank(String str, String defaultStr) {
    	return isBlank(str) ? defaultStr : str;
    }

    /**
     *
     * @param str
     * @param defaultStr
     * @return
     */
    public static String defaultIfEmpty(String str, String defaultStr) {
    	return isEmpty(str) ? defaultStr : str;
    }

    /* ***************************************
     * join
     * 将字符串拼接
     * ***************************************/
    /**
     * <p></p>
     *
     * <pre>
     * StringUtils.join(null)      = null
     * StringUtils.join([10,20])   = "1020"
     * StringUtils.join([true,10]) = "true10"
     * StringUtils.join([a,b,c])   = "abc"
     * </pre>
     * @param objArray
     * @return
     */
    public static String join(Object[] objArray) {
    	if (objArray == null) return null;
    	StringBuilder sb = new StringBuilder();
    	for (Object object : objArray) {
			sb.append(object);
		}
    	return sb.toString();
    }

    /**
     * <pre>
     * StringUtils.join(null,"a")      	= null
     * StringUtils.join([10,20],",")   	= "10,20"
     * StringUtils.join([true,10],"-") 	= "true-10"
     * StringUtils.join([a,b,c],",")   	= "a,b,c"
     * StringUtils.join([a,b,c], null)	= "abc"
     * </pre>
     * @param objArray
     * @param separator
     * @return
     */
    public static String join(Object[] objArray, String separator) {
    	if (objArray == null) return null;
    	if (separator == null) return join(objArray);
    	StringBuilder sb = new StringBuilder();

    	int arraySize = objArray.length - 1;
    	for (int i = 0; i < objArray.length; i++) {
			sb.append(objArray[i]);
			if (arraySize != i) {
				sb.append(separator);
			}
		}
    	return sb.toString();
    }

    /**
     *
     * <pre>
     * StringUtils.join(null,"a")      	= ""
     * StringUtils.join([10,20],",")   	= "10,20"
     * StringUtils.join([true,10],"-") 	= "true-10"
     * StringUtils.join([a,b,c],",")   	= "a,b,c"
     * StringUtils.join([a,b,c], null)  = "abc"
     * </pre>
     *
     * @param objArray
     * @param separator
     * @return
     */
    public static String joinToEmpty(Object[] objArray, String separator) {
    	if (objArray == null) return EMPTY;
    	if (separator == null) separator = EMPTY;

    	int arraySize = objArray.length - 1;
    	String val;

    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < objArray.length; i++) {
    		val = String.valueOf(objArray[i]);
    		if (isNotEmpty(val)) {
    			sb.append(val);
    			if (arraySize != i) {
    				sb.append(separator);
    			}
			}
		}
    	return sb.toString();
    }

    /**
     *
     * <pre>
     * StringUtils.join(null,"a")      	= ""
     * StringUtils.join([10,20],",")   	= "10,20"
     * StringUtils.join([true,10],"-") 	= "true-10"
     * StringUtils.join([a,b,c],",")   	= "a,b,c"
     * StringUtils.join([a,b,c], null)  = "abc"
     * </pre>
     *
     * @param objArray
     * @param separator
     * @return
     */
    public static String joinToNull(Object[] objArray, String separator) {
    	String val = joinToEmpty(objArray, separator);
    	if (isEmpty(val)) return null;
    	return val;
    }

    /**
     * <pre>
     * StringUtils.split(null)      	= null
     * StringUtils.split("10 20")   	= [10,20]
     * StringUtils.split("true 10") 	= [true,10]
     * StringUtils.split("a b c")   	= [a,b,c]
     * </pre>
     * @param str
     * @return
     */
    public static String[] split(String str) {
    	return splitWorker(str, null, -1, -1, true);
    }

    /**
     *
     * @param str
     * @param separator
     * @return
     */
    public static String[] split(String str, String separator) {
    	return splitWorker(str, separator, -1, -1, true);
    }

    /**
     *
     * @param str
     * @param separator
     * @param max
     * @return
     */
    public static String[] split(String str, String separator, int max) {
    	return splitWorker(str, separator, max, -1, true);
    }

    /**
     *
     * @param str
     * @param separator
     * @param max
     * @param min
     * @return
     */
    public static String[] split(String str, String separator, int max, int min) {
    	return splitWorker(str, separator, max, min, true);
    }

    /**
     *
     * @param str
     * @param separator
     * @param max
     * @param min
     * @param returnNull
     * @return
     */
    public static String[] split(String str, String separator, int max, int min, boolean returnNull) {
    	return splitWorker(str, separator, max, min, returnNull);
    }


    /**
     *
     * @param str
     * @param separator
     * @param returnNull
     * @return
     */
    public static String[] splitOnce(String str, String separator, boolean returnNull) {
    	if (returnNull == true && str == null) {
			return null;
		}else if (returnNull == false && str == null) {
			return ArrayUtils.makeEmptyStringArray(2);
		}

    	ArrayList<String> list = new ArrayList<>();

    	int len = str.length();
    	if (separator == null) {
    		for (int i = 0; i < len; i++) {
    			if (Character.isWhitespace(str.charAt(i))) {
					list.add(str.substring(0, i));
					list.add(str.substring(i + 1));
					break;
				}
			}
		}else if (separator.length() == 1) {
			char sep = separator.charAt(0);
			for (int i = 0; i < len; i++) {
				if (str.charAt(i) == sep) {
					list.add(str.substring(0, i));
					list.add(str.substring(i + 1));
					break;
				}
			}
		}else {
			for (int i = 0; i < len; i++) {
				if (separator.indexOf(str.charAt(i)) >= 0) {
    				list.add(str.substring(0, i));
    				list.add(str.substring(i + separator.length()));
    				break;
				}
			}
		}

    	return ArrayUtils.makeStringArray(2, list);
    }

    /**
     *
     * @param str
     * @param separator
     * @param max
     * @param min
     * @param returnNull
     * @return
     */
    private static String[] splitWorker(final String str, final String separator, int max, int min, boolean returnNull) {
    	if (min > max) throw new IndexOutOfBoundsException(String.format("max:%s , min:%s", max, min));

    	if (str == null && returnNull == true) {
    		return null;
    	}else if (str == null && returnNull == false){
    		return ArrayUtils.makeEmptyStringArray(min);
    	}

    	int len = str.length();
    	int i = 0;
    	int start = 0;
    	int end = 0;
    	int size = 1;
    	ArrayList<String> list = new ArrayList<>();
    	if (separator == null) {
    		while (i < len) {
    			if (Character.isWhitespace(str.charAt(i))) {
    				if (start < end) {
    					if (size++ == max) {
    						list.add(str.substring(start));
							break;
						}
						list.add(str.substring(start, end));
						start = end;
					}
					start++; end++;
				}else {
					end++;
				}
    			i++;
    			if (i == len) {
					list.add(str.substring(start));
				}
			}
		}else if (separator.length() == 1) {
			char sep = separator.charAt(0);
			while (i < len) {
				if (str.charAt(i) == sep) {
					if (start < end) {
						if (size++ == max) {
    						list.add(str.substring(start));
							break;
						}
						list.add(str.substring(start, end));
						start = end;
					}
					start++; end++;
				}else {
					end++;
				}
				i++;
				if (i == len) {
					list.add(str.substring(start));
				}
			}
		}else {
			while (i < len) {
    			if (separator.indexOf(str.charAt(i)) >= 0) {
    				if (start < end) {
    					if (size++ == max) {
    						list.add(str.substring(start));
							break;
						}
						list.add(str.substring(start, end));
						start = end;
					}
					start++; end++;
				}else {
					end++;
				}
    			i++;
    			if (i == len) {
					list.add(str.substring(start));
				}
			}
		}

    	return ArrayUtils.makeStringArray(min, list);
    }

    /**
	 * charPlus,
	 * 次の数字や英字を出力
	 * <pre>
	 *  StringUtils.charPlus("90")		= "91"
	 *  StringUtils.charPlus("10")		= "11"
	 *  StringUtils.charPlus("99")		= "100"
	 *  StringUtils.charPlus("89")		= "90"
	 *  StringUtils.charPlus("aa")		= "ab"
	 *  StringUtils.charPlus("zz")		= "aaa"
	 *  StringUtils.charPlus("sd")		= "se"
	 *  StringUtils.charPlus("r5")		= "r6"
	 *  StringUtils.charPlus("u7")		= "u8"
	 *  StringUtils.charPlus("1z")		= "2a"
	 *  StringUtils.charPlus("z9")		= "aa0"
	 * </pre>
	 * @param str
	 * @return
	 */
	public static String charPlus(String str) {

		final char[] charArray = str.toCharArray();

		char[] itemArray = new char[charArray.length];
		boolean goOne = true;
		char c = 0;
		for (int i = charArray.length - 1; i >= 0; i--) {
			c = charArray[i];
			if (goOne) {
				if (c >= 'a' && c < 'z' || c >= 'A' && c < 'Z' || c >= '0' && c <'9' ) {
					itemArray[i] = (char)(c+1);
					goOne = false;
				} else if (c == 'z') {
					itemArray[i] = 'a';
					goOne = true;
				} else if (c == 'Z') {
					itemArray[i] = 'A';
					goOne = true;
				} else if (c == '9') {
					itemArray[i] = '0';
					goOne = true;
				}
			}else {
				itemArray[i] = c;
			}
		}
		if (goOne) {
			char[] returnCharArray = new char[itemArray.length + 1];
			char a = itemArray[0];
			if (a == 'a') {
				returnCharArray[0] = 'a';
			} else if (a == 'A') {
				returnCharArray[0] = 'A';
			} else if (a == '0') {
				returnCharArray[0] = '1';
			}
			for (int i = 1; i < returnCharArray.length; i++) {
				returnCharArray[i] = itemArray[i - 1];
			}
			return new String(returnCharArray);
		}else {
			return new String(itemArray);
		}
	}

	/**
	 *
	 * @param str
	 * @param SpaceLen
	 * @return
	 */
	public static String addSpaceStart(String str, int SpaceLen) {
		return addSpace(str, SpaceLen, Where.Left);
	}

	/**
	 *
	 * @param str
	 * @param SpaceLen
	 * @return
	 */
	public static String addSpaceCenter(String str, int SpaceLen) {
		return addSpace(str, SpaceLen, Where.Center);
	}

	/**
	 *
	 * @param str
	 * @param SpaceLen
	 * @return
	 */
	public static String addSpaceEnd(String str, int SpaceLen) {
		return addSpace(str, SpaceLen, Where.Reght);
	}

	/**
	 *
	 * @param str
	 * @param len
	 * @param where
	 * @param charSpace
	 * @return
	 */
	private static String addSpace(String str, int len, Where where) {
		if (str == null) return null;
		if (len < 0) throw new IndexOutOfBoundsException("error : len < 0 ,len is"+len);

		StringBuilder sb = new StringBuilder();

		int strLen = str.length();
		if (where == Where.Left) {
			sb.append(getSpace(len)).append(str);
		} else if (where == Where.Center) {
			int cut = (int)(strLen / 2);
			sb.append(str.substring(0, cut)).append(getSpace(len)).append(str.substring(cut));
		} else if (where == Where.Reght) {
			sb.append(str).append(getSpace(len));
		}
		return sb.toString();
	}

	/**
	 *
	 * @param len
	 * @return
	 */
	private static String getSpace(int len) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}

	/**
	 *
	 * @author M
	 *
	 */
	public enum Where{
		Left,Reght,Center
	}

	/**
	 *
	 * @param num
	 * @return
	 */
	public static boolean isNumber(String num) {
		for (int i = 0; i < num.length(); i++) {
			if (!Character.isDigit(num.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 *
	 * @param num
	 * @return
	 */
	public static boolean isNotNumber(String num) {
		return !isNumber(num);
	}

	/**
	 *
	 * @param str
	 * @param separator
	 * @return
	 */
	public static String chomp(String str, String separator) {
		if (isEmpty(str) || separator == null) {
            return str;
        }
        if (str.endsWith(separator)) {
            return str.substring(0, str.length() - separator.length());
        }
        return str;
	}
}
