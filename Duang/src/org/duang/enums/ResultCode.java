package org.duang.enums;

public enum ResultCode {
	SUCCESS{
		@Override
		public String getVal() {
			return "00000";
		}
	},
	Doing{

		@Override
		public String getVal() {
			return "00001";
		}
		
	};
	public abstract String getVal();
}
