package com.sensetime.sample.core.view;

public interface ITimeViewBase {
	public void setProgress(float currentTime);
	public void hide();
	public void show();
	public int getMaxTime();
}