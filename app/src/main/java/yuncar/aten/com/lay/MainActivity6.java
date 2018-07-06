package yuncar.aten.com.lay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aten.yuncar.R;

import java.util.Random;

/**
 * project:UMPush
 * package:yuncar.aten.com.lay
 * Created by 佘少雄 on 2018/6/15.
 * e-mail : 610184089@qq.com
 */

public class MainActivity6 extends AppCompatActivity {

    private static final int[] COLORS = {0xff00FFFF, 0xffDEB887, 0xff5F9EA0,
            0xff7FFF00, 0xff6495ED, 0xffDC143C,
            0xff008B8B, 0xff006400, 0xff2F4F4F,
            0xffFF69B4, 0xffFF00FF, 0xffCD5C5C,
            0xff90EE90, 0xff87CEFA, 0xff800000};

    private RecyclerView mRecyclerView;
    private Adapter mAdapter = new Adapter();

    private int mCount = 10;
    private int mGroupSize = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        init();
    }

    private void init() {
//        mRecyclerView.setLayoutManager(new CardLayoutManager(mGroupSize, true));\
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity6.this, OrientationHelper.VERTICAL,false));
        mRecyclerView.setAdapter(mAdapter);
    }

    public void add(View view) {
        mCount += 10;
        mAdapter.notifyDataSetChanged();
    }

    public void change(View view) {
        if (mGroupSize == 3) { mGroupSize = 9;}
        else { mGroupSize = 3;}
        init();
    }

    class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item, parent, false);
            return new Holder(item);
        }

        @Override
        public void onBindViewHolder(final Holder holder, final int position) {
//            holder.item.setText("" + position);
            holder.item.setCardColor(randomColor());
            holder.text.setText("菜单" + position);
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity6.this, holder.text.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mCount;
        }

        private int randomColor() {
            return COLORS[new Random().nextInt(COLORS.length)];
        }

        class Holder extends RecyclerView.ViewHolder {
            CardItemView item;
            TextView text;
            public Holder(View itemView) {
                super(itemView);
                item = (CardItemView) itemView.findViewById(R.id.item);
                text = (TextView) itemView.findViewById(R.id.text);
            }
        }
    }
}
