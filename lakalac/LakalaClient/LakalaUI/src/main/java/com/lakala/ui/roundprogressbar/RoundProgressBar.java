package com.lakala.ui.roundprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.lakala.library.util.LogUtil;
import com.lakala.ui.R;

import java.util.Timer;
import java.util.TimerTask;

public class RoundProgressBar extends ImageView {

	private Paint mFramePaint;

	// --------------------
	private Paint mRoundPaints; // 主进度条画笔
	private RectF mRoundOval; // 矩形区域
	private int mPaintWidth; // 画笔宽度
	private int mPaintColor; // 画笔颜色

	private int mStartProgress; // 进度条起始位置
	private int mCurProgress; // 主进度条当前位置
	private int mMaxProgress; // 进度条最终位置

	private boolean mBRoundPaintsFill; // 是否填充区域
	// ---------------------
	private int mSidePaintInterval; // 圆环向里缩进的距离

	private Paint mSecondaryPaint; // 辅助进度条画笔

	private int mSecondaryCurProgress; // 辅助进度条当前位置

	private Paint mBottomPaint; // 进度条背景图画笔

	private boolean mBShowBottom; // 是否显示进度条背景色

	// ----------------------
	private Handler mHandler;

	private boolean mBCartoom; // 是否正在作动画

	private Timer mTimer; // 用于作动画的TIMER

	private MyTimerTask mTimerTask; // 动画任务

	private int mSaveMax; // 在作动画时会临时改变MAX值，该变量用于保存值以便恢复

	private int mTimerInterval; // 定时器触发间隔时间(ms)

	private float mCurFloatProcess; // 作动画时当前进度值

	private float mProcessRInterval; // 作动画时每次增加的进度值

	private final static int TIMER_ID = 0x100; // 定时器ID

	private long mCurTime;
	
	private int mCanProcess;

	public RoundProgressBar(Context context) {
		super(context);

		initParam();
	}

	public RoundProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub

