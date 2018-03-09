package pro.chr.softwareengineeringtwo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button checkButton;
    Button resetButton;
    EditText checkText;
    TextView infoText;
    String startInfo;
    Float startPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkButton = (Button) findViewById(R.id.checkButton);
        resetButton = (Button) findViewById(R.id.resetButton);
        checkText = (EditText) findViewById(R.id.inputText);
        infoText = (TextView) findViewById(R.id.infoText);
        startInfo = (String) (infoText.getText());
        startPosition = infoText.getTranslationY();


        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = checkText.getText().toString();

                if(doValidations(input)){
                    if(palindromeCheck(input)) {
                        success("Wow you entered a palindrome! :-)");
                    }
                    else {
                        info("Seems like you didnt enter a palindrome, please try again! :-(");
                    }
                }

            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkText.setText("");
                info(startInfo);
            }
        });

    }

    protected boolean doValidations(String str) {
        String error = "";

        if(0 == str.length()) {
            // nothing entered
            error = "Please type in something first!";
        }
        else if(5 > str.length()) {
            // too few characters
            error = "Please type at least 5 characters!";
        }
        else if(!str.matches("[A-Za-z]*")) {
            // only letters please
            error = "Please only use letters!";
        }

        if(0 < error.length()) {
            error(error);
            return false;
        }

        return true;
    }

    protected void error(String str) {
        userNotify(Color.RED, str);
    }

    protected void info(String str) {
        userNotify(Color.BLACK, str);

    }

    protected void success(String str) {
        userNotify(Color.GREEN, str);
    }

    protected void userNotify(int color, String str) {
        infoText.setText(str);
        infoText.setTextColor(color);
        Animation animationUp = new TranslateAnimation(0, 0,startPosition, startPosition - 10);
        animationUp.setDuration(400);
        infoText.startAnimation(animationUp);
        Animation animationDown = new TranslateAnimation(0, 0,startPosition - 10, startPosition);
        animationDown.setDuration(300);
        infoText.startAnimation(animationDown);

    }

    protected boolean palindromeCheck(String str) {
        return str.equals(new StringBuilder(str).reverse().toString());
    }
}
