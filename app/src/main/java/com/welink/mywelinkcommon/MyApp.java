package com.welink.mywelinkcommon;

import android.app.Application;
import android.content.Context;

import java.util.Map;

/**
 * @author xiaoxiangtata#gmail.com
 *
 */
public class MyApp extends Application {

	public static int devMode = -1;
	public static String appVersion;
	public static String clientId;
	public static String username;
	public static String user_id;
	public static String head_image;
	public static String user_password;
	public static String channel_user_key;
	public static String user_session;
	public static String session_expire_time;
	public static String phone;
	public static String email;
	public static String token="";
	public static boolean isLogin;
	public static Context context;
	public static String wxAppId;
	public static boolean isMain;
	
	public static String store_key;
	public static Map<String, Long> map;
	//用于存放当前的城市
	public static int cityId=1;

	@Override
	public void onCreate() {
		super.onCreate();
//		ApplicationInfo appInfo = null;
//		try {
//			appInfo = getPackageManager().getApplicationInfo(getPackageName(),
//					PackageManager.GET_META_DATA);
//		} catch (NameNotFoundException e) {
//			e.printStackTrace();
//		}
//		devMode = appInfo.metaData.getInt("DEV_TEST_PUB");
//		GetDataConfing.setAppConfig(APIs.app_keyValue, APIs.app_sessionValue, APIs.app_secretValue);
//		context = getApplicationContext();
//			// 捕获全局异常模块
//		LogUtil.setDebug(false);
//		MyCrashHandler handler = MyCrashHandler.getInstance();
//		handler.init(context);
//
//		Thread.setDefaultUncaughtExceptionHandler(handler);
//
//		initImageLoader(getApplicationContext());
//		Properties pro = new Properties();
//		InputStream is = null;
//		try {
//			is = context.getAssets().open("system.properties");
//			pro.load(is);
//			String sys_edition = pro.getProperty("sys.edition");
//			if (sys_edition.equals("test")){
//				CrashReport.initCrashReport(getApplicationContext(), "900031555", true);//测试版
//			}else{
//				CrashReport.initCrashReport(getApplicationContext(), "900031527", false);//正式版
//			}
//		}catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		UdeskSDKManager.getInstance().initApiKey(context,"15026555209.udesk.cn", "742f2c18edfe783c96fdb6e5ca4e11cc");

	}

	public static void resetParams(){
		devMode = -1;
		appVersion=null;
		clientId=null;
		username=null;
		user_id=null;
		head_image=null;
		user_password=null;
		channel_user_key=null;
		user_session=null;
		session_expire_time=null;
		isLogin=false;
	}

	public static void initImageLoader(Context context) {
//		File cacheDir = StorageUtils.getOwnCacheDirectory(context, "cache");
//		ImageLoaderConfiguration config = new ImageLoaderConfiguration
//				.Builder(context)
//				//.memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
//				.threadPoolSize(3)//线程池内加载的数量
//				.threadPriority(Thread.NORM_PRIORITY - 2)
//				.denyCacheImageMultipleSizesInMemory()
//				.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
//				.memoryCacheSize(8* 1024 * 1024)
//				.discCacheSize(50 * 1024 * 1024)
//				.discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
//				.tasksProcessingOrder(QueueProcessingType.LIFO)
//				//.discCacheFileCount(100) //缓存的文件数量
//				//.discCache(new UnlimitedDiskCache(cacheDir))//自定义缓存路径
//				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
//				.imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
//				.writeDebugLogs() // Remove for release app
//				.build();//开始构建
//		ImageLoader.getInstance().init(config);
//		L.disableLogging();
	}
}
