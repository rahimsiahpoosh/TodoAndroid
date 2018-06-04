package ebookfrenzy.com.todo;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddNote extends AppCompatActivity {

    Note newNote;
    Button addNote;
    SQLhelper helper;
    TextView noteview;

    //TOOLBAR
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
//END TOOLBAR

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        helper = new SQLhelper(this);
        addNote = (Button)findViewById(R.id.button2);
        noteview = (TextView)findViewById(R.id.editText);

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb = new StringBuilder(noteview.getText().length());
                sb.append(noteview.getText());
                String subject = sb.toString();

                newNote = new Note();
                newNote.setSubject(subject);
                helper.addNote(newNote);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
