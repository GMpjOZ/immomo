package com.immomo.momo.android.activity.register;

import java.util.regex.Pattern;

import com.immomo.momo.android.BaseApplication;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;

/**
 * 抽象类，注册步骤
 * @author pj
 *
 */
public abstract class RegisterStep {
	protected RegisterActivity mActivity;
	protected Context mContext;
	private View mContentRootView;
	protected onNextActionListener mOnNextActionListener;

	/**
	 * 构造函数吗，实现注册步骤的初始化
	 * @param activity 注册activity
	 * @param contentRootView 展示
	 */
	public RegisterStep(RegisterActivity activity, View contentRootView) {
		mActivity = activity;
		mContext = (Context) mActivity;
		mContentRootView = contentRootView;
		initViews();
		initEvents();
	}

	public abstract void initViews();

	public abstract void initEvents();

	public abstract boolean validate();

	public abstract boolean isChange();

	public View findViewById(int id) {
		return mContentRootView.findViewById(id);
	}

	public void doPrevious() {

	}

	public void doNext() {

	}

	public void nextAnimation() {

	}

	public void preAnimation() {

	}

	protected boolean isNull(EditText editText) {
		String text = editText.getText().toString().trim();
		if (text != null && text.length() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * 正则表达式定义手机号规则
	 * @param text
	 * @return
	 */
	protected boolean matchPhone(String text) {
		if (Pattern.compile("(\\d{11})|(\\+\\d{3,})").matcher(text).matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 正则表达式定义邮箱规则
	 * @param text
	 * @return
	 */
	protected boolean matchEmail(String text) {
		if (Pattern.compile("\\w[\\w.-]*@[\\w.]+\\.\\w+").matcher(text)
				.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 获得手机号
	 * @return
	 */
	protected String getPhoneNumber() {
		return mActivity.getPhoneNumber();
	}

	/**
	 * 
	 * @param text
	 */
	protected void showCustomToast(String text) {
		mActivity.showCustomToast(text);
	}

	protected void putAsyncTask(AsyncTask<Void, Void, Boolean> asyncTask) {
		mActivity.putAsyncTask(asyncTask);
	}

	protected void showLoadingDialog(String text) {
		mActivity.showLoadingDialog(text);
	}

	protected void dismissLoadingDialog() {
		mActivity.dismissLoadingDialog();
	}

	/**
	 * 获得屏幕宽度
	 * @return
	 */
	protected int getScreenWidth() {
		return mActivity.getScreenWidth();
	}

	protected BaseApplication getBaseApplication() {
		return mActivity.getBaseApplication();
	}

	public void setOnNextActionListener(onNextActionListener listener) {
		mOnNextActionListener = listener;
	}

	public interface onNextActionListener {
		void next();
	}
}
