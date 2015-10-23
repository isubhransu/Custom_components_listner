package asu.edu.ssmishra.layoutdemo;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends Activity {

    private static final String TOTAL_BILL = "TOTAL_BILL";
    private static final String CURRENT_TIP = "CURRENT_TIP";
    private static final String BILL_WITHOUT_TIP = "BILL_WITHOUT_TIP";

    private double billBeforeTIp;
    private double tipAmount;
    private double finalBill;

    EditText billBeforeTipET;
    EditText tipAmountTipET;
    EditText finalBillET;

    SeekBar tipSeekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){

            billBeforeTIp = 0.0;
            tipAmount = .15;
            finalBill = 0.0;
        }else{
            billBeforeTIp = savedInstanceState.getDouble(TOTAL_BILL);
            tipAmount = savedInstanceState.getDouble(CURRENT_TIP);
            finalBill = savedInstanceState.getDouble(BILL_WITHOUT_TIP);
        }

        billBeforeTipET = (EditText) findViewById(R.id.BilleditText);
        tipAmountTipET = (EditText) findViewById(R.id.TipeditText);;
        finalBillET = (EditText) findViewById(R.id.FinalbilleditText);

        tipSeekBar = (SeekBar) findViewById(R.id.ChangeTipseekBar);
        tipSeekBar.setOnSeekBarChangeListener(tipSeekBarListner);

        billBeforeTipET.addTextChangedListener(billBeforeTIpListner);

    }

    private TextWatcher billBeforeTIpListner = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try{
                billBeforeTIp = Double.parseDouble(s.toString());

            }

            catch(NumberFormatException e){
                billBeforeTIp = 0.0;
            }

            updateTipAndFindBill();

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void updateTipAndFindBill(){
        double tipAmount = Double.parseDouble(tipAmountTipET.getText().toString());
        double finalBill = billBeforeTIp + (billBeforeTIp * tipAmount);

        finalBillET.setText(String.format("%.02f", finalBill));

    }

    private SeekBar.OnSeekBarChangeListener tipSeekBarListner = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            tipAmount = (tipSeekBar.getProgress()) * 0.1;
            tipAmountTipET.setText(String.format("%.02f", tipAmount));
            updateTipAndFindBill();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    } ;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