		initParam();

		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.RoundProgressBar);

		mMaxProgress = array.getInt(R.styleable.RoundProgressBar_max, 100);
		mSaveMax = mMaxProgress;
		mBRoundPaintsFill = array.getBoolean(
				R.styleable.RoundProgressBar_isFill, true); // 获得是否是填充模式
		if (mBRoundPaintsFill == false) {
			mRoundPaints.setStyle(Paint.Style.STROKE);
			mSecondaryPaint.setStyle(Paint.Style.STROKE);
			mBottomPaint.setStyle(Paint.Style.STROKE);
		}

		mSidePaintInterval = array.getInt(
				R.styleable.RoundProgressBar_Inside_Interval, 0);// 圆环缩进距离

		mBShowBottom = array.getBoolean(
				R.styleable.RoundProgressBar_Show_Bottom, true);

		mPaintWidth = array.getInt(R.styleable.RoundProgressBar_Paint_Width, 10);
		if (mBRoundPaintsFill) // 填充模式则画笔长度改为0
		{
			mPaintWidth = 0;
		}

		mRoundPaints.setStrokeWidth(mPaintWidth);
		mSecondaryPaint.setStrokeWidth(mPaintWidth);
		mBottomPaint.setStrokeWidth(mPaintWidth);

		mPaintColor = array.getColor(R.styleable.RoundProgressBar_Paint_Color,
				0xffffcc00);
		// mRoundPaints.setColor(getResources().getColor(R.color.bg_text_color));
		int color = mPaintColor & 0x00ffffff | 0x66000000;
		mSecondaryPaint.setColor(color);

		array.recycle(); // 一定要调用，否则会有问题

	}

	private void initParam() {
		mFramePaint = new Paint();
		mFramePaint.setAntiAlias(true);
		mFramePaint.setStyle(Paint.Style.STROKE);
		mFramePaint.setStrokeWidth(0);

		mPaintWidth = 0;
		mPaintColor = 0xffffcc00;

		mRoundPaints = new Paint();
		mRoundPaints.setAntiAlias(true);
		mRoundPaints.setStyle(Paint.Style.FILL);

		mRoundPaints.setStrokeWidth(mPaintWidth);
		mRoundPaints.setColor(mPaintColor);

		mSecondaryPaint = new Paint();
		mSecondaryPaint.setAntiAlias(true);
		mSecondaryPaint.setStyle(Paint.Style.FILL);
		mSecondaryPaint.setStrokeWidth(mPaintWidth);

		int color = mPaintColor & 0x00ffffff | 0x66000000;
		mSecondaryPaint.setColor(color);

		mBottomPaint = new Paint();
		mBottomPaint.setAntiAlias(true);
		mBottomPaint.setStyle(Paint.Style.FILL);
		mBottomPaint.setStrokeWidth(mPaintWidth);
		mBottomPaint.setColor(Color.GRAY);

		mStartProgress = -90;
		mCurProgress = 0;
		mMaxProgress = 100;
		mSaveMax = 100;

		mBRoundPaintsFill = true;
		mBShowBottom = true;

		mSidePaintInterval = 0;

		mSecondaryCurProgress = 0;

		mRoundOval = new RectF(0, 0, 0, 0);

		mTimerInterval = 25;

		mCurFloatProcess = 0;

		mProcessRInterval = 0;

		mBCartoom = false;

		mHandler = new Handler() {
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.what == TIMER_ID) {
					// long now = System.currentTimeMillis();
					// if (mCurTime != 0)
					// {
					// Log.i("", "interval time = " + (now - mCurTime));
					// }
					//
					// mCurTime = now;

					if (mBCartoom == false) {
						return;
					}

					mCurFloatProcess += mProcessRInterval;
					setProgress((int) mCurFloatProcess);

					if (mCurFloatProcess > mMaxProgress) {

						mBCartoom = false;
						mMaxProgress = mSaveMax;
						if (mTimerTask != null) {
							mTimerTask.cancel();
							mTimerTask = null;
						}
					}

				}
			}

		};

		mTimer = new Timer();

	}

	public synchronized void setProgress(int progress) {
		
		if(progress > mCanProcess){
			return;
		}
		
		if(onProcessListener != null){
			onProcessListener.onProcess(progress);
		}
		
		mCurProgress = progress;
		if (mCurProgress < 0) {
			mCurProgress = 0;
		}

		if (mCurProgress > mMaxProgress) {
			mCurProgress = mMaxProgress;
		}

		invalidate();
	}

	public synchronized int getProgress() {
		return mCurProgress;
	}

	public synchronized void setSecondaryProgress(int progress) {
		mSecondaryCurProgress = progress;
		if (mSecondaryCurProgress < 0) {
			mSecondaryCurProgress = 0;
		}

		if (mSecondaryCurProgress > mMaxProgress) {
			mSecondaryCurProgress = mMaxProgress;
		}

		invalidate();
	}

	public synchronized int getSecondaryProgress() {
		return mSecondaryCurProgress;
	}

	public synchronized void setMax(int max) {
		if (max <= 0) {
			return;
		}

		mMaxProgress = max;
		if (mCurProgress > max) {
			mCurProgress = max;
		}

		if (mSecondaryCurProgress > max) {
			mSecondaryCurProgress = max;
		}

		mSaveMax = mMaxProgress;

		invalidate();
	}

	public synchronized int getMax() {
		return mMaxProgress;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);

		LogUtil.i("", "W = " + w + ", H = " + h);

		if (mSidePaintInterval != 0) {
			mRoundOval.set(mPaintWidth / 2 + mSidePaintInterval, mPaintWidth
					/ 2 + mSidePaintInterval, w - mPaintWidth / 2
					- mSidePaintInterval, h - mPaintWidth / 2
					- mSidePaintInterval);
		} else {

			int sl = getPaddingLeft();
			int sr = getPaddingRight();
			int st = getPaddingTop();
			int sb = getPaddingBottom();

			mRoundOval.set(sl + mPaintWidth / 2, st + mPaintWidth / 2, w - sr
					- mPaintWidth / 2, h - sb - mPaintWidth / 2);
		}

	}

	public synchronized void startCartoom(int time, Boolean resume) {
		if (time <= 0 || mBCartoom == true)
		{
			return ;
		}
		mBCartoom = true;
		
		if (mTimerTask != null)
		{
			mTimerTask.cancel();
			mTimerTask = null;
		}
		if (!resume) {
			setProgress(mCurProgress);
			
			mSaveMax = mMaxProgress;
			//mMaxProgress = (1000 / mTimerInterval) * time;
				
			mProcessRInterval = (float)mTimerInterval * mMaxProgress / (time * 1000);	
			mCurFloatProcess = 0;
			
			mCurTime = 0;
			
			mTimerTask = new MyTimerTask();
			mTimer.schedule(mTimerTask, mTimerInterval, mTimerInterval);
			
		}
		else {
			mTimerTask = new MyTimerTask();
			mTimer.schedule(mTimerTask, mTimerInterval, mTimerInterval);
			
		}


	}

	public synchronized void startSecondCartoom(int time, Boolean resume) {
		if (time <= 0 || mBCartoom == true) {
			return;
		}
		mBCartoom = true;

		if (mTimerTask != null) {
			mTimerTask.cancel();
			mTimerTask = null;
		}
		if (!resume) {
			setProgress(mCurProgress);
			//mMaxProgress = 1000 * time;
			mProcessRInterval = 1000;
			mCurFloatProcess = 0;
			mCurTime = 0;
			mTimerTask = new MyTimerTask();
			mTimer.schedule(mTimerTask, 0, 1000);
		}
		else {
			mTimerTask = new MyTimerTask();
			mTimer.schedule(mTimerTask, 0, 1000);
		}
	}

	public synchronized void stopCartoom() {

		mBCartoom = false;
		// mStartProgress = mCurProgress;
		// setProgress((int)mCurFloatProcess);
		if (mTimerTask != null) {
			mTimerTask.cancel();
			mTimerTask = null;
		}
	}
	public synchronized void restartCartoom(int time) {
		if (time <= 0 || mBCartoom == true)
		{
			return ;
		}
		mBCartoom = true;
		
		if (mTimerTask != null)
		{
			mTimerTask.cancel();
			mTimerTask = null;
		}
			setProgress(0);
			
			mSaveMax = mMaxProgress;
			//mMaxProgress = (1000 / mTimerInterval) * time;
				
			mProcessRInterval = (float)mTimerInterval * mMaxProgress / (time * 1000);	
			mCurFloatProcess = 0;
			
			mCurTime = 0;
			
			mTimerTask = new MyTimerTask();
			mTimer.schedule(mTimerTask, mTimerInterval, mTimerInterval);

	}
	
	public synchronized void restartSecondCartoom(int time) {
		if (time <= 0 || mBCartoom == true) {
			return;
		}
		mBCartoom = true;

		if (mTimerTask != null) {
			mTimerTask.cancel();
			mTimerTask = null;
		}
			setProgress(0);
			//mMaxProgress = 1000 * time;
			mProcessRInterval = 1000;
			mCurFloatProcess = 0;
			mCurTime = 0;
			mTimerTask = new MyTimerTask();
			mTimer.schedule(mTimerTask, 0, 1000);

	}


	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		if (mBShowBottom) {
			canvas.drawArc(mRoundOval, 0, 360, mBRoundPaintsFill, mBottomPaint);
		}

		float secondRate = (float) mSecondaryCurProgress / mMaxProgress;
		float secondSweep = 360 * secondRate;
		canvas.drawArc(mRoundOval, mStartProgress, secondSweep,
				mBRoundPaintsFill, mSecondaryPaint);

		float rate = (float) mCurProgress / mMaxProgress;
		float sweep = 360 * rate;
		canvas.drawArc(mRoundOval, mStartProgress, sweep, mBRoundPaintsFill,
				mRoundPaints);

	}

	class MyTimerTask extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = mHandler.obtainMessage(TIMER_ID);
			msg.sendToTarget();

		}

	}
	
	// 设置 画笔颜色
	public void setmPaintColor(int mPaintColor) {
		this.mPaintColor = mPaintColor;
		mRoundPaints.setColor(mPaintColor);
	}
	
	// 设置背景颜色
	public void setBackgroudColor(int color) {
		mBottomPaint.setColor(color);
	}

	public void setmCanProcess(int mCanProcess) {
		this.mCanProcess = mCanProcess;
	}

	private OnProcessListener onProcessListener;
	
	public void setOnProcessListener(OnProcessListener onProcessListener) {
		this.onProcessListener = onProcessListener;
	}
	public interface OnProcessListener {
		void onProcess(int process);
	}
}
