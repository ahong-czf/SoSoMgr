/**
 * 话唠套餐
 * @author Administrator
 *
 */
public class TalkPackage extends ServicePackage{
	
	public TalkPackage(){
		super(500, 30, 1024, 58);
	}
	
	public TalkPackage(int talkTime, int smsCount, int flow, int price){
		super(talkTime, smsCount, flow, price);
	}
	
	@Override
	public void showInfo() {
		System.out.println("话唠套餐：通话时长为"+this.getTalkTime()+"分钟/月，短信条数为"+this.getSmsCount()+"条/月，" +
				"网络流量:"+this.getFlow()+"资费为"+this.getPrice()+"元/月。");
	}
	
}
