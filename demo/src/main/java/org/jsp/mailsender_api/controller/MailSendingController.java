package org.jsp.mailsender_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class MailSendingController {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${activation.token}")
	private String token;
	
	@PostMapping("/send-mail")
	public String sendMail(@RequestParam String mailId,HttpServletRequest request) {
		String url=request.getRequestURL().toString();
		String path=request.getServletPath();
		String activation_link=url.replace(path, "/api/activate");
		activation_link=activation_link+"?token: "+token;
		System.out.println(activation_link);
		MimeMessage message=javaMailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		
		
		try {
			helper.setTo(mailId);
			helper.setSubject("Testing The New Mail Sending API");
			helper.setText("Activate Your Account By Clicking on the Following URL: "+activation_link);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		javaMailSender.send(message);
		return "Mail Has Been Send To: "+mailId;
		
		
		}
	
	@PostMapping("/activate")
	public String activate(@RequestParam String token){
		if(token.equals(this.token)) {
			return "Your Account has been Activated";
		}
		return "Cannot Activate Account because Verification Link Is Invalid";
		
	}

}
