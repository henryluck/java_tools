package com.jlx.jdk15.enumtest;

public enum Priority {
	High(38) {
		public void perform() {
			System.out.println("high 38");
		}
	},
	Medium(36.5) {
		public void perform() {
			System.out.println("medium 36.5");
		}
	},
	Low(5.2) {
		public void perform() {
			System.out.println("low 5.2");
		}
	};
	
	double temperature;

	Priority(double t) {
		temperature = t;
	}

	public abstract void perform();

	public String getDescription(Priority p) {
		return null;
	}
}
