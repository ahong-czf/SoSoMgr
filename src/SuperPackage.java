
public class SuperPackage extends ServicePackage{

	public SuperPackage(){
		super(500, 200, 10*1024, 78);
	}
	
	public SuperPackage(int talkTime, int smsCount, int flow, int price){
		super(talkTime, smsCount, flow, price);
	}
	
	@Override
	public void showInfo() {
		System.out.println("超人套餐：通话时长为"+this.getTalkTime()+"分钟/月，短信条数为"+this.getSmsCount()+"条/月，网络流量:"+this.getFlow()+"资费为"+this.getPrice()+"元/月。");
	}
	
}
