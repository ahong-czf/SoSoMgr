
public class SuperPackage extends ServicePackage{

	public SuperPackage(){
		super(500, 200, 10*1024, 78);
	}
	
	public SuperPackage(int talkTime, int smsCount, int flow, int price){
		super(talkTime, smsCount, flow, price);
	}
	
	@Override
	public void showInfo() {
		System.out.println("�����ײͣ�ͨ��ʱ��Ϊ"+this.getTalkTime()+"����/�£���������Ϊ"+this.getSmsCount()+"��/�£���������:"+this.getFlow()+"�ʷ�Ϊ"+this.getPrice()+"Ԫ/�¡�");
	}
	
}
