package br.senai.sc.projeto1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.projeto1.database.entity.ProductEntity;
import br.senai.sc.projeto1.modelo.Product;

public class ProductDAO {

    private final String SQL_LIST_ALL = "SELECT * FROM " + ProductEntity.TABLE_NAME;
    private DBGateway dbGateway;

    public ProductDAO(Context context) {
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean save(Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductEntity.COLUMN_NAME_NAME, product.getName());
        contentValues.put(ProductEntity.COLUMN_NAME_VALOR, product.getValor());
        if (product.getId() > 0) {
            return dbGateway.getDatabase().update(ProductEntity.TABLE_NAME,
                    contentValues,
                    ProductEntity._ID + "=?",
                    new String[]{String.valueOf(product.getId())}) > 0;

        }
        return dbGateway.getDatabase().insert(ProductEntity.TABLE_NAME,
                null, contentValues) > 0;
    }

    public List<Product> list() {
        List<Product> products = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LIST_ALL, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(ProductEntity._ID));
            String name = cursor.getString(cursor.getColumnIndex(ProductEntity.COLUMN_NAME_NAME));
            Float valor = cursor.getFloat(cursor.getColumnIndex(ProductEntity.COLUMN_NAME_VALOR));
            products.add(new Product(id, name, valor));
        }
        cursor.close();
        return products;
    }

    public boolean delete(Product product) {
        return dbGateway.getDatabase().delete(ProductEntity.TABLE_NAME,
                ProductEntity._ID + "=?",
                new String[]{String.valueOf(product.getId())}) > 0;
    }

}
