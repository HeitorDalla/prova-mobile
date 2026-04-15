package com.heitor.prova;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProdutoDao {

    @Insert
    void insert(Produto produto);

    @Query("SELECT * FROM produto ORDER BY nome ASC")
    List<Produto> getAllProdutos();
}