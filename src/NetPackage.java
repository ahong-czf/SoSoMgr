
public class NetPackage extends ServicePackage{

	public NetPackage(){
		super(100, 20, 30*1024, 68);
	}
	
	public NetPackage(int talkTime, int smsCount, int flow, int price){
		super(talkTime, smsCount, flow, price);
	}
	
	@Override
	public void showInfo() {
		System.out.println("�����ײͣ�ͨ��ʱ��Ϊ"+this.getTalkTime()+"����/�£���������Ϊ"+this.getSmsCount()+"��/�£���������:"+this.getFlow()+"�ʷ�Ϊ"+this.getPrice()+"Ԫ/�¡�");
	}
	
}
