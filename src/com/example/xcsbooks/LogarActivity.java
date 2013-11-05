package com.example.xcsbooks;

import com.example.xcsbooks.control.LoginControl;
import com.examples.xcsbooks.model.Cliente;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LogarActivity extends BaseActivity {
	private EditText mEditLogin;
	private EditText mEditPassword;
	private Button mBtnLogar;
	public static LogarActivity instance;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logar);
	
		instance = this;
		
		mEditLogin = (EditText) findViewById(R.id.logar_txtName);
		mEditPassword = (EditText) findViewById(R.id.logar_txtPassword);
		mBtnLogar = (Button) findViewById(R.id.logar_btnLogar);
		
		mBtnLogar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String username = mEditLogin.getText().toString();
				Log.d("USER_INFO", "String Username: " + username);
				String password = mEditPassword.getText().toString();
				Log.d("USER_INFO", "String Senha: " + password);
				
				Cliente cli = LoginControl.logar(username, password);
				
				if(cli != null){
					//Intent intent = new Intent(LogarActivity.this, HomeActivity.class);
					//startActivity(intent);
					HomeActivity.getInstance().changeLoginFragment();
					finish();
				} else {
					//Login errado
					AlertDialog.Builder builder = new AlertDialog.Builder(LogarActivity.this, android.R.style.Theme_DeviceDefault_Dialog_MinWidth);
					builder.setMessage(R.string.login_erro_info).setTitle(R.string.login_erro).setCancelable(false).setIcon(android.R.drawable.stat_sys_warning)
											.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													dialog.dismiss();
												}
												
											});
					AlertDialog dialog = builder.create();
					dialog.show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		return super.onOptionsItemSelected(item);
	}
	
	public static LogarActivity getInstance(){
		return instance;
	}
}
