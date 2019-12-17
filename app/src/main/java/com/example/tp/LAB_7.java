package com.example.tp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LAB_7 extends AppCompatActivity {

    EditText search;
    EditText chang;
    EditText generate;
    TextView resultSearch;
    TextView book;
    TextView resultGenerate;
    Button button1;
    Button button2;
    LinkedList<String> Book1= new LinkedList<>();
    LinkedList<Character> Book2= new LinkedList<>();
    int picBook=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_7);
        button1 = (Button)findViewById(R.id.book);
        button2 = (Button)findViewById(R.id.cha);
        search= (EditText)findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                searchOut();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        chang= (EditText)findViewById(R.id.editText1);
        generate= (EditText)findViewById(R.id.editGen);
        resultSearch=(TextView)findViewById(R.id.resSearch);
        resultSearch.setMovementMethod(new ScrollingMovementMethod());
        book=(TextView)findViewById(R.id.textPreview);
        book.setMovementMethod(new ScrollingMovementMethod());
        resultGenerate=(TextView)findViewById(R.id.resultPrewiew);
        resultGenerate.setMovementMethod(new ScrollingMovementMethod());
        openFile(true);
        openFile(false);
        book.setText(Book1.toString());

    }
//поиск
    public  void searchOut(){
        String temp="";
        if(search.getText().toString().isEmpty())
        {
            resultSearch.setText("");
            return;
        }
        for (int i = 0; i <Book1.size() ; i++) {
            if(Book1.get(i).matches(search.getText().toString()+"\\w*")){
                temp+=Book1.get(i)+"\n";
            }
        }
        resultSearch.setText(temp);
    }
//добавление и удалени
    public void onClickChang(View view){
        switch (view.getId()) {
            case R.id.add1:
                switch (picBook) {
                    case 0:
                        break;
                    case 1:
                        Book1.add(chang.getText().toString());
                        checkChar();
                        book.setText(Book1.toString());
                        break;
                    case 2:
                        Book2.add(chang.getText().toString().charAt(0));
                        book.setText(Book2.toString());
                        break;
                }
                break;
            case R.id.Delete1:
                switch (picBook) {
                    case 0:
                        break;
                    case 1:
                        Book1.removeFirstOccurrence(chang.getText().toString());
                        checkChar();
                        book.setText(Book1.toString());
                        break;
                    case 2:
                        Book2.removeFirstOccurrence(chang.getText().toString().charAt(0));
                        checkChar();
                        book.setText(Book2.toString());
                        break;
                }
                break;
        }
    }
//перключение словаря
    public void onClickSetMode(View view){
        switch (view.getId()) {
            case R.id.book:
                picBook=1;
                button1.setBackgroundColor(getResources().getColor(R.color.pick));
                System.out.println("boooook"+Book1.size());
                book.setText(Book1.toString());
                button2.setBackgroundColor(getResources().getColor(R.color.notpick));
                break;
            case R.id.cha:
                picBook=2;
                button2.setBackgroundColor(getResources().getColor(R.color.pick));
                book.setText(Book2.toString());
                button1.setBackgroundColor(getResources().getColor(R.color.notpick));
                break;
        }
    }

    public void onClickGenerate(View view){
        List<String> temp= new ArrayList<>();
        boolean flag1 = true;
        String h;
        for (int i = 0; i <Book1.size(); i++) {
            h = Book1.get(i);
            flag1 =true;
            for (int j = 0; j <h.length() ; j++) {
                if(generate.getText().toString().indexOf(h.charAt(j))<0)
                {
                    flag1 = false;
                    break;
                }
            }
            if (flag1)
            {
                temp.add(h);
            }
        }
        resultGenerate.setText(temp.toString());
        temp.clear();
    }

    private void openFile(boolean mode) {
        InputStream inputStream1;
        if (mode)
            inputStream1 = getResources().openRawResource(R.raw.book4);
        else
            inputStream1 = getResources().openRawResource(R.raw.char_book);
        InputStream inputStream = inputStream1;
        try {

            if (inputStream != null) {
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(isr);
                String line;
                while ((line = reader.readLine()) != null) {
                    if(mode){
                        System.out.println(line);
                        Book1.addLast(line.toString());
                        System.out.println(Book1.getLast());
                        System.out.println(Book1.size());
                    }
                    else{
                        Book2.add(line.charAt(0));
                    }
                }
                inputStream.close();
            }
        } catch (Throwable t) {
            Toast.makeText(getApplicationContext(),
                    "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void checkChar(){
        List<String> temp= new ArrayList<>();
        boolean flag1 = true;
        String g="";
        String h;
        for (int i=0;i<Book2.size() ; i++)
            g += Book2.get(i);
        for (int i = 0; i <Book1.size(); i++) {
            h = Book1.get(i);
            flag1 =true;
            for (int j = 0; j <h.length() ; j++) {
                if(g.indexOf(h.charAt(j))<0)
                {
                    flag1 = false;
                    break;
                }
            }
            if (flag1)
            {
                temp.add(h);
            }
        }
        Book1.clear();
        Book1.addAll(temp);
        temp.clear();
    }

}
