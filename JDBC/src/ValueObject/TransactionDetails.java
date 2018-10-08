package ValueObject;

public class TransactionDetails {
	
	protected String cust_ssn, ssn, year, month, day , cust_zip, transaction_id , branch_code, count;
	protected String transaction_value ;
	protected String totalValue;
	protected String last_name, first_name, transaction_type, branch_state;
	public String getCust_ssn() {
		return cust_ssn;
	}
	public void setCust_ssn(String cust_ssn) {
		this.cust_ssn = cust_ssn;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getCust_zip() {
		return cust_zip;
	}
	public void setCust_zip(String cust_zip) {
		this.cust_zip = cust_zip;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getBranch_code() {
		return branch_code;
	}
	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getTransaction_value() {
		return transaction_value;
	}
	public void setTransaction_value(String transaction_value) {
		this.transaction_value = transaction_value;
	}
	public String getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(String totalValue) {
		this.totalValue = totalValue;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	public String getBranch_state() {
		return branch_state;
	}
	public void setBranch_state(String branch_state) {
		this.branch_state = branch_state;
	}
	
	
	
}
