import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Gareth on 26/01/2016.
 */
public class Utils
{
    public static void hideKeyboard(Activity activity)
    {
        // Check if no view has focus:
        View view = activity.getCurrentFocus();
        if (view != null)
        {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}