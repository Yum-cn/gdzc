package com.assets.store.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity  
 * @Description: 入库管理
 * @author onlineGenerator
 * @date 2018-05-12 11:09:34
 * @version V1.0   
 *
 */
@Entity
@Table(name = "assets_store", schema = "")
@SuppressWarnings("serial")
public class StoreEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	private java.lang.String bpmStatus;

	/**资产类别*/
	@Excel(name="资产类别")
	private java.lang.String type;
	/**入库时间*/
	@Excel(name="入库时间",format = "yyyy-MM-dd")
	private java.util.Date storageTime;
	/**资产名称*/
	@Excel(name="应用名称")
	private java.lang.String name;
	/**来源*/
	@Excel(name="来源")
	private java.lang.String source;

	/**渠道*/
	@Excel(name="渠道")
	private java.lang.String channel;
	/**单价*/
	@Excel(name="价格")
	private java.lang.String amount;
	/**数量*/
	@Excel(name="数量")
	private java.lang.String number;
	/**过保时间*/
	@Excel(name="过保时间",format = "yyyy-MM-dd")
	private java.util.Date overInsuranceTime;
	/**预计报废时间*/
	@Excel(name="预计报废时间",format = "yyyy-MM-dd")
	private java.util.Date discardedTime;

	
	
	/**资产分类*/
	@Excel(name="资产分类")
	private String category;
	/**组别*/
	@Excel(name="组别")
	private String groupTypeCode;
	private String groupTypeName;
	/**所属网络*/
	@Excel(name="所属网络")
	private String netTypeCode;
	private String netTypeName;
	/**设备名称*/
	@Excel(name="设备名称")
	private String deviceName;
	/**所属项目*/
	@Excel(name="所属项目")
	private String proTypeCode;
	private String proTypeName;
	/**部署软件清单*/
	private String softListIds;
	@Excel(name="软件清单")
	private String softListNames;
	/**资产编码*/
	@Excel(name="资产编码")
	private java.lang.String code;
	/**品牌*/
	@Excel(name="品牌")
	private java.lang.String brand;
	/**设备型号*/
	@Excel(name="设备型号")
	private String deviceType;
	/**设备序列号*/
	@Excel(name="设备序列号")
	private String deviceSN;
	/**CPU*/
	@Excel(name="cpu")
	private String cpu;
	/**内存*/
	@Excel(name="内存")
	private String memory;
	/**硬盘*/
	@Excel(name="硬盘")
	private String disk;
	/**责任单位*/
	@Excel(name="责任单位")
	private String devicePartmentCode;
	private String devicePartmentName;
	/**责任人*/
	@Excel(name="责任人")
	private String devicePerson;
	/**联系方式*/
	@Excel(name="联系方式")
	private String devicePhone;
	/**安装地点*/
	@Excel(name="安装地点")
	private String installPlace;
	/**部署位置*/
	@Excel(name="部署位置")
	private String deployPlace;
	/**部署时间*/
	@Excel(name="部署时间")
	private String deployTime;
	/**购买时间*/
	@Excel(name="购买时间")
	private String payTime;
	/**维修到期时间*/
	@Excel(name="维保到期时间")
	private String repairEndTime;
	/**合同文件*/
	@Excel(name="合同名称")
	private java.lang.String contractName;
	private java.lang.String contractPath;
	/**备注*/
	@Excel(name="备注")
	private java.lang.String remark;
	/**设备状态*/
	@Excel(name="设备状态")
	private String deviceStatusCode;
	private String deviceStatusName;
	
	/**版本*/
	@Excel(name="版本")
	private String version;
	/**厂商*/
	@Excel(name="厂商")
	private String manufacturer;
	/**供应商*/
	@Excel(name="供应商")
	private String supplier;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */
	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */
	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */
	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */
	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程状态
	 */
	@Column(name ="BPM_STATUS",nullable=true,length=32)
	public java.lang.String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程状态
	 */
	public void setBpmStatus(java.lang.String bpmStatus){
		this.bpmStatus = bpmStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  资产编码
	 */
	@Column(name ="CODE",nullable=true,length=100)
	public java.lang.String getCode(){
		return this.code;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资产编码
	 */
	public void setCode(java.lang.String code){
		this.code = code;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  资产类别
	 */
	@Column(name ="TYPE",nullable=true,length=50)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资产类别
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  入库时间
	 */
	@Column(name ="STORAGE_TIME",nullable=true,length=32)
	public java.util.Date getStorageTime(){
		return this.storageTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  入库时间
	 */
	public void setStorageTime(java.util.Date storageTime){
		this.storageTime = storageTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  资产名称
	 */
	@Column(name ="NAME",nullable=true,length=500)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资产名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  来源
	 */
	@Column(name ="SOURCE",nullable=true,length=100)
	public java.lang.String getSource(){
		return this.source;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  来源
	 */
	public void setSource(java.lang.String source){
		this.source = source;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  品牌
	 */
	@Column(name ="BRAND",nullable=true,length=100)
	public java.lang.String getBrand(){
		return this.brand;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  品牌
	 */
	public void setBrand(java.lang.String brand){
		this.brand = brand;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  渠道
	 */
	@Column(name ="CHANNEL",nullable=true,length=200)
	public java.lang.String getChannel(){
		return this.channel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  渠道
	 */
	public void setChannel(java.lang.String channel){
		this.channel = channel;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  金额
	 */
	@Column(name ="AMOUNT",nullable=true,length=50)
	public java.lang.String getAmount(){
		return this.amount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  金额
	 */
	public void setAmount(java.lang.String amount){
		this.amount = amount;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  过保时间
	 */
	@Column(name ="OVER_INSURANCE_TIME",nullable=true,length=32)
	public java.util.Date getOverInsuranceTime(){
		return this.overInsuranceTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  过保时间
	 */
	public void setOverInsuranceTime(java.util.Date overInsuranceTime){
		this.overInsuranceTime = overInsuranceTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  预计报废时间
	 */
	@Column(name ="DISCARDED_TIME",nullable=true,length=32)
	public java.util.Date getDiscardedTime(){
		return this.discardedTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  预计报废时间
	 */
	public void setDiscardedTime(java.util.Date discardedTime){
		this.discardedTime = discardedTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARK",nullable=true,length=32)
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}

	public java.lang.String getNumber() {
		return number;
	}

	public void setNumber(java.lang.String number) {
		this.number = number;
	}

	public java.lang.String getContractName() {
		return contractName;
	}

	public void setContractName(java.lang.String contractName) {
		this.contractName = contractName;
	}

	public java.lang.String getContractPath() {
		return contractPath;
	}

	public void setContractPath(java.lang.String contractPath) {
		this.contractPath = contractPath;
	}

	public String getGroupTypeCode() {
		return groupTypeCode;
	}

	public void setGroupTypeCode(String groupTypeCode) {
		this.groupTypeCode = groupTypeCode;
	}

	public String getGroupTypeName() {
		return groupTypeName;
	}

	public void setGroupTypeName(String groupTypeName) {
		this.groupTypeName = groupTypeName;
	}

	public String getNetTypeCode() {
		return netTypeCode;
	}

	public void setNetTypeCode(String netTypeCode) {
		this.netTypeCode = netTypeCode;
	}

	public String getNetTypeName() {
		return netTypeName;
	}

	public void setNetTypeName(String netTypeName) {
		this.netTypeName = netTypeName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getProTypeCode() {
		return proTypeCode;
	}

	public void setProTypeCode(String proTypeCode) {
		this.proTypeCode = proTypeCode;
	}

	public String getProTypeName() {
		return proTypeName;
	}

	public void setProTypeName(String proTypeName) {
		this.proTypeName = proTypeName;
	}

	public String getSoftListIds() {
		return softListIds;
	}

	public void setSoftListIds(String softListIds) {
		this.softListIds = softListIds;
	}

	public String getSoftListNames() {
		return softListNames;
	}

	public void setSoftListNames(String softListNames) {
		this.softListNames = softListNames;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceSN() {
		return deviceSN;
	}

	public void setDeviceSN(String deviceSN) {
		this.deviceSN = deviceSN;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getDisk() {
		return disk;
	}

	public void setDisk(String disk) {
		this.disk = disk;
	}

	public String getDevicePartmentCode() {
		return devicePartmentCode;
	}

	public void setDevicePartmentCode(String devicePartmentCode) {
		this.devicePartmentCode = devicePartmentCode;
	}

	public String getDevicePartmentName() {
		return devicePartmentName;
	}

	public void setDevicePartmentName(String devicePartmentName) {
		this.devicePartmentName = devicePartmentName;
	}

	public String getDevicePerson() {
		return devicePerson;
	}

	public void setDevicePerson(String devicePerson) {
		this.devicePerson = devicePerson;
	}

	public String getDevicePhone() {
		return devicePhone;
	}

	public void setDevicePhone(String devicePhone) {
		this.devicePhone = devicePhone;
	}

	public String getInstallPlace() {
		return installPlace;
	}

	public void setInstallPlace(String installPlace) {
		this.installPlace = installPlace;
	}

	public String getDeployPlace() {
		return deployPlace;
	}

	public void setDeployPlace(String deployPlace) {
		this.deployPlace = deployPlace;
	}

	public String getDeployTime() {
		return deployTime;
	}

	public void setDeployTime(String deployTime) {
		this.deployTime = deployTime;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getRepairEndTime() {
		return repairEndTime;
	}

	public void setRepairEndTime(String repairEndTime) {
		this.repairEndTime = repairEndTime;
	}

	public String getDeviceStatusCode() {
		return deviceStatusCode;
	}

	public void setDeviceStatusCode(String deviceStatusCode) {
		this.deviceStatusCode = deviceStatusCode;
	}

	public String getDeviceStatusName() {
		return deviceStatusName;
	}

	public void setDeviceStatusName(String deviceStatusName) {
		this.deviceStatusName = deviceStatusName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	
}
