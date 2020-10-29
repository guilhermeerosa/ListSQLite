package br.senai.sc.projeto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.senai.sc.projeto1.database.ProductDAO;
import br.senai.sc.projeto1.modelo.Product;

public class MainActivity extends AppCompatActivity {

    private ListView listViewProducts;
    private ArrayAdapter<Product> adapterProducts;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Lista de Produtos");
        listViewProducts = findViewById(R.id.listview_products);
        defineOnClickListenerListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ProductDAO productDAO = new ProductDAO(getBaseContext());
        adapterProducts = new ArrayAdapter<Product>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                productDAO.list());
        listViewProducts.setAdapter(adapterProducts);
    }

    private void defineOnClickListenerListView() {
        listViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product productSelected = adapterProducts.getItem(position);
                Intent intent = new Intent(MainActivity.this, NewProductActivity.class);
                intent.putExtra("productEdit", productSelected);
                intent.putExtra("productDelete", productSelected);
                startActivity(intent);
            }
        });
    }

    public void onClickNewProduct(View v) {
        Intent intent = new Intent(MainActivity.this, NewProductActivity.class);
        startActivity(intent);
    }

}