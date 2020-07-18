import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 电话卡管理类
 * @author Jack
 */
public class MobileCardBiz {
	Scanner input = new Scanner(System.in);
	//声明保存电话卡的集合
	Map<String, MobileCard> cardList = new HashMap<String, MobileCard>();
	Scene[] scenes = new Scene[3];//记录三种使用嗖嗖的场景
	MobileCard card = null;//尚未登录
	boolean isStop = false;//记录是否停机
	//显示主菜单
	public void showMenu(){
		System.out.println("*************欢迎使用嗖嗖移动业务大厅***************");
		System.out.println("1.用户登录   2.用户注册   3.使用嗖嗖   4.话费充值  5.资费说明  6.退出系统");
		System.out.println("请选择业务:");
		int choose = input.nextInt();
		switch (choose) {
		case 1:
			//用户登录
			System.out.println("请输入手机号码:");
			String num = input.next();
			System.out.println("请输入密码:");
			String pwd = input.next();
			isValidate(num,pwd);
			break;
		case 2:
			//用户注册
			register();
			break;
		case 3:
			//调用使用嗖嗖的方法
			useSoSo();
			break;
		case 4:
			//调用余额充值的方法
			System.out.println("请输入充值金额:");
			double money = input.nextDouble();
			addBalance(money);
			break;
		case 5:
			System.out.println("抱歉，该功能尚未完善，敬请期待！");
			break;
		case 6:
			System.out.println("欢迎下次光临！");
			System.exit(0);

		}
		showMenu();
	}
	
	/**
	 * 用户登录
	 * @return 是否登陆成功 
	 */
	public boolean isValidate(String num,String pwd){
		if(cardList.containsKey(num)){
			MobileCard mobilecard = cardList.get(num);
			if(mobilecard.getUserPwd().equals(pwd)){
				System.out.println("登录信息正确");
				card = mobilecard;
				return true;
			}else{
				System.out.println("登录密码不正确");
				return false;
			}
		}else{
			System.out.println("该手机号码不存在");
			return false;
		}
	}
	
	/**
	 * 使用soso
	 */
	public void useSoSo(){
		//1.先判断是否已经登录
		if(card == null){
			System.out.println("您尚未登录，请先登录");
			return;
		}
		if(isStop == true){
			System.out.println("已停机,请及时充值");
			return;
		}
		//2.选择使用场景
		System.out.println("***************场景列表*****************");
		System.out.println("序号\t类型\t数据\t描述场景\t");
		int num = 1;
		for (Scene scene : scenes) {
			System.out.println(num+"\t"+scene.getType()+"\t"+scene.getData()+"\t"+scene.getDesc()+"\t");
			num++;
		}
		System.out.println("请选择场景:");
		int type = input.nextInt();
		Scene scene = scenes[type-1];//获取用户选择的场景对象 
		//3. 计算套餐余量?
		String stype = scene.getType();
		int result = 0;
		double price = 0;//记录数量单价
		if("talk".equals(stype)){
			price = 0.2;
			int time = card.getRealTalkTime();
			time += scene.getData();
			card.setRealTalkTime(time);
			result = card.getService().getTalkTime()-card.getRealTalkTime();//计算余量
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
		//4.判断余量与场景谁大
		double money=0;
		if(result<0){
			//5.更新余量 计算套餐外的费用
			money = (0-result)*price;//计算套餐外费用
		}
		//6.判断套餐外费用与余额谁大 
		card.setBalance(card.getBalance()-money);
		if(card.getBalance()<0){
			System.out.println("您的余额不足以扣费，已停机，请及时充值");
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
	 * 注册
	 */

	public void register(){
		//1.生成9个可选的卡号
		String[] nums = getNum();
		System.out.println("*******可选择的卡号*******");
		for (int i = 0; i < nums.length; i++) {
			System.out.print((i+1)+"."+nums[i]+"\t");
			if((i+1)%3==0){
				//换行
				System.out.println();
			}
		}
		
		System.out.println("请选择序号:(1~9)");
		int choose = input.nextInt();
		String num = nums[choose-1];
		System.out.println("请输入用户名:");
		String userName = input.next();
		System.out.println("请输入密码:");
		String userPwd = input.next();
		//2.根据用户选择生成套餐对象
		System.out.println("请选择套餐:1.话唠套餐\t2.网虫套餐\t3.超人套餐\t");
		choose = input.nextInt();
		ServicePackage service = getPackage(choose);
		System.out.println("请输入预存话费金额:");
		int money = input.nextInt();
		int balance = money-service.getPrice();
		//3.构造电话卡对象
		MobileCard card = new MobileCard(num, userName, userPwd, service, balance);
		//4.将对象保存于cardList集合中
		cardList.put(card.getCardNum(), card);
		//5.打印电话卡和套餐相关信息
		service.showInfo();
		card.showMsg();
	}
	
	//生成随机卡号 以139开头
	public String[] getNum(){
		String[] nums = new String[9];
		for (int i = 0; i < nums.length; i++) {
			int ran = (int)(Math.random()*90000000)+10000000;
			nums[i] = "139"+ran;
		}
		return nums;
	}
	
	/**
	 * 简单工厂模式(生成套餐对象)
	 * @return 套餐对象
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
	 * 初始化使用场景
	 */
	public void initScene(){
		Scene scene1 = new Scene("talk", 200, "跟老王打电话沟通关于中美贸易摩擦升级");
		Scene scene2 = new Scene("SMS", 100, "发短信通知所有通讯录人员换了微信头像");
		Scene scene3 = new Scene("net", 4*1024, "看来自火星的你大结局蓝光版本");
		scenes[0] = scene1;
		scenes[1] = scene2;
		scenes[2] = scene3;
	}

	/**
	 * 初始化手机卡
	 */
	public void initCard(){
		ServicePackage service = new TalkPackage();
		MobileCard card = new MobileCard("13965784201", "Mike", "123954", service, 2);
		cardList.put(card.getCardNum(), card);
		this.card = card;
	}
}
