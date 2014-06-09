package com.example.tipcalculator;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	Double amount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);				
	}

	public void onSubmit10(View v) {
		calcTip(0.1);		
	}
	
	public void onSubmit15(View v) {
		calcTip(0.15);
	}

	public void onSubmit20(View v) {
		calcTip(0.2);
	}

	public void calcTip(Double rate) {
		EditText amountTxt = (EditText) findViewById(R.id.amount);
		
		System.out.println("get AmountTxt");
		amount = readAmount(amountTxt);
		System.out.println(amount);
		Double tip = amount * rate;
		TextView tipTxt = (TextView) findViewById(R.id.tip);
		DecimalFormat df = new DecimalFormat("#.00");
		tipTxt.setText("Tip: " + df.format(tip), TextView.BufferType.NORMAL);
	} 
	
	private double readAmount(EditText amtTxt) {
		String amountTxt = amtTxt.getText().toString();
		try {
			return Double.parseDouble(amountTxt);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
