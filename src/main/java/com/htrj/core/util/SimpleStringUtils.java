package com.htrj.core.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;



/**
 * 
 * @author he
 *
 */
public class SimpleStringUtils {

	public static String getApplicationClassName(String fullName) {
		if (fullName.contains("$")) {
			fullName = fullName.substring(0, fullName.indexOf("$"));
		}
		if (fullName.contains(".")) {
			return StringUtils.substringAfterLast(fullName, ".");
		}
		return fullName;
	}

	public static String getControllerClassName(Class<?> clazz) {
		String name = clazz.getName();
		if (name.startsWith("controllers.")) {
			name = name.substring(12);
		}
		return name;
	}

	public static String urlPath(Class<?> clazz) {
		return urlPath(clazz.getName());
	}

	@Deprecated
	public static String packageName(Class<?> clazz) {
		String name = StringUtils.substringBeforeLast(clazz.getName(), ".");
		if (StringUtils.isBlank(name)) {
			return "";
		}

		if (name.contains("models")) {
			name = StringUtils.substringAfter(name, "models.");
		}
		if ((!StringUtils.isBlank(name)) && (!name.endsWith("."))) {
			name = name + ".";
		}
		return name;
	}

	public static String controllerNameFromResourceValue(String value) {
		value = StringUtils.substringBeforeLast(value, ".");
		if (value.contains(".")) {
			return StringUtils.substringAfterLast(value, ".");
		}
		return value;
	}

	public static String controllerPackageAndNameFromResourceValue(String value) {
		return StringUtils.substringBeforeLast(value, ".");
	}

	public static String urlPath(String fullName) {
		if (fullName.contains("$")) {
			fullName = fullName.substring(0, fullName.indexOf("$"));
		}
		fullName = StringUtils.substringAfter(fullName, "controllers.");
		String[] results = StringUtils.split(fullName, ".");
		List list = new ArrayList(results.length);
		for (String str : results) {
			list.add(SEOName(str, "-"));
		}
		return StringUtils.join(list.iterator(), "/");
	}

	@Deprecated
	public static String modelMessageKey(Class<?> clazz) {
		String prefix = StringUtils.substringBeforeLast(clazz.getName(), ".");
		return prefix + "." + modelInstanceName(clazz.getSimpleName());
	}

	public static String string(String[] strings) {
		StringBuffer sb = new StringBuffer();
		String[] arrayOfString = strings;
		int j = strings.length;
		for (int i = 0; i < j; i++) {
			String str = arrayOfString[i];
			if (StringUtils.isEmpty(str)) {
				continue;
			}
			sb.append(str);
		}
		return sb.toString();
	}

	public static String plural(String word) {
		String prefix = StringUtils.substring(word, 0, word.length() - 1);
		String last = "";
		char c = word.charAt(word.length() - 1);
		switch (c) {
		case 'y':
			last = "ies";
			break;
		case 's':
			last = "ses";
			break;
		default:
			last = c + "s";
		}

		return prefix + last;
	}

	public static String simplest(String word) {
		if (StringUtils.endsWith(word, "ies")) {
			return word.substring(0, word.length() - 3) + "y";
		}
		if (StringUtils.endsWith(word, "ses")) {
			return word.substring(0, word.length() - 2);
		}
		if (StringUtils.endsWith(word, "s")) {
			return word.substring(0, word.length() - 1);
		}

		return word;
	}

	public static String modelInstanceName(String name) {
		return StringUtils.uncapitalize(name);
	}

	public static String modelsInstanceName(String name) {
		return plural(modelInstanceName(name));
	}

	public static String getActionName(Class<?> clazz) {
		String actionName = clazz.getSimpleName();

		if (actionName.endsWith("Controller"))
			actionName = actionName.substring(0, actionName.length() - "Controller".length());
		else if (actionName.endsWith("Action")) {
			actionName = actionName.substring(0, actionName.length() - "Action".length());
		}

		return SEOName(actionName);
	}

	public static String SEOName(String name) {
		return SEOName(name, "-");
	}

	public static String SEOName(String name, String separator) {
		char[] ca = name.toCharArray();
		StringBuilder build = new StringBuilder(ca[0]);
		boolean lower = true;
		for (int i = 1; i < ca.length; i++) {
			char c = ca[i];
			if ((Character.isUpperCase(c)) && (lower)) {
				build.append(separator);
				lower = false;
			} else if (!Character.isUpperCase(c)) {
				lower = true;
			}
			build.append(c);
		}
		return build.toString().toLowerCase();
	}

	public static String SEOToMethod(String seoName) {
		return SEOToMethod(seoName, '-');
	}

	public static String SEOToMethod(String seoName, char separator) {
		char[] ca = seoName.toCharArray();
		StringBuilder build = new StringBuilder(ca[0]);
		for (int i = 1; i < ca.length; i++) {
			char c = ca[i];
			if (ca[i] == separator) {
				i++;
				build.append(Character.toUpperCase(ca[i]));
			} else {
				build.append(c);
			}
		}
		return build.toString();
	}

	public static String contact(String url, String params) {
		if (StringUtils.isBlank(params)) {
			return url;
		}
		if (url.indexOf("?") > 0) {
			return url + "&" + params;
		}
		return url + "?" + params;
	}

	public static String toParams(Map<String, String> params) {
		Iterator iterator = params.entrySet().iterator();
		StringBuffer sb = new StringBuffer();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			sb.append((String) entry.getKey()).append("=").append((String) entry.getValue()).append("&");
		}
		if (sb.length() == 0) {
			return null;
		}
		sb.delete(sb.length() - 1, sb.length());
		return sb.toString();
	}

	/*public static String currentParams(String path, String other) {
		Map params = Scope.Params.current().all();
		Iterator iterator = params.entrySet().iterator();
		StringBuffer sb = new StringBuffer();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			if (("action".equals(entry.getKey())) || ("controller".equals(entry.getKey())) || ("page".equals(entry.getKey()))) {
				continue;
			}
			for (String str : (String[]) entry.getValue()) {
				sb.append((String) entry.getKey()).append("=").append(str).append("&");
			}
		}
		if (sb.length() == 0) {
			return contact(path, other);
		}
		sb.delete(sb.length() - 1, sb.length());
		String p = contact(path, other);
		return contact(p, sb.toString());
	}*/

	public static String URLEncoding(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String inputStreamToString(InputStream inputStream) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
			return buffer.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String outputStreamToString(OutputStream outputStream) {
		try {
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			outputStream.write(bytes.toByteArray());
			return bytes.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String uuid() {
		return UUID.randomUUID().toString();
	}

	public static String removeRelatively(String str) {
		while (StringUtils.startsWith(str, "../")) {
			str = StringUtils.substringAfter(str, "../");
		}
		return str;
	}

	public static String nomal(String str) {
		return StringUtils.replace(str, "//", "/");
	}

	public static int unicodeLength(String str) {
		if (str == null)
			str = "";
		try {
			return str.getBytes("GBK").length;
		} catch (UnsupportedEncodingException e) {
		}
		return str.length();
	}

	public static String urlEncode(String url) throws Exception {
		try {
			return URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		Exception e = null;
		throw new Exception(e);
	}

	public static String urlDecode(String url) throws Exception {
		try {
			return URLDecoder.decode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		Exception e = null;
		throw new Exception(e);
	}
}
