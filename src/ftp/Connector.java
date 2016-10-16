package ftp;

import ftp.view.components.QuickConnecDialog.ConnectInfo;

public interface Connector {
	public ConnectResult connect(ConnectInfo cf);
	public static class ConnectResult{
		private boolean success;
		private String msg;
		
		public ConnectResult(boolean success, String msg) {
			super();
			this.success = success;
			this.msg = msg;
		}
		public boolean isSuccess() {
			return success;
		}
		public void setSuccess(boolean success) {
			this.success = success;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public ConnectResult() {
			super();
			// TODO Auto-generated constructor stub
		}
	}
}
