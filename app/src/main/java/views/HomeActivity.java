package views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.*;
import com.example.vijay.memorygame.R;

import java.util.ArrayList;
import java.util.Random;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private GridView gvNumbers;
    private TextView userHintNumber;
    private ArrayList<String> itemSelected;
    private Button mBtnStart;
    private CardView mCvHint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        setGridAdapter();
    }

    private void init() {
        gvNumbers= findViewById(R.id.gv_numbers);
        userHintNumber = findViewById(R.id.tv_user_hint);
        mBtnStart = findViewById(R.id.btn_start);
        mBtnStart.setOnClickListener(this);
        mCvHint = findViewById(R.id.cv_hint);
        itemSelected = new ArrayList<>();
    }

    private void setGridAdapter() {
        final GridAdapter adapter = new GridAdapter(this, getData());
        gvNumbers.setAdapter(adapter);
        gvNumbers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String number = parent.getItemAtPosition(position).toString();
                if(number.equals(userHintNumber.getText().toString().trim())){
                    adapter.posSelected.add(position);
                    itemSelected.add(number);
                    adapter.notifyDataSetChanged();
                    if(itemSelected.size()==9){
                        mCvHint.setVisibility(View.GONE);
                        showWinner();
                    }else{
                        setUserHintNumber();
                    }


                }else {
                    showError();
                }
            }
        });
    }

    private void showWinner() {
        Toast.makeText(this, "*** Winner ***",Toast.LENGTH_LONG).show();
    }

    private void showError() {
        Toast.makeText(this, "Wrong Selection..!! Try again",Toast.LENGTH_SHORT).show();
    }

    private void setUserHintNumber() {
        int hint;
        do{
            hint = getRandomNumber();
        } while(checkIfNumberDisplayed(hint));
        userHintNumber.setText(String.valueOf(hint));
    }

    private boolean checkIfNumberDisplayed(int hint) {
        for(String value: itemSelected){
            if(value.equals(String.valueOf(hint))){
                return true;
            }
        }
        return false;
    }


    private int getRandomNumber(){
        Random random = new Random();
        return random.nextInt(9)+1;
    }

    private ArrayList<Integer> getData() {
        ArrayList<Integer> list = new ArrayList<>();

        while (list.size() != 9){
            int num = getRandomNumber();
            if(!list.contains(num)){
                list.add(num);
            }
        }
        return list;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_start){
            setUserHintNumber();
            mCvHint.setVisibility(View.VISIBLE);
            mBtnStart.setVisibility(View.GONE);
        }
    }
}
