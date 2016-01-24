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

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        final TextView txtTitle = (TextView)findViewById(R.id.titleText);
        //Buttons
        Button btnBoop = (Button)findViewById(R.id.boopButton);
        Button btnDice = (Button)findViewById(R.id.diceButton);
        Button btnClose = (Button)findViewById(R.id.closeButton);

        btnBoop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                txtTitle.setText("Boop");
            }
        });

        btnDice.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,DiceActivity.class);
                startActivity(intent);
            }
        });

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
