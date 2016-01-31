package garethgriffiths.tabletopcompanion;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;


public class DiceActivity extends AppCompatActivity
{
    ListView LVDiceList;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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

        //Display Array
        final List<Integer> diceList = new ArrayList<Integer>(10);
        final List<Integer> oldDiceList = new ArrayList<Integer>();

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

        btnFilter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
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


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    //Returns a int of rolled dice result
    private int rollDice(int chance)
    {
        int result;
        //Gives a random number between 1 and X,Y number of times
        {
            //BASE Equation||Min + (int)(Math.random() * ((Max - Min) + 1))
            //1 = minimum roll
            result = 1 + (int) (Math.random() * ((chance - 1) + 1));
        }
        return result;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Dice Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://garethgriffiths.tabletopcompanion/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop()
    {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Dice Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://garethgriffiths.tabletopcompanion/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}


