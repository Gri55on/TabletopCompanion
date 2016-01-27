package garethgriffiths.tabletopcompanion;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Arrays;
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


        //Buttons
        Button btnRoll = (Button) findViewById(R.id.rollButton);
        Button btnDiscard = (Button) findViewById(R.id.discardButton);

        //Display Array
        final List<Integer> diceList = new ArrayList<>(10);
        //Array Adapter
        LVDiceList = (ListView) findViewById(R.id.diceResultListV);
        final ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, diceList);
        //Set adapter
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
                    ;
                {
                    //Populate array
                    for (Integer i = 0; i < Integer.valueOf(txtNumberDice.getText().toString()); i++)
                    {
                        diceList.add(i, rollDice(Integer.valueOf(txtDiceType.getText().toString()), Integer.valueOf(txtNumberDice.getText().toString())).get(i));
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

        //Radio Button Validation
        rbGreater.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(rbGreater.isChecked() == true)
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
                if(rbLess.isChecked() == true)
                {
                    rbGreater.setChecked(false);
                };
            }
        });




        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    //Returns a list of rolled dice results
    private List<Integer> rollDice(int chance, int amount)
    {
        final List<Integer> rollArray = new ArrayList<Integer>(amount);
        int result;
        //Gives a random number between 1 and X,Y number of times
        {

            //BASE Equation||Min + (int)(Math.random() * ((Max - Min) + 1))
            //1 = minimum roll
            result = 1 + (int) (Math.random() * ((chance - 1) + 1));
            rollArray.add(result);
        }
        return rollArray;
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


