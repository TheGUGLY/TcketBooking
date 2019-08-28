package pojos;

public class Train {
	private int Train_number;
	private String Train_name;
	
	public Train(int train_number, String train_name) {
		super();
		Train_number = train_number;
		Train_name = train_name;
	}

	public int getTrain_number() {
		return Train_number;
	}

	public void setTrain_number(int train_number) {
		Train_number = train_number;
	}

	public String getTrain_name() {
		return Train_name;
	}

	public void setTrain_name(String train_name) {
		Train_name = train_name;
	}

	@Override
	public String toString() {
		return "Train [Train_number=" + Train_number + ", Train_name=" + Train_name + "]";
	}
	
}
