package test.sql.com.myapplication.utils;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.evgenii.jsevaluator.interfaces.JsCallback;
import com.google.gson.Gson;
import com.net.shop.car.manager.BaseActivity;
import com.net.shop.car.manager.Constant;
import com.net.shop.car.manager.api.volley.Request;
import com.net.shop.car.manager.thirdparty.aescrypto.JsEncryptor;

/**
 * @description 网络访问工具<br>
 *              可配置缓存，配置加密<br>
 *              <b>注：以volley框架为底层</b>
 * @author wangpeng
 * @date 2014-12-15
 */
public class VolleyUtils {

	/** 是否使用缓存 [配置true后，将在第一时间返回缓存数据] */
	private static final boolean useDiskCache = false;
	/** 数据加密器 */
	private JsEncryptor jsEncryptor;
	/** 网络异常或超时回调 */
	private Response.ErrorListener errorListener;
	/** 网络请求队列 */
	private RequestQueue requestQueue;
	private Context context;
	private Gson gson = new Gson();

	private static final String TEXT = "text";

	private VolleyUtils() {
	}

	/**
	 * 初始化配置
	 * 
	 * @param requestQueue
	 *            网络请求队列
	 * @param errorListener
	 *            网络异常或超时回调
	 * @param context
	 *            context
	 * @return
	 */
	public static VolleyUtils init(RequestQueue requestQueue,
			Response.ErrorListener errorListener, BaseActivity context) {
		VolleyUtils v = new VolleyUtils();
		v.requestQueue = requestQueue;
		v.errorListener = errorListener;
		v.context = context;
//		v.jsEncryptor = JsEncryptor.evaluateAllScripts((BaseActivity) context);
		return v;
	}

	/**
	 * 提交网络请求 <br>
	 * <b>注：此项目特有方法</b>
	 * 
	 * @param hfyId
	 *            命令编号
	 * @param message
	 *            请求内容
	 * @param clazz
	 *            返回数据对应实体
	 * @param listener
	 *            返回回调
	 */
	public <T> void post(String hfyId, Request message, final Class<T> clazz,
			final Listener<T> listener) {
		post(hfyId, message, clazz, listener, false);
	}

	/**
	 * 提交网络请求 <br>
	 * <b>注：此项目特有方法</b>
	 * 
	 * @param hfyId
	 *            命令编号
	 * @param message
	 *            请求内容
	 * @param clazz
	 *            返回数据对应实体
	 * @param listener
	 *            返回回调
	 * @param securityMode
	 *            是否加密
	 */
	public <T> void post(String hfyId, Request message, final Class<T> clazz,
			final Listener<T> listener, final boolean securityMode) {
//		/** 配置常用请求參數 */
//		final Request request = new Request();
//		request.uuid = App.i().uuid;
//		request.ostype = Constant.api.ostype;
//		request.msgid = hfyId;
//		request.msg = message != null ? message : new Request();
//		request.signature = signature(App.i().uuid, Constant.api.ostype, hfyId,
//				gson.toJson(request), Constant.api.apicall_key);
//		/** 获取缓存密匙 */
//		final String url = Constant.api.server;
//		final String cacheKey = url + "_" + hfyId;
//
//		if (useDiskCache) {
//			/** 获取缓存响应 */
//			final InputStream in = DiskLruCacheUtils.get(context,
//					DiskLruCacheUtils.object, cacheKey);
//			if (in != null) {
//				final String cacheResult = BaseUtils.inputStreamToString(in);
//				if (securityMode) {
//					/** 解密缓存信息 */
//					jsEncryptor.decrypt(cacheResult, Constant.api.security_key,
//							new JsCallback() {
//								@Override
//								public void onResult(String encrypt) {
//									listener.onResponse(gson.fromJson(encrypt,
//											clazz));
//								}
//							});
//				} else {
//					listener.onResponse(gson.fromJson(cacheResult, clazz));
//				}
//			}
//		}
//		/** 生成请求文本 */
//		final String requestString = gson.toJson(request);
//
//		LogUtils.d("postGson< " + clazz.getSimpleName() + " >");
//		LogUtils.d("url : " + url + " - [" + hfyId + "]" + " \nrequest : "
//				+ requestString);
//
//		if (securityMode) {
//			/** 加密请求内容 */
//			jsEncryptor.encrypt(requestString, Constant.api.security_key,
//					new JsCallback() {
//						@Override
//						public void onResult(String encrypt) {
//							post(url, encrypt, clazz, listener, cacheKey,
//									securityMode);
//						}
//					});
//		} else {
//			post(url, requestString, clazz, listener, cacheKey, securityMode);
//		}
	}

	/**
	 * 提交网络请求
	 * 
	 * @param url
	 *            请求地址
	 * @param request
	 *            参数
	 * @param clazz
	 *            返回参数对应实体类型
	 * @param listener
	 *            回调监听
	 * @param cacheKey
	 *            <b>缓存密匙 注：此项目特有参数</b>
	 * @param securityMode
	 *            <b>是否加密 注：此项目特有参数</b>
	 */
	private <T> void post(final String url, String request,
			final Class<T> clazz, final Listener<T> listener,
			final String cacheKey, final boolean securityMode) {
		if (requestQueue == null) {
			throw new IllegalArgumentException(
					"requestQueue has not init in volley utils");
		}

		final Map<String, String> params = new HashMap<String, String>();
		params.put(TEXT, request);

		StringRequest stringRequest = new StringRequest(Method.POST, url,
				new Listener<String>() {
					@Override
					public void onResponse(final String response) {
						if (useDiskCache) {
							new Thread(new Runnable() {
								public void run() {
									DiskLruCacheUtils.save(context,
											DiskLruCacheUtils.object, cacheKey,
											new ByteArrayInputStream(gson
													.toJson(response)
													.getBytes()));
								}
							}).start();
						}
						String result = response;
						if (securityMode) {
							jsEncryptor.decrypt(result,
									Constant.api.security_key,
									new JsCallback() {
										@Override
										public void onResult(String encrypt) {
											LogUtils.d("response:" + encrypt);
											listener.onResponse(gson.fromJson(
													encrypt, clazz));
										}
									});
						} else {
							LogUtils.d("response:" + response);
							listener.onResponse(gson.fromJson(result, clazz));
						}

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						LogUtils.e(error.getMessage());
						errorListener.onErrorResponse(error);
					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				return params;
			}
		};

		requestQueue.add(stringRequest);
	}

	/**
	 * 加密算法
	 * 
	 * @param uuid
	 *            设备UUID
	 * @param ostype
	 *            设备操作系统
	 * @param id
	 *            请求命令编号
	 * @param message
	 *            请求消息
	 * @param apicall_key
	 *            加密密匙
	 * @return 加密字符串
	 */
	private static String signature(String uuid, String ostype, String id,
			String message, String apicall_key) {
		if (message == null)
			message = "";
		return MD5.md5crypt(uuid + "&" + ostype + "&" + id + "&" + message
				+ "&" + MD5.md5crypt((apicall_key)));
	}

}
