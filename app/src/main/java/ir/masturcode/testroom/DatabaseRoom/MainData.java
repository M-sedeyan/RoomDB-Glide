package ir.masturcode.testroom.DatabaseRoom;



import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//table name
@Entity(tableName="table_favorite")
public class MainData implements Serializable {
   //create ID
   @PrimaryKey(autoGenerate = true)
   private int ID;

   //create Column Text
   @ColumnInfo(name = "text")
   private String text;


   //create Column Image
   @ColumnInfo(name = "image")
   private String image;

   //Generate getter and setter
   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public int getID() {
      return ID;
   }

   public void setID(int ID) {
      this.ID = ID;
   }

   public String getText() {
      return text;
   }

   public void setText(String text) {
      this.text = text;
   }

//   public String getImage() {
//      return image;
//   }
//
//   public void setImage(String image) {
//      this.image = image;
//   }
//
//   public String getTitle() {
//      return title;
//   }
//
//   public void setTitle(String title) {
//      this.title = title;
//   }
}
