package com.edu.sicnu.cs.zzy.cirdealtest;

import android.hardware.ConsumerIrManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ConsumerIrManager IR;
    private int[] pattern ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controllayout);
//        IR=(ConsumerIrManager)getSystemService(CONSUMER_IR_SERVICE);
//
//        pattern = new int[] {9000,4500, 560,560,    560,560,   560,560,   560,560,   560,560,   560,560,   560,560,   560,560,
//                560,1690,  560,1690,  560,1690,  560,1690, 560,1690, 565,1685, 560,1690, 560,1690,
//                560,1690,  560,560,  560,1690, 560,560, 560,560, 560,560, 560,1690,   560,560,
//                560,560 ,560,1690,  560,560,   560,1690, 560,1690, 560,1690,560,560, 560,1690
//                ,9000,2250,2250,94000  , 9000,2250,2250,94000};

        textView = findViewById(R.id.textView);
    }

    public void BtnSend(View v){
//        IR.transmit(38000, pattern);
        Button button = (Button) v;
        switch (button.getId()){
            case R.id.button0://0x16
            CIRdeal.transmitKey(this,KeyReverse("00010110"),ConversePositionLocigal(KeyReverse("00010110")));
                textView.setText("You Send The "+button.getText());
            break;
            case R.id.button1://0x0c
                CIRdeal.transmitKey(this,KeyReverse("00001100"),ConversePositionLocigal(KeyReverse("00001100")));
                textView.setText("You Send The "+button.getText());
                break;
            case R.id.button2://0x18
                CIRdeal.transmitKey(this,KeyReverse("00011000"),ConversePositionLocigal(KeyReverse("00011000")));
                textView.setText("You Send The "+button.getText());
                break;
            case R.id.button3://0x5e
                CIRdeal.transmitKey(this,KeyReverse("01011110"),ConversePositionLocigal(KeyReverse("01011110")));
                textView.setText("You Send The "+button.getText());
                break;
            case R.id.button4://0x08
                CIRdeal.transmitKey(this,KeyReverse("00001000"),ConversePositionLocigal(KeyReverse("00001000")));
                textView.setText("You Send The "+button.getText());
                break;
            case R.id.button5://0x1c
                CIRdeal.transmitKey(this,KeyReverse("00011100"),ConversePositionLocigal(KeyReverse("00011100")));
                textView.setText("You Send The "+button.getText());
                break;
            case R.id.button6://0x5a
                CIRdeal.transmitKey(this,KeyReverse("01011010"),ConversePositionLocigal(KeyReverse("01011010")));
                textView.setText("You Send The "+button.getText());
                break;
            case R.id.button7://0x42
                CIRdeal.transmitKey(this,KeyReverse("01000010"),ConversePositionLocigal(KeyReverse("01000010")));
                textView.setText("You Send The "+button.getText());
                break;
            case R.id.button8://0x52
                CIRdeal.transmitKey(this,KeyReverse("01010010"),ConversePositionLocigal(KeyReverse("01010010")));
                textView.setText("You Send The "+button.getText());
                break;
            case R.id.button9://0x4a
                CIRdeal.transmitKey(this,KeyReverse("01001010"),ConversePositionLocigal(KeyReverse("01001010")));
                textView.setText("You Send The "+button.getText());
                break;
            case R.id.buttonReset://0x01
                CIRdeal.transmitKey(this,KeyReverse("00000001"),ConversePositionLocigal(KeyReverse("00000001")));
                textView.setText("You Send The "+button.getText());
                break;
            case R.id.buttonStart://0x02
                CIRdeal.transmitKey(this,KeyReverse("00000010"),ConversePositionLocigal(KeyReverse("00000010")));
                textView.setText("You Send The "+button.getText());
                break;
        }
    }       //按钮响应....不知道为什么二进制码要颠倒顺序。。应该跟红外接收头有关

    public String ConversePositionLocigal(String key){
        char[] temp=key.toCharArray();
        for (int i=0;i<temp.length;i++){
            if(temp[i] =='0'){
                temp[i]='1';
            }
            if(temp[i] =='1'){
                temp[i]='0';
            }
        }
        String result = String.copyValueOf(temp);
        return result;
    }       //将二进制字符串取反

    public String KeyReverse(String key){
        if (key == null || key.equals(""))
        {
            return key;
        }
        return key.charAt(key.length() - 1) + KeyReverse(key.substring(0, key.length() - 1));   //递归调用
    }       //将字符串颠倒
}
