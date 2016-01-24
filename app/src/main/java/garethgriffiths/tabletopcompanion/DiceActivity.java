package garethgriffiths.tabletopcompanion;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;

public class DiceActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText txtDiceType = (EditText)findViewById(R.id.diceTypeEdTxt);
        final EditText txtNumberDice = (EditText)findViewById(R.id.numDiceEdTxt);

        Button btnRoll = (Button)findViewById(R.id.rollButton);


        btnRoll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Validation of fields

                //Return list
                rollDice(Integer.valueOf(txtDiceType.getText().toString()),
                        Integer.valueOf(txtNumberDice.getText().toString()));
            }
        });

    }

    //Returns a list of rolled dice results
    private List<Integer> rollDice(int chance,int amount)
    {
        final List<Integer> rollArray = new ArrayList<Integer>(amount);
        int result;
        //Gives a random number between 1 and X,Y number of times
        for(int i = 0;i<amount;i++)
        {
            //BASE Equation||Min + (int)(Math.random() * ((Max - Min) + 1))
            //1 = minimum roll
            result = 1 + (int)(Math.random() * ((chance - 1) + 1));
            rollArray.add(result);
        }
        return rollArray;
    }
}
