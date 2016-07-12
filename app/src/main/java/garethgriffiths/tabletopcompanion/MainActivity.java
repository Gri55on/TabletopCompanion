package garethgriffiths.tabletopcompanion;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


//Main menu activity
public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView txtTitle = (TextView)findViewById(R.id.titleText);

        //Buttons
        Button btnBoop = (Button)findViewById(R.id.boopButton);
        Button btnDice = (Button)findViewById(R.id.diceButton);
        Button btnClose = (Button)findViewById(R.id.closeButton);
        Button btnMeasure = (Button)findViewById(R.id.measureButton);

        //test button
        btnBoop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                txtTitle.setText("Boop");
            }
        });

        //Open dice activity
        btnDice.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, DiceActivity.class);
                startActivity(intent);
            }
        });

        //Open measure activity
        btnMeasure.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, MeasureActivity.class);
                startActivity(intent);
            }
        });

        //"Closes" the program as per mobile development logic (not a force close)
        //Allows for android to dictate when to close
        btnClose.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }
}
