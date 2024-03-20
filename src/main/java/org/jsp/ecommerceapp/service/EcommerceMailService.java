package org.jsp.ecommerceapp.service;

import org.jsp.ecommerceapp.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import static org.jsp.ecommerceapp.util.ApplicationConstants.VERIFY_LINK;
@Service
public class EcommerceMailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public String sendWelcomeEmail(Merchant merchant,HttpServletRequest request)
	{
		String siteUrl=request.getRequestURL().toString();
		String url=siteUrl.replace(request.getServletPath(), "");
		String actual_url=url+VERIFY_LINK+merchant.getToken();
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		
		try {
			helper.setTo(merchant.getEmail());
			helper.setSubject("Activate your Account");
			helper.setText(actual_url);
			mailSender.send(message);
			return "Verification Mail been sent";
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Cannot send the verification mail";
		}
		
	}
}
