package com.example.fetchrewards_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import android.util.Log;
import android.widget.ExpandableListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    String FILENAME = "hello_file";

    String string = "hello world!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        readFile();
//        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

//        TextView tv1 = (TextView)findViewById(R.id.myAwesomeTextView);
       // writeFile();

    }

    private String readFile() {
        File fileEvents = new File(MainActivity.this.getFilesDir()+"/text/webResponse2");
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileEvents));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) { }
        String result = text.toString();
        prepareListData(result);

        return result;
    }

    private void prepareListData(String  result) {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        HashMap <String, List<Integer>> dataList = new HashMap<String, List<Integer>>();

        HashMap<Object, String> jsonObjs = new HashMap<>();

        try {
            JSONArray jsonarray  = new JSONArray (result);
            for(int i=0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String listId   = jsonobject.getString("listId");
                if (!listDataHeader.contains("List ID: "+listId)) {
                    listDataHeader.add("List ID: "+listId);
                    dataList.put("List ID: "+listId,new ArrayList<Integer>());
                }
                String name   = jsonobject.getString("name");
                if (name.length() != 0 && !name.equals("null"))
                {
                    JSONObject jsonObj = new JSONObject();

                    jsonObj.put("name", name);
                    jsonObj.put("id", jsonobject.get("id"));
                    jsonObjs.put(jsonobject.get("id"),String.valueOf(jsonobject));
                    dataList.get("List ID: "+listId).add((Integer) jsonobject.get("id"));
                }
            }
        }catch (JSONException err){
            Log.d("Error", err.toString());
        }

        Collections.sort(listDataHeader);

        for (String item:listDataHeader) {
            List<String> newList=new ArrayList<String>();
            String r = "";
            Collections.sort(dataList.get(item));
            List<Integer> newList1 = dataList.get(item);
            for(int name:newList1) {
                r = jsonObjs.get(name);
                newList.add(r);
            }

            listDataChild.put(item, newList); // Header, Child data

        }
    }
}

