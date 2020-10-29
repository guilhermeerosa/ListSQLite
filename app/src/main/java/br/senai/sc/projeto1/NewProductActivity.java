package br.senai.sc.projeto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.senai.sc.projeto1.database.ProductDAO;
import br.senai.sc.projeto1.modelo.Product;

public class NewProductActivity extends AppCompatActivity {

    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_products);
        setTitle("Cadastro de Produto");

        loadProduct();
    }

    private void loadProduct() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null &&
                intent.getExtras().get("productEdit") != null) {
            Product product = (Product) intent.getExtras().get("productEdit");
            EditText editTextNome = findViewById(R.id.editText_name);
            EditText editTextValor = findViewById(R.id.editText_valor);
            editTextNome.setText(product.getName());
            editTextValor.setText(String.valueOf(product.getValor()));
            id = product.getId();
        }
    }

    public void onClickBack(View v) {
        finish();
    }

    public void onClickSave(View v) {
        EditText editTextName = findViewById(R.id.editText_name);
        EditText editTextValor = findViewById(R.id.editText_valor);

        String name = editTextName.getText().toString();
        Float valor = Float.parseFloat(editTextValor.getText().toString());

        Product product = new Product(id, name, valor);
        ProductDAO productDAO = new ProductDAO(getBaseContext());
        boolean saved = productDAO.save(product);
        if (saved) {
            finish();
        } else {
            Toast.makeText(NewProductActivity.this, "Erro ao salvar", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickDelete(View v) {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().getSerializable("productDelete") != null) {
            Product product = (Product) intent.getExtras().getSerializable("productDelete");
            ProductDAO productDAO = new ProductDAO(getBaseContext());
            productDAO.delete(product);
            finish();
        } else {
            Toast.makeText(NewProductActivity.this, "Não foi possivel excluir", Toast.LENGTH_LONG).show();
        }
    }
}