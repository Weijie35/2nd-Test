package edu.qc.seclass.glm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {
    private ArrayList<String> groups;
    private ArrayList<ArrayList<String>> children;
    private Context context;

    public MyExpandableListAdapter(Context context, ArrayList<String> groups, ArrayList<ArrayList<String>> children){
        this.groups = groups;
        this.children = children;
        this.context = context;
    }

    //this method is to add a new list
    public void addGroups(String listName){
        groups.add(listName);
        ArrayList<String> subchildren = new ArrayList<>();
        subchildren.add("please add item");
        children.add(subchildren);
        this.notifyDataSetChanged();
    }

    public void addChild(){
        ArrayList<String> subchildren = new ArrayList<>();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return children.get(groupPosition).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return children.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup viewGroup) {
        TextView group_name;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.parent, null);
        }else{
            group_name = (TextView) convertView.getTag();
        }
        group_name = (TextView) convertView.findViewById(R.id.group_name);
        group_name.setText(groups.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isExpanded, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.child, null);
        }
        TextView group_item = (TextView) convertView.findViewById(R.id.group_item);
        group_item.setText(children.get(groupPosition).get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
