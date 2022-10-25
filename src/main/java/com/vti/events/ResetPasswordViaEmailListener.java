package com.vti.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vti.services.impl.EmailServiceImpl;


@Component
public class ResetPasswordViaEmailListener implements ApplicationListener<OnResetPasswordViaEmailEvent> {

	@Autowired
	private EmailServiceImpl emailService;

	@Override
	public void onApplicationEvent(final OnResetPasswordViaEmailEvent event) {
		sendResetPasswordViaEmail(event.getEmail());
	}

	private void sendResetPasswordViaEmail(String email) {
		emailService.sendResetPassword(email);
	}

}