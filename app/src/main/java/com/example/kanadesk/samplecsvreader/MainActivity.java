package com.example.kanadesk.samplecsvreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    private Button ReaderButton;
    private EditText outEditTxt;


    private String TargetCsvFile = "myID.csv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outEditTxt = findViewById(R.id.outEditTxt);

        ReaderButton = findViewById(R.id.CsvReaderBtn);
        ReaderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Map<String, String>> OutData = InputCsvFile(TargetCsvFile);

                if(OutData != null){
                    String tmp = "";
                    for (String key : OutData.keySet()) {
                        String OutDataValue = key + ":" + OutData.get(key).get("NAME") + "," + OutData.get(key).get("OVERVIEW");

                        Log.d("DATA", OutDataValue);
                        tmp += OutDataValue + "\n";
                    }
                    outEditTxt.setText(tmp);
                }
                else{
                    outEditTxt.setText("CSVファイルが読み込めなかった");
                    Log.e("ERROR", "File Not found");
                }



            }
        });
    }


    /**
     * CsvFileを読み込む関数
     * @param csv 読み込みたいCsvFile
     * @return Map<String, Map<String,String>>
     */
    private Map<String, Map<String,String>> InputCsvFile(String csv) {
        Map<String, Map<String, String>> outDataArray = null;

        try {

            //assetファイルにあるcsvファイルの読み込み
            InputStream inputStream = getResources().getAssets().open(csv);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


            //outFileの準備
            outDataArray = new HashMap<String, Map<String,String>>();


            //csvの列名を取得
            ArrayList<String> csvColumnArray = new ArrayList<String>();
            //csvの最初の一行を読み取り列名を取得
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), ",");

            int column = stringTokenizer.countTokens();
            for(int i=0; i<column; i++ ){
                csvColumnArray.add(stringTokenizer.nextToken());
            }

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringTokenizer = new StringTokenizer(line, ",");

                String Key = stringTokenizer.nextToken();
                outDataArray.put(Key, new HashMap<String, String>());
                for(int i=1; i < csvColumnArray.size(); i++){
                    outDataArray.get(Key).put(csvColumnArray.get(i), stringTokenizer.nextToken());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return outDataArray;
    }
}
