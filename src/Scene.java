/**
 * ≥°æ∞¿‡
 * @author ∞¢∫‰
 *
 */
public class Scene {
	private String type;
	private int data;
	private String desc;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Scene(String type, int data, String desc) {
		this.type = type;
		this.data = data;
		this.desc = desc;
	}
	public Scene() {

	}	
}
