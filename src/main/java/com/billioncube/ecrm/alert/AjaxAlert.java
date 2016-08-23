/**
 * 
 */
package com.billioncube.ecrm.alert;

import com.weheros.framework.core.front.Message;

/**
 * @author beijingzhongshangjiaogongyinglianguanliyouxiangongsi
 *
 */
public class AjaxAlert {
	/**
	 * {
	"statusCode":"200",
	"message":"chenggongne",
	"tabid":"",
    "dialogid":"dialog-mask",
	"closeCurrent":false,
    "forward":"",
    "forwardConfirm":""
}
	 */
	/**
	 * 默认成功操作后，关闭此提示框。
	 */
	public final static AjaxAlert SUCCESS=new AjaxAlert("200","操作成功！");
	public final static AjaxAlert ERROR=new AjaxAlert("400","操作错误！");
	private String message; 
	private String tabid;
	private String dialogid;
	private boolean closeCurrent;
	private String statusCode;
	
	public static AjaxAlert getSuccess() {
		return SUCCESS;
	}

	public static AjaxAlert getError() {
		return ERROR;
	}

	public String getMessage() {
		return message;
	}

	public String getDialogid() {
		return dialogid;
	}

	public boolean isCloseCurrent() {
		return closeCurrent;
	}

	public String getStatusCode() {
		return statusCode;
	}
	

	public String getTabid() {
		return tabid;
	}

	public AjaxAlert(String statusCode,String message, String tabid, boolean closeCurrent) {
		super();
		this.message = message;
		this.tabid = tabid;
		this.closeCurrent = closeCurrent;
		this.statusCode = statusCode;
	}

	public AjaxAlert(String code,String message){
		this.statusCode=code;
		this.message=message;
	}
	
	public static AjaxAlert convertFrameworkMessageToAlert(Message message){
		return new AjaxAlert(message.getCode(),message.getMsg());
	}
	
	

}
