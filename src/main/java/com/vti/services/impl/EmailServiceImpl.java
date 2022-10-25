package com.vti.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.vti.entities.User;
import com.vti.repositories.RegistrationUserTokenRepository;
import com.vti.repositories.ResetPasswordTokenRepository;
import com.vti.services.EmailService;
import com.vti.services.UserService;

@Component
public class EmailServiceImpl implements EmailService {

	@Autowired
	private UserService userService;

	@Autowired
	private RegistrationUserTokenRepository registrationUserTokenRepository;

	@Autowired
	private ResetPasswordTokenRepository resetPasswordTokenRepository;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendRegistrationUserConfirm(String email) {

		User user = userService.findUserByEmailNotActive(email);
		String token = registrationUserTokenRepository.findByUserId(user.getId());

		String confirmationUrl = "http://localhost:8080/api/v1/users/activeUser?token=" + token;

		String subject = "Xác Nhận Đăng Ký Account";
		String content = "Bạn đã đăng ký thành công. Click vào link dưới đây để kích hoạt tài khoản \n"
				+ confirmationUrl;

		sendEmail(email, subject, content);
	}

	@Override
	public void sendResetPassword(String email) {

		User user = userService.findUserByEmail(email);
		String token = resetPasswordTokenRepository.findByUserId(user.getId());

		String confirmationUrl = "http://localhost:3000/auth/new-password/" + token;

		String subject = "Thiết lập lại mật khẩu";
		String content = "Click vào link dưới đây để thiết lập lại mật khẩu (nếu không phải bạn xin vui lòng bỏ qua).\n"
				+ confirmationUrl;

		sendEmail(email, subject, content);
	}

	private void sendEmail(final String recipientEmail, final String subject, final String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(recipientEmail);
		message.setSubject(subject);
		message.setText(content);

		mailSender.send(message);
	}

}
