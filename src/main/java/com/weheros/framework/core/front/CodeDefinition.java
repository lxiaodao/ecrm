/**
 * Copyright (c) 2011-2013 iTel Technology Inc,All Rights Reserved.
 */

package com.weheros.framework.core.front;

/**
 * @ClassName: ErrorCode
 * @author Administrator
 * @date 2013年11月22日 下午12:53:02
 */
public enum CodeDefinition {
	 //2xx 成功                     4xx 客户端错误                         5xx 服务端错误
	 LOGIN_SUCCESS("200","login_is_sucess"),
	 USER_OR_PASSWORD_RIGHT("200","user_or_password_right"),
	 LOGIN_FAIL("400","action_is_fail"),
	 USER_OR_PASSWORD_INCORRECT("401","user_or_password_incorrect"),
	 VERIFICATION_CODE_INCORRECT("402","verification_code_incorrect"),	
	 BLACK_LIST_ALREADY_EXISTS("405","black_list_already_exists"),	
	 
	 /*******************************************注册**********************************************************/
	 THE_ITEL_NUMBER_IS_LEGITIMATE("200","the_itel_number_is_legitimate"),
	 ITEL_LENGTH_TOO_LONG("401","itel_length_too_long"),
	 THE_ITEL_NUMBER_IS_REPEAT("405","the_itel_number_is_repeat"),
	 THE_ITEL_NUMBER_IS_PRETTY("403","the_itel_number_is_pretty"),
	 THE_PHONE_NUMBER_IS_REPEAT("405","the_phone_number_is_repeat"),
	 
	 /*三次验证失败*/
	 THREE_TIMES_INPUT_ERROR("406","three_times_input_error"),
	 /*密保验证失败*/ 
	 SECURITY_VALIDATION_FAILED("407","security_validation_failed"),	
	 /*修改密码失败*/
	 CHANGE_THE_PASSWORD_FAILED("408","change_the_password_failed"),
	 /*设置密保失败*/ 
	 SET_PASSWORD_PROTECTION_FAILED("409","set_password_protection_failed"),	
	 /*修改密保失败*/
	 CHANGE_PASSWORD_PROTECTION_FAILED("410","change_password_protection_failed"),	
	 /*数据为空*/
	 DATA_IS_EMPTY("411","data_is_empty"),
	 /*重复添加好友*/
	 APPLY_ITEL_FRIEND_REPEAT("412","apply_itel_friend_repeat"),	
	 /*密码长度错误*/
	 PASSWORD_LENGTH_ERROR("413","password_length_error"),
	 /*输入数据不能为空*/
	 CANNOT_ENTER_EMPTY_STRING("414","cannot_enter_empty_string"),
	 /*号码不存在*/
	 NUMBER_DOES_NOT_EXIST("415","number_does_not_exist"),
	 /*没有设置密保问题*/
	 NOT_SET_SECURITY_QUESTION("222","not_set_security_question"),
	 /*已经是最新版本*/
	 IS_THE_LATEST_VERSION("201","is_the_latest_version"),
	 /*有新版本*/
	 THERE_IS_A_NEW_VERSION("202","there_is_a_new_version"),
	 /*版本号不存在或者类型错误*/
	 THE_VERSION_NUMBER_DOES_NOT_EXIT_OR_THE_WRONG_TYPE("416","the_version_number_does_not_exist_or_the_wrong_type"),
	 /******服务器错误，请联系管理员*******/
	 SERVER_EXCEPTION("500","something_wrong_with_server"),
    /**
     * 支付错误码
     */
	 PARAMETER_ISNULL("20001","com.zsj.parameter.isnull"),
	 SERVICE_NOTEXIST("20002","com.zsj.service.notexist"),
	 
     PAY_BUSINESS_PARTNERID_ISNULL("10001","com.zsj.cooperate.pay.dataCheck.create.business.zsjpay.partnerId.checkNull"),
     PAY_BUSINESS_SERVICENAME_SIGN_SIGNTYPE_ISNULL("10002","com.zsj.cooperate.pay.dataCheck.create.business.zsjpay."
         + "serviceName.checkNull"),
     PAY_BUSINESS_TOTALFEE_ISNULL("10003","com.zsj.cooperate.pay.dataCheck.create.business.zsjpay.totalFee.checkNull"),
     PAY_BUSINESS_SELLERID_ISNULL("10004","com.zsj.cooperate.pay.dataCheck.create.business.zsjpay.sellerId.checkNull"),
     PAY_BUSINESS_BUYERID_ISNULL("10005","com.zsj.cooperate.pay.dataCheck.create.business.zsjpay.buyerId.checkNull"),
     PAY_BUSINESS_CHARSETTYPE_ISNULL("10006","com.zsj.cooperate.pay.dataCheck.create.business.zsjpay.charsetType.checkNull"),
     PAY_BUSINESS_RETURNURL_ISNULL("10007","com.zsj.cooperate.pay.dataCheck.create.business.zsjpay.returnUrl.checkNull"),
     PAY_BUSINESS_NOTIFYURL_ISNULL("10008","com.zsj.cooperate.pay.dataCheck.create.business.zsjpay.notifyUrl.checkNull"),
     PAY_BUSINESS_SIGN_ISNULL("10009","com.zsj.cooperate.pay.dataCheck.create.business.zsjpay.sign.checkNull"),
     PAY_BUSINESS_SIGNTYPE_ISNULL("10010","com.zsj.cooperate.pay.dataCheck.create.business.zsjpay.signType.checkNull"),
     PAY_BUSINESS_PRODUCTSN_ISNULL("10011","com.zsj.cooperate.pay.dataCheck.create.business.zsjpay.productSn.checkNull"),
	 
