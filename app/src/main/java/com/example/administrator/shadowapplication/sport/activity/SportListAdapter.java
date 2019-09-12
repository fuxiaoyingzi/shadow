package com.example.administrator.shadowapplication.sport.activity;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.shadowapplication.R;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */

public class SportListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOT = 2;
    private List<SportBean> titleList;
    private Context mContext;
    private ItemOnClickListener itemOnClickListener;

    public interface ItemOnClickListener {
        void onClick(int position);
    }

    public SportListAdapter(List<SportBean> titleList, Context context) {
        this.titleList = titleList;
        this.mContext = context;
    }

    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_sport_adapter, null, false);
            return new HeaderHolder(view);
        }else if (viewType == TYPE_FOOT){
            View view = View.inflate(parent.getContext(), R.layout.foot_sport_adapter, null);
            return new FootHolder(view);
        }
        else {
            View view = View.inflate(parent.getContext(), R.layout.adapter_sport, null);
            return new MyHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (position > 0 && position <= titleList.size()) {
            MyHolder holder1 = (MyHolder) holder;
            final SportBean sportBean = titleList.get(position - 1);
            holder1.sportTitle.setText(sportBean.getTitle());
            holder1.time.setText(sportBean.getTime());
            holder1.time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(position);
                }
            });
            holder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemOnClickListener != null) {
                        itemOnClickListener.onClick(position - 1);
                    }
                }
            });
        }else if (position == titleList.size()+1){
            FootHolder footHolder = (FootHolder) holder;
            footHolder.addNewSport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAddSportDialog();


                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position == titleList.size()+1){
            return TYPE_FOOT;
        } else {
            return TYPE_ITEM;
        }
    }


    @Override
    public int getItemCount() {
        return titleList != null ? titleList.size()+2 : 0;
    }


    public void showDialog(final int postion) {
        AlertDialog.Builder customDia = new AlertDialog.Builder(mContext);
        final View viewDia = LayoutInflater.from(mContext).inflate(R.layout.dialog_input_time, null);
        customDia.setView(viewDia);
        Button confitm = (Button) viewDia.findViewById(R.id.conFirm);
        Button cancle = (Button) viewDia.findViewById(R.id.cancle);
        final AlertDialog dialog = customDia.show();
        confitm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText diaInput = (EditText) viewDia.findViewById(R.id.inputTime);
                String sportTime = diaInput.getText().toString();
                if (TextUtils.isEmpty(sportTime)) {
                    Toast.makeText(mContext, "运动时间不允许为空", Toast.LENGTH_SHORT).show();
                } else {
                    SportBean bean = titleList.remove(postion - 1);
                    bean.setTime(diaInput.getText().toString());
                    titleList.add(postion - 1, bean);
                    notifyDataSetChanged();
                    dialog.dismiss();
                }

            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void showAddSportDialog(){
        AlertDialog.Builder customDia = new AlertDialog.Builder(mContext);
        final View viewDia = LayoutInflater.from(mContext).inflate(R.layout.dialog_add_sport, null);
        customDia.setView(viewDia);
        Button confitm = (Button) viewDia.findViewById(R.id.conFirm);
        Button cancle = (Button) viewDia.findViewById(R.id.cancle);
        final AlertDialog dialog = customDia.show();
        confitm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText diaInput = (EditText) viewDia.findViewById(R.id.inputTime);
                EditText inputTitle = (EditText) viewDia.findViewById(R.id.inputTitle);
                String sportTime = diaInput.getText().toString();
                String sportTitle = inputTitle.getText().toString();
                if (TextUtils.isEmpty(sportTime) || TextUtils.isEmpty(sportTitle)) {
                    Toast.makeText(mContext, "运动名称和时间不允许为空", Toast.LENGTH_SHORT).show();
                } else {
                    SportBean bean = new SportBean(sportTitle,sportTime);
                    titleList.add( bean);
                    notifyDataSetChanged();
                    dialog.dismiss();
                }

            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView sportTitle, time;

        public MyHolder(View itemView) {
            super(itemView);
            sportTitle = (TextView) itemView.findViewById(R.id.sportTitle);
            time = (TextView) itemView.findViewById(R.id.time);
        }
    }

    class FootHolder extends RecyclerView.ViewHolder{
        TextView addNewSport;

        public FootHolder(View itemView) {
            super(itemView);
            addNewSport = (TextView) itemView.findViewById(R.id.addNewSport);
        }
    }

    class HeaderHolder extends RecyclerView.ViewHolder {
        public HeaderHolder(View itemView) {
            super(itemView);

        }
    }

}
