package yuncar.aten.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.aten.yuncar.R;

import java.util.ArrayList;

/**
 * project:UMPush
 * package:yuncar.aten.com
 * Created by 佘少雄 on 2018/7/10.
 * e-mail : 610184089@qq.com
 * 学习BaseExpandableListAdapter
 */

public class MyExpendListView extends BaseExpandableListAdapter {
    ArrayList<String> strings;
    ArrayList<ArrayList<String>> arrayLists;
    Context context;
    private View groupview;
    private GroupHolder groupHolder;
    private View childview;
    private ChildHolder childHolder;

    public MyExpendListView(ArrayList<String> strings, ArrayList<ArrayList<String>> arrayLists, Context context) {
        this.strings = strings;
        this.arrayLists = arrayLists;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return strings.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return arrayLists.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return strings.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return arrayLists.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        groupview = convertView;
        if(groupview==null){
            groupHolder = new GroupHolder();
            groupview = LayoutInflater.from(context).inflate(R.layout.saaa,null,false);
            TextView textView = (TextView) groupview.findViewById(R.id.heehe);
            groupHolder.groupName=textView;
            groupview.setTag(groupHolder);
        }else {
          groupHolder = (GroupHolder) convertView.getTag();
        }
        groupHolder.groupName.setText(strings.get(groupPosition));
        return groupview;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        childview =convertView;
        if(childview==null){
            childHolder = new ChildHolder();
            childview = LayoutInflater.from(context).inflate(R.layout.list_chidle,null,false);
            TextView textView = (TextView) childview.findViewById(R.id.heehe);
            childHolder.childName=textView;
            childview.setTag(childHolder);
        }else {
            childHolder = (ChildHolder) convertView.getTag();
        }
        childHolder.childName.setText(arrayLists.get(groupPosition).get(childPosition));
        return childview;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class GroupHolder{
        public TextView groupName;
    }

    class ChildHolder{
        public TextView childName;
    }
}
