/**
 * 套餐抽象类
 * @author Jack
 */
public abstract class ServicePackage {
	private int talkTime;
	private int smsCount;
	private int flow;
	private int price;//月租
	public int getTalkTime() {
		return talkTime;
	}
	public void setTalkTime(int talkTime) {
		this.talkTime = talkTime;
	}
	public int getSmsCount() {
		return smsCount;
	}
	public void setSmsCount(int smsCount) {
		this.smsCount = smsCount;
	}
	public int getFlow() {
		return flow;
	}
	public void setFlow(int flow) {
		this.flow = flow;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public ServicePackage(){}
	
	public ServicePackage(int talkTime, int smsCount, int flow, int price) {
		this.talkTime = talkTime;
		this.smsCount = smsCount;
		this.flow = flow;
		this.price = price;
	}
	//打印本套餐信息
	public abstract void showInfo();
}
