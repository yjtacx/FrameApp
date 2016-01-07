package yjt.frameapp.bean;

import java.util.List;

/**
 *
 * @author yujiangtao
 * {"retCode":0,"success":true,"msg":"登录成功","userid":"1234","accountid":1000,"gayteway":[{"hecsid":10,"hecmac":"Wetn_Z2R3299998"},{"hecsid":10,"hecmac":"Wetn_Z2R3299998"}]}

 *
 */
public class LoginBean extends BaseBean {
	private static final long serialVersionUID = 10000000l;
	private String userid;
	private String accountid;
	private String pictureurl;
	private List<Data> gayteway=null;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPictureurl() {
		return pictureurl;
	}

	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public List<Data> getGayteway() {
		return gayteway;
	}

	public void setGayteway(List<Data> gayteway) {
		this.gayteway = gayteway;
	}

	@Override
	public String toString() {
		return "LoginBean{" +
				"userid='" + userid + '\'' +
				", accountid='" + accountid + '\'' +
				", pictureurl='" + pictureurl + '\'' +
				", gayteway=" + gayteway +
				'}';
	}

	public class Data {
		private String hecmacsid=null;
		private String hecsid=null;

		public String getHecmacsid() {
			return hecmacsid;
		}

		public void setHecmacsid(String hecmacsid) {
			this.hecmacsid = hecmacsid;
		}

		public String getHecsid() {
			return hecsid;
		}

		public void setHecsid(String hecsid) {
			this.hecsid = hecsid;
		}
	}
}
