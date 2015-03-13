package com.immomo.momo.android.activity.register;

import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.immomo.momo.android.BaseDialog;
import com.immomo.momo.android.R;

/**
 * 注册步骤基本信息输入页面，继承于抽象的注册页面
 * @author pj
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class StepBaseInfo extends RegisterStep implements TextWatcher,
		OnCheckedChangeListener {

	private EditText mEtName;
	private RadioGroup mRgGender;
	private RadioButton mRbMale;
	private RadioButton mRbFemale;

	private boolean mIsChange = true;
	private boolean mIsGenderAlert;
	private BaseDialog mBaseDialog;

	public StepBaseInfo(RegisterActivity activity, View contentRootView) {
		super(activity, contentRootView);
	}

	@Override
	public void initViews() {
		mEtName = (EditText) findViewById(R.id.reg_baseinfo_et_name);
		mRgGender = (RadioGroup) findViewById(R.id.reg_baseinfo_rg_gender);
		mRbMale = (RadioButton) findViewById(R.id.reg_baseinfo_rb_male);
		mRbFemale = (RadioButton) findViewById(R.id.reg_baseinfo_rb_female);
	}

	@Override
	public void initEvents() {
		mEtName.addTextChangedListener(this);
		mRgGender.setOnCheckedChangeListener(this);
	}

	@Override
	public void doNext() {
		mOnNextActionListener.next();
	}

	/**
	 * 检查用户名和性别的输入情况
	 */
	@Override
	public boolean validate() {
		if (isNull(mEtName)) {
			showCustomToast("请输入用户名");
			mEtName.requestFocus();
			return false;
		}
		if (mRgGender.getCheckedRadioButtonId() < 0) {
			showCustomToast("请选择性别");
			return false;
		}
		return true;
	}

	/**
	 * 判断是否允许修改
	 */
	@Override
	public boolean isChange() {
		return mIsChange;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		mIsChange = true;
		//如果mIsGenderAlert为false时弹出对话框，即只有第一次才会弹
		if (!mIsGenderAlert) {
			mIsGenderAlert = true;
			mBaseDialog = BaseDialog.getDialog(mContext, "提示", "注册成功后性别将不可更改",
					"确认", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			mBaseDialog.show();
		}
		
		switch (checkedId) {
		case R.id.reg_baseinfo_rb_male:
			mRbMale.setChecked(true);//性别已经被设置
			break;

		case R.id.reg_baseinfo_rb_female:
			mRbFemale.setChecked(true);
			break;
		}
	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		mIsChange = true;
	}

}
