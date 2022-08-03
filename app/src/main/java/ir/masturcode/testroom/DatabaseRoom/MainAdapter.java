package ir.masturcode.testroom.DatabaseRoom;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ir.masturcode.testroom.GlideApp;
import ir.masturcode.testroom.R;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
   private List<MainData> dataList;
   private Activity context;
   private RoomDB database;

   //Create constructor
   public MainAdapter(List<MainData> dataList, Activity context) {
      this.dataList = dataList;
      this.context = context;
      notifyDataSetChanged();
   }

   @NonNull
   @Override
   public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main,parent,false);
      return new ViewHolder(view);
   }

   @Override
   public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
      //Initialize main data
      MainData data=dataList.get(position);
      //Initialize database
      database=RoomDB.getInstance(context);
      //Set text on text view
      holder.title.setText(data.getText());
      //Get image and show with Glide
      GlideApp
              .with(this.context)
              .load(data.getImage())
              .skipMemoryCache(true)
              .thumbnail(0.2f)
              .transition(DrawableTransitionOptions.withCrossFade(800))
              .into(holder.imgRcycl);
      holder.imgEdit.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            MainData d=dataList.get(holder.getAdapterPosition());
            int sID=d.getID();
            String sText=d.getText();

//            Create dialog for edit
            Dialog dialog=new Dialog(context);
            dialog.setContentView(R.layout.dialog_update);
            int width= WindowManager.LayoutParams.MATCH_PARENT;
            int height= WindowManager.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width,height);
            dialog.show();

            //Initialize and assign variable
           final EditText editText=dialog.findViewById(R.id.edit_text);
            Button btUpdate=dialog.findViewById(R.id.update);
            //Set text on edit text
            editText.setText(sText);

            //Update new changes
            btUpdate.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                  dialog.dismiss();
                  //Get updated text from edit text
                  String uText=editText.getText().toString().trim();
                  //Update text in database
                  database.mainDao().update(sID,uText);
                  dataList.clear();
                  dataList.addAll(database.mainDao().getAll());
                  notifyDataSetChanged();
               }
            });
         }
      });

      //Delete a row from the database
      holder.delete.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            MainData d=dataList.get(holder.getAdapterPosition());
            database.mainDao().delete(d);
            int position =holder.getAdapterPosition();
            dataList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,dataList.size());
         }
      });
   }

   @Override
   public int getItemCount() {
      return dataList.size();
   }
   public class ViewHolder extends RecyclerView.ViewHolder{
      TextView title;
      ImageView imgEdit,delete,imgRcycl;
      public ViewHolder(@NonNull View itemView) {
         super(itemView);
         title=itemView.findViewById(R.id.text_view);
         imgEdit=itemView.findViewById(R.id.dtn_edit);
         delete=itemView.findViewById(R.id.btn_delete);
         imgRcycl=itemView.findViewById(R.id.image_recycl);
      }
   }
}