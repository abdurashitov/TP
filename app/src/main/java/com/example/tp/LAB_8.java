package com.example.tp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;


public class LAB_8 extends AppCompatActivity {

    Spinner rule1;
    Spinner rule2;
    Spinner rule3;
    Button button1;
    Button button2;
    Button button3;
    TextView out;
    TextView eror;
    EditText input;
    EditText exp;
    String Book1="";
    String Book2="";
    String Book3="";
    String[] Books=new String[3];
    int picBook=0;
    int[] rul = new int[]{0,0,0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_8);
        rule1 = (Spinner) findViewById(R.id.rule1);
        rule2 = (Spinner) findViewById(R.id.rule2);
        rule3 = (Spinner) findViewById(R.id.rule3);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        out=(TextView)findViewById(R.id.textOut);
        out.setMovementMethod(new ScrollingMovementMethod());
        eror=(TextView)findViewById(R.id.Error);
        input=(EditText)findViewById(R.id.editText) ;
        exp=(EditText)findViewById(R.id.editExp);
        Book1 = openFile(getResources().openRawResource(R.raw.book1));
        Book2 = openFile(getResources().openRawResource(R.raw.book2));
        Book3 = openFile(getResources().openRawResource(R.raw.book3));
        out.setText(Book1);
        AdapterView.OnItemSelectedListener onSelector = new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId)
            {
                String[] choose = getResources().getStringArray(R.array.rules);
                switch (parent.getId()) {
                    case R.id.rule1:
                        rul[0]=selectedItemPosition;
                        Toast.makeText(getApplicationContext(),
                                "rull1: " + selectedItemPosition, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.rule2:
                        rul[1]=selectedItemPosition;
                        Toast.makeText(getApplicationContext(),
                                "rull2: " + selectedItemPosition, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.rule3:
                        rul[2]=selectedItemPosition;
                        Toast.makeText(getApplicationContext(),
                                "rull3: " + selectedItemPosition, Toast.LENGTH_LONG).show();
                        break;
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

        rule1.setOnItemSelectedListener(onSelector);
        rule2.setOnItemSelectedListener(onSelector);
        rule3.setOnItemSelectedListener(onSelector);

    }
    public void onPickVocabulary(View view){
        switch (view.getId()) {
            case R.id.button1:
                picBook=1;
                button1.setBackgroundColor(getResources().getColor(R.color.pick));
                out.setText(Book1);
                button2.setBackgroundColor(getResources().getColor(R.color.notpick));
                button3.setBackgroundColor(getResources().getColor(R.color.notpick));
                break;
            case R.id.button2:
                picBook=2;
                button2.setBackgroundColor(getResources().getColor(R.color.pick));
                out.setText(Book2);
                button1.setBackgroundColor(getResources().getColor(R.color.notpick));
                button3.setBackgroundColor(getResources().getColor(R.color.notpick));
                break;
            case R.id.button3:
                picBook=3;
                button3.setBackgroundColor(getResources().getColor(R.color.pick));
                out.setText(Book3);
                button2.setBackgroundColor(getResources().getColor(R.color.notpick));
                button1.setBackgroundColor(getResources().getColor(R.color.notpick));
                break;
        }
    }
    public void onEditVocabulary(View view){
        switch (view.getId()) {
            case R.id.Add:
                switch (picBook) {
                    case 0:
                        break;
                    case 1:
                        Book1+=input.getText().toString()+"\n";
                        out.setText(Book1);
                        break;
                    case 2:
                        Book2+=input.getText().toString()+"\n";
                        out.setText(Book2);
                        break;
                    case 3:
                        Book3+=input.getText().toString()+"\n";
                        out.setText(Book3);
                        break;
                }
                break;
            case R.id.Delete:
                switch (picBook) {
                    case 0:
                        break;
                    case 1:
                        Book1=Book1.replace("\n"+input.getText().toString()+"\n","\n");
                        out.setText(Book1);
                        break;
                    case 2:
                        Book2=Book2.replace("\n"+input.getText().toString()+"\n","\n");
                        out.setText(Book2);
                        break;
                    case 3:
                        Book3=Book3.replace("\n"+input.getText().toString()+"\n","\n");
                        out.setText(Book3);
                        break;
                }
                break;
        }
    }

    private String openFile(InputStream inputStream1) {
        try {
            InputStream inputStream = inputStream1;

            if (inputStream != null) {
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(isr);
                String line;
                StringBuilder builder = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    builder.append(line + "\n");
                }

                inputStream.close();
                return builder.toString();
            }
        } catch (Throwable t) {
            Toast.makeText(getApplicationContext(),
                    "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
        return "";
    }

    // Метод для сохранения файла
    public void onCheck(View view) {
        Books=new String[]{Book1,Book2,Book3};
        int[] res = new int[]{-1,-1,-1};
        System.out.println(exp.getText().toString());
        String[] words = exp.getText().toString().split(" ");
        if (words.length != 3)
        {
            eror.setText("Invalid word count");
            return;
        }

        System.out.println(""+words[0]+" " + words[1]+" " + words[2]);
        System.out.println(Books[1]);
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                System.out.println("в словаре "+ i + " слово " + j + " indexOf = "+Books[i].contains(words[j]));
                if (Books[i].contains(words[j])) {
                    res[j] = i;
                }
            }
        }
        System.out.println(""+res[0]+res[1]+res[2]);
        System.out.println(""+rul[0]+rul[1]+rul[2]);
        if ( Arrays.equals (res, rul))
        {
            eror.setText("OK");
        }
        else
        {
            eror.setText("maybe you mean ");
            for (int i = 0; i < 3; i++)
            {
                if (findIndex(res, rul[i]) >= 0)
                {
                    eror.setText(eror.getText() + words[findIndex(res, rul[i])] + " ");
                    words[findIndex(res, rul[i])] = "";
                    res[findIndex(res, rul[i])] = -1;
                }
                else
                {
                    eror.setText("ERROR");
                    break;
                }
            }


        }
    }
    public int findIndex(int arr[], int t)
    {
         // if array is Null
        if(arr == null) {
            return-1;
        }
        for (int i = 0; i< arr.length; i++)
            if (arr[i] == t) {
                return i;
            }

        return-1;
    }


}
