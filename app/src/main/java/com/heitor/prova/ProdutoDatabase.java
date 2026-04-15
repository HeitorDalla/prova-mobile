package com.heitor.prova;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Produto.class}, version = 1, exportSchema = false)
public abstract class ProdutoDatabase extends RoomDatabase {

    private static ProdutoDatabase instance;

    public abstract ProdutoDao produtoDao();

    public static synchronized ProdutoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ProdutoDatabase.class,
                    "produto_database"
            ).allowMainThreadQueries().build();
        }
        return instance;
    }
}