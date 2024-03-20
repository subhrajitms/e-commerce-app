package org.jsp.ecommerceapp.service;

import static org.jsp.ecommerceapp.util.ApplicationConstants.VERIFY_LINK;

import org.jsp.ecommerceapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import static org.jsp.ecommerceapp.util.ApplicationConstants.USER_LINK;

@Service
public class UserMailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public String sendWelcomeMail(User user,HttpServletRequest request)
	{
		String siteUrl=request.getRequestURL().toString();
		String url=siteUrl.replace(request.getServletPath(), "");
		String actual_url=url+USER_LINK+user.getToken();
		MimeMessage message=javaMailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		
		try {
			helper.setTo(user.getEmail());
			helper.setSubject("Activate your Account");
			helper.setText(actual_url);
			javaMailSender.send(message);
			return "Verification Mail been sent";
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Cannot send the verification mail";
		}
		

	}
}
