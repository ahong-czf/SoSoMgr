/**
 * 手机卡类
 * @author Jack
 */
public class MobileCard {
	private String cardNum;
	private String userName;
	private String userPwd;
	private ServicePackage service;//套餐父类
	private double balance;//余额
	private int realTalkTime;//真实通话时间
	private int realSMSCount;//真实短信条数
	private int realFlow;//真实网络流量
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public ServicePackage getService() {
		return service;
	}
	public void setService(ServicePackage service) {
		this.service = service;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getRealTalkTime() {
		return realTalkTime;
	}
	public void setRealTalkTime(int realTalkTime) {
		this.realTalkTime = realTalkTime;
	}
	public int getRealSMSCount() {
		return realSMSCount;
	}
	public void setRealSMSCount(int realSMSCount) {
		this.realSMSCount = realSMSCount;
	}
	public int getRealFlow() {
		return realFlow;
	}
	public void setRealFlow(int realFlow) {
		this.realFlow = realFlow;
	}
	public MobileCard(String cardNum, String userName, String userPwd,
			ServicePackage service, double balance) {
		this.cardNum = cardNum;
		this.userName = userName;
		this.userPwd = userPwd;
		this.service = service;
		this.balance = balance;
	}
	public MobileCard() {

	}
	public void showMsg(){
		System.out.println("卡号:"+getCardNum()+"\t用户名:"+getUserName()+"\t当前余额为:"+getBalance());
	}
}
