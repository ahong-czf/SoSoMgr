import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * �绰��������
 * @author Jack
 */
public class MobileCardBiz {
	Scanner input = new Scanner(System.in);
	//��������绰���ļ���
	Map<String, MobileCard> cardList = new HashMap<String, MobileCard>();
	Scene[] scenes = new Scene[3];//��¼����ʹ���ವĳ���
	MobileCard card = null;//��δ��¼
	boolean isStop = false;//��¼�Ƿ�ͣ��
	//��ʾ���˵�
	public void showMenu(){
		System.out.println("*************��ӭʹ�����ƶ�ҵ�����***************");
		System.out.println("1.�û���¼   2.�û�ע��   3.ʹ����   4.���ѳ�ֵ  5.�ʷ�˵��  6.�˳�ϵͳ");
		System.out.println("��ѡ��ҵ��:");
		int choose = input.nextInt();
		switch (choose) {
		case 1:
			//�û���¼
			System.out.println("�������ֻ�����:");
			String num = input.next();
			System.out.println("����������:");
			String pwd = input.next();
			isValidate(num,pwd);
			break;
		case 2:
			//�û�ע��
			register();
			break;
		case 3:
			//����ʹ���ವķ���
			useSoSo();
			break;
		case 4:
			//��������ֵ�ķ���
			System.out.println("�������ֵ���:");
			double money = input.nextDouble();
			addBalance(money);
			break;
		case 5:
			System.out.println("��Ǹ���ù�����δ���ƣ������ڴ���");
			break;
		case 6:
			System.out.println("��ӭ�´ι��٣�");
			System.exit(0);

		}
		showMenu();
	}
	
	/**
	 * �û���¼
	 * @return �Ƿ��½�ɹ� 
	 */
	public boolean isValidate(String num,String pwd){
		if(cardList.containsKey(num)){
			MobileCard mobilecard = cardList.get(num);
			if(mobilecard.getUserPwd().equals(pwd)){
				System.out.println("��¼��Ϣ��ȷ");
				card = mobilecard;
				return true;
			}else{
				System.out.println("��¼���벻��ȷ");
				return false;
			}
		}else{
			System.out.println("���ֻ����벻����");
			return false;
		}
	}
	
	/**
	 * ʹ��soso
	 */
	public void useSoSo(){
		//1.���ж��Ƿ��Ѿ���¼
		if(card == null){
			System.out.println("����δ��¼�����ȵ�¼");
			return;
		}
		if(isStop == true){
			System.out.println("��ͣ��,�뼰ʱ��ֵ");
			return;
		}
		//2.ѡ��ʹ�ó���
		System.out.println("***************�����б�*****************");
		System.out.println("���\t����\t����\t��������\t");
		int num = 1;
		for (Scene scene : scenes) {
			System.out.println(num+"\t"+scene.getType()+"\t"+scene.getData()+"\t"+scene.getDesc()+"\t");
			num++;
		}
		System.out.println("��ѡ�񳡾�:");
		int type = input.nextInt();
		Scene scene = scenes[type-1];//��ȡ�û�ѡ��ĳ������� 
		//3. �����ײ�����?
		String stype = scene.getType();
		int result = 0;
		double price = 0;//��¼��������
		if("talk".equals(stype)){
			price = 0.2;
			int time = card.getRealTalkTime();
			time += scene.getData();
			card.setRealTalkTime(time);
			result = card.getService().getTalkTime()-card.getRealTalkTime();//��������
		}else if("SMS".equals(stype)){
			price = 0.1;
			int count = card.getRealSMSCount();
			if(count > card.getService().getSmsCount()){
				result = 0-scene.getData();
			}else{
				count += scene.getData();
				card.setRealSMSCount(count);
				result = card.getService().getSmsCount()-card.getRealSMSCount();
			}
		}else if("net".equals(stype)){
			price = 0.1;
			int flow = card.getRealFlow();
			flow += scene.getData();
			card.setRealFlow(flow);
			result = card.getService().getFlow()-card.getRealFlow();
		} 
		//4.�ж������볡��˭��
		double money=0;
		if(result<0){
			//5.�������� �����ײ���ķ���
			money = (0-result)*price;//�����ײ������
		}
		//6.�ж��ײ�����������˭�� 
		card.setBalance(card.getBalance()-money);
		if(card.getBalance()<0){
			System.out.println("���������Կ۷ѣ���ͣ�����뼰ʱ��ֵ");
			isStop = true;
		}
		card.showMsg();
	}
	
	public void addBalance(double money){
		double balance = card.getBalance();
		balance += money;
		card.setBalance(balance);
		card.showMsg();
		isStop = balance>0?false:true;
	}
	
	/**
	 * ע��
	 */

	public void register(){
		//1.����9����ѡ�Ŀ���
		String[] nums = getNum();
		System.out.println("*******��ѡ��Ŀ���*******");
		for (int i = 0; i < nums.length; i++) {
			System.out.print((i+1)+"."+nums[i]+"\t");
			if((i+1)%3==0){
				//����
				System.out.println();
			}
		}
		
		System.out.println("��ѡ�����:(1~9)");
		int choose = input.nextInt();
		String num = nums[choose-1];
		System.out.println("�������û���:");
		String userName = input.next();
		System.out.println("����������:");
		String userPwd = input.next();
		//2.�����û�ѡ�������ײͶ���
		System.out.println("��ѡ���ײ�:1.�����ײ�\t2.�����ײ�\t3.�����ײ�\t");
		choose = input.nextInt();
		ServicePackage service = getPackage(choose);
		System.out.println("������Ԥ�滰�ѽ��:");
		int money = input.nextInt();
		int balance = money-service.getPrice();
		//3.����绰������
		MobileCard card = new MobileCard(num, userName, userPwd, service, balance);
		//4.�����󱣴���cardList������
		cardList.put(card.getCardNum(), card);
		//5.��ӡ�绰�����ײ������Ϣ
		service.showInfo();
		card.showMsg();
	}
	
	//����������� ��139��ͷ
	public String[] getNum(){
		String[] nums = new String[9];
		for (int i = 0; i < nums.length; i++) {
			int ran = (int)(Math.random()*90000000)+10000000;
			nums[i] = "139"+ran;
		}
		return nums;
	}
	
	/**
	 * �򵥹���ģʽ(�����ײͶ���)
	 * @return �ײͶ���
	 */
	public ServicePackage getPackage(int type){
		ServicePackage service = null;
		switch (type) {
		case 1:
			service = new TalkPackage();
			break;
		case 2:
			service = new NetPackage();
			break;
		case 3:
			service = new SuperPackage();
			break;
		default:
			break;
		}
		return service; 
	}

	/**
	 * ��ʼ��ʹ�ó���
	 */
	public void initScene(){
		Scene scene1 = new Scene("talk", 200, "��������绰��ͨ��������ó��Ħ������");
		Scene scene2 = new Scene("SMS", 100, "������֪ͨ����ͨѶ¼��Ա����΢��ͷ��");
		Scene scene3 = new Scene("net", 4*1024, "�����Ի��ǵ����������汾");
		scenes[0] = scene1;
		scenes[1] = scene2;
		scenes[2] = scene3;
	}

	/**
	 * ��ʼ���ֻ���
	 */
	public void initCard(){
		ServicePackage service = new TalkPackage();
		MobileCard card = new MobileCard("13965784201", "Mike", "123954", service, 2);
		cardList.put(card.getCardNum(), card);
		this.card = card;
	}
}
