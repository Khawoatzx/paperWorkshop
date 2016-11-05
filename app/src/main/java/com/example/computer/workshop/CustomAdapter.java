
/**
 * Created by Khawoat on 11/5/2016.
 */
    package com.example.computer.workshop;

    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.BaseAdapter;
    import android.widget.ImageView;
    import android.widget.TextView;
    public class CustomAdapter extends BaseAdapter {
        Context mContext;
        String[] NewsName;
        String[] DateName;
        int[] resId;

        public CustomAdapter(Context context, String[] NewsName,String[] DateName, int[] resID) {
            this.mContext = context;
            this.NewsName = NewsName;
            this.DateName = DateName;
            this.resId = resID;
        }

        @Override
        public int getCount() {
            return NewsName.length;

        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ViewHolder myHolder = null;

            if (convertView == null) {
                //คือช่วงแรกๆที่ convertView ยังไม่มีค่า

                //เตรียม Layout ที่ต้องการ ให้กับ convertView
                convertView = mInflater.inflate(R.layout.activity_row_list_, null);

                myHolder = new ViewHolder();
                myHolder.tvNews = (TextView) convertView.findViewById(R.id.tvNews);
                myHolder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
                myHolder.imgAnd = (ImageView) convertView.findViewById(R.id.imgAnd);
                convertView.setTag(myHolder);
            } else {
                //คือช่วงที่ convertView ผ่านการ Scroll มาแล้ว
                myHolder = (ViewHolder) convertView.getTag();
            }

            // ต้อง set ค่าที่จะให้แสดงผลกับแต่ละ widget
            myHolder.tvNews.setText(NewsName[position]);
            myHolder.tvDate.setText(DateName[position]);
            myHolder.imgAnd.setImageResource(resId[position]);



            return convertView;
        }

        public class ViewHolder {
            //ประกาษว่าภายใน view จะมี widget อะไร ชื่ออะไรบ้าง
            TextView tvNews;
            TextView tvDate;
            ImageView imgAnd;

        }

    }

