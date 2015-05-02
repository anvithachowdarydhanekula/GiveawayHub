package com.myproject.myhub.giveawayhub;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.parse.DeleteCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter
  extends BaseAdapter
{
  private ArrayList<ParseData> arrayList;
  boolean flag = true;
  LayoutInflater inflater;
  private List<ParseData> list = null;
  Context mContext;
  ImageView receiveItemImage;
  Button receiveItemName;
  
  public ListViewAdapter(Context paramContext, List<ParseData> paramList, int paramInt)
  {
    this.mContext = paramContext;
    this.list = paramList;
    this.inflater = LayoutInflater.from(this.mContext);
    this.arrayList = new ArrayList();
    this.arrayList.addAll(paramList);
    if (paramInt == -1) {
      this.flag = false;
    }
  }
  
  public boolean areAllItemsEnabled()
  {
    return true;
  }
  
  public int getCount()
  {
    return this.list.size();
  }
  
  public ParseData getItem(int paramInt)
  {
    return (ParseData)this.list.get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(final int paramInt, View paramView, final ViewGroup paramViewGroup)
  {
    paramView = this.inflater.inflate(2130968620, null);
    this.receiveItemName = ((Button)paramView.findViewById(2131296392));
    if (this.flag) {
      this.receiveItemName.setText("Receive item from " + ((ParseData)this.list.get(paramInt)).getReceiveItemLocation());
    }
    for (;;)
    {
      Object localObject = ((ParseData)this.list.get(paramInt)).getImageBytes();
      localObject = BitmapFactory.decodeByteArray((byte[])localObject, 0, localObject.length);
      this.receiveItemImage = ((ImageView)paramView.findViewById(R.id.));
      this.receiveItemImage.setImageBitmap((Bitmap)localObject);
      this.receiveItemName.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          new AlertDialog.Builder(paramViewGroup.getContext()).setTitle("Confirm").setMessage("Do you really want to receive?").setIcon(17301543).setPositiveButton(17039379, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              paramAnonymous2DialogInterface = ParseQuery.getQuery(ListViewAdapter.this.mContext.getResources().getText(2131427418).toString());
              try
              {
                paramAnonymous2DialogInterface.get(((ParseData)ListViewAdapter.this.list.get(ListViewAdapter.1.this.val$position)).getId()).deleteInBackground(new DeleteCallback()
                {
                  public void done(ParseException paramAnonymous3ParseException)
                  {
                    ((FragmentActivity)ListViewAdapter.this.mContext).getSupportFragmentManager().beginTransaction().replace(2131296338, new MainActivity.UserFragment()).commit();
                  }
                });
                return;
              }
              catch (ParseException ex)
              {
                Toast.makeText(ListViewAdapter.this.mContext, "Unknown error!", 1).show();
              }
            }
          }).setNegativeButton(17039369, null).show();
        }
      });
      return paramView;
      this.receiveItemName.setText("Receive item " + ((ParseData)this.list.get(paramInt)).getReceiveItemName() + " at " + ((ParseData)this.list.get(paramInt)).getReceiveItemLocation());
    }
  }
  
  public boolean isEnabled(int paramInt)
  {
    return true;
  }
}