package com.workshops.masseyhacks.appideastorage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // References to our text field widgets.
    private EditText appIdeaName, dueDate, appDescription;

    // A reference to our SeekBar widget.
    private SeekBar priorityBar;

    // A reference to our Button widget.
    private Button addAppButton, goToAppListButton;

    private ArrayList<AppIdea> appIdeas = new ArrayList<AppIdea>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retrieve the text field widgets.
        appIdeaName    = (EditText) findViewById(R.id.appIdeaNameField);
        dueDate        = (EditText) findViewById(R.id.appDueDateField);
        appDescription = (EditText) findViewById(R.id.appDescriptionField);

        // Retrieve the other widgets.
        priorityBar  = (SeekBar) findViewById(R.id.priority);
        addAppButton = (Button) findViewById(R.id.addAppIdeaButton);
        goToAppListButton = (Button) findViewById(R.id.goToListButton);

        appIdeaName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addAppButton.setEnabled(s.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        addAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Your app has been added!", Toast.LENGTH_LONG).show();
                AppIdea newIdea = new AppIdea(appIdeaName.getText().toString(),
                        dueDate.getText().toString(),
                        appDescription.getText().toString(),
                        priorityBar.getProgress());
                appIdeas.add(newIdea);
            }
        });

        final MainActivity main = this;

        goToAppListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main, AppListActivity.class);
                intent.putExtra("APP_IDEAS", (ArrayList<Parcelable>)(ArrayList<?>) appIdeas);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
