package catwithbowtie.slonsharedprefs;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText login;
    private EditText password;

    private Button save_btn;
    private Button load_btn;

    private TextView login_tv;
    private TextView password_tv;

    public static String KEY_LOGIN = "KEY_LOGIN";
    public static String KEY_PASSWORD = "KEY_PASSWORD";

    private SharedPreferences prefs;

    private String login_str;
    private String password_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (EditText) findViewById(R.id.Login);
        password = (EditText) findViewById(R.id.Password);

        save_btn = (Button) findViewById(R.id.save_btn);
        load_btn = (Button) findViewById(R.id.load_btn);

        login_tv = (TextView) findViewById(R.id.login_tv);
        password_tv = (TextView) findViewById(R.id.password_tv);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_str = login.getText().toString();
                password_str = password.getText().toString();

                saveSomeString(KEY_LOGIN, login_str);
                saveSomeString(KEY_PASSWORD, password_str);
            }
        });

        load_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_str = loadSomeString(KEY_LOGIN, "nothing");
                password_str = loadSomeString(KEY_PASSWORD, "nothing");

                login_tv.setText(login_str);
                password_tv.setText(password_str);
            }
        });
    }

    private void saveSomeString(String key, String value) {
        prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
        Toast.makeText(this, "Save {key : " + key + "; value : " + value + "}", Toast.LENGTH_LONG).show();
    }

    private String loadSomeString(String key, String dflt) {
        prefs = getPreferences(MODE_PRIVATE);
        Toast.makeText(this, "Load {key : " + key + "}", Toast.LENGTH_LONG).show();
        return prefs.getString(key, dflt);
    }
}
