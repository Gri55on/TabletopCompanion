package garethgriffiths.tabletopcompanion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;


public class DiceActivity extends AppCompatActivity
{
    ListView LVDiceList;

    int diceType = 0;
    int diceNumber = 0;

    private void setType(int type)
    {
        diceType = type;
    }

    private int getType()
    {
        return diceType;
    }

    private void setNumber(int number)
    {
        diceNumber = number;
    }

    private int getNumber()
    {
        return diceNumber;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Radio button
        final RadioButton rbGreater = (RadioButton) findViewById(R.id.greaterThanRadioB);
        final RadioButton rbLess = (RadioButton) findViewById(R.id.lessThanRadioB);


        //Edit boxes
        final EditText txtDiceType = (EditText) findViewById(R.id.diceTypeEdTxt);
        final EditText txtNumberDice = (EditText) findViewById(R.id.numDiceEdTxt);
        final EditText txtFilterNum = (EditText) findViewById(R.id.filterNumEdTxt);

        //Buttons
        Button btnRoll = (Button) findViewById(R.id.rollButton);
        Button btnDiscard = (Button) findViewById(R.id.discardButton);
        Button btnFilter = (Button)findViewById(R.id.filterButton);
        Button btnReRoll = (Button) findViewById(R.id.reRollButton);

        //Display Array
        final List<Integer> diceList = new ArrayList<Integer>(10);

        //Array Adapter
        final ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, diceList);

        //Set adapter
        LVDiceList = (ListView) findViewById(R.id.diceResultListV);
        LVDiceList.setAdapter(adapter);

        //Roll the dice
        btnRoll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Hide soft keyboard
                Utils.hideKeyboard(DiceActivity.this);

                //Validation of fields
                //If both fields >0
                if (Integer.valueOf(txtDiceType.getText().toString()) > 0 && Integer.valueOf(txtNumberDice.getText().toString()) > 0)
                {
                    int number = Integer.valueOf(txtNumberDice.getText().toString());
                    int sides = Integer.valueOf(txtDiceType.getText().toString());

                    setNumber(number);
                    setType(sides);

                    //Populate array
                    for (int i = 0; i < number; i++)
                    {
                        diceList.add(i, rollDice(sides));
                    }
                    //Update the list view
                    adapter.notifyDataSetChanged();
                }
                return;

            }
        });

        //Clear the list of results
        btnDiscard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Utils.hideKeyboard(DiceActivity.this);
                diceList.clear();
                adapter.notifyDataSetChanged();
            }
        });

        //Radio Button Validation for filter
        rbGreater.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (rbGreater.isChecked() == true)
                {
                    rbLess.setChecked(false);
                }
            }
        });
        rbLess.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (rbLess.isChecked() == true)
                {
                    rbGreater.setChecked(false);
                }
                ;
            }
        });

        //Filters table removing results either greater or less than X
        btnFilter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Utils.hideKeyboard(DiceActivity.this);

                if(rbGreater.isChecked())
                {
                    for (int i=0;i<diceList.size();i++)
                    {
                        if (diceList.get(i)< Integer.valueOf(txtFilterNum.getText().toString()))
                        {
                            diceList.remove(i);
                            i-=1;
                        }
                    }
                    adapter.notifyDataSetChanged();
                }

                else if(rbLess.isChecked())
                {
                    for (int i=0;i<diceList.size();i++)
                    {
                        if (diceList.get(i)> Integer.valueOf(txtFilterNum.getText().toString()))
                        {
                            diceList.remove(i);
                            i-=1;
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });

        //For the number of results in the table, roll again
        btnReRoll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int numReRoll = diceList.size();
                adapter.notifyDataSetChanged();

                diceList.clear();
                for (int i = 0; i < numReRoll; i++)
                {
                    diceList.add(i, rollDice(getType()));
                }

            }
        });
    }

    //Returns a (int)result for the dice size given
    private int rollDice(int chance)
    {
        int result;
        //Gives a random number between 1 and X.
        {
            //BASE Equation||Min + (int)(Math.random() * ((Max - Min) + 1))
            //1 = minimum roll
            result = 1 + (int) (Math.random() * ((chance - 1) + 1));
        }
        return result;
    }
}


