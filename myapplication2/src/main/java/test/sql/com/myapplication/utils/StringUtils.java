/**
 * date
 */
package test.sql.com.myapplication.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;

/**
 * @author Deve
 * 
 */
public class StringUtils {

	/**
	 * @param str
	 * @return
	 */
	public static String filterNull(String str) {
		if (!TextUtils.isEmpty(str)) {
			if ("null".equalsIgnoreCase(str)) {
				return null;
			}
		}
		return str;
	}

	/**
	 * @param str
	 *            String
	 * @return boolean
	 */
	public static boolean filterBoolNull(String str) {
		if ("yes".equalsIgnoreCase(str)) {
			return true;
		}
		return false;
	}

	/**
	 * @param str
	 * @return
	 */
	public static int filterIntegerNull(String str) {
		if (!TextUtils.isEmpty(str)) {
			if ("null".equalsIgnoreCase(str)) {
				return 0;
			} else {
				try {
					return Integer.parseInt(str);
				} catch (NumberFormatException e) {
					return 0;
				}
			}
		}
		return 0;
	}
	
	/**
	 * @param str
	 * @return
	 */
	public static long filterLongNull(String str) {
		if (!TextUtils.isEmpty(str)) {
			if ("null".equalsIgnoreCase(str)) {
				return 0;
			} else {
				try {
					return Long.parseLong(str);
				} catch (NumberFormatException e) {
					return 0;
				}
			}
		}
		return 0;
	}

	/**
	 * @param str
	 * @return
	 */
	public static BigDecimal filterMoneyNull(String str) {
		if (!TextUtils.isEmpty(str)) {
			if ("null".equalsIgnoreCase(str)) {
				return new BigDecimal(0);
			} else {
				try {
					BigDecimal money = new BigDecimal(str);
					return money;
				} catch (NumberFormatException e) {
					return new BigDecimal(0);
				}
			}
		}
		return new BigDecimal(0);
	}

	/**
	 * @param content
	 * @return
	 */
	public static List<String> filterTelsNull(String content) {
		List<String> tels = new ArrayList<String>();
		if (!TextUtils.isEmpty(content)) {
			String[] telStrings = content.split(",");
			for (int i = 0; i < telStrings.length; i++) {
				if (!"null".equals(telStrings[i])) {
					tels.add(telStrings[i]);
				}
			}
		}
		return tels;
	}

	/**
	 * @param distance
	 * @return int
	 */
	public static String parseDistance(int distance) {
		if (distance < 100) {
			return distance + "m";
		}
		DecimalFormat df = new DecimalFormat("#.0km");
		return df.format(distance / (float) 1000).toString();
	}
	
	/**
	 * @param money
	 * @return
	 */
	public static long moneyToVB(BigDecimal money) {
		long vb = money.multiply(new BigDecimal(100)).longValue();
		return vb;
	}

	/**
	 * @param money
	 *            double
	 * @return String
	 */
	public static String parseMoney(BigDecimal money) {
		return "￥" + money + "";
	}

	/**
	 * @param i
	 *            将整形值转换为中文
	 * @return
	 */
	public static String parseIntToCNStr(int i) {
		switch (i) {
		case 0:
			return "一";
		case 1:

			return "二";
		case 2:
			return "三";
		case 3:

			return "四";
		case 4:

			return "五";
		case 5:

			return "六";
		case 6:

			return "七";
		case 7:

			return "八";
		case 8:

			return "九";

		default:
			return "";
		}
	}

}
