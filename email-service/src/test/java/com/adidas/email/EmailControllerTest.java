package com.adidas.email;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.adidas.email.dto.EmailTemplate;
import com.adidas.email.dto.SubscriptionRequest;
import com.adidas.email.service.EmailService;
import com.adidas.email.service.impl.EmailServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author leandrobelluscio
 *
 */
@SpringBootTest(classes = EmailApplication.class)
@ActiveProfiles("test")
public class EmailControllerTest extends AbstractTestNGSpringContextTests{

	@Autowired
	private WebApplicationContext context;
   

	@Autowired
	private EmailService emailService = new EmailServiceImpl();

	private  MockMvc  mockMvc;
   

	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	@BeforeClass
	public  void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test()
	public void testSentEmail() throws Exception{

		Date bithDate = df.parse("1983-15-06");
		SubscriptionRequest request = new SubscriptionRequest();
		request.setCampaignId(1L);
		request.setConsent(true);
		request.setEmail("example6@adidas.com");
		request.setFirstName("MockName");
		request.setGender("M");
		request.setBirthDate(bithDate);
		
		EmailTemplate emailTemplate = emailService.sendEmail(request);
		assertEquals(emailTemplate.getTo(), request.getEmail());
		assertTrue(emailTemplate.getBody().contains(request.getFirstName()));
		
	}
}
