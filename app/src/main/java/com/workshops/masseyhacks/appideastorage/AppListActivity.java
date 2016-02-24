package com.workshops.masseyhacks.appideastorage;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AppListActivity extends AppCompatActivity {

    private ArrayList<AppIdea> AppIdeas;

    private ListView appIdeaListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);

        ArrayList<Parcelable> appIdeas = getIntent().getParcelableArrayListExtra("APP_IDEAS");
        AppIdeas = (ArrayList<AppIdea>)(ArrayList<?>)appIdeas;
        appIdeaListView = (ListView) findViewById(R.id.listView);

        PopulateArray();
    }

    private void PopulateArray() {
        ArrayAdapter<AppIdea> adapter = new AppIdeaListAdapter();
        appIdeaListView.setAdapter(adapter);
    }

    private class AppIdeaListAdapter extends ArrayAdapter<AppIdea> {
        public AppIdeaListAdapter() {
            super(AppListActivity.this, R.layout.listview_item, AppIdeas);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null)
            {
                view = getLayoutInflater().inflate(R.layout.listview_item, parent, false);
            }

            AppIdea thisIdea = AppIdeas.get(position);

            TextView name        = (TextView) view.findViewById(R.id.appIdeaNameField);
            TextView dueDate     = (TextView) view.findViewById(R.id.appDueDate);
            TextView description = (TextView) view.findViewById(R.id.appDescriptionField);
            TextView priority    = (TextView) view.findViewById(R.id.appPriority);

            name.setText(thisIdea.getName());
            dueDate.setText(thisIdea.getDueDate());
            description.setText(thisIdea.getDescription());
            priority.setText("Priority: " + thisIdea.getPriority());

            return view;
        }
    }
}
