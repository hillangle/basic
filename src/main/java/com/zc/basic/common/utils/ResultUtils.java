package com.zc.basic.common.utils;

import java.util.HashMap;
import java.util.Map;

public class ResultUtils extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public ResultUtils() {
		put("code", 0);
		put("msg", "操作成功");
	}

	public static ResultUtils error() {
		return error(1, "操作失败");
	}

	public static ResultUtils error(String msg) {
		return error(500, msg);
	}

	public static ResultUtils error(int code, String msg) {
		ResultUtils r = new ResultUtils();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static ResultUtils ok(String msg) {
		ResultUtils r = new ResultUtils();
		r.put("msg", msg);
		return r;
	}

	public static ResultUtils ok(Map<String, Object> map) {
		ResultUtils r = new ResultUtils();
		r.putAll(map);
		return r;
	}

	public static ResultUtils ok() {
		return new ResultUtils();
	}

	@Override
	public ResultUtils put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
