package ebookfrenzy.com.todo;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateNote extends AppCompatActivity {

    EditText subjectText, noteText;
    Button updateButton, deleteButton;
    int position;
    SQLhelper helper;
    Note selected;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuItem item1 = menu.add(0, 0, 0, R.string.menu1);
        MenuItem item2 = menu.add(1, 1, 0, R.string.menu2);
        item1.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_NEVER);
        item2.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_NEVER);
        MenuItem itemSettings = menu.getItem(0);
        itemSettings.setIcon(R.mipmap.settings);

        item1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                return false;
            }
        });

        item2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        helper = new SQLhelper(this);
        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            position = bundle.getInt("position");
        }

        subjectText = (EditText)findViewById(R.id.editText2);
        noteText = (EditText)findViewById(R.id.editText3);
        updateButton = (Button)findViewById(R.id.button3);
        deleteButton = (Button)findViewById(R.id.button4);

        selected = helper.getNote(position);
        Toast.makeText(this, selected.getSubject(), Toast.LENGTH_LONG).show();

        subjectText.setText(selected.getSubject());
        noteText.setText(selected.getNotes());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuilder sb = new StringBuilder(subjectText.getText().length());
                sb.append(subjectText.getText());
                String subject = sb.toString();

                sb = new StringBuilder(noteText.getText().length());
                sb.append(noteText.getText());
                String note = sb.toString();

                selected.setSubject(subject);
                selected.setNotes(note);
                helper.updateData(selected);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();;

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.deleteNote(selected);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
