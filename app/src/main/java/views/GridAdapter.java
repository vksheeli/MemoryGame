package views;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.vijay.memorygame.R;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Integer> numberList;
    public ArrayList<Integer> posSelected;

    public GridAdapter(Context context, ArrayList<Integer> numberList) {
        this.context = context;
        this.numberList = numberList;
        posSelected = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return numberList.size();
    }

    @Override
    public Object getItem(int position) {
        return numberList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_model, parent,false);
        }

        int number = this.numberList.get(position);
        final TextView tvNumber = convertView.findViewById(R.id.text_number);
        tvNumber.setText(String.valueOf(number));

        if(!posSelected.isEmpty()) {
            for (int i : posSelected) {
                if (i != position && tvNumber.getVisibility() == View.INVISIBLE) {
                    tvNumber.setVisibility(View.INVISIBLE);
                } else
                    tvNumber.setVisibility(View.VISIBLE);
            }
        }else {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    tvNumber.setVisibility(View.INVISIBLE);
                }
            }, 5000);

        }
        return convertView;
    }

}