     //商户账户不存在
     PAY_BUSINESS_SELLERID_ISNOTEXIST("10012","com.zsj.pay.cooperate.pay.dataCheck.create.business.zsjpay.productSn.checkNull"),
     //买方个人账户不存在
     PAY_BUSINESS_BUYERID_ISNOTEXIST("10013","com.zsj.cooperate.pay.dataCheck.create.business.zsjpay.buyerId.checkNull"),
     //合作号商户不存在
     PAY_BUSINESS_PARTNERID_ISNOTEXIST("10014","com.zsj.cooperate.pay.dataCheck.create.business.zsjpay.partnerId.checkNull"),
     //支付密码错误
     PAY_BUSINESS_PAYPASSWORD_ERROR("10015","com.zsj.pay.payPassword.isError"),
     //余额不足
     PAY_BUSINESS_CASHLESS_ERROR("10016","com.zsj.pay.cashless.error"),
     //服务协议
     PAY_BUSINESS_SERVICEAGREEMENT_ERROR("10017",""),
     //额度不足
     PAY_BUSINESS_VMONEYLESS_ERROR("10018","com.zsj.pay.virtualmoneyless.error"),
     //支付成功
     PAY_BUSINESS_PAY_SUCCESS("10019","com.zsj.pay.paymoney.success"),
     //输入数据有误
     PAY_BUSINESS_INPUTDATACHECK_ERROR("10020","com.zsj.pay.inputDataCheck.error"),
     //输入数据有误
     PAY_BUSINESS_PARAMETERVALIDATIONEXECPTION_ERROR("10021","com.zsj.pay.parameterValidationExecption.info"),
     //重复交易
     PAY_BUSINESS_REPEAT_SALE_ERROR("10022","com.zsj.pay.repeatsale.error"),
     //重复退货
     PAY_BUSINESS_REPEAT_REFUND_ERROR("10023","com.zsj.pay.repeat.refund.error"),
     //退货金额不为空
     PAY_BUSINESS_REFUND_MONEYNULL_ERROR("10024","com.zsj.pay.refund.isnull.error"),
     //退货金额小于该订单总额（不合法退货请求）
     PAY_BUSINESS_REPEAT_MONEYISLESS_ERROR("10025","com.zsj.pay.refund.moneyisless.error"),
     //合作号不合法 
     PAY_BUSINESS_PARTNERID_ISNOTVALID("10026","com.zsj.cooperate.pay.dataCheck.create.business.zsjpay.partnerId.isNotValid"),
     //签名不合法
     PAY_BUSINESS_SING_ISNOTVALID("10027","com.zsj.cooperate.pay.dataCheck.create.business.zsjpay.sign.isNotValid"),
     //批量支付商品数据集合参数不合法
     BATCHPAY_PRODUCTDATALIST_ISNOTVALID("10028",""),
     //批量支付订单支付总额参数不合法
     BATCHPAY_TOTALFEE_ISNOTVALID("10029",""),
     //批量支付付款方账户参数不合法
     BATCHPAY_BUYERID_ISNOTVALID("10030",""),
     //批量支付前端回跳URL参数不合法
     BATCHPAY_RETURNURL_ISNOTVALID("10031",""),
     //批量支付后端回调URL参数不合法
     BATCHPAY_NOTIFYURL_ISNOTVALID("10032",""),
     //批量支付付款人类型参数不合法
     BATCHPAY_BIZTYPE_ISNOTVALID("10033",""),
     //批量支付批量付款笔数参数不合法
     BATCHPAY_BATCHNUM_ISNOTVALID("10034",""),
     //批量支付批次号参数不合法
     BATCHPAY_BATCHNO_ISNOTVALID("10035",""),
     //批量支付支付时间参数不合法
     BATCHPAY_PAYTIME_ISNOTVALID("10036",""),
     //支付的订单集合总额与批参数总额不等
     BATCHPAY_TOTALFEE_NOTEQUAL_ISNOTVALID("10037",""),
     //支付的比数和总比数不一致
     BATCHPAY_BATCHNUM_NOTEQUAL_ISNOTVALID("10038",""),
     //支付方式不合法
     PAY_BUSINESS_PAYTYPE_ISNOTVALID("10039","com.zsj.pay.paytype.isnotvalid"),
     //系统繁忙
     OS_BUSY("10040","com.zsj.pay.os.busy"),
     //该交易状态不满足退货要求。
     PAY_BUSINESS_REPEAT_PAYSTATUSISFINISH_ERROR("10041","");
     
     private final String code;
     private final String internationalMessageCode;
	 public String getCode() {
		return code;
	}
	public String getInternationalMessageCode() {
		return internationalMessageCode;
	}
	private CodeDefinition(String code, String internationalMessageCode) {
		this.code = code;
		this.internationalMessageCode = internationalMessageCode;
    }
    
}
