/**
 * �����ײ�
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
		System.out.println("�����ײͣ�ͨ��ʱ��Ϊ"+this.getTalkTime()+"����/�£���������Ϊ"+this.getSmsCount()+"��/�£�" +
				"��������:"+this.getFlow()+"�ʷ�Ϊ"+this.getPrice()+"Ԫ/�¡�");
	}
	
}
