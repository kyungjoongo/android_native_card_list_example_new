package cardlist001.genius.kyungjoon.cardlistnew;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by kyungjoon on 17. 10. 18.
 */

public class ListViewAdapter extends BaseAdapter implements View.OnClickListener {

    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList


    Context mctx;


    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;


    // ListViewAdapter의 생성자
    public ListViewAdapter(Context context) {

        this.mctx = context;

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView1) ;
        TextView urlView = (TextView) convertView.findViewById(R.id.textView1) ;
        TextView descTextView = (TextView) convertView.findViewById(R.id.textView2) ;

        

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem listViewItem = listViewItemList.get(position);
        Picasso.with(mctx).load(listViewItem.getImage1()).placeholder(R.drawable.placeholder).into(imageView);
        urlView.setText(listViewItem.getUrl());
        descTextView.setText(listViewItem.getDesc());


        // position을 tag로 저장한다.
        convertView.setTag(position);

        // 만들어진 뷰(row)에 클릭이벤트를 등록한다. 이벤트의 처리는 123번 라인의 onClick()에서 한다.
        convertView.setOnClickListener(this);




        return convertView;
    }



    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {

        return listViewItemList.get(position) ;
    }


    public String getUrl(int position) {

        String _url = (listViewItemList.get(position)).getUrl();

        Log.d("_test", _url);

        return _url;
    }



    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String imageUrl, String url, String title) {

        ListViewItem item = new ListViewItem();
        item.setImage1(imageUrl);
        item.setUrl(url);
        item.setDesc(title);
        listViewItemList.add(item);
    }

    public void onClick(View v)
    {
        // 111번 라인에서 저장한 tag(position)을 꺼내온다.
        int position = (Integer) v.getTag();



        Log.d("_test", String.valueOf(position));
        String _url= getUrl(position);


        Intent intent = new Intent(mctx, WebViewActivity.class);
        intent.putExtra("url", _url);
        mctx.startActivity(intent);
    }





}
