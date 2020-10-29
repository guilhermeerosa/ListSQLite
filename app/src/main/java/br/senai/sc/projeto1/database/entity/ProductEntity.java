package br.senai.sc.projeto1.database.entity;

import android.provider.BaseColumns;

public final class ProductEntity implements BaseColumns {

    private ProductEntity() {}

    public static final String TABLE_NAME = "product";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_VALOR = "valor";

}
