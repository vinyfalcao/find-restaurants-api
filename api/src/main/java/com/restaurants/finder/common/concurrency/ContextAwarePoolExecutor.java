package com.restaurants.finder.common.concurrency;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.web.context.request.RequestContextHolder;

public class ContextAwarePoolExecutor extends SimpleAsyncTaskExecutor {
	private static final long serialVersionUID = -1598614973823737784L;

	private static ContextAwarePoolExecutor INSTANCE = new ContextAwarePoolExecutor();

	private ContextAwarePoolExecutor() {
		super("APIWorkerThread-");
	}

	public static ContextAwarePoolExecutor getInstance() {
		return INSTANCE;
	}

	@Override
	public void execute(Runnable task) {
		super.execute(new ContextAwareTask(task, RequestContextHolder.currentRequestAttributes()));
	}

	@Override
	public void execute(Runnable task, long startTimeout) {
		super.execute(new ContextAwareTask(task, RequestContextHolder.currentRequestAttributes()), startTimeout);
	}
}
