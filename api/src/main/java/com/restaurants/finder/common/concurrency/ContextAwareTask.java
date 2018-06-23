package com.restaurants.finder.common.concurrency;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 
 * @author Vinicius Falcão
 *
 */
public class ContextAwareTask implements Runnable {

	private RequestAttributes context;
	private Runnable task;

	public ContextAwareTask(Runnable task, RequestAttributes context) {
		this.context = context;
		this.task = task;
	}

	@Override
	public void run() {
		if (context != null) {
			RequestContextHolder.setRequestAttributes(context);
		}

		try {
			task.run();
		} finally {
			RequestContextHolder.resetRequestAttributes();
		}
	}
}
