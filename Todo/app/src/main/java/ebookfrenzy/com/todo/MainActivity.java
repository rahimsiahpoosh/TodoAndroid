package ebookfrenzy.com.todo;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;


public class MainActivity extends ListActivity {

    List<Note> values;
    private SQLhelper datasource;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuItem item1 = menu.add(0, 0, 0, R.string.menu1);
        MenuItem item2 = menu.add(1, 1, 0, R.string.menu2);
        MenuItem addTODO = menu.add(2, 2, 0, R.string.menu1);
        addTODO.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        addTODO.setIcon(R.mipmap.add);
        item1.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_NEVER);
        item2.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_NEVER);

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

        addTODO.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent = new Intent(getApplicationContext(), AddNote.class);
                startActivity(intent);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//TOOLBAR
        AppCompatCallback callback = new AppCompatCallback() {
            @Override
            public void onSupportActionModeStarted(ActionMode actionMode) {
            }

            @Override
            public void onSupportActionModeFinished(ActionMode actionMode) {
            }

            @Nullable
            @Override
            public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
                return null;
            }
        };

        AppCompatDelegate delegate = AppCompatDelegate.create(this, callback);

        delegate.onCreate(savedInstanceState);
        delegate.setContentView(R.layout.activity_main);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        delegate.setSupportActionBar(toolbar);
        toolbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F51B5")));
        //Spannable text = new SpannableString(toolbar.getTitle());
        //text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//END TOOLBAR
        datasource = new SQLhelper(this);
        datasource.open();

        values = datasource.getAllNotes();
        final ArrayAdapter<Note> adapter = new ArrayAdapter<Note>(this,
                R.layout.list_black_text, R.id.list_content, values);
        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent i = new Intent(this, UpdateNote.class);
        i.putExtra("position", position);
        startActivity(i);

    }

    public void finish() {
        super.finish();
        finishAffinity();
    }

    public void finishAffinity() {
        super.finishAffinity();
    }
}
