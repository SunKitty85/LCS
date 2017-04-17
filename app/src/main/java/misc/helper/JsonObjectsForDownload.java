package misc.helper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by moltox on 26.03.2017.
 */


/*
JsonObject:
['myKey'] = String
['ToDoFlag'] = String (SYNC,)
['TableName'] = String
['Category_ID'] = INT
['minAge'] = INT
['lastPoll'] = INT
 */
public class JsonObjectsForDownload {
    private static final String MyKey = "myKey2017";
    private static final String ToDoFlag_SYNC = "SYNC";
    private static final String TableName_Cards = "cards";
    private static final String TableName_Category = "category";
    
    public JsonObjectsForDownload()  {

    }
    public JSONObject getJsonForCategory()  {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("MyKey", MyKey);
            jsonObject.put("ToDoFlag",ToDoFlag_SYNC);
            jsonObject.put("TableName",TableName_Category);
            jsonObject.put("Category_ID","");
            jsonObject.put("minAge","");
            jsonObject.put("lastPoll",4711);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject getJsonForCards()  {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("MyKey", MyKey);
            jsonObject.put("ToDoFlag",ToDoFlag_SYNC);
            jsonObject.put("TableName",TableName_Cards);
            jsonObject.put("Card_ID","");
            jsonObject.put("minAge","");
            jsonObject.put("lastPoll",4711);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    
    
    
}
