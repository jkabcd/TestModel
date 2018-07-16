package yuncar.aten.com;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import com.aten.yuncar.R;

import java.util.ArrayList;

/**
 * project:UMPush
 * package:yuncar.aten.com
 * Created by 佘少雄 on 2018/7/10.
 * e-mail : 610184089@qq.com
 * 学习使用ExtendListview
 */

public class ExtendListview extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_group);
        ExpandableListView extendListview = (ExpandableListView)findViewById(R.id.contentPanelll);
        extendListview.setGroupIndicator(null);
        extendListview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });

        ArrayList<String> aaa= new ArrayList<>();
        aaa.add("111111组");
       aaa.add("222222组");
        aaa.add("333333组");
        aaa.add("444444组");
        aaa.add("555555组");
        aaa.add("666666组");
        aaa.add("777777组");


        ArrayList<ArrayList<String>> A= new ArrayList<>();
        for (int i = 0; i < aaa.size(); i++) {
            ArrayList<String> aaab= new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                aaab.add("测试"+i+"----"+j);
            }
            A.add(aaab);
        }

        MyExpendListView simpleExpandableListAdapter = new MyExpendListView(aaa,A,ExtendListview.this);

//        List<Map<String,String>> mapList = new ArrayList<>();
//        Map<String,String> onemap = new HashMap<>();
//        onemap.put("group","第一注");
//        Map<String,String> twomap = new HashMap<>();
//        twomap.put("group","第二注");
//        mapList.add(onemap);
//        mapList.add(twomap);
//
//       List<List<Map<String,String>>> lists = new ArrayList<>();
//        List<Map<String,String>> child_mapList_one = new ArrayList<>();
//        for (int i = 0; i < 7; i++) {
//            Map<String,String> child_onemap = new HashMap<>();
//            child_onemap.put("roup","第一注"+i);
//            child_mapList_one.add(child_onemap);
//        }
//
//        List<Map<String,String>> child_mapList_two = new ArrayList<>();
//        for (int i = 0; i < 8; i++) {
//            Map<String,String> child_onemap_two = new HashMap<>();
//            child_onemap_two.put("roup","第一注"+i);
//            child_mapList_two.add(child_onemap_two);
//        }
////        List<Map<String,String>> child_mapList_three = new ArrayList<>();
////        Map<String,String> child_onemap_three = new HashMap<>();
////        child_onemap_three.put("roup","第一注3");
////        child_mapList_three.add(child_onemap_three);
//
//        lists.add(child_mapList_one);
//        lists.add(child_mapList_two);
////        lists.add(child_mapList_three);
//
//
//        SimpleExpandableListAdapter simpleExpandableListAdapter = new SimpleExpandableListAdapter
//                (this, mapList, R.layout.saaa,new String[]{"group"}, new int[]{R.id.heehe},
//                        lists,R.layout.list_chidle,new String[]{"roup"},new int[]{R.id.heehe}
//                );
        extendListview.setAdapter(simpleExpandableListAdapter);
        for (int i = 0; i < aaa.size(); i++) {
            extendListview.expandGroup(i);
        }
    }
}
