package com.vti.services;

public interface EmailService {

	void sendRegistrationUserConfirm(String email);

	void sendResetPassword(String email);

}
