package ir.masturcode.testroom.DatabaseRoom;
import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao {
   //Insert query
   @Insert(onConflict = REPLACE)
   void insert(MainData mainData);

   //Delete query
   @Delete
   void delete(MainData mainData);

   //Delete all query
   @Delete
   void reset(List<MainData> mainData);

//Update query
   @Query("UPDATE table_favorite SET text=:sText WHERE ID = :sID ")
   void update(int sID,String sText);

   //Get all data query
   @Query("SELECT * FROM table_favorite")
   List<MainData> getAll();
}