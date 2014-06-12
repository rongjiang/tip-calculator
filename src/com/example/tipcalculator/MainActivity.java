package com.example.tipcalculator;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	EditText amountTxt;
	SeekBar tipSlider;
	TextView tipRateText;
	TextView tipTxt;
	Double amount;
	Double tipRate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		amountTxt = (EditText) findViewById(R.id.amount);
		
		tipSlider = (SeekBar) findViewById(R.id.tipSlider);
		tipRateText = (TextView) findViewById(R.id.tipRateText);
		tipRateText.setText(tipSlider.getProgress() + "%");
		tipTxt = (TextView) findViewById(R.id.tip);
		tipTxt.setText("Your tip is $0.00");
		setupListener();
	}

	public void onSubmit10(View v) {
		tipRate = 0.1;
		calcTip();		
	}
	
	public void onSubmit15(View v) {
		tipRate = 0.15;
		calcTip();
	}

	public void onSubmit20(View v) {
		tipRate = 0.2;
		calcTip();
	}

	public void calcTip() {		
		amount = readAmount();
		Double tip = amount * tipRate;
		DecimalFormat df = new DecimalFormat("#.00");
		tipTxt.setText("Your tip is $" + df.format(tip), TextView.BufferType.NORMAL);
	}
	
	private double readAmount() {
		String amtTxt = amountTxt.getText().toString();
		try {
			return Double.parseDouble(amtTxt);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void setupListener() {
		amountTxt.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				if (tipRate != null && tipRate > 0)
					calcTip();
				return;
			}
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
		});
		
		tipSlider.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			int rate = 0;
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				rate = progress;
				return;
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			
			@Override 
			public void onStopTrackingTouch(SeekBar seekBar) {
				tipRateText.setText("Tip Rate: " + rate + "%");
				tipRate = Double.valueOf(rate) / 100;
				calcTip();
				return;
			}
		});
		return;
	}
	
}
