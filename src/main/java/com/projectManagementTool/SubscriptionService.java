package com.projectManagementTool;

public interface SubscriptionService {
 
	Subscription createSubscription(User user);
	Subscription getUsersSubscription(Long userId) throws Exception;
	Subscription upgradeSubscription(Long userId,PlanType planType);
	boolean isValid(Subscription subscription);
	
}
