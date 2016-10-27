package com.welink.mywelinkcommon;

import java.util.HashMap;

/**
 * Created by dell on 2016/3/2.
 */
public class API {

    private static final String TAG="API";


    // shared preference
    public static String preferenceName = "client";
    public static String clientid = "clientid";

    // 接口定义的通用字段
    public static String success = "success";
    public static String successTrue = "true";
    public static String successFalse = "false";
    public static String errcode = "errcode";
    /**
     * '20026' => 'session 过期'
     */
    public static final int errcode_20026 = 20026;
    /**
     * '20027' => 'session 无效'
     */
    public static final int errcode_20027 = 20027;
    public static final int errcode_48136 = 48136;
    public static final int errode_48103 = 48103;
    public static String ok = "ok";
    public static final int errcode_10000 = 10000;
    public static String message = "message";
    public static String data = "data";
    public static String list = "list";
    public static final int channel_rice=1002;
    public static final int channel_walk=1001;
    public static final int pay_password_error_time=5;

    public static String app_keyValue = "6c5ea2ca96c165e86cd11f83875f0135";
    public static String app_sessionValue = "f81eb851a320a29b7f792d99f85f4abe";
    public static String app_secretValue = "6deef98aae576547a5930876affe0056";

    public static String expire_time = "expire_time";

    public static String myBroadCastReceieverIntentFilter = "com.fck.action.Push";

    public static String session_expire_time = "session_expire_time";
    public static String channel_user_key = "channel_user_key";
    public static String user_session = "user_session";
    public static int room_product_page_size = 10;
    public static int holiday_product_page_size = 10;


    //资产列表
    public static String channel_asset_list="/sxtx/mobile/info/list";
    public static final int channel_asset_list_mark = 10000200;

    //资产收益
    public static String channel_asset_iccome="/sxtx/mobile/income/list";
    public static final int channel_asset_income_mark = 10000201;

    //管家服务
    public static String channel_house_service="/sxtx/mobile/housekeeper/list";
    public static final int channel_house_service_mark = 10000202;

    //维修记录
    public static String channel_service_record="/sxtx/mobile/repair/list";
    public static final int channel_service_record_mark = 10000203;

    //新闻通告
    public static String channel_news_list="/sxtx/mobile/notice/list";
    public static final int channel_news_list_mark = 10000204;

    //我的订单
    public static String channel_tenement_order="/sxtx/mobile/order/info";
    public static final int channel_tenement_order_mark = 10000205;

    //订单填写
    public static String channel_write_order="/sxtx/mobile/order/preCommit";
    public static final int channel_write_order_mark = 10000206;

    //提交订单
    public static String channel_commit_order="/sxtx/mobile/order/confirmOrder";
    public static final int channel_commit_order_mark = 10000207;

    //预支付
    public static String channel_pre_pay="/sxtx/mobile/pay/prePay";
    public static final int channel_pre_pay_mark = 10000208;

    //获取短租订单信息
    public static String channel_room_product_order_info="/sxtx/mobile/order/info";
    public static final int channel_room_product_order_info_mark = 10005001;

    //账户荣盛币查询
    public static String channel_rs_coin_info="/sxtx/mobile/rsCoin/info";
    public static final int channel_rs_coin_info_mark = 10005002;

    //获取城市
    public static String confirm_getCity="/sxtx/mobile/category/cities";
    public static final int  confirm_getcity_mark=10000023;

    //有限期荣盛币列表
    public static String channel_has_date_rs_coin_list="/sxtx/mobile/rsCoin/hasDateList";
    public static final int channel_has_date_rs_coin_list_mark = 10005003;

    //住宿产品列表
    public static String channel_room_product_list="/sxtx/mobile/product/list";
    public static final int channel_room_product_list_mark=10050004;

    //住宿产品明细
    public static String channel_room_product_detail="/sxtx/mobile/product/detail";
    public static final int channel_room_product_detail_mark=10050005;

    //生成预订单
    public static String channel_gen_pre_order="/sxtx/mobile/order/preCommit";
    public static final int channel_gen_pre_order_mark=1005006;

    //获取首页Banner
    public static String channel_main_fragment_banner="/sxtx/mobile/index/list";
    public static final int channel_main_fragment_banner_mark=1005007;
    //选择租房日期
    public static String channel_select_data="/sxtx/mobile/product/date";
    public static final int channel_select_data_mark = 10000210;

    //判断2个日期是否可行
    public static String channel_two_data="/sxtx/mobile/product/continuity";
    public static final int channel_two_data_mark = 10000212;

    public static String channel_check_version="/sxtx/mobile/version/check";
    public static final int channel_check_version_mark=1005008;

    public static String channel_rs_coin_pay="/sxtx/mobile/rsCoins";
    public static final int channel_rs_coin_pay_mark=1005009;

    //选择度假日期
    public static String channel_select_holiday_data="/sxtx/mobile/productDateTravel/datePrice";
    public static final int channel_select_holiday_data_mark = 10000213;

    //度假订单
    public static String channel_tour_order="/sxtx/mobile/orderTravel/list";
    public static final int channel_tour_order_mark = 10000214;

    //租房收藏列表
    public static String channel_colloct_tenment="/sxtx/mobile/collect/communitList";
    public static final int channel_colloct_tenment_mark = 10000215;

    //生成度假预订单
    public static String channel_crearte_order="/sxtx/mobile/orderTravel/createOrder";
    public static final int channel_crearte_order_mark = 10000216;

    //修改度假订单
    public static String channel_change_order="/sxtx/mobile/orderTravel/changeOrder";
    public static final int channel_cchange_order_mark = 10000217;

    //删除联系人
    public static String delect_linkman="/sxtx/mobile/user/contactCancel";
    public static String delect_linkman_LinkId="id";
    public static String delect_linkmane_userId="userId";
    public static final int delect_linkman_mark=10000019;


    //生成订单
    public static String get_create_order="/sxtx/mobile/orderTravel/confirmOrder";
    public static final int  confirm_order_mark=10000020;

    public static String channel_main_holiday_gallery="/sxtx/mobile/index/travel";
    public static final int channel_main_holiday_gallery_mark=1005010;

    public static String channel_holiday_product_list="/sxtx/mobile/productTravel/list";
    public static final int channel_holiday_product_list_mark=1005011;

    public static String channel_holiday_product_detail="/sxtx/mobile/productTravel/productInfo";
    public static final int channel_holiday_product_detail_mark=1005014;

    public static String channel_city_into_list="/sxtx/mobile/city/list";
    public static final int channel_city_into_list_mark=1005012;

    public static String channel_collect_product="/sxtx/mobile/user/collectAdd";
    public static final int  channel_collect_product_mark=1005013;

    //扫码支付
    public static String channel_pay_erwei="/sxtx/mobile/qrCode/qrCodePay";
    public static final int channel_pay_erwei_mark= 10000033;

    /**
     * 扫码支付
     * @param activity
     */
    public static void payByErwei(MyActivity activity,String password,Long amount,String qrCodeTypeId){
        HashMap<String,Object> params=new HashMap<String, Object>();
        params.put("userId",MyApp.user_id);
        params.put("password",password);
        params.put("amount",amount);
        params.put("qrCodeTypeId",qrCodeTypeId);
        activity.getData(channel_pay_erwei, params,channel_pay_erwei_mark);
    }


}
