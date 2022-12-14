package ir.masturcode.testroom.DatabaseRoom;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MainData.class},version = 2,exportSchema = false)

public abstract class RoomDB extends RoomDatabase {
   //create database instance
   private static RoomDB database;
   //Define database name
   private static String DATABASE_NAME = "database";

   public synchronized static RoomDB getInstance(Context context) {
      //Check condition
      if (database == null) {
         database = Room.databaseBuilder(context.getApplicationContext(),
                 RoomDB.class, DATABASE_NAME)
                 .allowMainThreadQueries()
                 .fallbackToDestructiveMigration()
                 .build();
      }
      return database;
   }

   //create DAO
   public abstract MainDao mainDao();
}