package com.projectManagementTool;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	private UserService userService;
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Override
	public Subscription createSubscription(User user) {
		// TODO Auto-generated method stub
		Subscription subscription = new Subscription();
		subscription.setUser(user);
		subscription.setSubscriptionStartDate(LocalDate.now());
		subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(12));
		subscription.setValid(true);
		subscription.setPlanType(PlanType.FREE);
		
		return subscriptionRepository.save(subscription);
	}

	@Override
	public Subscription getUsersSubscription(Long userId) throws Exception {
		// TODO Auto-generated method stub
		Subscription subscription = subscriptionRepository.findByUserId(userId);
		if(!isValid(subscription)) {
			subscription.setPlanType(PlanType.FREE);
			subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(12));
			subscription.setSubscriptionStartDate(LocalDate.now());
		}
		return subscriptionRepository.save(subscription);
	}

	@Override
	public Subscription upgradeSubscription(Long userId, PlanType planType) {
		// TODO Auto-generated method stub
		Subscription subscription=subscriptionRepository.findByUserId(userId);
		subscription.setPlanType(planType);
		subscription.setSubscriptionStartDate(LocalDate.now());
		if(planType.equals(PlanType.ANNUALLY)) {
			subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(12));
		}else {
			subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(1));
		}
		return subscriptionRepository.save(subscription);
	}

	@Override
	public boolean isValid(Subscription subscription) {
		// TODO Auto-generated method stub
		if(subscription.getPlanType().equals(PlanType.FREE)) {
			return true;
		}
		LocalDate endDate=subscription.getGetSubscriptionEndDate();
		LocalDate currentDate = LocalDate.now();
		return endDate.isAfter(currentDate) || endDate.isEqual(currentDate);
	}

}
