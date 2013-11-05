package com.example.xcsbooks;

import com.example.xcsbooks.control.LoginControl;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity extends FragmentActivity {
	public static final String KEY_BUSCA = "com.example.xcsbooks.KEY_BUSCA";
	public static String PACKAGE_NAME = "com.example.xcsbooks";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM, ActionBar.DISPLAY_SHOW_HOME);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		if(LoginControl.getClienteLogado() == null){
			menu.getItem(2).setVisible(true);
			menu.getItem(3).setVisible(false);
		} else {
			menu.getItem(2).setVisible(false);
			menu.getItem(3).setVisible(true);
		}
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		Intent intent = null;
		switch (item.getItemId()) {
		case R.id.action_carrinho:
			intent = new Intent(this, CarrinhoActivity.class);
			startActivity(intent);
			break;
		case R.id.action_home:
			intent = new Intent(this, HomeActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			break;
		case R.id.action_logar:
			intent = new Intent(this, LogarActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			break;
		case R.id.action_deslogar:
			LoginControl.logout();
			HomeActivity.getInstance().changeLoginFragment();
			break;
		case R.id.action_busca:
			intent = new Intent(this, BuscarActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
		default:
			//Adicionar erro
			return false;
		}
		return true;
	}


}
