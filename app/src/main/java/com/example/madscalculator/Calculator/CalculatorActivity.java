package com.example.madscalculator.Calculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madscalculator.Authentication.LoginActivity;
import com.example.madscalculator.History.HistoryActivity;
import com.example.madscalculator.History.HistoryData;
import com.example.madscalculator.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener{

    public ArrayList<HistoryData> historyDataList;
    TextView tv_formula, tv_result;
    Button btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnZero,
            btnAddition, btnSubtraction, btnMultiplication, btnDivision, btnResults, btnClear;
    SharedPreferences sharedPreferences;
    private final String fileName = "login";
    String strFinal, strResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        initViews();
    }

    private void initViews() {
        sharedPreferences = getSharedPreferences("SHAREDPREFERENCES", Context.MODE_PRIVATE);

        historyDataList = new ArrayList<>();
        tv_formula = findViewById(R.id.formula);
        tv_result = findViewById(R.id.result);

        btnOne = findViewById(R.id.btn_1);
        btnOne.setOnClickListener(this);
        btnTwo = findViewById(R.id.btn_2);
        btnTwo.setOnClickListener(this);
        btnThree = findViewById(R.id.btn_3);
        btnThree.setOnClickListener(this);
        btnFour = findViewById(R.id.btn_4);
        btnFour.setOnClickListener(this);
        btnFive = findViewById(R.id.btn_5);
        btnFive.setOnClickListener(this);
        btnSix = findViewById(R.id.btn_6);
        btnSix.setOnClickListener(this);
        btnSeven = findViewById(R.id.btn_7);
        btnSeven.setOnClickListener(this);
        btnEight = findViewById(R.id.btn_8);
        btnEight.setOnClickListener(this);
        btnNine = findViewById(R.id.btn_9);
        btnNine.setOnClickListener(this);
        btnZero = findViewById(R.id.btn_0);
        btnZero.setOnClickListener(this);
        btnAddition = findViewById(R.id.btn_plus);
        btnAddition.setOnClickListener(this);
        btnSubtraction = findViewById(R.id.btn_minus);
        btnSubtraction.setOnClickListener(this);
        btnMultiplication = findViewById(R.id.btn_multiply);
        btnMultiplication.setOnClickListener(this);
        btnDivision = findViewById(R.id.btn_divide);
        btnDivision.setOnClickListener(this);
        btnResults = findViewById(R.id.btn_equals);
        btnResults.setOnClickListener(this);
        btnClear = findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_0:
                tv_result.setText(tv_result.getText() + "0");
                break;
            case R.id.btn_1:
                tv_result.setText(tv_result.getText() + "1");
                break;
            case R.id.btn_2:
                tv_result.setText(tv_result.getText() + "2");
                break;
            case R.id.btn_3:
                tv_result.setText(tv_result.getText() + "3");
                break;
            case R.id.btn_4:
                tv_result.setText(tv_result.getText() + "4");
                break;
            case R.id.btn_5:
                tv_result.setText(tv_result.getText() + "5");
                break;
            case R.id.btn_6:
                tv_result.setText(tv_result.getText() + "6");
                break;
            case R.id.btn_7:
                tv_result.setText(tv_result.getText() + "7");
                break;
            case R.id.btn_8:
                tv_result.setText(tv_result.getText() + "8");
                break;
            case R.id.btn_9:
                tv_result.setText(tv_result.getText() + "9");
                break;
            case R.id.btn_plus:
                if (!TextUtils.isEmpty(tv_result.getText())) {

                    if (checkOperator()) {
                        tv_result.setText(tv_result.getText() + "+");
                    }
                }

                break;
            case R.id.btn_minus:
                if (!TextUtils.isEmpty(tv_result.getText())) {
                    if (checkOperator()) {
                        tv_result.setText(tv_result.getText() + "-");
                    }
                }

                break;
            case R.id.btn_multiply:
                if (!TextUtils.isEmpty(tv_result.getText())) {
                    if (checkOperator()) {
                        tv_result.setText(tv_result.getText() + "*");
                    }
                }

                break;
            case R.id.btn_divide:
                if (!TextUtils.isEmpty(tv_result.getText())) {
                    if (checkOperator()) {
                        tv_result.setText(tv_result.getText() + "/");
                    }
                }

                break;
            case R.id.btn_clear:
                tv_result.setText("");
                tv_formula.setText("");
                break;
            case R.id.btn_equals:
                if (!TextUtils.isEmpty(tv_result.getText())) {

                    if (!isLastNumber()) {
                        madsCalculations();
                    } else {
                        Toast.makeText(getApplicationContext(), "invalid input", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                break;
        }
    }


    private boolean checkOperator() {
        String last = tv_result.getText().toString();
        last = last.substring(last.length() - 1);
        return !last.equalsIgnoreCase("+") && !last.equalsIgnoreCase("-") && !last.equalsIgnoreCase("*") && !last.equalsIgnoreCase("/");
    }

    private boolean isLastNumber() {
        String last = tv_result.getText().toString();
        last = last.substring(last.length() - 1);
        return !last.equalsIgnoreCase("0") && !last.equalsIgnoreCase("1") && !last.equalsIgnoreCase("2") && !last.equalsIgnoreCase("3")
                && !last.equalsIgnoreCase("4") && !last.equalsIgnoreCase("5") && !last.equalsIgnoreCase("6") && !last.equalsIgnoreCase("7")
                && !last.equalsIgnoreCase("8") && !last.equalsIgnoreCase("9");
    }


    private void madsCalculations() {
        strFinal = tv_result.getText().toString().trim();

        String[] operator = new String[]{"*", "+", "/", "-"};
        List<String> operatorlist = Arrays.asList(operator);
        List<String> myList = new ArrayList<String>();

        int operatorCount = 0;
        int nextCounter = 0;
        for (int i = 0; i < strFinal.length(); i++) {
            String strOperator = "" + strFinal.charAt(i);

            if (operatorlist.contains(strOperator)) {
                String leftString = strFinal.substring(nextCounter, i);

                myList.add(leftString);
                myList.add(strOperator);
                operatorCount++;
                Log.d("found_operator", leftString + "," + strOperator);
                nextCounter = i + 1;

            }
        }

        String temp = "";
        for (int i = 0; i < myList.size(); i++) {
            temp = temp + myList.get(i);
        }


        if (temp.length() == strFinal.length())
        {
            if (operatorlist.contains(myList.get(myList.size() - 1))) {
                myList.remove(myList.size() - 1);
                operatorCount--;
            }
        } else {
            String leftString = strFinal.substring(nextCounter);
            myList.add(leftString);
        }

        processArray(myList, operatorlist, operatorCount);

    }

    void processArray(List<String> myList, List<String> operatorlist, int processCount) {
        for (int k = 0; k < operatorlist.size(); k++) {
            String forOperator = operatorlist.get(k);

            for (int j = 0; j < processCount; j++) {
                for (int i = 0; i < myList.size(); i++) {
                    if (operatorlist.contains(myList.get(i)) && forOperator.equals(myList.get(i))) {
                        if (i != 0 && i != myList.size() - 1) {
                            double d = performOperationWithOperator(myList.get(i - 1), myList.get(i), myList.get(i + 1));
                            String s = String.valueOf(d);
                            myList.set(i + 1, s);
                            myList.remove(i);
                            myList.remove(i - 1);
                        }
                        break;
                    }
                }
            }

        }

        Log.d("found_operator", "," + myList.get(0));

        double d = Double.parseDouble(myList.get(0));
        if (d == (long) d) {
            strResult = String.format("%d", (long) d);
        } else {
            strResult = String.format("%s", d);
        }
        tv_formula.setText(strFinal);
        tv_result.setText(strResult);

        if (historyDataList.size() < 10) {
            HistoryData historyData = new HistoryData();
            historyData.setOperation(strFinal);
            historyData.setResults(strResult);
            historyDataList.add(historyData);
        }
        Log.d("Results", historyDataList.toString());
    }

    double performOperationWithOperator(String opLeft, String op, String opRight) {
        try {


            double l = Double.parseDouble(opLeft);
            double r = Double.parseDouble(opRight);

            switch (op) {
                case "*":
                    return (l * r);
                case "+":
                    return (l + r);
                case "/":
                    return (l / r);
                case "-":
                    return (l - r);
                default:
                    return 0;
            }


        } catch (Exception e) {

        }
        return 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_history:
                Intent intent = new Intent(CalculatorActivity.this, HistoryActivity.class);
                intent.putExtra("history_data_list", new Gson().toJson(historyDataList));
                startActivity(intent);
                break;
            case R.id.action_logout:
                sharedPreferences = getSharedPreferences(fileName, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", "");
                editor.commit();
                finish();
                Intent i = new Intent(CalculatorActivity.this, LoginActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
        return true;
    }
}

