package library.app;

import java.awt.EventQueue;

import library.view.HomePage;

public class AppLibrary {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new HomePage(0, "ĐĂNG NHẬP ĐỂ VÀO HỆ THỐNG THƯ VIỆN", "");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
