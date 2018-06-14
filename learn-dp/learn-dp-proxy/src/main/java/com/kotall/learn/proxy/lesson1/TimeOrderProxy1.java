package com.kotall.learn.proxy.lesson1;

import com.kotall.learn.proxy.order.OrderServiceImpl;

public class TimeOrderProxy1 extends OrderServiceImpl {

	@Override
	public void order() {
		System.out.println("== startTime");
		long startTime = System.currentTimeMillis();
		super.order();
		long endTime = System.currentTimeMillis();
		System.out.println("== endTime, cost: " + (endTime - startTime));
	}

}