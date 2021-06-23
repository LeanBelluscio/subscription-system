/**
 * 
 */
package com.adidas.subscription;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.adidas.subscription.domain.Campaign;
import com.adidas.subscription.domain.Subscription;
import com.adidas.subscription.domain.User;
import com.adidas.subscription.dto.SubscriptionRequest;
import com.adidas.subscription.errorhandling.ObjectNotFoundException;
import com.adidas.subscription.errorhandling.SubscriptionUniqueErrorException;
import com.adidas.subscription.service.SubscriptionService;
import com.adidas.subscription.service.impl.SubscriptionServiceImpl;
import com.adidas.subscription.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
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
@SpringBootTest(classes = SubscriptionApplication.class)
@ActiveProfiles("test")
public class SubscriptionControllerTest  extends AbstractTestNGSpringContextTests{

	@Autowired
	private WebApplicationContext context;
   

	@Autowired
	private SubscriptionService subscriptionService = new SubscriptionServiceImpl();

	private  MockMvc  mockMvc;
   

	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	@BeforeClass
	public  void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	


	@Test
	public void createSubscription() throws Exception{

			Date bithDate = df.parse("1983-15-06");
			SubscriptionRequest request = new SubscriptionRequest();
			request.setCampaignId(1L);
			request.setConsent(true);
			request.setEmail("example3@adidas.com");
			request.setFirstName("MockName");
			request.setGender("M");
			request.setBirthDate(bithDate);
			
			Subscription newSubscription = this.subscriptionService.createNewSubscription(request);
			
			assertNotNull(newSubscription.getId());
			assertEquals(newSubscription.getUser().getEmail(), request.getEmail());
			
	}


	@Test(expectedExceptions = SubscriptionUniqueErrorException.class)
	public void testExistingSubscription() throws Exception{
		Date bithDate = df.parse("1983-15-06");
		SubscriptionRequest request = new SubscriptionRequest();
		request.setCampaignId(1L);
		request.setConsent(true);
		request.setEmail("example3@adidas.com");
		request.setFirstName("MockName");
		request.setGender("M");
		request.setBirthDate(bithDate);
		
		User mockUser = new User(1L, "example@adidas.com", "MockName", "M", bithDate);
		Campaign mockCampaign = new Campaign(1L,"Summer Offers","Enjoy the Summer with Adidas",df.parse("2021-06-21"),df.parse("2021-09-20"));


		Subscription newSubscription = this.subscriptionService.createNewSubscription(request);
		
	}

	
	@Test(expectedExceptions = ObjectNotFoundException.class)
	public void testMissingCampaing() throws Exception{
		Date bithDate = df.parse("1983-15-06");
		SubscriptionRequest request = new SubscriptionRequest();
		request.setCampaignId(6L);
		request.setConsent(true);
		request.setEmail("example4@adidas.com");
		request.setFirstName("MockName");
		request.setGender("M");
		request.setBirthDate(bithDate);
		
		Subscription newSubscription = this.subscriptionService.createNewSubscription(request);
		
	}

	@Test(expectedExceptions = ObjectNotFoundException.class)
	public void testMissingSubscription() throws Exception{
		Optional<Subscription> newSubscription = this.subscriptionService.findById(9999L);
		
	}

	@Test()
	public void testGetSubscription() throws Exception{

		Date bithDate = df.parse("1983-15-06");
		SubscriptionRequest request = new SubscriptionRequest();
		request.setCampaignId(1L);
		request.setConsent(true);
		request.setEmail("example6@adidas.com");
		request.setFirstName("MockName");
		request.setGender("M");
		request.setBirthDate(bithDate);
		
		Subscription newSubscription = this.subscriptionService.createNewSubscription(request);
		Optional<Subscription> details = this.subscriptionService.findById(newSubscription.getId());
		assertTrue(details.isPresent());
		assertEquals(details.get().getId(), newSubscription.getId());
		
	}

	@Test()
	public void testGetAllSubscriptions() throws Exception{

		List<Subscription> subscriptions = this.subscriptionService.findAll();
		assertTrue(subscriptions.size()>0);
		
		
	}
}
