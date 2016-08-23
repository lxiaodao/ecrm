/**
 * Copyright (c) 2011-2015 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.utils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 供应索引定义对象 .
 * <p/>代码注释标明了在solr里面的索引数据类型
 * @ClassName: SupplyIndex 
 * @author Administrator
 * @date 2015年1月14日 下午2:49:58
 */
public class SupplyIndex {
	
	private Long id;// id of supply
	private String supply_title;// text_general,
	private String supply_memo;// text_general,
	private String shop_title;// text_general,
	private String itel_no;// string, //itel号码
	private String trade_name;// string,
	private String area_name;// string,
	private Integer reallegalize;// int, //供应商类型，0:个人，1:企业 ( 0：未认证；1：已认证)
	private Integer quality_guarantee;// int, //品质认证
	private Integer sevenlegalize;// int, // 7天无理由退换货
	private Integer realname_guarantee;// int, //实名认证
	private BigDecimal caution_money;// currency,
	private BigDecimal low_price;// currency,
	private BigDecimal high_price;// currency,
	private Date updatetime;// date,
	private Date end_date;// date, //到期日期
	private Integer soldcount;// int, //销售数量
    
	public void setId(Long id) {
		this.id = id;
	}
	public void setSupply_title(String supply_title) {
		this.supply_title = supply_title;
	}
	public void setSupply_memo(String supply_memo) {
		this.supply_memo = supply_memo;
	}
	public void setShop_title(String shop_title) {
		this.shop_title = shop_title;
	}
	public void setItel_no(String itel_no) {
		this.itel_no = itel_no;
	}
	public void setTrade_name(String trade_name) {
		this.trade_name = trade_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	public void setReallegalize(Integer reallegalize) {
		this.reallegalize = reallegalize;
	}
	public void setQuality_guarantee(Integer quality_guarantee) {
		this.quality_guarantee = quality_guarantee;
	}
	public void setSevenlegalize(Integer sevenlegalize) {
		this.sevenlegalize = sevenlegalize;
	}
	public void setRealname_guarantee(Integer realname_guarantee) {
		this.realname_guarantee = realname_guarantee;
	}
	public void setCaution_money(BigDecimal caution_money) {
		this.caution_money = caution_money;
	}
	public void setLow_price(BigDecimal low_price) {
		this.low_price = low_price;
	}
	public void setHigh_price(BigDecimal high_price) {
		this.high_price = high_price;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public void setSoldcount(Integer soldcount) {
		this.soldcount = soldcount;
	}

	
	public Long getId() {
		return id;
	}
	public String getSupply_title() {
		return supply_title;
	}

	public String getSupply_memo() {
		return supply_memo;
	}

	public String getShop_title() {
		return shop_title;
	}

	public String getItel_no() {
		return itel_no;
	}

	public String getTrade_name() {
		return trade_name;
	}

	public String getArea_name() {
		return area_name;
	}

	public Integer getReallegalize() {
		return reallegalize;
	}

	public Integer getQuality_guarantee() {
		return quality_guarantee;
	}

	public Integer getSevenlegalize() {
		return sevenlegalize;
	}

	public Integer getRealname_guarantee() {
		return realname_guarantee;
	}

	public BigDecimal getCaution_money() {
		return caution_money;
	}

	public BigDecimal getLow_price() {
		return low_price;
	}

	public BigDecimal getHigh_price() {
		return high_price;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public Integer getSoldcount() {
		return soldcount;
	}

}
